package models

import scala.language.postfixOps

import play.api.Play.current
import play.api.db.DB

import anorm.SQL
import anorm.SqlQuery
import anorm.Row
import anorm.RowParser
import anorm.ResultSetParser

/*
 * Anorm - 'Anorm is Not an Object Relational Mapper' 
 *  
 *    - Developer constructs SQL queries (unaltered SQL statements in strings)
 *    - Provides 3 methods to parse result sets and map to any collection structure:
 *        1. Stream API
 *        2. Scala pattern matching
 *        3. Parser combinators        
 */


/* Model
 *
 *  Anorm model classes are plain ole Data Transfer Objects (DTOs).  No wiring,
 *  annotations or imports required.
 */
case class Document (
    documentId: String,
    title: String,
    author: String,
    content: String)


/* Document Data Access Object
 *
 */
object Document {

    // Define query
    val sql: SqlQuery = SQL("select * from document order by title asc")


    // Anorm Stream
    //  Executing the query returns a Stream[SqlRow] which we transform to a DTO 
    //  via map.  Since Scala streams are lazy we convert to List to fire the retrieval.
    def getAll: List[Document] = DB.withConnection { implicit connection =>

        sql().map(row => Document(row[String]("documentId"), 
                                  row[String]("title"),
                                  row[String]("author"), 
                                  row[String]("content"))).toList
    }

    
    // Anorm Pattern Match
    //  Construct a Document DTO for each row matching the pattern.  Anorm wraps each
    //  column value in a Some allowing null values to be represented with None.
    def getAllWithPatterns: List[Document] = DB.withConnection { implicit connection =>

        sql().collect {
          case Row(Some(documentId: String), 
                   Some(title: String), 
                   Some(author: String), 
                   Some(content: String)) => Document(documentId, title, author, content)}.toList
    }


    // Anorm Parser Combinators 
    //  The final result set parser is composed of a row parser which in turn is composed 
    //  of individual column parsers.

    // Construct row parser 
    val documentParser: RowParser[Document] = {

        import anorm.~
        import anorm.SqlParser._

        str("documentId") ~ str("title") ~ str("author") ~ str("content") map {
            case documentId ~ title ~ author ~ content =>
              Document(documentId, title, author, content)
        }
    }

    // Construct result set parser 
    val documentsParser: ResultSetParser[List[Document]] = {
        documentParser *
    }

    // Pass the result set parser to Anorm
    def getAllWithParser: List[Document] = DB.withConnection { implicit connection =>
        sql.as(documentsParser)
    }
    

    // Qualified query leveraging parser
    def findByDocumentId(documentId: String): Option[Document] = {
        DB.withConnection { implicit connection =>
            SQL(s"select * from document where documentId = $documentId")
                .on("documentId" -> documentId).as(documentParser.singleOpt)
        }
    }

    // Search leveraging parser
    def search(query: String) = DB.withConnection { implicit connection =>
        SQL(s"""select *
                  from document
                  where title  like '%' || '$query' || '%'
                     or author like '%' || '$query' || '%'""")
            .on("query" -> query)
            .as(documentsParser)
    }

    // Insert
    def insert(document: Document): Boolean = { DB.withConnection { implicit connection =>
      SQL(s"""insert 
                into document
                values ({documentId}, 
                        {title}, 
                        {author}, 
                        {content})""").on("documentId" -> document.documentId,
                                          "title" -> document.title,
                                          "author" -> document.author,
                                          "content" -> document.content
                                         ).executeUpdate() == 1
      }
    }

    // Update
    def update(document: Document): Boolean = { DB.withConnection { implicit connection =>
        SQL("""update document
                 set title = {title},
                     author = {author},
                     content = {content}
                 where documentId = {documentId}""")
            .on("documentId" -> document.documentId,
                "title" -> document.title,
                "author" -> document.author,
                "content" -> document.content
               ).executeUpdate() == 1
        }
    }

    // Delete
    def delete(document: Document): Boolean = { DB.withConnection { implicit connection =>
        SQL("delete from document where documentId = {documentId}")
           .on("documentId" -> document.documentId).executeUpdate() == 0
      }
    }
}
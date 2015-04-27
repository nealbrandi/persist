package modelTests

import org.specs2.mutable.Specification

import play.api.db.DB
import play.api.Play.current

import java.sql.ResultSet

import models.Document

class ProductSpec extends Specification with DatabaseHelpers {

    "Documents" should {

        "be able to be inserted" in EmptySchema {
            val document = Document("0", "Title 0", "Neal 0", """{ "documentId": "0", "title": "Title 0" }""")
            val inserted = Document.insert(document)
            var documents = List[Document]()
            DB.withConnection { connection =>
                val results: ResultSet = connection.createStatement().executeQuery("select * from document")
                results.first()
                while (!results.isAfterLast()) {
                    val documentId = results.getString("documentId")
                    val title = results.getString("title")
                    val author = results.getString("author")
                    val content = results.getString("content")
                    documents = Document(documentId, title, author, content) :: documents
                    results.next()
                }
            }
            inserted must beTrue
            documents must contain(document)
            documents must have size(1)
        }

        "be able to be retrieved" in SingleDocument {
            Document.getAll must contain(this.document)
            documents must have size(10)
        }

        "be able to be retrieved (Pattern matching)" in SeveralDocuments {
            Document.getAllWithPatterns must containAllOf(this.documents)
        }

        "be able to be retrieved (Parser API)" in SeveralDocuments {
            Document.getAllWithParser must containAllOf(this.documents)
        }

        "be retrievable in bulk" in SeveralDocuments {
            Document.getAll must containAllOf(this.documents)
        }

        "be retrievable in bulk (Pattern matching)" in SeveralDocuments {
            Document.getAllWithPatterns must containAllOf(this.documents)
        }

        "be retrievable in bulk (Parser API)" in SeveralDocuments {
            Document.getAllWithParser must containAllOf(this.documents)
        }

        "be retrievable in bulk sorted" in SeveralDocuments {
            Document.getAll must containAllOf(this.documents.sortBy(_.documentId)).inOrder
        }

        "be retrievable in bulk sorted (Pattern matching)" in SeveralDocuments {
            Document.getAllWithPatterns must containAllOf(this.documents.sortBy(_.documentId)).inOrder
        }

        "be retrievable in bulk sorted (Parser API)" in SeveralDocuments {
            Document.getAllWithParser must containAllOf(this.documents.sortBy(_.documentId)).inOrder
        }

        "be retrievable by documentId" in SeveralDocuments {
            Document.findByDocumentId("4") must beSome(this.documents(4))
            Document.findByDocumentId("40") must beNone
        }

        "be able to be deleted" in SeveralDocuments {
            val deletedDocument = this.documents(7)
            Document.delete(deletedDocument)
            val remainingDocuments = Document.getAllWithPatterns

            remainingDocuments must contain(deletedDocument).not
            remainingDocuments must have length(this.documents.size - 1)
        }

        "be able to be updated" in SeveralDocuments {
            val Some(document) = Document.findByDocumentId("8")
            val copy = document.copy(title = "Updated Title")
            Document.update(copy)

            val Some(updatedDocument) = Document.findByDocumentId("8")

            updatedDocument must equalTo(copy)
            Document.findByDocumentId("8").get must equalTo("No good!").not
        }

        "be searchable - No matching documents" in SeveralDocuments {
            Document.search("10") must have size(0)
        }
        "be searchable - Single match document" in SeveralDocuments {
            Document.search("8") must contain(this.documents(8))
        }

        "be searchable - Multiple matching documents" in SeveralDocuments {
            Document.search("Neal") must containAllOf(this.documents)
        }        
    }
}
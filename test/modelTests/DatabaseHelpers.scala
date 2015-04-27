package modelTests

import play.api.test.Helpers._

import org.specs2.mutable.{Around, SpecificationFeatures}
import org.specs2.execute.{AsResult, Result}

import play.api.test.FakeApplication

import models.Document

trait DatabaseHelpers { this: SpecificationFeatures =>

    val document = Document("1", "Title 1", "Neal 1", """{ "documentId": "1", "title": "Title 1" }""")
    
    val documents = 0 to 9 map { i =>
        Document(s"$i", s"Title $i", s"Neal $i", s"""{ "documentId": "$i", "title": "Title $i" }""")
    }

    trait Schema extends Around {
        def around[T : AsResult](test: => T) = { 
            running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
                AsResult(test)
            }
        }
    }

    object EmptySchema extends Schema {
    }

    object SingleDocument extends Schema {
        override def around[T : AsResult](test: => T) = super.around {
            Document.insert(document)
            AsResult(test)
        }
    }

    object SeveralDocuments extends Schema {
        override def around[T : AsResult](test: => T) = super.around {
            documents.foreach { Document.insert(_) }
            AsResult(test)
        }
    }
}
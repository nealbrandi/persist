
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(/*3.2*/main("Welcome to Play 2.0")/*3.29*/ {_display_(Seq[Any](format.raw/*3.31*/("""
    
    """),_display_(/*5.6*/play20/*5.12*/.welcome(message)),format.raw/*5.29*/("""
    
""")))}))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Apr 26 21:53:04 EDT 2015
                  SOURCE: /Users/nealbrandi/scalaApplications/persist/app/views/index.scala.html
                  HASH: 413a86f4637f24d140926d76e52faa0b520c98f8
                  MATRIX: 505->1|610->18|638->21|673->48|712->50|748->61|762->67|799->84
                  LINES: 19->1|22->1|24->3|24->3|24->3|26->5|26->5|26->5
                  -- GENERATED --
              */
          
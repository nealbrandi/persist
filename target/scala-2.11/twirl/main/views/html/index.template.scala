
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

"""),_display_(/*3.2*/main("MeerKat - Meer2Meer")/*3.29*/ {_display_(Seq[Any](format.raw/*3.31*/("""

   """),format.raw/*5.4*/("""<h1>MeerKat - Meer To Meer</h1>
   <ul>
     <li><a target="_blank" href=""""),_display_(/*7.36*/routes/*7.42*/.Chat.showRoom("Nishant")),format.raw/*7.67*/("""">Join as user Nishant</a>
     <li><a target="_blank" href=""""),_display_(/*8.36*/routes/*8.42*/.Chat.showRoom("Nikhil")),format.raw/*8.66*/("""">Join as user Nikhil</a>
     <li><a target="_blank" href=""""),_display_(/*9.36*/routes/*9.42*/.Chat.showRoom("Neal")),format.raw/*9.64*/("""">Join as user Neal</a>
   </ul>

""")))}))}
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 06 19:41:47 EDT 2015
                  SOURCE: /Users/nealbrandi/scalaApplications/meer2Meer/app/views/index.scala.html
                  HASH: 5923ca125a90b3ae2b3fac4e635c2985b66ab7fb
                  MATRIX: 505->1|610->18|638->21|673->48|712->50|743->55|844->130|858->136|903->161|991->223|1005->229|1049->253|1136->314|1150->320|1192->342
                  LINES: 19->1|22->1|24->3|24->3|24->3|26->5|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9
                  -- GENERATED --
              */
          

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
object main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<html>
<head>
<title>"""),_display_(/*5.9*/title),format.raw/*5.14*/("""</title>
<link rel="stylesheet" type="text/css" media="screen" href='"""),_display_(/*6.62*/routes/*6.68*/.Assets.at("stylesheets/bootstrap.css")),format.raw/*6.107*/("""'>
<link rel="stylesheet" type="text/css" media="screen" href='"""),_display_(/*7.62*/routes/*7.68*/.Assets.at("stylesheets/main.css")),format.raw/*7.102*/("""'>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
</head>
<body>

  <div class="screenshot">

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
          </a> <a class="brand" href="#">MeerKat - Meer To Meer</a>

          <div class="nav-collapse">
            <ul class="nav">
              
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      """),_display_(/*30.8*/content),format.raw/*30.15*/("""
    """),format.raw/*31.5*/("""</div>
</body>
</html>"""))}
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 06 19:41:47 EDT 2015
                  SOURCE: /Users/nealbrandi/scalaApplications/meer2Meer/app/views/main.scala.html
                  HASH: 20c1f9fefbbbe3e06402fb2f902f1e2670044172
                  MATRIX: 509->1|627->31|654->32|717->70|742->75|838->145|852->151|912->190|1002->254|1016->260|1071->294|1804->1001|1832->1008|1864->1013
                  LINES: 19->1|22->1|23->2|26->5|26->5|27->6|27->6|27->6|28->7|28->7|28->7|51->30|51->30|52->31
                  -- GENERATED --
              */
          
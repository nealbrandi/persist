
package views.html.chat

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
object showRoom extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(userName: String)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.53*/("""

"""),_display_(/*3.2*/main("Chatroom for " + userName)/*3.34*/ {_display_(Seq[Any](format.raw/*3.36*/("""
  """),format.raw/*4.3*/("""<h1>Chatroom - You are """),_display_(/*4.27*/userName),format.raw/*4.35*/("""</h1>
  <form id="chatForm">
    <input id="text" placeholder="Say something..." />
    <button type="submit">Say</button>
  </form>
  <ul id="messages"></ul>

  <script type="text/javascript">
  $(function() """),format.raw/*12.16*/("""{"""),format.raw/*12.17*/("""

    """),format.raw/*14.5*/("""console.log("Constructing WebSocket . . . ")
    ws = new WebSocket(""""),_display_(/*15.26*/routes/*15.32*/.Chat.chatSocket(userName).webSocketURL()),format.raw/*15.73*/("""")
    console.log("WebSocket constructed.")

    ws.onmessage = function(message) """),format.raw/*18.38*/("""{"""),format.raw/*18.39*/("""
      """),format.raw/*19.7*/("""console.log("Received from server:" + message.data)
      $('<li />').text(message.data).appendTo('#messages')
    """),format.raw/*21.5*/("""}"""),format.raw/*21.6*/("""

    """),format.raw/*23.5*/("""$('#chatForm').submit(function()"""),format.raw/*23.37*/("""{"""),format.raw/*23.38*/("""
      """),format.raw/*24.7*/("""console.log("Sending to server:" + $('#text').val())
      ws.send($('#text').val())
      $('#text').val("").focus()
      return false;
    """),format.raw/*28.5*/("""}"""),format.raw/*28.6*/(""")
  """),format.raw/*29.3*/("""}"""),format.raw/*29.4*/(""")
  </script>
""")))}))}
  }

  def render(userName:String,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(userName)(request)

  def f:((String) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (userName) => (request) => apply(userName)(request)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Mon Apr 06 19:41:47 EDT 2015
                  SOURCE: /Users/nealbrandi/scalaApplications/meer2Meer/app/views/chat/showRoom.scala.html
                  HASH: 6ec52e7390c4bfe80b2ddc4ceef7954c4f339611
                  MATRIX: 527->1|666->52|694->55|734->87|773->89|802->92|852->116|880->124|1117->333|1146->334|1179->340|1276->410|1291->416|1353->457|1464->540|1493->541|1527->548|1669->663|1697->664|1730->670|1790->702|1819->703|1853->710|2022->852|2050->853|2081->857|2109->858
                  LINES: 19->1|22->1|24->3|24->3|24->3|25->4|25->4|25->4|33->12|33->12|35->14|36->15|36->15|36->15|39->18|39->18|40->19|42->21|42->21|44->23|44->23|44->23|45->24|49->28|49->28|50->29|50->29
                  -- GENERATED --
              */
          
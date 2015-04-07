// @SOURCE:/Users/nealbrandi/scalaApplications/meer2Meer/conf/routes
// @HASH:df0cd5a43057f2a5073c3e717c98b4bc23e16059
// @DATE:Mon Apr 06 19:41:46 EDT 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:9
private[this] lazy val controllers_Chat_showRoom1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("room/"),DynamicPart("userName", """[^/]+""",true))))
private[this] lazy val controllers_Chat_showRoom1_invoker = createInvoker(
controllers.Chat.showRoom(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Chat", "showRoom", Seq(classOf[String]),"GET", """ Chat page""", Routes.prefix + """room/$userName<[^/]+>"""))
        

// @LINE:10
private[this] lazy val controllers_Chat_chatSocket2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("room/socket/"),DynamicPart("userName", """[^/]+""",true))))
private[this] lazy val controllers_Chat_chatSocket2_invoker = createInvoker(
controllers.Chat.chatSocket(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Chat", "chatSocket", Seq(classOf[String]),"GET", """""", Routes.prefix + """room/socket/$userName<[^/]+>"""))
        

// @LINE:13
private[this] lazy val controllers_Assets_at3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at3_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """room/$userName<[^/]+>""","""controllers.Chat.showRoom(userName:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """room/socket/$userName<[^/]+>""","""controllers.Chat.chatSocket(userName:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
   }
}
        

// @LINE:9
case controllers_Chat_showRoom1_route(params) => {
   call(params.fromPath[String]("userName", None)) { (userName) =>
        controllers_Chat_showRoom1_invoker.call(controllers.Chat.showRoom(userName))
   }
}
        

// @LINE:10
case controllers_Chat_chatSocket2_route(params) => {
   call(params.fromPath[String]("userName", None)) { (userName) =>
        controllers_Chat_chatSocket2_invoker.call(controllers.Chat.chatSocket(userName))
   }
}
        

// @LINE:13
case controllers_Assets_at3_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at3_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     
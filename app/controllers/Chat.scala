package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

import akka.actor.{ Actor, Props, actorRef2Scala }
import akka.pattern.ask
import akka.util.Timeout

import play.api.Play.current
import play.api.libs.concurrent.Akka
import play.api.libs.iteratee.{ Concurrent, Enumerator, Iteratee }
import play.api.mvc.{ Action, Controller, WebSocket }
import play.api.Logger


object Chat extends Controller {

  implicit val timeout = Timeout(1 seconds)

  // Instantiate chat room actor
  val room = Akka.system.actorOf(Props[ChatRoom])

  // HTTP action - Render chat room and invoke WebSocket on load
  def showRoom(userName: String) = Action { implicit request =>
    Ok(views.html.chat.showRoom(userName))
  }

  // WebSocket action
  def chatSocket(userName: String) = WebSocket.async { request =>

    Logger.info("Constructing WebSocket for %s." format userName)

    // Send chat room actor the join message. The ask returns a Future[Any]
    // which we then cast via mapTo to a Future[Iteratee, Enumerator] which 
    // is what the WebSocket's async method expects.
    val channelFuture = room ? Join(userName)

    channelFuture.mapTo[(Iteratee[String, _], Enumerator[String])]
  }
}

// ChatRoom Events
case class Join(userName: String)
case class Leave(userName: String)
case class Broadcast(message: String)

// ChatRoom Actor
class ChatRoom extends Actor {

  // Construct a collection of users in the chat room
  var users = Set[String]()

  // Create an Enumerator and an outbound channel is used to push 
  // outbound chat posts.
  val (enumerator, channel) = Concurrent.broadcast[String]

  // Handle chat room events
  def receive = {

    case Join(userName) => {

      if (!users.contains(userName)) {

        // Broadcast user message
        val iteratee = Iteratee.foreach[String] { message =>

          self ! Broadcast("%s: %s" format (userName, message));

        }.map { _ =>

          // Post leave message on disconnect
          self ! Leave(userName)
        }

        // Add user to room
        users += userName

        // Post join message
        channel.push("User %s has joined the room, now %s users" format (userName, users.size))

        // Return iteratee and enumerator to action
        sender ! (iteratee, enumerator)

      } else {

        // Post error and ignore duplicate user's messages
        val enumerator = Enumerator("userName %s is already in use." format userName)

        // Ignore duplicate user's messages
        val iteratee = Iteratee.ignore

        // Return iteratee and enumerator to action
        sender ! (iteratee, enumerator)
      }
    }

    case Leave(userName) => {

      users -= userName

      channel.push("User %s has left the room, %s users left" format (userName, users.size))
    }

    case Broadcast(message: String) => channel.push(message)
  }

}
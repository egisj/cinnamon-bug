import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{HttpApp, Route}
import com.lightbend.cinnamon.akka.http.scaladsl.server.Endpoint

import scala.concurrent.Future

object CinnamonBug {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("system")
    implicit val executionContext = system.dispatcher

    val app = new HttpApp {
      override protected def routes: Route =
        path("foo") {
          Endpoint.withName("foo") {
            get {
              onSuccess(Future("s")) { _ =>
                parameter('p) {
                  case "a" =>
                    Endpoint.withName("foo-a") {
                      //parameter('pa) { _ =>
                      onSuccess(Future("s")) { _ =>
                        complete(StatusCodes.OK)
                      }
                     //}
                    }
                  case "b" =>
                    Endpoint.withName("foo-b") {
                      //parameter('pb) { _ =>
                      onSuccess(Future("s")) { _ =>
                        complete(StatusCodes.OK)
                      }
                      //}
                    }
                }
              }
            }
          }
        }
    }

    app.startServer("localhost", 8080, system)
  }
}

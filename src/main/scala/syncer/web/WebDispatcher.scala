package syncer.web

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream.{ActorMaterializer, Materializer}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity

import akka.http.scaladsl.server.Directives._
import akka.util.ByteString
import com.typesafe.config.{Config, ConfigFactory}
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContextExecutor

case class user(id: String = "1", name: String = "Ryan")

trait Service extends DefaultJsonProtocol {
  import akka.http.scaladsl.model.MediaTypes.`text/html`

  implicit val system: ActorSystem
  implicit val executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter


//  val res = ByteString("<h1>Say hello to akka-http</h1>")
//  HttpEntity(`text/html`,res)

  val routes = {
    path("idmmgtsvc") {
      get {

        //      complete(HttpEntity(`text/html`,res))
        complete("test")
      }
    }
  }
}

// Web Dispatcher for Microservices
object WebDispatcher extends App with Service {

  override implicit val system: ActorSystem = ActorSystem("syncer-web")
  override implicit val executor: ExecutionContextExecutor = system.dispatcher
  override implicit val materializer: Materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}

package syncer.web

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import akka.http.scaladsl.Http


// Web Dispatcher for Microservices

object WebDispatcher extends App {
  implicit val system = ActorSystem("syncer")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  //Http().bind



}

package com.example.mgmt

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._

object WebServer extends App {
  implicit val system = ActorSystem("http")
  implicit val materializer = ActorMaterializer()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val healthCheckTimeout: Timeout = 3.seconds

  val route = Routes.route ~ Routes.serviceRoute(MockRecordService)


  val bindAndHandle = Http().bindAndHandle(route, "localhost", 8181)
}


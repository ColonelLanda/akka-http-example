package com.example.mgmt

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.example.mgmt.models.Models.Record
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{DefaultFormats, Serialization, jackson}

import scala.concurrent.ExecutionContext


object Routes extends Json4sSupport {
  private implicit val formats: DefaultFormats = DefaultFormats
  private implicit val serialization: Serialization = jackson.Serialization

  def route(implicit ec: ExecutionContext): Route = {
    path("foo") {
      get {
        complete("bar")
      }
    } ~ path("data" / "test") {
      parameters("joId") { jobId =>
        //All the logic of handling the request must be in complete!
        complete {
          Logic.doSomething(jobId)
        }
      }
    }
  }

  def serviceRoute(service: Service[Record])(implicit ec: ExecutionContext): Route = {
    path("version" / DoubleNumber / "records" / IntNumber) { (_: Double, itemId: Int) =>
      get {
        complete(service.getItemById(itemId))
      }
    } ~ path("version" / DoubleNumber / "records") { version =>
      (get & pathEndOrSingleSlash) {
        complete(service.getAll())
      } ~
      (post & pathEndOrSingleSlash) {
        entity(as[Record]) { record =>
          complete{
            service.createItem(record)
          }
        }
      }
    }
  }
}

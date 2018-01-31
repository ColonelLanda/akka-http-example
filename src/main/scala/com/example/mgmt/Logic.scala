package com.example.mgmt

import scala.concurrent.{ExecutionContext, Future}

object Logic {
  def doSomething(input: String)(implicit ec: ExecutionContext): Future[String] = {
    Future { input.toLowerCase()}
  }
}

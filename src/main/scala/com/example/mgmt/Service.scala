package com.example.mgmt

import com.example.mgmt.models.Models.Record

import scala.concurrent.Future

trait Service[T] {
  def createItem(item: T): Future[T]
  def getItemById(id: Int): Future[Option[T]]
  def getAll(): Future[Seq[T]]
}


object MockRecordService extends Service[Record] {
  var state = Map.empty[Int, Record]


  override def createItem(item: Record): Future[Record] = {
    this.synchronized {
      state = state + (item.id -> item)
    }
    Future.successful(item)
  }

  override def getItemById(id: Int) = {
    Future.successful(state.get(id))
  }

  override def getAll() = Future.successful(state.values.toSeq)
}
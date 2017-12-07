package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current
import scala.collection.mutable.MutableList
import models.MapsData

object FindController2 extends Controller {

  def find = Action {

    import play.api.db._

    val list = MutableList[MapsData]()

    DB.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""
      select
         *
      from
         markers
              """)
      while (res.next()) {
        list.+=(MapsData(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4), res.getDouble(5), res.getString(6), res.getString(7), res.getString(8)))
      }
    }

    Ok(views.html.find(list))
  }
  def newPlace = Action {
    Ok(views.html.newPlace())
  }
}
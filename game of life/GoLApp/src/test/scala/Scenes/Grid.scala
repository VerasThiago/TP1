package Scenes

import scalafx.scene.layout.BorderPane
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.text.Text

class Grid extends BorderPane {

  val oi = new Text("Welcome to Hell!")
  val button = new Button("Ae Karai")
  button.onAction = (ae : ActionEvent) => {
    //Main.stage1.hide
  }

  this.center = oi
  this.bottom = button

}

package Scenes

import scalafx.scene.layout.BorderPane
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.text.Text

class Opening extends BorderPane{

  val oi = new Text("Welcome to Game of Life")
  val button = new Button("Switch")
  button.onAction = (ae : ActionEvent) => {
    //Main.switch(2)
  }
  this.center = oi
  this.bottom = button

}

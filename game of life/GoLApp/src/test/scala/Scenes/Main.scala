package Scenes

import scalafx.application
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.text.Text

object Main extends JFXApp {



  val firstScene = new Scene(500, 500)
  val secondScene = new Scene(250, 250)

  val button = new Button("Go to next scene")
  button.onAction = (ae : ActionEvent) => {
    stage.hide
    stage.scene = secondScene
    stage.show
  }

  val karl = new Button("Go to previous scene")
  karl.onAction = (ae : ActionEvent) => {
    stage.hide
    stage.scene = firstScene
    stage.show
  }

  val text = new Text("Scene changed")
  text.style = "    .text { -fx-text-fill: blue;  -fx-font-size: 300%;}"

  firstScene.content = button
  secondScene.content = List(text, karl)

  stage = new JFXApp.PrimaryStage
  stage.title = "This title"
  stage.scene = firstScene
  stage.show
}

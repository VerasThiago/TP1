package GoLApp

import GoLBase.RuleGuide

import scala.collection.JavaConverters._
import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.text.Text

class TheGrid extends JFXApp {

  def execute : Scene = {
     val gridView = new Scene(500, 500) {
      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
      val rootPane = new BorderPane

      val nextGen = new Button("Next")
      nextGen.styleClass = List("bleh")
      val start = new Button("Start")
      start.styleClass = List("bleh")
      val stop = new Button("Stop")
      stop.styleClass = List("bleh")
      val exit = new Button("Exit")
      exit.styleClass = List("bleh")
      exit.onAction = (ae: ActionEvent) => {

      }


      val bar = new ToolBar
      bar.items = List(nextGen, start, stop, exit)

      val grid = new GridPane
      var gridColumns = 10
      var gridRows = 10

      for (i <- 0 until gridRows) {
        for (j <- 0 until gridColumns) {
          grid.add(getCell, j, i)
        }
      }

      rootPane.top = bar
      rootPane.center = grid
      content = rootPane
    }

    gridView
  }

  private def getCell: Button = {
    val cell = new Button("Dead")

    cell.onAction = (ae: ActionEvent) => {
      if (cell.getText.equals("Dead")) {
        cell.setText("Alive")
        cell.style = "-fx-background-color: blue;"
      }
      else {
        cell.text = "Dead"
        cell.style = "-fx-background-color: red;"
      }
    }

    cell
  }

}

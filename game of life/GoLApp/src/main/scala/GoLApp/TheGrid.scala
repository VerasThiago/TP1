package GoLApp

import GoLBase.{Board, RuleGuide}

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

// Where the magic happens
class TheGrid(val rule : RuleGuide, val w : Int, val h : Int) extends JFXApp {

  // Board of cells
  val board = new Board(w, h)


  // Defines and creates this view's scene
  def execute : Scene = {

     val gridView = new Scene(500, 500) {
       // TODO: Make view's design
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
          Main.getControl
      }


      val bar = new ToolBar
      bar.items = List(nextGen, start, stop, exit)

      val grid = new GridPane


      for (i <- 0 until h) {
        for (j <- 0 until w) {
          grid.add(getCell, j, i)
        }
      }

       // TODO: Organize items. Add missing items.
      rootPane.top = bar
      rootPane.center = grid
      content = rootPane
    }

    gridView
  }

  // Creates the cells in the view
  private def getCell: Button = {
    val cell = new Button("Dead")

    // TODO Connect buttons with actual cells in board
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

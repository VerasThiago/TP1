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


  // Defines and creates this view's scene
  def execute : Scene = {

    val gridView = new Scene(500, 500) {

      val board = new Board(w, h)

       // TODO: Make view's design
      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
      val rootPane = new BorderPane

      val nextGen = new Button("Next")
      nextGen.styleClass = List("bleh")
      nextGen.onAction = (ae: ActionEvent) => {
        val (live, kill) = rule.nextGen(w, h, board)
        board.update(live, kill)
      }
      val start = new Button("Start")
      start.styleClass = List("bleh")
      start.onAction = (ae: ActionEvent) => {
        show_grid(board)
      }
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


      for (i <- 0 until w) {
        for (j <- 0 until h) {
          grid.add(getCell(board,j,i), j, i)

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
  private def getCell(board: Board, j : Int , i : Int): Button = {
    val cell = new Button("Dead")

    // TODO Connect buttons with actual cells in board
    cell.onAction = (ae: ActionEvent) => {
      if (cell.getText.equals("Dead")) {
        board.universe(i)(j).revive
        cell.setText("Alive")
        cell.style = "-fx-background-color: blue;"

      }
      else {
        board.universe(i)(j).kill
        cell.text = "Dead"
        cell.style = "-fx-background-color: red;"
      }
    }

    cell
  }
  def show_grid( board : Board) = {
    println("")
    for(i <- 0 until w){
      for(j <- 0 until h){
        print(if(board.universe(i)(j).isAlive) 1 else 0)
        printf(" ")
      }
      println("")
    }
    println("")
  }

}

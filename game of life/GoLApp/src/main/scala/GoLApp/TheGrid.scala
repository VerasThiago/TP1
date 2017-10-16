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


  // Defines and creates this view's scene
  def execute : Scene = {

    val gridView = new Scene(483, 502) {

      // Board of cells
      private var board = new Board(w, h)
      private var restore : Array[Memento] = Array.ofDim(100)
      private var restoreIdx = 0

       // TODO: Make view's design
      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
      val rootPane = new BorderPane

      val nextGen = new Button("Next")
      nextGen.styleClass = List("bleh")

      nextGen.onAction = (ae: ActionEvent) => {
        val (live, kill) = rule.nextGen(w, h, board)
        board.update(live, kill)
        update_grid(grid, board)

        // save state to further undo
        restore(restoreIdx) = new Memento(w, h)
        restore(restoreIdx).saveState(board)
        println(s"Saved board to index $restoreIdx")
        restoreIdx = (restoreIdx+1)%100
      }

      val start = new Button("Start")
      start.styleClass = List("bleh")
      start.onAction = (ae: ActionEvent) => {
        for(i <- 0 until w){
          var (live, kill) = rule.nextGen(w, h, board)
          board.update(live, kill)
          update_grid(grid, board)
          println(i)

          // save state to further undo
          restore(restoreIdx) = new Memento(w, h)
          restore(restoreIdx).saveState(board)
          restoreIdx = (restoreIdx+1)%100
          Thread.sleep(1000)
        }
      }

      val stop = new Button("Stop")
      stop.styleClass = List("bleh")
      val exit = new Button("Exit")
      exit.styleClass = List("bleh")
      exit.onAction = (ae: ActionEvent) => {
          Main.getControl
      }

      val undo = new Button("Undo")
      undo.onAction = (ae : ActionEvent) => {
        restoreIdx = (restoreIdx+100-1)%100
        println(s"Restored board from index $restoreIdx")
        board = restore(restoreIdx).restoreState
        update_grid(grid, board)
        restore(restoreIdx) = null
      }

      val bar = new ToolBar
      bar.items = List(nextGen, start, stop, undo, exit)

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

  def update_grid(grid: GridPane, board: Board): Unit = {
    val rows  = grid.getChildren

    var i = 0
    var j = 0
    rows.foreach(x => {
      var cell = x.asInstanceOf[javafx.scene.control.Button]
      if(board.universe(i)(j).isAlive){
        cell.setText("Alive")
        cell.style = "-fx-background-color: blue;"
      }
      else{
        cell.setText("Dead")
        cell.style = "-fx-background-color: red;"
      }
      j += 1

      if(j == h){
        j = 0
        i += 1
      }
    })

  }

}

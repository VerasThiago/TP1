package GoLApp

import java.util.{Timer, TimerTask}
import javafx.application.Platform
import javafx.concurrent.Task

import GoLBase.{Board, Cells, RuleGuide}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}

// Where the magic happens
class TheGrid(val rule : RuleGuide, val w : Int, val h : Int) extends JFXApp {

  var t : Thread = null
  // Defines and creates this view's scene
  def execute : Scene = {

    val gridView = new Scene(h*35, (w*35) + 30) {

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
        // save state to further undo4
        restore(restoreIdx) = getNewMemento(board)
        restoreIdx = (restoreIdx+1)%100
        nextRun(board,grid)
      }

      val start = new Button("Start")
      start.styleClass = List("bleh")
      start.onAction = (ae: ActionEvent) => {
        startClick(board, grid)
      }

      val stop = new Button("Stop")
      stop.styleClass = List("bleh")
      stop.onAction = (ae : ActionEvent) => {

      }

      val exit = new Button("Exit")
      exit.styleClass = List("bleh")
      exit.onAction = (ae: ActionEvent) => {
          Main.getControl
      }

      val undo = new Button("Undo")
      undo.styleClass = List("bleh")
      undo.onAction = (ae : ActionEvent) => {
        restoreIdx = (restoreIdx+100-1)%100
        if(restore(restoreIdx) != null) {
          this.board = restore(restoreIdx).restoreState
          update_grid(grid, board)
          restore(restoreIdx) = null
        }
        else
          restoreIdx = (restoreIdx+1)%100
      }


      val bar = new ToolBar
      bar.items = List(nextGen, start, stop, undo, exit)

      val grid = new GridPane

      for (i <- 0 until w) {
        for (j <- 0 until h) {
          grid.add(getCell(board,j,i), j, i)
        }
      }

      rootPane.top = bar
      grid.autosize
      rootPane.center = grid
      content = rootPane
    }

    gridView
  }

  // Creates the cells in the view
  private def getCell(board: Board, j : Int , i : Int): Button = {
    val cell = new Button("D")
    cell.prefHeight = 35
    cell.prefWidth = 35

    cell.onAction = (ae: ActionEvent) => {
      if (cell.getText.equals("D")) {
        board.universe(i)(j).revive
        cell.setText("A")
        cell.style = "-fx-background-color: #ffff00; -fx-text-fill:  #ffff00;"
      }
      else {
        board.universe(i)(j).kill
        cell.text = "D"
        cell.style = "-fx-background-color: #7e7e7e; -fx-text-fill: #7e7e7e;"
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

      if((board.universe(i)(j).isAlive)){
        cell.setText("A")
        cell.style = " -fx-background-color: #ffff00; -fx-text-fill: #ffff00;"
      }
      else{
        cell.setText("D")
        cell.style = "-fx-background-color: #7e7e7e; -fx-text-fill:#7e7e7e;"
      }
      j += 1

      if(j == h){
        j = 0
        i += 1
      }
    })

  }

  def getNewMemento(board : Board) : Memento = {
    val meme = new Memento(w, h)
    meme.saveState(board)
    meme
  }

  def nextRun(board: Board,grid: GridPane): Unit ={
    val (live, kill) = rule.nextGen(w, h, board)
    board.update(live, kill)
    update_grid(grid, board)
  }

  def startClick(button : Button): Unit ={
    for(i <- 0  until 10){
      button.fire()
      Thread.sleep(100)
    }
  }
  def startClick(board: Board,grid: GridPane): Unit ={
    val task = new Task[Void]() {
      var i = 1
      @throws[Exception]
      override def call: Void = {
        while ( {
          i <= 30
        }) {
          try {
            nextRun(board,grid)
            Thread.sleep(500)
            i += 1;
          }
          catch {
            case e: java.lang.IllegalStateException => {

            }
          }
        }
        null
      }
    }
    new Thread(task).start()
  }
}

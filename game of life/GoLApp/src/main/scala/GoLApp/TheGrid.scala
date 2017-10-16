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

    val gridView = new Scene(506, 538) {

      // Board of cells
      val board = new Board(w, h)

       // TODO: Make view's design
      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
      val rootPane = new BorderPane

      val nextGen = new Button("Next")
      nextGen.styleClass = List("bleh")

      nextGen.onAction = (ae: ActionEvent) => {
        val (live, kill) = rule.nextGen(w, h, board)
        board.update(live, kill)
        update_grid(grid, board)
      }

      val start = new Button("Start")
      start.styleClass = List("bleh")
      start.onAction = (ae: ActionEvent) => {
        for(i <- 0 until w){
          var (live, kill) = rule.nextGen(w, h, board)
          board.update(live, kill)
          update_grid(grid, board)
          println(i)
          Thread.sleep(250)
        }
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

    cell.onAction = (ae: ActionEvent) => {
      if (cell.getText.equals("Dead")) {
        board.universe(i)(j).revive
        cell.setText("Alive")
        cell.style = "-fx-background-color: #ffff00; -fx-text-fill:  #ffff00;"
      }
      else {
        board.universe(i)(j).kill
        cell.text = "Dead"
        cell.style = "-fx-background-color: #7e7e7e; -fx-text-fill: #7e7e7e;"
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

  def update_grid(grid: GridPane, board: Board): Unit ={
    val rows  = grid.getChildren

    var i = 0
    var j = 0
    rows.foreach(x => {
      var cell = x.asInstanceOf[javafx.scene.control.Button]
      if((board.universe(i)(j).isAlive)){
        cell.setText("Alive")
        cell.style = " -fx-background-color: #ffff00; -fx-text-fill: #ffff00;"
      }
      else{
        cell.setText("Dead")
        cell.style = "-fx-background-color: #7e7e7e; -fx-text-fill:#7e7e7e;"
      }
      j += 1

      if(j == h){
        j = 0
        i += 1
      }
    })

  }

}

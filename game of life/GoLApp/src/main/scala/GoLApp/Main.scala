package GoLApp

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}



object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {

    title = "Custom Rule"

    scene = new Scene(500, 500) {
      val rootPane =new BorderPane

      val nextGen = new Button("Next")
      nextGen.styleClass = List("bleh")
      val start = new Button("Start")
      start.styleClass = List("bleh")
      val stop = new Button("Stop")
      stop.styleClass = List("bleh")
      val exit = new Button("Exit")
      exit.styleClass = List("bleh")


      val bar = new ToolBar
      bar.items = List(nextGen, start, stop, exit)

      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
        val grid = new GridPane
        var gridColumns = 10
        var gridRows = 10

        for(i <- 0 until gridRows) {
          for(j <- 0 until gridColumns) {
            grid.add(getCell, j, i)
          }
        }

      rootPane.top = bar
      rootPane.center = grid
      content = rootPane
    }
  }

  private def getCell : Button = {
    val cell = new Button("Dead")

    cell.onAction = (ae : ActionEvent) => {
      if(cell.getText.equals("Dead")){
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
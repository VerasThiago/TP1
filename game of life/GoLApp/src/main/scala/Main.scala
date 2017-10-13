import javafx.event.EventHandler

import GoLBase.RuleGuide

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.{HorizontalDirection, Insets, Pos}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.{Node, Scene}
import scalafx.scene.control._
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.shape.{Circle, Line, Rectangle}
import scalafx.scene.text.{Font, Text}



object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {

    title = "Custom Rule"

    scene = new Scene(500, 500) {
      stylesheets = List(getClass.getResource("mainGrid.css").toExternalForm)
        val grid = new GridPane
        var gridColumns = 10
        var gridRows = 10

        for(i <- 0 until gridRows) {
          for(j <- 0 until gridColumns) {
            grid.add(getCell, j, i)
          }
        }

      content = grid
    }
  }

  private def getCell : Button = {
    val cell = new Button("Dead")

    cell.onAction = (ae : ActionEvent) => {
      if(cell.getText.equals("Dead")){
        cell.setText("Alive")
      }
    else
        cell.text = "Dead"
    }

    cell
  }

}
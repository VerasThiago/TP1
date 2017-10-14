package GoLApp

import GoLBase.RuleGuide

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}



object Main extends JFXApp {

  stage = new JFXApp.PrimaryStage

  val opening = new Scene(500, 500) {
    val rootPane = new BorderPane

    val nextGen = new Button("Next")
    nextGen.styleClass = List("bleh")
    val start = new Button("Start")
    start.styleClass = List("bleh")
    val stop = new Button("Stop")
    stop.styleClass = List("bleh")
    val exit = new Button("Exit")
    exit.styleClass = List("bleh")
    exit.onAction = (ae : ActionEvent) => chooseScene(1)


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

  stage.scene = opening
  stage.show


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

  def chooseScene(x : Int) : Unit = {
    stage.hide
    x match {
      case 0 => stage.scene = opening
      case 1 => stage.scene = CustomRuleCreator.getScene
    }
    stage.show
  }

  private def getRules(fileName: String = "rules.txt"): List[RuleGuide] = {
    var rules: List[RuleGuide] = List()
    for (rule <- Source.fromResource(fileName).getLines()) {
      val strategy = Class.forName(rule)
      rules = strategy.newInstance.asInstanceOf[RuleGuide] :: rules
    }
    rules = rules.reverse
    rules
  }

}

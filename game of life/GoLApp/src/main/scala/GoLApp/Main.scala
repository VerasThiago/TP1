package GoLApp

import javafx.collections.ObservableList
import javafx.geometry.Orientation

import GoLBase.RuleGuide
import apple.laf.JRSUIConstants.AlignmentHorizontal

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.SplitPane.Divider
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, GridPane}
import scalafx.scene.text.Text



object Main extends JFXApp {

  stage = new JFXApp.PrimaryStage
  stage.title = "Game of Life"

  private val openingView = new Scene(500, 500){
    val rootPanel = new BorderPane
    rootPanel.top = getText("Welcome to Game of Life")

    val split = new SplitPane
    split.orientation = Orientation.HORIZONTAL

    val setWidth = new Spinner[Int](3, 100, 3)
    setWidth.style = Spinner.StyleClassArrowsOnLeftVertical
    val setHeight = new Spinner[Int](3, 100, 3)
    setHeight.style = Spinner.StyleClassArrowsOnLeftVertical

    val bar = new ToolBar
    bar.items = List(getText("Width: "), setWidth, getText(" X "), setHeight, getText(" : Height"))

    val custom = new Button("Custom")
    custom.onAction = (ae : ActionEvent) => {
      stage.hide
      stage.scene = CustomRuleCreator.execute
      stage.show
    }

    split.items.addAll(bar, custom)
    rootPanel.center = split
    content = rootPanel

  }

  stage.scene = openingView
  stage.show


  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  private def getRules(fileName: String = "GoLApp/rules.txt"): List[RuleGuide] = {
    var rules: List[RuleGuide] = List()
    for (rule <- Source.fromResource(fileName).getLines()) {
      val strategy = Class.forName(rule)
      rules = strategy.newInstance.asInstanceOf[RuleGuide] :: rules
    }
    rules = rules.reverse
    rules
  }

}

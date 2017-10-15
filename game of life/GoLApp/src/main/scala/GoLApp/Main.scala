package GoLApp

import javafx.geometry.Orientation

import GoLBase.RuleGuide

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.scene.text.Text

object Main extends JFXApp {

  // The stage
  stage = new JFXApp.PrimaryStage
  stage.title = "Game of Life"


  // Available Rules from libraries
  private var rules = getRules()

  // Rule that will be used in the game
  private var actualRule = rules.head

  private val openingView = new Scene(488, 500){
    // TODO: Make view's design
    stylesheets = List(getClass.getResource("openingView.css").toExternalForm)
    val rootPanel = new BorderPane
    rootPanel.top = getText("Welcome to Game of Life")

    // Separates main view in two
    val boardDims = new SplitPane
    boardDims.orientation = Orientation.HORIZONTAL

    // separates rules area
    val rulesPane = new BorderPane

    val setWidth = new Spinner[Int](3, 100, 3)
    setWidth.style = Spinner.StyleClassArrowsOnLeftVertical
    val setHeight = new Spinner[Int](3, 100, 3)
    setHeight.style = Spinner.StyleClassArrowsOnLeftVertical

    val bar = new ToolBar
    bar.items = List(getText("Width: "), setWidth, getText(" X "), setHeight, getText(" : Height"))

    val chooseRule = new ChoiceBox[String]
    chooseRule.items = getRuleNames(rules)
    chooseRule.getSelectionModel.selectFirst()
    rulesPane.center = chooseRule

    // custom Rule button
    val custom = new Button("Custom")
      custom.onAction = (ae : ActionEvent) => {
      stage.hide
      stage.scene = CustomRuleCreator.execute
      stage.show
    }

    val input = new TextField

    val exit = new Button("Exit")
    exit.onAction = (ae : ActionEvent) => {
      sys.exit(0)
    }

    // Get Rules button
    // TODO: make this into a form that accepts a file as input (to be used as input for getRules() )
    val ruleBtn = new Button("Get Rules")
    ruleBtn.onAction = (ae : ActionEvent) => {
      val s : String = "GoLApp/" + input.getText
      if(s != "GoLApp/")
        rules = getRules(s)
      else
        rules = getRules()

      chooseRule.items = getRuleNames(rules)
      chooseRule.getSelectionModel.selectFirst()
    }

    val listOfButtons = new ButtonBar
    listOfButtons.buttons.addAll(custom, input, ruleBtn)

    rulesPane.bottom = listOfButtons

    val continue = new Button("Continue")
    continue.onAction = (ae : ActionEvent) => {
      val game = new TheGrid(actualRule, setWidth.getValue, setHeight.getValue)
      stage.hide
      stage.scene = game.execute
      stage.show()
    }

    // TODO: Organize all elements. Add missing elements.
    boardDims.items.addAll(bar, rulesPane)
    boardDims.dividerPositions = 0.5
    rootPanel.center = boardDims
    rootPanel.bottom = continue
    rootPanel.left = exit
    content = rootPanel

  }

  stage.scene = openingView
  stage.sizeToScene
  stage.show

  // Returns a Text with given string
  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  // Get available rules from libs
  private def getRules(fileName: String = "GoLApp/rules.txt"): List[RuleGuide] = {
    var useThis = fileName
    var rules: List[RuleGuide] = List()
    var err : Array[String] = Array()
    // tries to safely access file
    try {
      Source.fromResource(fileName).getLines()
    }
    catch {
      case e : Any => {
        new Alert(AlertType.Error) {
          initOwner(stage)
          title = "Error Loading File"
          headerText = "File provided could not be accessed"
          contentText = s" Error: ${e.getClass.toString}.\nDefault file used instead"
        }.showAndWait()

        useThis = "GoLApp/rules.txt"
      }
    }
    finally {

      for (rule <- Source.fromResource(useThis).getLines()) {
        try {
          val strategy = Class.forName(rule)
          rules = strategy.newInstance.asInstanceOf[RuleGuide] :: rules
        }
        catch {
          case _ : Any => err :+ rule.toString
        }
      }
      rules.reverse
    }
    if(err.length > 0) {
      var s : String = ""
      err.foreach(e => s += e + "1\n")
      println(s)

      new Alert(AlertType.Warning) {
        title = "Error loading some rules"
        headerText = "The following Rules were not found"
        contentText = s
      }.showAndWait()
    }
    rules
  }

  // If Custom Rule is selected, saves it in the rule to be used
  def overrideRule(rule : CustomRule) = {
    if(actualRule.name != "Custom") {
      openingView.chooseRule.getItems.add("Custom")
      openingView.chooseRule.getSelectionModel.selectLast()
    }
    this.actualRule = rule
  }

  // Gets control back from other view
  def getControl = {
    stage.hide
    stage.scene = openingView
    stage.show
  }

  def getRuleNames(rules : List[RuleGuide]) : ObservableBuffer[String] = {
    var names : ObservableBuffer[String] = ObservableBuffer()
    rules.foreach(r => names.add(r.name))

    names.reverse
  }
}

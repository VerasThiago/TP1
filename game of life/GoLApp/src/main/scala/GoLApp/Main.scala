package GoLApp

import javafx.scene.image.Image
import javafx.scene.text.TextAlignment

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
  stage.getIcons.add(new Image("GoLApp/icon.png"))
  // Available Rules from libraries
  private var rules = getRules()

  // Rule that will be used in the game
  private var actualRule = rules.head

  private val openingView = new Scene(880, 600){
    // TODO: Make view's design
    stylesheets = List(getClass.getResource("openingView.css").toExternalForm)
    val rootPanel = new BorderPane


    // Separates main view in two
    /*val boardDims = new SplitPane
    boardDims.orientation = Orientation.HORIZONTAL*/

    //--------------------Top Area--------------------//

    val welcomeTxt = new Text()

    welcomeTxt.setTextAlignment(TextAlignment.CENTER)
    welcomeTxt.setText("Welcome to Game of Life")
    welcomeTxt.setStyle("-fx-font: 68 arial;")

    rootPanel.top = welcomeTxt

    //---------------------Center Area------------------//

    //Separates center pane (will contain matrixPane(left) and rulesPane(right))
    val centerPane = new BorderPane

                    //--------Center Left---------//

    //Create matrix resolution selection
    val matrixPane = new BorderPane

    val setWidth = new Spinner[Int](3, 50, 15)
    setWidth.style = Spinner.StyleClassArrowsOnLeftVertical
    val setHeight = new Spinner[Int](3, 50, 15)
    setHeight.style = Spinner.StyleClassArrowsOnLeftVertical

    val bar = new ToolBar
    bar.items = List(getText("Width: "), setWidth, getText(" X "), setHeight, getText(" : Height"))

    matrixPane.center = bar
    centerPane.left = matrixPane


                    //---------Center Right--------//

    // separates rules area
    val rulesPane = new BorderPane

    val chooseRule = new ChoiceBox[String]
    chooseRule.items = getRuleNames(rules)
    chooseRule.getSelectionModel.selectFirst()


    // custom Rule button
    val custom = new Button("Custom")
      custom.onAction = (ae : ActionEvent) => {
      stage.hide
      stage.scene = CustomRuleCreator.execute
      stage.show
    }

    val input = new TextField

    // Get Rules button
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

    val listOfButtonsRuleTop = new ButtonBar
    listOfButtonsRuleTop.autosize
    listOfButtonsRuleTop.buttons.addAll(chooseRule)

    val listOfButtonsRuleBottom = new ButtonBar
    listOfButtonsRuleBottom.autosize
    listOfButtonsRuleBottom.buttons.addAll(custom, input, ruleBtn)

    rulesPane.top = listOfButtonsRuleTop
    rulesPane.bottom = listOfButtonsRuleBottom

    centerPane.right = rulesPane
    rootPanel.center = centerPane

    //----------------------Bottom Area---------------------//

    val continue = new Button("Continue")
    continue.onAction = (ae : ActionEvent) => {
      val game = new TheGrid(actualRule, setWidth.getValue, setHeight.getValue)
      stage.hide
      stage.scene = game.execute
      stage.sizeToScene
      stage.resizable
      stage.show
    }

    val exit = new Button("Exit")
    exit.onAction = (ae : ActionEvent) => {
      sys.exit(0)
    }


    val listButtonsBottom = new ButtonBar
    listButtonsBottom.buttons.addAll(continue, exit)
    //listButtonsBottom.autosize
    rootPanel.bottom = listButtonsBottom
    content = rootPanel

  }

  stage.scene = openingView
  stage.sizeToScene
  stage.resizable
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
          contentText = s"Error: ${e.getClass.toString}.\nDefault file used instead.\nMake sure file is in resources > GoLApp."
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
          case _ : Any => err = err :+ rule.toString
        }
      }
      rules.reverse
    }
    if(err.length > 0) {
      var s : String = ""
      err.foreach(e => s += e + "\n")

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
    stage.sizeToScene
    stage.resizable
    stage.show
  }

  def getRuleNames(rules : List[RuleGuide]) : ObservableBuffer[String] = {
    var names : ObservableBuffer[String] = ObservableBuffer()
    rules.foreach(r => names.add(r.name))

    names.reverse
  }

}

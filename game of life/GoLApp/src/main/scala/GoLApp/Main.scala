package GoLApp

import javafx.geometry.Orientation
import javafx.scene.image.Image
import javafx.scene.text.TextAlignment

import GoLBase.RuleGuide

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.{HPos, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{BorderPane, FlowPane}
import scalafx.scene.text.Text

object Main extends JFXApp {

    // The stage
    stage = new JFXApp.PrimaryStage
    stage.title = "Game of Life"
    stage.getIcons.add(new Image("GoLApp/icon.png"))


    stage.scene = this.execute
    stage.sizeToScene
    stage.resizable
    stage.show

  // Available Rules from libraries
   var rules = getRules()

  // Rule that will be used in the game
   var actualRule = rules.head

  def execute : Scene = {
    val openingView = new Scene(500, 440) {
      // TODO: Make view's design
      stylesheets = List(getClass.getResource("mainView.css").toExternalForm)

      val rootPanel = new FlowPane

      val welcome = new Text("Game of Life")
      welcome.setId("head-text")

      val continue = new Button("Start")
      continue.styleClass = List("button")
      continue.onAction = (ae: ActionEvent) => {
        changeScene(1)
      }

      val exit = new Button("Exit")
      //exit.setStyle("-fx-font-size : 30px;")
      exit.styleClass = List("button")
      exit.onAction = (ae: ActionEvent) => {
        changeScene(0)
      }

      val input = new TextField

      val files = new Button("Get Rules")
      files.onAction = (ae: ActionEvent) => {
        rules = getRules(input.getText)
        new Alert(AlertType.Information) {
          title = "Loading rules completed"
          headerText = "Rules loaded from " + input.getText
          contentText = "Unless file could not be accessed. In this case, loaded from rules.txt"
        }.showAndWait()
      }

      rootPanel.setAlignment(Pos.TopCenter)
      rootPanel.vgap = 40
      rootPanel.hgap = 500

      rootPanel.children = List(welcome, input, files, continue, exit)
      rootPanel.setStyle("-fx-background-color: grey;")
      rootPanel.setPrefSize(500, 440)

      content = rootPanel

    }
    openingView
  }

  def changeScene(s : Int): Unit ={

    if(s == 0){ //EXIT PROGRAM
      sys.exit(0)
    }
    else if(s == 1){ //MAIN TO RESOLUTION SELECTION
      val res = new ResolutionView
      stage.hide
      stage.scene = res.execute
      stage.sizeToScene
      stage.resizable
      stage.show
    }
    else if(s == 2){ //GAME TO RESOLUTION SELECTION
      val res = new ResolutionView
      stage.hide
      stage.scene = res.execute
      stage.sizeToScene
      stage.resizable
      stage.show
    }
    else if(s == 3){ //RESOLUTION SELECTION TO CUSTOM
      val cus = new CustomRuleCreator()
      stage.hide
      stage.scene = cus.execute
      stage.sizeToScene
      stage.resizable
      stage.show
    }
  }

  def changeScene(s : Int, w : Int , h : Int, str : String): Unit = {

    if(s == 0){ //RESOLUTION SELECTION TO GAME
      getRuleSelected(str)
      val game = new TheGrid(actualRule, w, h)
      stage.hide
      stage.scene = game.execute
      stage.sizeToScene
      stage.resizable
      stage.show
    }

  }



  // Returns a Text with given string
  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  // Get available rules from libs
  private def getRules(fileName: String = "rules.txt"): List[RuleGuide] = {
    var useThis = "GoLApp/" + fileName
    var rules: List[RuleGuide] = List()
    var err : Array[String] = Array()
    // tries to safely access file
    try {
      Source.fromResource(useThis).getLines()
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
    rules = rules :+ rule
    rules.foreach(x => println(x))

  }
  // Gets control back from other view
  def getControl = {  
    stage.hide
    stage.scene = this.execute
    stage.sizeToScene
    stage.resizable
    stage.show
  }

  def getRuleNames(rules : List[RuleGuide]) : ObservableBuffer[String] = {
    var names : ObservableBuffer[String] = ObservableBuffer()
    rules.foreach(r => names.add(r.name))

    names.reverse
  }

  def getRuleSelected(s : String) : Unit = {
    var allRules = rules

    allRules.foreach(r => {
      //println(r.name)
      if(r.name == s){
        actualRule = r
        //println("selected " + r.name)
      }
    } )
  }


}

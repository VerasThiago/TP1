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

class ResolutionView (rule : RuleGuide) extends JFXApp {



  def execute : Scene = {

    val resView = new Scene(550,435){
      stylesheets = List(getClass.getResource("resView.css").toExternalForm)

      val resPanel = new FlowPane

      val welcome = new Text("Game of Life")
      welcome.styleClass = List("headtext")

      val select = new Text("Select the Grid Resolution")
      select.styleClass = List("bodytext")

      val setWidth = new Spinner[Int](3, 50, 15)
      setWidth.style = Spinner.StyleClassArrowsOnLeftVertical
      val setHeight = new Spinner[Int](3, 50, 15)
      setHeight.style = Spinner.StyleClassArrowsOnLeftVertical

      val bar = new ToolBar
      bar.items = List(getText("Width: "), setWidth, getText(" X "), setHeight, getText(" : Height"))

      val ruletxt = new Text("Select the Rule")
      ruletxt.styleClass = List("bodytext")

      val chooseRule = new ChoiceBox[String]
      chooseRule.items = Main.getRuleNames(Main.rules)
      chooseRule.getSelectionModel.selectFirst()

      val customBut = new Button("Custom Rules")
      customBut.styleClass = List("button")
      customBut.onAction = (ae : ActionEvent) => {
        Main.changeScene(3)
      }

      val continue = new Button("Begin")
      continue.styleClass = List("button")
      continue.onAction = (ae : ActionEvent) => {
        Main.changeScene(0,setWidth.getValue, setHeight.getValue, chooseRule.getSelectionModel.getSelectedItem())
      }

      val back = new Button("Back")
      back.styleClass = List("button")
      back.onAction = (ae : ActionEvent) => {
        Main.getControl
      }

      resPanel.setAlignment(Pos.TopCenter)
      resPanel.vgap = 40
      resPanel.hgap = 80

      resPanel.children = List(welcome,select, bar,ruletxt, chooseRule,customBut,continue, back)
      resPanel.setStyle("-fx-background-color: grey;")
      resPanel.setPrefSize(550,435)


      content = resPanel
    }

    resView
  }

  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

}

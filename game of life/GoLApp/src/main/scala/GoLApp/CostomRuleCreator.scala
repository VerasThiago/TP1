package GoLApp

import GoLBase.RuleGuide

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



object CustomRuleCreator extends JFXApp {

   def execute : Scene = {
      val customRuleView = new Scene(500, 500) {
       stylesheets = List(getClass.getResource("choiceBox.css").toExternalForm)
      val grid = new GridPane

      var gridRowIdx = 1

      grid.addRow(gridRowIdx, getRuleRow)
      grid.autosize


      val newRule = new Button("New Rule")
      newRule.onAction = (ae: ActionEvent) => {

        if (gridRowIdx < 5) {
          this.grid.addRow(gridRowIdx, getRuleRow)
          this.gridRowIdx += 1
        }
        else {

          new Alert(AlertType.Error) {
            initOwner(stage)
            title = "Rule definition"
            headerText = "Rule Definition Maximum number of Lines Reached"
            contentText = "You've reached the maximum lines available for a rule"
          }.showAndWait()
        }

      }

      val done = new Button("Done")
      done.onAction = (ae: ActionEvent) => {
        val rows  = grid.getChildren
        var testTemplate: Array[Array[String]] = Array.ofDim[String](gridRowIdx, 4)
        var Ridx = 0
        var Cidx = 0
        rows.foreach(x => {
          x.asInstanceOf[javafx.scene.control.ToolBar].getItems.forEach(
            c => {
              try {
                testTemplate(Ridx)(Cidx) = c.asInstanceOf[javafx.scene.control.ChoiceBox[String]].getSelectionModel.getSelectedItem
                Cidx += 1
              }
             catch {
                case e : ClassCastException =>
              }
            })
        Ridx += 1
        })

        testTemplate.foreach(x => x.foreach(s => println(s)))

      }

      val rootPane = new BorderPane
      rootPane.center = grid
      rootPane.bottom = newRule
      rootPane.right = done
      grid.margin = Insets(1, 1, 1, 1)
      content = rootPane
    }

    customRuleView
  }

  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  private def getRuleRow: ToolBar = {

    // definition of what goes in the RuleRow
    val cellState = ObservableBuffer("alive", "dead")
    val modifier = ObservableBuffer("exactly", "more than", "less than")
    val numbers = ObservableBuffer("1", "2", "3", "4", "5", "6", "7", "8")

    val initial = new ChoiceBox[String]
    initial.items = cellState
    initial.getSelectionModel.selectFirst

    val specifier = new ChoiceBox[String]
    specifier.items = modifier
    specifier.getSelectionModel.selectFirst

    val neighbours = new ChoiceBox[String]
    neighbours.items = numbers
    neighbours.getSelectionModel.selectFirst

    val finale = new ChoiceBox[String]
    finale.items = cellState
    finale.getSelectionModel.selectFirst


    // Actually creating the RuleRow
    val ruleRow = new ToolBar
    ruleRow.items = List(getText("A cell"), initial, getText("with"), specifier, neighbours, getText("living neighbours. Result:"), finale)
    ruleRow.autosize
    ruleRow.alignmentInParent = Pos.Center

    // returning the RuleRow
    ruleRow
  }

}
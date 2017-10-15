package GoLApp

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


// Opens view to create custom rules
object CustomRuleCreator extends JFXApp {

  // Defines and returns the view's scene
   def execute : Scene = {
      val customRuleView = new Scene(500, 500) {

        // TODO: Make scene's design
       stylesheets = List(getClass.getResource("customRuleCreator.css").toExternalForm)
      val grid = new GridPane

      // Number of Rows in Grid = number of Tests in new rule
      var gridRowIdx = 1

      // Adds initial row
      grid.addRow(gridRowIdx, getRuleRow)
      grid.autosize

      // Adds more rows. Max of 5
      val newRule = new Button("New Rule")
      newRule.onAction = (ae: ActionEvent) => {

        if (gridRowIdx < 5) {
          this.grid.addRow(gridRowIdx, getRuleRow)
          this.gridRowIdx += 1
        }
        else {

          // Given that Max number of Rows is reached, an alert is emitted if trying to add a new row
          new Alert(AlertType.Error) {
            initOwner(stage)
            title = "Rule definition"
            headerText = "Rule Definition Maximum number of Lines Reached"
            contentText = "You've reached the maximum lines available for a rule"
          }.showAndWait()
        }

      }

      // Finishes creating rule and sets it as rule to be used in the game.
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
          Cidx = 0
        })
        // Creates the rule passing selections
        Main.overrideRule(new CustomRule(testTemplate))
        // Returns view to openingView
        Main.getControl
      }

      // TODO: Organize all elements. Add cancel button.
      val rootPane = new BorderPane
      rootPane.center = grid
      rootPane.bottom = newRule
      rootPane.right = done
      grid.margin = Insets(1, 1, 1, 1)
      content = rootPane
    }

    customRuleView
  }

  // Returns a Text with given text
  private def getText(v: String): Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  // Returns a new Row for the grid.
  // TODO: Discover why first add doesn't apprear in the correct position and fix it
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

    // returning the RuleRow
    ruleRow
  }

}
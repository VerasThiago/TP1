package GoLApp

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.{FlowPane, GridPane}
import scalafx.scene.text.Text


// Opens view to create custom rules
class CustomRuleCreator extends JFXApp {

  // Defines and returns the view's scene
  def execute : Scene = {
    val customRuleView = new Scene(620, 530) {

      // TODO: Make scene's design
      stylesheets = List(getClass.getResource("customRuleCreator.css").toExternalForm)

      val cusRulePanel = new FlowPane
      val argsPanel = new GridPane

      // Number of Rows in Grid = number of Tests in new rule
      var gridRowIdx = 1

      val custxt = new Text("Custom Rule")
      custxt.setId("head-text")

      // Adds more rows. Max of 5
      val newRule = new Button("New Rule")
      newRule.styleClass = List("button")
      newRule.onAction = (ae: ActionEvent) => {

        if (gridRowIdx < 5) {
          this.gridRowIdx += 1
          argsPanel.addRow(gridRowIdx, getRuleRow)

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
      done.styleClass = List("button")
      done.onAction = (ae: ActionEvent) => {
        //Main.changeScene(1)

       val rows = argsPanel.getChildren
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
        Main.overrideRule(new CustomRule(testTemplate, input.getText))
        // Returns view to openingView
        Main.changeScene(1)
      }

      val input = new TextField

      val name = new Text("Rule name:")
      name.setId("name")

      argsPanel.addRow(gridRowIdx, getRuleRow)

      argsPanel.setAlignment(Pos.TopCenter)
      argsPanel.vgap = 15
      argsPanel.hgap = 500

      cusRulePanel.setAlignment(Pos.TopCenter)
      cusRulePanel.vgap = 40
      cusRulePanel.hgap = 200

      cusRulePanel.children = List(custxt,newRule, done, name, input,argsPanel)
      cusRulePanel.setStyle("-fx-background-color: grey;")
      cusRulePanel.setPrefSize(620,530)

      //val grid = new GridPane



      // Adds initial row
      //grid.addRow(gridRowIdx, getRuleRow)









      // TODO: Organize all elements. Add cancel button.
      //newRule.setTranslateX(-400)


      /*val listOfButtons = new ButtonBar
      listOfButtons.buttons.addAll(newRule, done)
      val rootPane = new BorderPane
      rootPane.center = grid
      rootPane.bottom = listOfButtons
      grid.margin = Insets(1, 1, 1, 1)*/

      content = cusRulePanel
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
    ruleRow.prefWidth = 580
    ruleRow.items = List(getText("A cell"), initial, getText("with"), specifier, neighbours, getText("living neighbours. Result:"), finale)

    // returning the RuleRow
    ruleRow
  }

}
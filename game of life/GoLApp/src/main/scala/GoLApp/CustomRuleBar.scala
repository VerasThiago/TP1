package GoLApp

import scalafx.collections.ObservableBuffer
import scalafx.geometry.Pos
import scalafx.scene.control.{ChoiceBox, ToolBar}
import scalafx.scene.text.Text

class CustomRuleRow extends ToolBar {
  var selections : Array[String] = Array()

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

   def getRuleRow : ToolBar = {
    // Actually creating the RuleRow
    val ruleRow = new ToolBar
    ruleRow.items = List(getText("A cell"), initial, getText("with"), specifier, neighbours,getText("living neighbours. Result:"), finale)
    ruleRow.autosize

    // returning the RuleRow
    ruleRow
  }

  private def getText(v : String) : Text = {
    val text = new Text(v)
    text.alignmentInParent = Pos.Center
    text
  }

  def makeArray : Unit = {
    selections(0) = initial.getValue
    selections(1) = specifier.getValue
    selections(2) = neighbours.getValue
    selections(3) = finale.getValue
  }
}

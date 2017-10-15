import GoLBase.RuleGuide
import scala.io.Source

object ruleDependency {
  def main(args: Array[String]) {
    val availableRules = getRules()

    availableRules.foreach(r => println(r.name))

  }


  // method implemented by rbonifacio in https://github.com/rbonifacio/scala-gol-reflection

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
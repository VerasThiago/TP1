import GoLBase.{Board, Cells, RuleGuide}

// incomplete method. Needs GUI.
class CustomRule extends RuleGuide{
  override val name: String = "Custom"
  defineTests()

  override def nextGen(width: Int, height: Int, board: Board): (List[Cells], List[Cells]) = {
    var alive : List[Cells] = List()
    var dead: List[Cells] = List()
    var itr = 0
    board.universe.foreach(x =>{
      for( c : Cells <- x){

        val liveneighbours = board.countLiveNeighbors(itr , x.indexOf(c))
        // tests
      }
      itr += 1
    })

    dead = dead.reverse
    alive = alive.reverse
    (alive, dead)
  }

  override def info: Array[String] = {
    val r : Array[String] = Array(
      "Rule defined by player."
    )
    r
  }

  private def defineTests() : Unit = {
    println("Inform Rules. First, rules that should revive a cell or keep it alive")
    println("Rule example:\nalive, 2 living, keep alive")
  }
}


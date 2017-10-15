package GoLApp

import GoLBase.{Board, Cells, RuleGuide}


class CustomRule(args : Array[Array[String]]) extends RuleGuide {
  override val name: String = "Custom"


  override def nextGen(width: Int, height: Int, board: Board): (List[Cells], List[Cells]) = {
    var alive : List[Cells] = List()
    var dead: List[Cells] = List()
    var itr = 0
    board.universe.foreach(x =>{
      for( c : Cells <- x){

        val liveneighbours = board.countLiveNeighbors(itr , x.indexOf(c))
        // TODO: Make tests actually work
      }
      itr += 1
    })

    dead = dead.reverse
    alive = alive.reverse
    (alive, dead)
  }

  override def info: Array[String] = {
    var r : Array[String] = Array("Rule defined by player.")
    args.foreach(x => r = r :+ ("A cell that's " + x(0) + " with " + x(1) + " " + x(2) + " living neighbours. Result: " + x(3)))

    r
  }


}


package GoLExtensions

import GoLBase.{Board, Cells, RuleGuide}



class HiLife extends RuleGuide{
  override val name: String = "High Life"


  override def nextGen(width: Int, height: Int, board: Board): (List[Cells] , List[Cells]) = {
    var alive : List[Cells] = List()
    var dead: List[Cells] = List()
    var itr = 0
    board.universe.foreach(x =>{
      for( c : Cells <- x){

        val liveneighbours = board.countLiveNeighbors(itr , x.indexOf(c))
        if(c.isAlive && (liveneighbours == 2 || liveneighbours == 3 )) {
          alive = c :: alive
        }
        else if(!c.isAlive && (liveneighbours == 3 || liveneighbours == 6)) {
          alive = c :: alive
        }
        else if (c.isAlive && (liveneighbours < 2 || liveneighbours > 3)) {
          dead = c :: dead
        }
      }
      itr += 1
    })

    dead = dead.reverse
    alive = alive.reverse
    (alive, dead)
  }

  override def info(): Array[String] = {
    val info = Array(
      "Any living cell with 2 or 3 neighbours lives on",
      "Any dead cell with 3 or 6 neighbours lives",
      "Any living cell with fewer than 2 or more than 3 living neighbours dies")
    info
  }
}

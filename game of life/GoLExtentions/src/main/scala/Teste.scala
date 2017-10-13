import GoLBase.{Board, Cells, RuleGuide}



class Teste extends RuleGuide{
  override val name: String = "Teste"


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
        else if(!c.isAlive && liveneighbours == 3) {
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
      "Teste louco da balada")
    info
  }
}

package GoLExtensions

import GoLBase.{Board, Cells, RuleGuide}

class Random extends RuleGuide{
  override val name = "Random"

  override def nextGen(width: Int, height: Int, board: Board) = {
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

    val x = new scala.util.Random(System.nanoTime())
    if(x.nextInt(100) == 1){
      if(x.nextInt%2 == 0){
        alive = board.universe(x.nextInt%width)(x.nextInt%height) :: alive
      }
      else{
        dead = board.universe(x.nextInt%width)(x.nextInt%height) :: dead
      }
    }
    dead = dead.reverse
    alive = alive.reverse
    (alive, dead)
  }

  override def info = {
    Array(
      "Works exactly like Conway's rule, but with random events."
    )
  }
}

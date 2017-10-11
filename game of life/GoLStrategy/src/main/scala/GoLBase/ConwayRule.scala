package GoLBase

class ConwayRule extends RuleGuide {
  override val name: String = "Conway"

  override def nextGen(width: Int, height: Int, board: Board): (List[Cells] , List[Cells]) = {
    var alive : List[Cells] = List()
    var dead: List[Cells] = List()
    var itr = 0
      board.universe.foreach(x =>{
        for( c : Cells <- x){

          val liveneighbours = board.countLiveNeighbors(itr , x.indexOf(c))
          if(c.isAlive && (liveneighbours == 2 || liveneighbours == 3 )) {
            alive = c :: alive
            c.revive
          }
          else if(!c.isAlive && liveneighbours == 3) {
            alive = c :: alive
            c.revive
          }
          else if (c.isAlive && (liveneighbours < 2 || liveneighbours > 3)) {
            dead = c :: dead
            c.kill
          }
        }
        itr += 1
      })

    dead = dead.reverse
    alive = alive.reverse
    (alive, dead)
  }

  override def info(): Array[String] = {
    val info = Array( "Original rules for the Game of Life.",
                      "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation",
                      "Any live cell with two or three live neighbours lives on to the next generation.",
                      "Any live cell with more than three live neighbours dies, as if by overpopulation.",
                      "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.")

    info
  }
}

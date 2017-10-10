class ConwayRule extends RuleGuide {
  override val name: String = "Conway"

  override protected def shouldLive(width: Int, height: Int, board: Board): List[Cells] = {
    var alive : List[Cells] = List()
    var itr = 0
      for(x : Array[Cells] <- board){
        for( c : Cells <- x){
          if(c.isAlive && (board.countLiveNeighbors(itr , x.indexOf(c)) == 2 || board.countLiveNeighbors(itr , x.indexOf(c)) == 3 ))
            alive = c :: alive
          else if(!c.isAlive && board.countLiveNeighbors(itr , x.indexOf(c)) == 3)
            alive = c :: alive
        }
        itr += 1
      }

    alive
  }

  override protected def shouldDie(x : Int, y: Int): List[Cells] = ???

  override protected def info(): String = ???
}

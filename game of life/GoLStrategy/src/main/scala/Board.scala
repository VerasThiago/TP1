class Board(val width : Int = 10, val height : Int = 10) {

  var universe = Array.ofDim[Cells](width, height)

  def statistics : (Int, Int) = {
    var alive = 0
    universe.foreach(a => a.foreach(c => if(c.isAlive) alive += 1))
    (alive, width*height - alive)
  }

  def countLiveNeighbors(x : Int, y : Int) : Int= {
    var liveNeighbours = -1;  // includes position of cell of interest
    for(w <- (x-1+width)%width to (x+1)%width){
      for(h <- (y-1+height)%height to (y+1)%height){
        if(this.universe(w)(h).isAlive) liveNeighbours += 1
      }
    }

    liveNeighbours
  }


}

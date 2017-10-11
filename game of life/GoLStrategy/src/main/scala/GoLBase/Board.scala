package GoLBase

class Board(val width : Int = 10, val height : Int = 10) {

  var universe = Array.ofDim[Cells](width, height)
  var itr = 0
  for(i <- 0 until width){
    for(j <- 0 until height){
      universe(i)(j) = new Cells(i, j)
    }
  }

  def statistics : (Int, Int) = {
    var alive = 0
    universe.foreach(a => a.foreach(c => if(c.isAlive) alive += 1))
    (alive, width*height - alive)
  }

  // TODO: Make countLiveNeighbors pretty
  def countLiveNeighbors(x : Int, y : Int) : Int = {

    var liveNeighbours = if(this.universe(x)(y).isAlive) -1 else 0
    val itr = Array(-1, 0, 1)

    itr.foreach(i =>
      itr.foreach(j =>
        if (this.universe((x + i + width) % width)((y + j + height) % height).isAlive)
          liveNeighbours += 1
      )
    )
    liveNeighbours
  }

  def update(live : List[Cells], die : List[Cells] = null) : Unit = {
    live.foreach(c => this.revive(c.x, c.y))
    if(die != null) die.foreach(c => this.kill(c.x, c.y))
  }

  // TODO: Update to throw exception OutOfBounds
  private def revive(x : Int, y : Int) = {
    this.universe(x)(y).revive
  }

  // TODO: Update to throw exception OutOfBounds
  private def kill(x: Int, y: Int) = {
    this.universe(x)(y).kill
  }

  def show_grid() = {
    println("")
    for(i <- 0 until width){
      for(j <- 0 until height){
        print(if(this.universe(i)(j).isAlive) 1 else 0)
        printf(" ")
      }
      println("")
    }
    println("")
  }
}

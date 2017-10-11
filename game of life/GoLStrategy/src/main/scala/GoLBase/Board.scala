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

    println(s"Now computing neighbours of ($x, $y). Exploring in:")
    print(s"(${(x-1+width)%width}, ${(y-1+height)%height})  ")
    print(s"(${(x-1+width)%width}, ${y})  ")
    print(s"(${(x-1+width)%width}, ${(y+1)%height})  ")
    print(s"(${x}, ${(y-1+height)%height})  ")
    print(s"(${x}, ${(y+1)%height})  ")
    print(s"(${(x+1)%width}, ${(y-1+height)%height})  ")
    print(s"(${(x+1)%width}, ${y})  ")
    print(s"(${(x+1)%width}, ${(y+1)%height})  ")
    println()

    var liveNeighbours = 0;

    if(this.universe((x-1+width)%width)((y-1+height)%height).isAlive) liveNeighbours += 1
    if(this.universe((x-1+width)%width)(y).isAlive) liveNeighbours += 1
    if(this.universe((x-1+width)%width)((y+1)%height).isAlive) liveNeighbours += 1
    if(this.universe(x)((y-1+height)%height).isAlive) liveNeighbours += 1
    if(this.universe(x)((y+1)%height).isAlive) liveNeighbours += 1
    if(this.universe((x+1)%width)((y-1+height)%height).isAlive) liveNeighbours += 1
    if(this.universe((x+1)%width)(y).isAlive) liveNeighbours += 1
    if(this.universe((x+1)%width)((y+1)%height).isAlive) liveNeighbours += 1

    println(s"Result: $liveNeighbours\n")
    liveNeighbours
  }

  def update(live : List[Cells], die : List[Cells] = null) : Unit = {
    live.foreach(c => this.revive(c.x, c.y))
    if(die != null)
      die.foreach(c => this.kill(c.x, c.y))
  }

  // TODO: Update to throw exception OutOfBounds
  private def revive(x : Int, y : Int) = {
    this.universe(x)(y).revive
  }

  // TODO: Update to throw exception OutOfBounds
  private def kill(x: Int, y: Int) = {
    this.universe(x)(y).kill
  }

}

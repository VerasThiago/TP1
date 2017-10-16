package GoLApp

import GoLBase.{Board, Cells, RuleGuide}


class CustomRule(args : Array[Array[String]]) extends RuleGuide {
  override val name: String = "Custom"
  private val tests = extractTests(args)

  override def nextGen(width: Int, height: Int, board: Board): (List[Cells], List[Cells]) = {
    var alive : List[Cells] = List()
    var dead: List[Cells] = List()
    var itr = 0
    board.universe.foreach(x =>{
      for( c : Cells <- x){

        val liveneighbours = board.countLiveNeighbors(itr , x.indexOf(c))
        tests.foreach(x => {
          if(c.isAlive && x(0) == 0){
            if(operation(x(1), liveneighbours, x(2))){
              x(3) match {
                case 0 => alive = c :: alive
                case 1 => dead = c :: dead
              }
            }
          }
          else if(!c.isAlive && x(0) == 1){
            if(operation(x(1), liveneighbours, x(2))){
              x(3) match {
                case 0 => alive = c :: alive
                case 1 => dead = c :: dead
              }
            }
          }
        })
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

  private def extractTests(input : Array[Array[String]]) : Array[Array[Int]] = {

    val test : Array[Array[Int]] = Array.ofDim(input.length, 4)
    var i = 0
    var j = 0
    input.foreach(x => {
      x.foreach(s => {
        test(i)(j) = sMatch(s)
        j += 1
      })
      j = 0
      i += 1
    })
    test
  }

  private def sMatch(s : String) : Int = {

    s match {
      case "alive" =>  0
      case "dead" =>  1

      case "exactly" =>  0
      case "more than" =>  1
      case "less than" =>  2

      case "1" =>  1
      case "2" =>  2
      case "3" =>  3
      case "4" =>  4
      case "5" =>  5
      case "6" =>  6
      case "7" =>  7
      case "8" =>  8

    }
  }

  private def operation(op : Int, neighbours : Int, expected : Int) : Boolean = {
    op match {
      case 0 => neighbours == expected
      case 1 => neighbours > expected
      case 2 => neighbours < expected
    }
  }

}


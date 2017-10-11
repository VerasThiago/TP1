import GoLBase.{Board, Cells, ConwayRule}

object conceptTest extends App{

  var board = new Board(5, 5)
  val create = List(
    new Cells(0, 0),
    new Cells(0, 1),
    new Cells(0, 2)
  )
  board.update(create)

  println("\nGeneration 0")
  println("Alive: " + board.statistics._1)

  board.show_grid()

  val rule = new ConwayRule

  val (live, kill) = rule.nextGen(5, 5, board)

  println("To live: ")
  live.foreach(c => print(s"(${c.x},${c.y}) "))
  println("\nTo die: ")
  kill.foreach(c => print(s"(${c.x},${c.y}) "))

  board.update(live, kill)

  println("\nGeneration 1")
  println("Alive: " + board.statistics._1)

  board.show_grid()

  val (live1, kill1) = rule.nextGen(5, 5, board)

  board.update(live1, kill1)

  println("Generation 2")
  println("Alive: " + board.statistics._1)

  board.show_grid()
}

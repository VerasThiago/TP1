import GoLBase.{Board, Cells, RuleGuide}



class Teste extends RuleGuide{
  override val name: String = _

  override def nextGen(width: Int, height: Int, board: Board): (List[Cells], List[Cells]) = ???

  override def info: Array[String] = ???
}

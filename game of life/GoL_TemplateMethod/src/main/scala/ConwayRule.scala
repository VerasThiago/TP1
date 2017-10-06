class ConwayRule extends RuleGuide {
  override val name: String = "Conway"

  override protected def shouldLive(board: Board): List[Cells] = {
    var living: List[Cells] = List()

    living
  }

  override protected def shouldDie: List[Cells] = ???

  override protected def info(): String = ???
}

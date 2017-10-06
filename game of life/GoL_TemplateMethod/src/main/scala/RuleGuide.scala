abstract class RuleGuide{

    // RuleGuide knows how to create a Rule.
    val name: String
    protected def shouldLive(board: Board) : List[Cells] // calculates cells that will live
    protected def shouldDie : List[Cells]  // calculates cells that will die
    protected def info : String  // Explains how rule works


}

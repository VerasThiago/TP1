trait RuleGuide{

    // RuleGuide knows how to create a Rule.
    val name: String
    protected def shouldLive(width : Int, height : Int) : List[Cells]// calculates cells that will live
    protected def shouldDie(width : Int, height : Int) : List[Cells]  // calculates cells that will die
    protected def info : String  // Explains how rule works


}

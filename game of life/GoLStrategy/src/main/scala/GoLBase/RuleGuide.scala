package GoLBase

trait RuleGuide{

    // RuleGuide knows how to create a Rule.
    
    val name: String
    
    def nextGen(width: Int, height: Int, board: Board): (List[Cells] , List[Cells]) // computes next gen
    
    def info : Array[String]  // Explains how rule works


}

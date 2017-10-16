package GoLApp

import GoLBase.Board

class Memento(width : Int, height : Int) extends Board {

   private var state = new Board(width, height)

   def saveState(stateToSave : Board) : Unit = this.state = stateToSave

   def restoreState : Board = this.state

}

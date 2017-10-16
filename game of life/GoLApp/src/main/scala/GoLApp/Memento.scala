package GoLApp

import GoLBase.Board

class Memento(width : Int, height : Int) extends Board {

   private val state = new Board(width, height)

   def saveState(stateToSave : Board) : Unit = {
     for(i <- 0 until width){
       for(j <- 0 until height){
         if(stateToSave.universe(i)(j).isAlive)
           state.universe(i)(j).revive

         else
           state.universe(i)(j).kill
       }
     }
   }

   def restoreState : Board = state


}

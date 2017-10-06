

class Cells(val x : Int = 0, val y : Int = 0) {
      // cell definition
      var isAlive = false

      def kill = {isAlive = false}
      def revive = {isAlive = true}
}

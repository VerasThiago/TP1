

class Point(val xc: Int, val yc: Int) {
   var x: Int = xc
   var y: Int = yc
   
   def move(dx: Int, dy: Int) {
      x = x + dx
      y = y + dy
      println ("Point x location : " + x);
      println ("Point y location : " + y);
   }
   def att(){
	    println ("Point x location : " + x);
      println ("Point y location : " + y);
   }
}

object Hello {
    def main(args: Array [String]){
        val a = 15
        val b = 10
        var base = new Point(a,b);
        base.move(10, 20);
        base.att();
    }
}
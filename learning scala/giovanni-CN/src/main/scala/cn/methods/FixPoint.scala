package cn.methods

trait FixPoint {

  def iterationFunc(x : Double) : Double

  def solveByFP = {
    val init = scala.io.StdIn.readLine("Inform initial approximation\n").toDouble
    val err : Double = 0.5 * Math.pow(10, -scala.io.StdIn.readLine("Inform how many significant digits\n").toInt)
    var prev : Double = init
    var curr : Double = iterationFunc(init)
    var k = 0
    var go = true

    while(go){
      go = if(approxErr(curr, prev) > err) true else false
      println(s"k = $k.\tcurr = $curr.")
      println(s"approxErr = ${approxErr(curr, prev)}.\tAccepted error = $err.\n")
      prev = curr
      curr = iterationFunc(prev)
      k += 1
      if (k >= 100) {
        println("\nBAD INITIAL APPROXIMATION. NO CONVERSION OF SEQUENCE.")
        go = false
      }
    }

    curr
  }

  def approxErr(curr : Double, prev : Double) : Double = Math.abs((curr - prev)/curr)

}

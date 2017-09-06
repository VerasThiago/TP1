package cn.gabaritos


trait Questao1a {

  var r : Double = 0
  var l : Double = 0

  def solve = {
    interval
    while (scala.io.StdIn.readLine("Enter 1 if interval is correct, 0 otherwise\n").toInt != 1) {
      interval
    }

    val digits = scala.io.StdIn.readLine("How many significant digits? (just number)\n").toInt
    val err: Double = 5 * Math.pow(10, -digits)
    var mid: Double = (r + l) / 2.0
    var prev: Double = l
    var k = 0
    var go = if (aproxErr(mid, err) > err) true else false
    while (go) {
      mid = (r + l) / 2.0

      println(s"k = $k.\t[a, b] = [$l, $r]")
      println(s"mid = $mid")
      println(s"f(a) = ${func(l)} ${if (func(l) > 0) '>' else '<'} 0")
      println(s"f(mid) = ${func(mid)} ${if (func(mid) > 0) '>' else '<'} 0")
      println(s"f(b) = ${func(r)} ${if (func(r) > 0) '>' else '<'} 0")
      println(s"Err aprox: ${aproxErr(mid, prev)}.\taccepted error: $err\n")
      go = if (aproxErr(mid, prev) > err) true else false

      if (func(l) * func(mid) < 0) {
        r = mid
      }
      else {
        l = mid
      }

      prev = mid
      k += 1
    }
  }

  def func(x : Double) : Double = { Math.exp(-x) - x}

  def interval : Unit = {
    println("Inform interval, one number at a time.")
    l = scala.io.StdIn.readDouble()
    r = scala.io.StdIn.readDouble()

    if(l > r){
      val temp = l
      l = r
      r = temp
    }

    println(s"Interval entered: ${l} to ${r}.")

  }

  def aproxErr(mid : Double, prev : Double) : Double = Math.abs((mid - prev)/mid)
}

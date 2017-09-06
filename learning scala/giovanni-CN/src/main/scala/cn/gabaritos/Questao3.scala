package cn.gabaritos

trait Questao3 extends Questao1a{

  override def func(x: Double): Double = Math.pow(x, 5) - (3* Math.pow(x, 2)) + 1

  def solve4all = {
    val asw = scala.io.StdIn.readLine("How many different answers there are?\n").toInt
    val axes : Array[Double] = Array.fill(asw)(solve)

    println("The functions roots are")
    axes.foreach(println)
  }
}

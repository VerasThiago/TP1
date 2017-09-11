import cn.methods.NewtonRaphson

object Questao4 extends App with NewtonRaphson{
  def func(x: Double): Double = Math.exp(-x) - x
  def derivative(x: Double): Double = - Math.exp(-x) - 1

  solveByNR
}

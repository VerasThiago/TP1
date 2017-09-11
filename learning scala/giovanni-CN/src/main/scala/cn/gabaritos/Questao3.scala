package cn.gabaritos
import cn.methods.Bissection

trait Questao3 extends Bissection{

   def func(x: Double): Double = Math.pow(x, 5) - (3* Math.pow(x, 2)) + 1


}

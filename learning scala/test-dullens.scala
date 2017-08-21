object test extends App {
  println("Hello World")

  def square (x:Int) = x*x         //faz o quadrado

  println(square(7))    //49

  var input = Console.readInt()  //le um inteiro

  println(input)

  input += 1
  println(input)

  def add (y: Int, z: Int) = y+z //soma dois

  println(input+4)
  println(add(input, 4))
  println(square(add(input, 1))) //soma 1 depois faz o quadrado

}

class calculator(x:Int, y:Int) {
	def add (x: Int, y: Int) = x+y
	def sub (x: Int, y: Int) = x-y
	def mult (x: Int, y: Int) = x*y
	def div (x: Int, y: Int) = x/y

}

object trying {
	val calc = new calculator(1,1)            //> calc  : calculator = calculator@28ba21f3

	println(calc.add(3,2))                    //> 5
	println(calc.sub(3,2))                    //> 1
	println(calc.mult(3,2))                   //> 6
	println(calc.div(3,2))                    //> 1
	println(calc.div(22, 2))                  //> 11
}
object lambdaFunction {
	val square = (x:Int) => x*x               //> square  : Int => Int = <function1>  "funcao lambda, funcao numa linha
	square(3)                                 //> res0: Int = 9

}

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

object test extends App {
  println("Hello World")
  
  def square (x:Int) = x*x                    
  
  println(square(7))    
  
  var input = Console.readInt()
  
  println(input)
  
  input += 1
  println(input)
                           
  def add (y: Int, z: Int) = y+z                  
  
  println(input+4)    
  println(add(input, 4))
  println(square(add(input, 1)))                  
	
}

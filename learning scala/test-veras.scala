import java.util.Scanner;

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

class Conta(val nome: String, val saldo: Double){
	var name: String = nome;
	var money: Double = saldo;

	def sacar(quanto: Double){
		if(this.money - quanto < 0) println("Saldo insuficiente para efetuar o saque");
		else{
			this.money -= quanto;
			println("Saque efetuado com sucesso");
		}
	}

	def extrato(){
		println("Saldo do cliente " + name + " = " + money); 	
	}
}

object Hello{
    def main(args: Array [String]){
		var scanner = new Scanner(System.in);
		println("Digite o nome da conta que será criado");	
		var cliente = scanner.nextLine();
		println("Digite o saldo inicial do cliente " + cliente);
		var dinheiro = scanner.nextDouble();
 		var base = new Conta(cliente,dinheiro);
 		base.extrato();
 		println("Informe o valor para o saque");
 		dinheiro = scanner.nextDouble();
 		base.sacar(dinheiro);
 		base.extrato();
    }
}

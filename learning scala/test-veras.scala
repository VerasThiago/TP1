import java.util.Scanner;
import scala.collection.mutable.ArrayBuffer

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

class Conta(){
	var name: String = "";
	var money: Double = 0.0;

	def init(){
		println("Digite o nome da conta que será criado");	
		var cliente = Console.readLine();
		println("Digite o saldo inicial do cliente " + cliente);
		var dinheiro = Console.readDouble();
		this.name = cliente;
		this.money = dinheiro;
	}
	def sacar(){
		println("Informe o valor para o saque");
 		var quanto = Console.readDouble();
		if(quanto < 0) println("Valor inválido");
		else if(this.money - quanto < 0) println("Saldo insuficiente para efetuar o saque");
		else{
			this.money -= quanto;
			println("Saque efetuado com sucesso");
		}
	}

	def extrato(){
		println("Saldo do cliente " + name + " = " + money); 	
	}

	def deposito(){
		print("Infome a quantia para o depósito: ");
		var num = Console.readDouble();
		if(num < 0) println("Valor inválido");
		else{
			this.money += num;
			println("Depósito realizado com sucesso");
		}
	}
}

object Hello{
    def main(args: Array [String]){
 		var base = new Conta();
 		base.init();
  			base.extrato();
 		base.sacar();
 		base.extrato();
 		base.deposito();
 		base.extrato();
    }
}
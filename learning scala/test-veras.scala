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
        def att(){
            println ("Point x location : " + x);
            println ("Point y location : " + y);
        }
    }
}

class Conta(){
    var name: String = "";
    var money: Double = 0.0;
    var bool = true;

    def menu(){
        print("\u001b[2J")
        println("Digite o numero da operação");
        println("0 - Sair");
        println("1 - Sacar");
        println("2 - Extrato");
        println("3 - Deposito");
    }
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
        print("Infome a quantia para o deposito: ");
        var num = Console.readDouble();
        if(num < 0) println("Valor inválido");
        else{
            this.money += num;
            println("Deposito realizado com sucesso");
        }
    }
}
class Operador(banco : Conta) extends Conta{
    def operacao(op: Int) : Boolean = {
        if(op == 0) return false;
        else if(op == 1) banco.sacar();
        else if(op == 2) banco.extrato();
        else if(op == 3) banco.deposito();
        else println("Opção inválida");
        return true;
    }
    
}

object Hello{
    def main(args: Array[String]){
        var banco = new Conta();
        banco.init();
        var tipo = new Operador(banco);
        var bool = true;

        while(bool){
            banco.menu();
            val op = readInt();
            bool = tipo.operacao(op);
            Thread.sleep(3000) 
        }
    }
}
package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContaCorrente {
	
	private String Titular;
	private double Saldo;
	private String Cpf;
	private String Senha;
	public double ChequeEspecial;
	private double LimiteCartao;
	private int Numero;
	private String Agencia;
	private String[] ChavePix;
	private double JurosCheque = .2;
	private double valorEmJuizo=1.20;
	private List<String> extrato = new ArrayList<>();
	
	
	
	public ContaCorrente(String titular, String cpf, String senha) {
		this.Titular = titular;
		this.Cpf = cpf;
		this.Senha = senha;
		this.Saldo = 0;
		this.ChequeEspecial = 0;
		this.LimiteCartao = 0;	
		this.Agencia = "0001";
		this.Numero = (1000000 % new Random().nextInt()) + 1000;
		
	}
	
	public double getSaldo() {
		return this.Saldo;
	}
	
	public void setSaldo(double saldo) {
		this.Saldo = saldo;
	}
	
	public String[] getChavePix() {
		return this.ChavePix;
	}
	
	public double getLimiteChequeEspecialTotal() {
		if(this.Saldo < 0) {
			return this.ChequeEspecial + (-1 * this.Saldo);
		}else {
			return this.ChequeEspecial;
		}
	}
	
	public double getLimiteChequeEspecialAtual() {
		return this.ChequeEspecial;
	}
	
	public String getTitular(){
        return this.Titular+" "+"["+ this.Cpf.substring(0,3)+"."+this.Cpf.substring(3,6)+"."+this.Cpf.substring(6,9)+"-"+this.Cpf.substring(9)+"]";
    }

	public void depositar(double valor) {
		if(this.Saldo < 0) {
			valor += this.Saldo * (this.JurosCheque);
			this.ChequeEspecial += (valor - (this.Saldo*-1));
		}
		this.Saldo += valor;
		
		extrato.add("Deposito realizado em " + PegarData());
	}
	
	public void sacar(double valor) {
		if(this.Saldo + this.ChequeEspecial >= valor) {
			if(this.Saldo < valor) {
				this.ChequeEspecial -= (valor - this.Saldo);
			}
			this.Saldo -= valor;
		}else {
			System.out.println("Nao pode sacar");
		}
		
		extrato.add("Saque realizado em " + PegarData());
		
	}
	
	public void transferir(String agencia, int conta, double valor) {
		if(this.Saldo + this.ChequeEspecial >= valor) {
			if(this.Saldo < valor) {
				this.ChequeEspecial -= (valor - this.Saldo);
			}
			
			this.Saldo -= valor;
		}
		else {
			System.out.println("Saldo insuficiente para transferência");
		}
		
		extrato.add("transferência realizada em " + PegarData());
		
	}
	
	public void transferirPix(String pix, double valor) {
		if(this.Saldo + this.ChequeEspecial >= valor) {
			if(this.Saldo < valor) {
				this.ChequeEspecial -= (valor - this.Saldo);
			}	
			this.Saldo -= valor;
		}
		else {
			System.out.println("Saldo insuficiente para pix");
		}
		
		extrato.add("Pix realizada em " + PegarData());
		
	}
	
	public String toString() {
		return "Titular: " + this.Titular + " Agencia: " + this.Agencia + " Conta: " + this.Numero;
	}
	
	public String PegarData() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String data=dtf.format(now);
      
        return data;
	}
	
	public String ImprimirExtrato() {
		return this.extrato.toString();
	}
	
}
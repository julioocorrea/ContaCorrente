
import Models.ContaCorrente;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContaCorrente conta = new ContaCorrente("Marcos", "14769473680", "123");
		
		System.out.println(conta.getSaldo());
		
		conta.depositar(100);
		conta.SetLimiteChequeEspecial(100);
		
		
		
		System.out.println(conta.getSaldo());
		System.out.println(conta.getTitular());
		
		conta.sacar(200);
		
		System.out.println(conta.getSaldo());
		System.out.println(conta.getLimiteChequeEspecialAtual());
		System.out.println(conta.getLimiteChequeEspecialTotal());
		
		conta.depositar(200);
		
		System.out.println();
		System.out.println(conta.getSaldo());
		System.out.println(conta.getLimiteChequeEspecialAtual());
		
		
		
		conta.ImprimirExtrato();
	}

}

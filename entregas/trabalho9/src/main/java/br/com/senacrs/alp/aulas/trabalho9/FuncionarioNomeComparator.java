package br.com.senacrs.alp.aulas.trabalho9;

import java.util.Comparator;

public class FuncionarioNomeComparator implements Comparator<Funcionario> {

	private static Comparator<Funcionario> instancia = new FuncionarioNomeComparator();
	
	FuncionarioNomeComparator() {

		super();
	}
	
	@Override
	public int compare(Funcionario o1, Funcionario o2) {
		
		int resultado = 0;
		String nome1 = null;
		String nome2 = null;
		
		nome1 = o1.getNome();
		nome2 = o2.getNome();		
		resultado = nome1.compareToIgnoreCase(nome2);
		resultado = normalizar(resultado);
		
		return resultado;
	}
	
	private int normalizar(int valor) {
		
		int resultado = 0;
		
		if (valor > 0) {
			resultado = 1;
		} else if (valor < 0) {
			resultado = -1;
		}

		return resultado;
	}
	
	public static Comparator<Funcionario> getInstancia() {
		
		return instancia;
	}

}

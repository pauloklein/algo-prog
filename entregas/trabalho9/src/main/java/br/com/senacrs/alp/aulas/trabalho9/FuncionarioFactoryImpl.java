package br.com.senacrs.alp.aulas.trabalho9;

public class FuncionarioFactoryImpl implements FuncionarioFactory {	
	private static FuncionarioFactory instancia = null;
	
	@Override
	public Funcionario criaFuncionario(String nome, int salario) {
		FuncionarioImpl funcionario = null;

		if (nome != null && nome != "" && salario >= 0) {
			funcionario = new FuncionarioImpl(nome, salario);
		} else {
			throw new IllegalArgumentException();
		}
		return funcionario;
	}
	
	public static FuncionarioFactory getInstancia() {
		if (instancia == null) {
			instancia = new FuncionarioFactoryImpl();
		}
		return instancia;
	}
}

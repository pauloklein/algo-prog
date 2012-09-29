package br.com.senacrs.alp.aulas.trabalho5;

public class FuncionarioFactoryImp implements FuncionarioFactory {
	private static FuncionarioFactory instancia = null;

	public Funcionario criaFuncionario(String nome, int salario) {
		FuncionarioImp funcionario = null;

		if (nome != null && nome != "" && salario >= 0) {
			funcionario = new FuncionarioImp(nome, salario);
		} else {
			throw new IllegalArgumentException();
		}
		return funcionario;
	}

	public static FuncionarioFactory getInstancia() {
		if (instancia == null) {
			instancia = new FuncionarioFactoryImp();
		}
		return instancia;
	}

}
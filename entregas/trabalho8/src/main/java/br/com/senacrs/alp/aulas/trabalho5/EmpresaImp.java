package br.com.senacrs.alp.aulas.trabalho5;

import java.util.HashMap;
import java.util.Map;

public class EmpresaImp implements Empresa {

	private Map<String, Funcionario> funcionarios = null;

	public EmpresaImp() {
		if (funcionarios == null) {
			funcionarios = new HashMap<String, Funcionario>();
		}
	}

	public void adicionaFuncionario(Funcionario funcionario) {
		if (funcionario != null) {
			funcionarios.put(funcionario.getNome(), funcionario);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Funcionario buscaFuncionario(String nome) {
		Funcionario funcionario = null;

		if (nome != null) {
			funcionario = funcionarios.get(nome);
		}

		return funcionario;
	}

	public int totalFolhaPgto() {
		int salarioTotal = 0;

		for (Funcionario funcionario : funcionarios.values()) {
			salarioTotal += funcionario.getSalario();
		}

		return salarioTotal;
	}
}

package br.com.senacrs.alp.aulas.trabalho9;

public class FuncionarioImpl implements Funcionario {
	private String nome = null;
	private int salario = 0;

	public FuncionarioImpl(String nome, int salario) {
		setNome(nome);
		setSalario(salario);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public int getSalario() {
		return salario;
	}
}

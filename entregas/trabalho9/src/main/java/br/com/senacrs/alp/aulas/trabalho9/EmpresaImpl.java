package br.com.senacrs.alp.aulas.trabalho9;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

import java.util.Map;
import java.util.HashMap;

public class EmpresaImpl implements Empresa {

	private Map<String, Funcionario> funcionarios = null;
	
	public EmpresaImpl() {
		if (funcionarios == null) {
			funcionarios = new HashMap<String, Funcionario>();
		}
	}
	
	@Override
	public void adicionaFuncionario(Funcionario funcionario) {
		if (funcionario != null) {
			funcionarios.put(funcionario.getNome(), funcionario);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Funcionario buscaFuncionario(String nome) {
		Funcionario funcionario = null;

		if (nome != null) {
			funcionario = funcionarios.get(nome);
		}

		return funcionario;
	}

	@Override
	public int totalFolhaPgto() {
		int salarioTotal = 0;

		for (Funcionario funcionario : funcionarios.values()) {
			salarioTotal += funcionario.getSalario();
		}

		return salarioTotal;
	}

	@Override
	public List<Funcionario> ordemCrescenteSalario() {
		List<Funcionario> lista = null;
		FuncionarioSalarioComparator comparador = null;
		
		lista = new LinkedList<Funcionario>(funcionarios.values());
		comparador = new FuncionarioSalarioComparator();
		
		Collections.sort(lista, comparador);
		
		return lista;	
	}

	@Override
	public List<Funcionario> ordemDecrescenteSalario() {
		List<Funcionario> lista = null;
		FuncionarioSalarioComparator comparador = null;
		
		lista = new LinkedList<Funcionario>(funcionarios.values());
		comparador = new FuncionarioSalarioComparator();
		
		Collections.sort(lista, comparador);
		Collections.reverse(lista);
		
		return lista;	
	}

	@Override
	public List<Funcionario> ordemAlfabetica() {
		List<Funcionario> lista = null;
		FuncionarioNomeComparator comparador = null;
		
		lista = new LinkedList<Funcionario>(funcionarios.values());
		comparador = new FuncionarioNomeComparator();
		
		Collections.sort(lista, comparador);
		
		return lista;	
	}

}

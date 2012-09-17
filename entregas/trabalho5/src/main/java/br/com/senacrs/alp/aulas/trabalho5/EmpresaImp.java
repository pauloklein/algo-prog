package br.com.senacrs.alp.aulas.trabalho5;

import br.com.senacrs.alp.aulas.MinhaListaImp;

public class EmpresaImp implements Empresa{
	
	private MinhaListaImp<Funcionario> funcionarios = null;
	
	public EmpresaImp(){
		if(funcionarios == null){
			funcionarios = new MinhaListaImp<Funcionario>();		
		}
	}
	
	public FuncionarioImp cast(Funcionario funcionario){
		return (FuncionarioImp)funcionario;
	}
	
	public void adicionaFuncionario(Funcionario funcionario){
		if(funcionario != null){
			funcionarios.sufixar(funcionario);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public Funcionario buscaFuncionario(String nome){
		FuncionarioImp funcionario = null;
		FuncionarioImp busca = null;
		
		if(nome != null){
			for(int i=0; i < funcionarios.tamanho(); i++){
				busca = cast(funcionarios.buscar(i));
				if(nome == busca.getNome()){
					funcionario = busca;
				}
			}
		}
		
		return funcionario;
	}
	
	public int totalFolhaPgto(){
		FuncionarioImp funcionario = null;
		int salarioTotal = 0;
		
		for(int i=0; i < funcionarios.tamanho(); i++){
			funcionario = cast(funcionarios.buscar(i));
			salarioTotal += funcionario.getSalario();			
		}
		
		return salarioTotal;
	}
}

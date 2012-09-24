package br.com.senacrs.alp.aulas.trabalho5;

//import java.util.List;
import java.util.LinkedList;

//import br.com.senacrs.alp.aulas.MinhaListaImp;

public class EmpresaImp implements Empresa{
	
	private LinkedList<Funcionario> funcionarios = null;
	
	public EmpresaImp(){
		if(funcionarios == null){
			funcionarios = new LinkedList<Funcionario>();
		}
	}
	
	public FuncionarioImp cast(Funcionario funcionario){
		return (FuncionarioImp)funcionario;
	}
	
	public void adicionaFuncionario(Funcionario funcionario){
		if(funcionario != null){
			funcionarios.add(funcionario);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public Funcionario buscaFuncionario(String nome){
		FuncionarioImp funcionario = null;
		FuncionarioImp busca = null;
		
		if(nome != null){
			for(int i=0; i < funcionarios.size(); i++){
				busca = cast(funcionarios.get(i));
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
		
		for(int i=0; i < funcionarios.size(); i++){
			funcionario = cast(funcionarios.get(i));
			salarioTotal += funcionario.getSalario();			
		}
		
		return salarioTotal;
	}
}

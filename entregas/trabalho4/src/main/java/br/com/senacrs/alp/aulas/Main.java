package br.com.senacrs.alp.aulas;

public class Main {

	public static void main(String[] args) {
		
		MinhaLista<String> lista1 = 
				new MinhaListaImp<String>();
		
		MinhaLista<String> lista2 = 
				new MinhaListaImp<String>("inicio");
				
		System.out.println("Quantidade de alementos da lista vazia: " + lista1.tamanho());
		System.out.println("Quantidade de alementos da lista não vazia: " + lista2.tamanho());		
	}
}

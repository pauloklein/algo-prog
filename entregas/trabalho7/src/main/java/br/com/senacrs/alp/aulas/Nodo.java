package br.com.senacrs.alp.aulas;

public class Nodo<T extends Object> {

	private T valor;
	private Nodo<T> proximo = null;
	private Nodo<T> anterior = null;

	public Nodo(T valor) {

		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public Nodo<T> getProximo() {
		return proximo;
	}

	public void setProximo(Nodo<T> proximo) {
		this.proximo = proximo;
	}

	public Nodo<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(Nodo<T> anterior) {
		this.anterior = anterior;
	}

	@Override
	public String toString() {

		String resultado = null;

		resultado = "";

		if (this.anterior != null) {
			resultado += "<-";
		}

		resultado += "[" + this.valor + "]";
		if (this.proximo != null) {
			resultado += "->";
		}

		return resultado;
	}

}

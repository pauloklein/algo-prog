package br.com.senacrs.alp.aulas;

public class MinhaListaReversivelImp<T> implements MinhaListaReversivel<T> {

	private Nodo<T> inicio = null;

	public MinhaListaReversivelImp() {

		this.inicio = new Nodo<T>(null);
	}

	protected Nodo<T> getInicio() {
		return inicio;
	}

	public void reverter() {
		Nodo<T> elemento1 = new Nodo<T>(null);
		Nodo<T> elemento2 = new Nodo<T>(null);
		int posicao = 0;
		int tamanho = tamanho();

		for (int i = 1; i <= (tamanho / 2); i++) {
			elemento1 = buscarNodo(i);
			posicao = tamanho - (i - 1);
			elemento2 = buscarNodo(posicao);

			inserir(posicao, elemento1.getValor());
			remover(posicao - 1);
			inserir(i, elemento2.getValor());
			remover(i - 1);
		}
	}

	@Override
	public void sufixar(T valor) {
		Nodo<T> ultimo = buscarUltimoNodo();
		Nodo<T> novoUltimo = new Nodo<T>(valor);

		ultimo.setProximo(novoUltimo);
		novoUltimo.setAnterior(ultimo);
	}

	private Nodo<T> buscarUltimoNodo() {

		int tamanho = tamanho();
		Nodo<T> resultado = buscarNodo(tamanho);

		return resultado;
	}

	private Nodo<T> buscarNodo(int posicao) {

		Nodo<T> resultado = getInicio();

		for (int i = 0; i < posicao; i++) {
			resultado = resultado.getProximo();
		}

		return resultado;
	}

	@Override
	public void prefixar(T valor) {

		Nodo<T> inicio = null;
		Nodo<T> primeiro = null;
		Nodo<T> novoPrimeiro = null;

		inicio = this.inicio;
		primeiro = inicio.getProximo();
		novoPrimeiro = new Nodo<T>(valor);
		novoPrimeiro.setProximo(primeiro);
		if (primeiro != null) {
			primeiro.setAnterior(novoPrimeiro);
		}
		inicio.setProximo(novoPrimeiro);
		novoPrimeiro.setAnterior(inicio);
	}

	@Override
	public T buscar(int posicao) {
		Nodo<T> nodo = null;

		verificarPosicaoParaBuscar(posicao);
		nodo = buscarNodo(posicao + 1);

		return nodo.getValor();

	}

	private void verificarPosicaoParaBuscar(int posicao) {

		int posicaoMaxima = 0;

		posicaoMaxima = this.tamanho() - 1;
		verificarPosicao(posicao, posicaoMaxima);
	}

	private void verificarPosicao(int posicao, int posicaoMaxima) {

		boolean posicaoValida = false;

		posicaoValida = posicaoDentroDeLimitesValidos(posicao, posicaoMaxima);
		if (!posicaoValida) {
			throw new IndexOutOfBoundsException();
		}
	}

	private boolean posicaoDentroDeLimitesValidos(int posicao, int posicaoMaxima) {

		boolean resultado = false;

		resultado = (posicao >= 0) && (posicao <= posicaoMaxima);

		return resultado;
	}

	@Override
	public void inserir(int posicao, T valor) {

		Nodo<T> anterior = null;
		Nodo<T> proximo = null;
		Nodo<T> nodo = null;

		verificarPosicaoParaInserir(posicao);
		anterior = buscarNodo(posicao);
		proximo = anterior.getProximo();
		nodo = new Nodo<T>(valor);
		anterior.setProximo(nodo);
		nodo.setAnterior(anterior);
		nodo.setProximo(proximo);
		if (proximo != null) {
			proximo.setAnterior(nodo);
		}

	}

	private void verificarPosicaoParaInserir(int posicao) {

		int posicaoMaxima = 0;

		posicaoMaxima = this.tamanho();
		verificarPosicao(posicao, posicaoMaxima);
	}

	@Override
	public T remover(int posicao) {

		Nodo<T> anterior = null;
		Nodo<T> nodo = null;
		Nodo<T> proximo = null;

		verificarPosicaoParaBuscar(posicao);
		anterior = buscarNodo(posicao);
		nodo = anterior.getProximo();
		proximo = nodo.getProximo();
		anterior.setProximo(proximo);
		proximo.setAnterior(anterior);

		return nodo.getValor();
	}

	@Override
	public int tamanho() {

		Nodo<T> nodo = getInicio();
		int resultado = 0;

		while (nodo.getProximo() != null) {
			nodo = nodo.getProximo();
			resultado++;
		}

		return resultado;
	}

}

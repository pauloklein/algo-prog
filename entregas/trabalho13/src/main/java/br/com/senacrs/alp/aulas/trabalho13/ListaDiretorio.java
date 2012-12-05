package br.com.senacrs.alp.aulas.trabalho13;

import java.io.File;
import java.util.LinkedList;

public class ListaDiretorio {

	private final char TIPO_DIRETORIO = 'D';
	private final char TIPO_ARQUIVO = 'A';

	public String[] listaConteudoDiretorio(String diretorio) {

		File dir = null;
		String[] list = null;
		String[] mensagem = null;
		String caminhoTemp = null;

		if (diretorio == null) {
			throw new IllegalArgumentException();
		} else {
			dir = new File(diretorio);
			if (!dir.exists() || dir.isFile()) {
				throw new IllegalArgumentException();
			} else {
				list = dir.list();
				if (list.length == 0) {
					mensagem = new String[] { "list: ",
							"total: " + Integer.valueOf(0), };
					caminhoTemp = dir.getAbsolutePath();
					mensagem[0] += caminhoTemp;
				} else {
					mensagem = capturaMensagem(list, diretorio);
				}
			}
		}

		return mensagem;
	}

	private String[] capturaMensagem(String[] list, String diretorio) {

		String[] arrayMensagem = null;
		LinkedList<String> listaMensagem = null;
		LinkedList<String> diretorios = null;
		LinkedList<String> arquivos = null;

		listaMensagem = new LinkedList<String>();

		diretorios = capturaListaTipo(list, diretorio, TIPO_DIRETORIO);
		arquivos = capturaListaTipo(list, diretorio, TIPO_ARQUIVO);

		listaMensagem.add("list: " + diretorio);
		listaMensagem.add("total: " + Integer.valueOf(list.length));

		for (int i = 0; i < diretorios.size(); i++) {
			listaMensagem.add(diretorios.get(i));
		}

		for (int i = 0; i < arquivos.size(); i++) {
			listaMensagem.add(arquivos.get(i));
		}

		arrayMensagem = transformaListaArray(listaMensagem);

		return arrayMensagem;
	}

	private LinkedList<String> capturaListaTipo(String[] list,
			String diretorio, char tipo) {

		LinkedList<String> lista = null;
		File fileElemento = null;

		lista = new LinkedList<String>();

		for (String elemento : list) {
			fileElemento = new File(diretorio + File.separator + elemento);
			if (tipo == TIPO_ARQUIVO && fileElemento.isFile()) {
				lista.add("a " + elemento);
			} else if (tipo == TIPO_DIRETORIO && fileElemento.isDirectory()) {
				lista.add("d " + elemento);
			}
		}

		return lista;
	}

	private String[] transformaListaArray(LinkedList<String> lista) {

		String[] array = null;

		array = new String[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
			array[i] = (String) lista.get(i);
		}

		return array;
	}
}

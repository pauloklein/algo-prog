package br.com.senacrs.alp.aulas.trabalho10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class GerenciadorDeArquivo {

	private File arquivo = null;

	public GerenciadorDeArquivo(String nomeArquivo) {
		if (nomeArquivo == null
				|| nomeArquivo.charAt(nomeArquivo.length() - 1) == '\\') {
			throw new IllegalArgumentException();
		} else {
			arquivo = new File(nomeArquivo);
		}
	}

	public String[] lerArquivo() {
		FileReader reader = null;
		BufferedReader input = null;
		
		LinkedList<String> listaTexto = new LinkedList<String>();
		String[] arrayTexto = null;
		
		String line = null;
		
		try {
			reader = new FileReader(arquivo);
			try {
				input = new BufferedReader(reader);
				line = input.readLine();

				while (line != null) {
					listaTexto.add(line);
					line = input.readLine();
				}
				input.close();
			} catch (IOException e2) {
				throw new IllegalArgumentException();
			}
		} catch (FileNotFoundException e1) {
			throw new IllegalArgumentException();
		}

		arrayTexto = new String[listaTexto.size()];
		for (int i = 0; i < arrayTexto.length; i++) {
			arrayTexto[i] = listaTexto.get(i);
		}

		return arrayTexto;
	}

	public String[] lerArquivoComSubstituicao(String lido, String retornado) {
		String[] arrayTexto = null;
		arrayTexto = lerArquivo();
		
		for (int i = 0; i < arrayTexto.length; i++) {
			arrayTexto[i] = arrayTexto[i].replace(lido, retornado);
		}		
			
		return arrayTexto;
	}

	public void escreverArquivo(String[] conteudo) {
		FileWriter writer = null;
		BufferedWriter output = null;

		try {
			writer = new FileWriter(arquivo);
			output = new BufferedWriter(writer);
			for (int i = 0; i < conteudo.length; i++) {
				output.write(conteudo[i] + "\n");				
			}
			output.close();
		} catch (IOException e3) {
			throw new IllegalArgumentException();
		}
	}
}

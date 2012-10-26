package br.com.senacrs.alp.aulas.trabalho11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmissorMensagens {

	private File arquivo = null;

	public EmissorMensagens(String nomeArquivo) {
		FileReader reader = null;
		BufferedReader input = null;
		String line = null;

		if (nomeArquivo == null) {
			throw new IllegalArgumentException();
		} else {
			arquivo = new File(nomeArquivo);
			if (!arquivo.exists() || arquivo.isDirectory()) {
				throw new IllegalArgumentException();
			} else {
				try {
					reader = new FileReader(arquivo);
					try {
						input = new BufferedReader(reader);
						line = input.readLine();

						while (line != null) {
							int total = 0;
							for (int i = 0; i < line.length(); i++) {
								if (line.charAt(i) == '=') {
									total++;
								}
							}
							if (total != 1) {
								throw new IllegalArgumentException();
							}
							line = input.readLine();
						}
						input.close();
					} catch (IOException e2) {
						throw new IllegalArgumentException(e2);
					}
				} catch (FileNotFoundException e1) {
					throw new IllegalArgumentException(e1);
				}
			}
		}
	}

	public String formatarMensagem(String chave, Object... argumentos) {
		FileReader reader = null;
		BufferedReader input = null;
		String line = null;
		String[] par = new String[2];
		Map<String, String> hash = new HashMap<String, String>();
		String texto = null;

		if (chave == null) {
			throw new IllegalArgumentException();
		} else {
			try {
				reader = new FileReader(arquivo);
				try {
					input = new BufferedReader(reader);
					line = input.readLine();

					while (line != null) {
						par = line.split("=");
						hash.put(par[0].trim(), par[1].trim());
						line = input.readLine();
					}

					if (hash.containsKey(chave)) {
						texto = String.format(hash.get(chave), argumentos);
					} else {
						throw new IllegalArgumentException();
					}
					input.close();
				} catch (IOException e4) {
					throw new IllegalArgumentException(e4);
				}
			} catch (FileNotFoundException e3) {
				throw new IllegalArgumentException(e3);
			}
		}
		return texto;
	}
}

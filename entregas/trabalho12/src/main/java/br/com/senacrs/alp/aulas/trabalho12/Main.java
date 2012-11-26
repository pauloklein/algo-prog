package br.com.senacrs.alp.aulas.trabalho12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class Main {

	public static void main(String[] args) {

		String nomeArquivoConfiguracao = null;
		String nomeArquivoRequisicao = null;
		String nomeArquivoSaida = null;
		
		Map<String, String> hashConfiguracao = new HashMap<String, String>();
		Map<String, String> hashRequisicao = new HashMap<String, String>();

		String caminhoRelativo = null;
		String caminhoAbsoluto = null;
		String caminhoRequisicao = null;

		//String host = null;
		String get = null;

		if (args == null) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		} else {
			if (args.length == 1) {
				nomeArquivoConfiguracao = args[0];
			} else if (args.length == 2) {
				nomeArquivoConfiguracao = args[0];
				nomeArquivoRequisicao = args[1];
			} else if (args.length == 3) {
				nomeArquivoConfiguracao = args[0];
				nomeArquivoRequisicao = args[1];
				nomeArquivoSaida = args[2];
			}
				
			if (nomeArquivoConfiguracao != null) {
				hashConfiguracao = validaArquivoConfiguracao(nomeArquivoConfiguracao);
				caminhoRelativo = hashConfiguracao.get("root_dir")
						.replace(".", System.getProperty("user.dir"))
						.replace('/', File.separatorChar);
				caminhoAbsoluto = caminhoRelativo.replace(".",
						System.getProperty("user.dir"));

				System.out.println(hashConfiguracao.get("port") + ":"
						+ caminhoAbsoluto);

				if (nomeArquivoRequisicao != null) {
					hashRequisicao = validaArquivoRequisicao(nomeArquivoRequisicao);
					//host = hashRequisicao.get("Host:");
					get = hashRequisicao.get("GET").split(" ")[0];

					caminhoRequisicao = caminhoAbsoluto + get;
					
					if (nomeArquivoSaida != null) {
						saidaRequisicao(caminhoRequisicao, nomeArquivoSaida);	
					}
				}
			}
		}
	}

	private static void saidaRequisicao(String caminhoRequisicao, String nomeArquivoSaida) {
		
		File arquivo = null;
		boolean arquivoEncontrado;
		Date data = null;
		SimpleDateFormat formatador = null;
		String dataFormatada = "";
		long tamanhoArquivo = 0;
		String conteudo = "";
		String mensagem = "";
		String[] saida = null;
		
		arquivo = validaArquivoWebserver(caminhoRequisicao);
		arquivoEncontrado =	verificaArquivoWebserver(arquivo);
		
		data = new Date();
		formatador = new SimpleDateFormat(
	            "EEE, dd MMM yyyy HH:mm:ss z",
	            Locale.getDefault());
		formatador.setTimeZone(TimeZone.getTimeZone("GMT"));
		dataFormatada = formatador.format(data);
		
		String[] arrayTextoSaida = null;
		
		if(arquivoEncontrado){
			mensagem = "202 OK";
			conteudo = capturaConteudoArquivoWebserver(arquivo);						
			tamanhoArquivo = capturaTamanhoArquivo(arquivo);
		} else {
			mensagem = "404 NotFound";
		}
		
		saida = new String[8];
		saida[0] = "HTTP/1.0 " + mensagem;
		saida[1] = "Date: " + dataFormatada;
		saida[2] = "Server: SenacAPL2012";
		saida[3] = "Content-Length: " + tamanhoArquivo;
		saida[4] = "Content-Type: text/html; charset=utf8";
		saida[5] = "Connection: Close";
		saida[6] = "";
		saida[7] = conteudo;
		
		escreveArquivoSaida(saida, nomeArquivoSaida);
		
		arrayTextoSaida = leArquivoSaida(nomeArquivoSaida);
		for (int i = 0; i < arrayTextoSaida.length; i++) {
			System.out.println(arrayTextoSaida[i]);
		}
	}

	private static void escreveArquivoSaida(String[] saida, String nomeArquivoSaida) {
		
		File arquivo = null;
		FileWriter writer = null;
		BufferedWriter output = null;
		String linha = "";
		
		arquivo = new File(nomeArquivoSaida);
		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
		
		try {
			writer = new FileWriter(arquivo);
			output = new BufferedWriter(writer);
			for (int i = 0; i < saida.length; i++) {
				linha = saida[i];
				if(i != (saida.length - 1)){
					linha += "\n";
				}
				output.write(linha);				
			}
			output.close();
		} catch (IOException e6) {
			System.out.println("ERRO");
			throw new IllegalArgumentException(e6);
		}			
	}

	private static String[] leArquivoSaida(String nomeArquivoSaida) {
		
		File arquivo = null;
		FileReader reader = null;
		BufferedReader input = null;

		LinkedList<String> listaTexto = new LinkedList<String>();
		String[] arrayTexto = null;

		String line = null;
		
		arquivo = new File(nomeArquivoSaida);
		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
		
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
			} catch (IOException e8) {
				throw new IllegalArgumentException(e8);
			}
		} catch (FileNotFoundException e7) {
			throw new IllegalArgumentException(e7);
		}

		arrayTexto = new String[listaTexto.size()];
		for (int i = 0; i < arrayTexto.length; i++) {
			arrayTexto[i] = listaTexto.get(i);
			System.out.println(arrayTexto[i]);
		}
		
		return arrayTexto;
	}
	
	private static File validaArquivoWebserver(String caminhoRequisicao) {

		File arquivo = null;
		String caminhoCompleto = null;

		arquivo = new File(caminhoRequisicao);
		caminhoCompleto = caminhoRequisicao;

		if (arquivo.isDirectory()) {
			caminhoCompleto = caminhoRequisicao + "index.html";
			arquivo = new File(caminhoCompleto);
		}

		return arquivo;			
	}

	private static boolean verificaArquivoWebserver(File arquivo) {
		
		boolean arquivoEncontrado;
		
		if (!arquivo.exists()) {
			System.out.println("404 NotFound");
			arquivoEncontrado = false;
		} else {
			arquivoEncontrado = true;
			System.out.println("200 OK " + arquivo.getAbsolutePath());
		}
		
		return arquivoEncontrado;
	}

	private static String capturaConteudoArquivoWebserver(File arquivo) {
		
		FileReader reader = null;
		String conteudo = "";
		
		try {
			reader = new FileReader(arquivo);

			BufferedReader input = null;
			String line = null;

			try {
				input = new BufferedReader(reader);
				line = input.readLine();

				while (line != null) {
					conteudo += line;
					line = input.readLine();
					if(line != null){
						conteudo += "\n";
					}
				}

				input.close();

			} catch (IOException e5) {
				System.out.println("ERRO");
				throw new IllegalArgumentException(e5);
			}							

		} catch (FileNotFoundException e4) {
			System.out.println("ERRO");
			throw new IllegalArgumentException(e4);
		}	
		
		return conteudo; 
	}
	
	private static long capturaTamanhoArquivo(File arquivo) {
		
		long tamanhoArquivo = 0;
		
		tamanhoArquivo = arquivo.length();		
		
		return tamanhoArquivo;
	}
	
	private static Map<String, String> validaArquivoConfiguracao(
			String nomeArquivo) {

		File arquivo = null;
		Map<String, String> hash = new HashMap<String, String>();
		FileReader reader = null;

		arquivo = new File(nomeArquivo);

		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		} else {
			try {
				reader = new FileReader(arquivo);

				hash = capturaHashConfiguracao(reader);

				if (!hash.containsKey("root_dir") || !hash.containsKey("port")) {
					System.out.println("ERRO");
					throw new IllegalArgumentException();
				}

				verificaRootDir(hash);
				verificaPort(hash);

			} catch (FileNotFoundException e1) {
				System.out.println("ERRO");
				throw new IllegalArgumentException(e1);
			}
		}

		return hash;
	}

	private static Map<String, String> capturaHashConfiguracao(FileReader reader) {

		String[] par = new String[2];
		Map<String, String> hash = new HashMap<String, String>();
		BufferedReader input = null;
		String line = null;

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
					input.close();
					System.out.println("ERRO");
					throw new IllegalArgumentException();
				}

				par = line.split("=");
				hash.put(par[0].trim(), par[1].trim());
				line = input.readLine();
			}

			input.close();

		} catch (IOException e2) {
			System.out.println("ERRO");
			throw new IllegalArgumentException(e2);
		}

		return hash;
	}

	private static void verificaRootDir(Map<String, String> hash) {

		String caminhoRelativo = null;
		File arquivo = null;

		caminhoRelativo = hash.get("root_dir")
				.replace(".", System.getProperty("user.dir"))
				.replace('/', File.separatorChar);

		arquivo = new File(caminhoRelativo);

		if (!arquivo.isDirectory() || !arquivo.exists()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
	}

	private static void verificaPort(Map<String, String> hash) {

		try {
			Integer.parseInt(hash.get("port"));
		} catch (Exception e3) {
			System.out.println("ERRO");
			throw new IllegalArgumentException(e3);
		}

		if (hash.get("port").length() > 5) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
	}

	private static Map<String, String> validaArquivoRequisicao(
			String nomeArquivo) {

		File arquivo = null;
		Map<String, String> hash = new HashMap<String, String>();
		FileReader reader = null;

		arquivo = new File(nomeArquivo);

		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		} else {
			try {
				reader = new FileReader(arquivo);

				hash = capturaHashRequisicao(reader);

				if (!hash.containsKey("GET") || !hash.containsKey("Host:")) {
					System.out.println("ERRO");
					throw new IllegalArgumentException();
				}

				validaGet(hash);
				verificaHost(hash);

			} catch (FileNotFoundException e1) {
				System.out.println("ERRO");
				throw new IllegalArgumentException(e1);
			}
		}

		return hash;
	}

	private static Map<String, String> capturaHashRequisicao(FileReader reader) {

		String[] parametros = null;
		Map<String, String> hash = new HashMap<String, String>();
		BufferedReader input = null;
		String line = null;

		try {
			input = new BufferedReader(reader);
			line = input.readLine();

			while (line != null) {
				line = line.trim();

				int total = 0;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ' ') {
						total++;
					}
				}

				parametros = line.split(" ");

				if (total == 2) {
					hash.put(parametros[0].trim(), parametros[1].trim() + " "
							+ parametros[2].trim());
				} else if (total == 1) {
					hash.put(parametros[0].trim(), parametros[1].trim());
				} else {
					input.close();
					System.out.println("ERRO");
					throw new IllegalArgumentException();
				}

				line = input.readLine();
			}
			input.close();

		} catch (IOException e2) {
			System.out.println("ERRO");
			throw new IllegalArgumentException(e2);
		}
		return hash;
	}

	private static void validaGet(Map<String, String> hash) {

		String[] get = new String[2];
		get = hash.get("GET").split(" ");

		String path = get[0];
		String protocol = get[1];

		verificaPath(path);
		verificaProtocol(protocol);
	}

	private static void verificaPath(String path) {

		int total = 0;
		int posicao = -1;
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '/') {
				if (i == 0) {
					posicao = i;
				}
				total++;
			}
		}
		if (posicao != 0 || total < 1) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
	}

	private static void verificaProtocol(String protocol) {

		if (!protocol.equalsIgnoreCase("http/1.1")) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
	}

	private static void verificaHost(Map<String, String> hash) {

		String host = hash.get("Host:");

		if (host.equalsIgnoreCase("")) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		}
	}

}
package br.com.senacrs.alp.aulas.trabalho12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		String nomeArquivoConfiguracao = null;
		String nomeArquivoRequisicao = null;
		
		Map<String, String> hashConfiguracao = new HashMap<String, String>();
		Map<String, String> hashRequisicao = new HashMap<String, String>();
		
		String caminhoRelativo = null;
		String caminhoAbsoluto = null;
		
		String host = null;
		String get = null;
		
		if(args == null){
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		} else {
			if(args.length == 1){
				nomeArquivoConfiguracao = args[0];
			} else if(args.length == 2){
				nomeArquivoConfiguracao = args[0];
				nomeArquivoRequisicao = args[1];
			}
			
			if(nomeArquivoConfiguracao != null){
				hashConfiguracao = validaArquivoConfiguracao(nomeArquivoConfiguracao);
				caminhoRelativo = hashConfiguracao.get("root_dir").replace(".",System.getProperty("user.dir")).replace('/', File.separatorChar);
				caminhoAbsoluto = caminhoRelativo.replace(".",System.getProperty("user.dir"));
				
				System.out.println(hashConfiguracao.get("port") + ":" + caminhoAbsoluto);
				
				if(nomeArquivoRequisicao != null){
					hashRequisicao = validaArquivoRequisicao(nomeArquivoRequisicao);
					host = hashRequisicao.get("Host:");
					get = hashRequisicao.get("GET").split(" ")[0];
					
					System.out.println(host + get);
				}				
			}			
		}			
	}

	private static Map<String, String> validaArquivoConfiguracao(String nomeArquivo) {
		
		File arquivo = null;
		
		String[] par = new String[2];
		Map<String, String> hash = new HashMap<String, String>();
		
		FileReader reader = null;
		BufferedReader input = null;
		String line = null;
		
		String caminhoRelativo = null;
		
		arquivo = new File(nomeArquivo);
		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
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
							input.close();
							System.out.println("ERRO");
							throw new IllegalArgumentException();
						}
						
						par = line.split("=");
						hash.put(par[0].trim(), par[1].trim());
						line = input.readLine();							
					}					
					input.close();
					
					if(!hash.containsKey("root_dir") || !hash.containsKey("port")){
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					} 
					
					try{
						Integer.parseInt(hash.get("port"));
					} catch(Exception e3){
						System.out.println("ERRO");
						throw new IllegalArgumentException(e3);
					}
					
					if(hash.get("port").length() > 5){
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					}
					
					caminhoRelativo = hash.get("root_dir").replace(".",System.getProperty("user.dir")).replace('/', File.separatorChar);
					arquivo = new File(caminhoRelativo);
					
					if(!arquivo.isDirectory() || !arquivo.exists()){
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					}
					
				} catch (IOException e2) {
					System.out.println("ERRO");
					throw new IllegalArgumentException(e2);
				}
			} catch (FileNotFoundException e1) {
				System.out.println("ERRO");
				throw new IllegalArgumentException(e1);
			}
		}
		
		return hash;
	}		
	
	private static Map<String, String> validaArquivoRequisicao(String nomeArquivo) {
		File arquivo = null;
		
		String[] parametros = null;
		Map<String, String> hash = new HashMap<String, String>();
		
		FileReader reader = null;
		BufferedReader input = null;
		String line = null;
		
		arquivo = new File(nomeArquivo);
		if (!arquivo.exists() || arquivo.isDirectory()) {
			System.out.println("ERRO");
			throw new IllegalArgumentException();
		} else {
			try {
				reader = new FileReader(arquivo);
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
						if (total == 2) {
							parametros = new String[3];
							parametros = line.split(" ");
							hash.put(parametros[0].trim(), parametros[1].trim() + " " + parametros[2].trim());
						} else if(total == 1) {
							parametros = new String[2];
							parametros = line.split(" ");
							hash.put(parametros[0].trim(), parametros[1].trim());
						} else {
							input.close();
							System.out.println("ERRO");
							throw new IllegalArgumentException();
						}
						
						line = input.readLine();							
					}
					input.close();
					
					if(!hash.containsKey("GET") || !hash.containsKey("Host:")){
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					} 
					
					String[] get = new String[2];
					get = hash.get("GET").split(" ");
					
					String path = get[0];
								
					int total = 0;
					int posicao = -1;
					for (int i = 0; i < path.length(); i++) {
						if (path.charAt(i) == '/') {
							if(i == 0){
								posicao = i;
							}
							total++;
						}
					}
					if (posicao != 0 || total < 1) {
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					}
					
					String protocol = get[1];
					
					if(!protocol.equalsIgnoreCase("http/1.1")){
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					}
					
					String host = hash.get("Host:");
					total = 0;
					for (int i = 0; i < host.length(); i++) {
						if (host.charAt(i) == '.') {
							total++;
						}
					}
					if (total < 2) {
						System.out.println("ERRO");
						throw new IllegalArgumentException();
					}
						
				} catch (IOException e2) {
					System.out.println("ERRO");
					throw new IllegalArgumentException(e2);
				}
			} catch (FileNotFoundException e1) {
				System.out.println("ERRO");
				throw new IllegalArgumentException(e1);
			}
		}
		
		return hash;
		
	}

}
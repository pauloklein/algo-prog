package br.com.senacrs.alp.aulas.trabalho12;

import static org.junit.Assert.fail;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class MainTest {

	private final static String DIRETORIO_CONFIGURACOES = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "configuracoes"
			+ File.separatorChar;
	private final static String NOME_ARQUIVO_CONFIG_CORRETO = "config_correto.prop";
	private final static String ARQUIVO_CONFIG_CORRETO = DIRETORIO_CONFIGURACOES
			+ NOME_ARQUIVO_CONFIG_CORRETO;
	private final static String NOME_ARQUIVO_CONFIG_INCORRETO = "config_incorreto.prop";
	private final static String ARQUIVO_CONFIG_INCORRETO = DIRETORIO_CONFIGURACOES
			+ NOME_ARQUIVO_CONFIG_INCORRETO;
	private final static String NOME_ARQUIVO_REQ_CORRETO_VALIDO = "req_correto_200.txt";
	private final static String ARQUIVO_REQ_CORRETO_VALIDO = DIRETORIO_CONFIGURACOES
			+ NOME_ARQUIVO_REQ_CORRETO_VALIDO;
	private final static String NOME_ARQUIVO_REQ_CORRETO_INVALIDO = "req_correto_404.txt";
	private final static String ARQUIVO_REQ_CORRETO_INVALIDO = DIRETORIO_CONFIGURACOES
			+ NOME_ARQUIVO_REQ_CORRETO_INVALIDO;
	private final static String NOME_ARQUIVO_REQ_INCORRETO = "req_incorreto.txt";
	private final static String ARQUIVO_REQ_INCORRETO = DIRETORIO_CONFIGURACOES
			+ NOME_ARQUIVO_REQ_INCORRETO;
	
	private final static String DIRETORIO_SAIDA = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "saída"
			+ File.separatorChar;
	private final static String NOME_ARQUIVO_SAIDA = "output.txt";
	private final static String ARQUIVO_SAIDA = DIRETORIO_SAIDA
			+ NOME_ARQUIVO_SAIDA;	
	
	@Test
	public void testeArquivosNull() {
		
		try {
			Main.main(null);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
	}
	
	@Test
	public void testeArquivoConfigDiretorio() {
		
		String[] args = new String[1];
		args[0] = DIRETORIO_CONFIGURACOES;
		
		try {
			Main.main(args);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
		
	}
	
	@Test
	public void testeArquivoConfigInexistente() {

		String[] args = new String[1];
		args[0] = "NaoExiste" + ".nao_existe";
		
		try {
			Main.main(args);
			fail("Deveria ter abortado");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}	
	
	@Test
	public void testeArquivoConfigIncorreto() {
		
		String[] args = new String[1];
		args[0] = ARQUIVO_CONFIG_INCORRETO;
		
		try {
			Main.main(args);
			fail("Deveria ter abortado.");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}		
	}	

	@Test
	public void testeArquivoConfigCorreto() {
		
		String[] args = new String[1];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}	
	
	@Test
	public void testeArquivoReqDiretorio() {
		
		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = DIRETORIO_CONFIGURACOES;
		
		try {
			Main.main(args);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
		
	}
	
	@Test
	public void testeArquivoReqInexistente() {

		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = "NaoExiste" + ".nao_existe";
		
		try {
			Main.main(args);
			fail("Deveria ter abortado");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}	
	
	@Test
	public void testeArquivoReqIncorreto() {
		
		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_INCORRETO;
		
		try {
			Main.main(args);
			fail("Deveria ter abortado.");
		} catch (Exception e) {
			Assert.assertTrue(true);	
		}		
	}	
	
	@Test
	public void testeArquivoReqCorretoInvalido() {
		
		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO_INVALIDO;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}
	}	
	
	@Test
	public void testeArquivoReqCorretoValido() {
		
		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO_VALIDO;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}	
	
	@Test
	public void testeArquivoSaidaDiretorio() {
		
		String[] args = new String[3];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = DIRETORIO_CONFIGURACOES;
		args[2] = DIRETORIO_SAIDA;
		
		try {
			Main.main(args);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
		
	}
	
	@Test
	public void testeArquivoSaidaInexistente() {

		String[] args = new String[3];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO_VALIDO;
		args[2] = "NaoExiste" + ".nao_existe";
		
		try {
			Main.main(args);
			fail("Deveria ter abortado");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}	
	
	@Test
	public void testeEscreverArquivoSaida() {
		
		String[] args = new String[3];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO_VALIDO;
		args[2] = ARQUIVO_SAIDA;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}	
	
	@Test
	public void testeLerArquivoSaida() {
		
		String[] args = new String[3];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO_VALIDO;
		args[2] = ARQUIVO_SAIDA;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}
}

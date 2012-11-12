package br.com.senacrs.alp.aulas.trabalho12;

import static org.junit.Assert.fail;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class MainTest {

	private final static String DIRETORIO = System
			.getProperty("user.dir")
			+ File.separatorChar
			+ "configuracoes"
			+ File.separatorChar;
	private final static String NOME_ARQUIVO_CONFIG_CORRETO = "config_correto.prop";
	private final static String ARQUIVO_CONFIG_CORRETO = DIRETORIO
			+ NOME_ARQUIVO_CONFIG_CORRETO;
	private final static String NOME_ARQUIVO_CONFIG_INCORRETO = "config_incorreto.prop";
	private final static String ARQUIVO_CONFIG_INCORRETO = DIRETORIO
			+ NOME_ARQUIVO_CONFIG_INCORRETO;
	private final static String NOME_ARQUIVO_REQ_CORRETO = "req_correto.txt";
	private final static String ARQUIVO_REQ_CORRETO = DIRETORIO
			+ NOME_ARQUIVO_REQ_CORRETO;
	private final static String NOME_ARQUIVO_REQ_INCORRETO = "req_incorreto.txt";
	private final static String ARQUIVO_REQ_INCORRETO = DIRETORIO
			+ NOME_ARQUIVO_REQ_INCORRETO;
	
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
		args[0] = DIRETORIO;
		
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
		args[1] = DIRETORIO;
		
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
	public void testeArquivoReqCorreto() {
		
		String[] args = new String[2];
		args[0] = ARQUIVO_CONFIG_CORRETO;
		args[1] = ARQUIVO_REQ_CORRETO;
		
		try {
			Main.main(args);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}	
	
}

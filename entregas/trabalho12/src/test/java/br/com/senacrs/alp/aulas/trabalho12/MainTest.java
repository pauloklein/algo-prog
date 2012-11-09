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
	private final static String NOME_ARQUIVO_CORRETO = "configuracoes_correto.txt";
	private final static String ARQUIVO_CORRETO = DIRETORIO
			+ NOME_ARQUIVO_CORRETO;
	private final static String NOME_ARQUIVO_INCORRETO = "configuracoes_incorreto.txt";
	private final static String ARQUIVO_INCORRETO = DIRETORIO
			+ NOME_ARQUIVO_INCORRETO;
	
	public void criarMain(String argumento) {
		
		String[] args = null;
		
		if(argumento != null){
			args = new String[1];
			args[0] = argumento;
		}
		
		Main.main(args);
	}
	
	@Test
	public void testeArquivoNull() {
		
		try {
			criarMain(null);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
	}
	
	@Test
	public void testeArquivoDiretorio() {
		
		try {
			criarMain(DIRETORIO);
			fail("Deveria ter abortado.");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}		
		
	}
	
	@Test
	public void testArquivoInexistente() {

		try {
			criarMain("NaoExiste" + ".nao_existe");
			fail("Deveria ter abortado");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}	
	
	@Test
	public void testArquivoIncorreto() {
		
		try {
			criarMain(ARQUIVO_INCORRETO);
			fail("Deveria ter abortado.");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}		
	}	

	@Test
	public void testArquivoCorreto() {
		
		try {
			criarMain(ARQUIVO_CORRETO);
			Assert.assertTrue(true);
		} catch (Exception e) {
			fail("Deveria ter passado.");			
		}		
	}	
}

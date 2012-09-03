package br.com.senacrs.alp.aulas;

import static org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MinhaListaImpTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMinhaListaImpVazia() {
		
		MinhaListaImp<String> obj = null;
		Nodo<String> primeiro = null;
		
		obj = new MinhaListaImp<String>();
		primeiro = obj.getInicio();
		Assert.assertNull(primeiro);		
	}
	
	@Test
	public void testMinhaListaImp() {
		
		MinhaListaImp<String> obj = null;
		String arg = null;
		
		arg = "valor valido";
		obj = new MinhaListaImp<String>(arg);
		Assert.assertNotNull(obj);		
		
	}
	
	@Test
	public void testMinhaListaImpNull() {
		
		MinhaListaImp<String> obj = null;
		
		try{
			obj = new MinhaListaImp<String>(null);
			fail();
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		Assert.assertNull(obj);		
	}

	@Test
	public void testGetInicio() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetInicio() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscar() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrefixar(){
		String esperado = null;
		String resultado = null;
		
		MinhaListaImp<String> obj = null;
		String arg = null;
		String prefixo = null;
		
		arg = "valor inicial";
		obj = new MinhaListaImp<String>(arg);
		
		prefixo = "valor para prefixar";
		obj.prefixar(prefixo);
		
		Nodo<String> primeiro = obj.getInicio();
		
		esperado = prefixo;
		resultado = primeiro.getValor();
		
		Assert.assertEquals(
				"O primeiro elemento deve possuir o valor " + esperado + "na lista" 
				+ ", valor retornando: " + resultado,
				esperado, resultado);			
	}
	
	@Test
	public void testPrefixarListaVazia(){
		String esperado = null;
		String resultado = null;
		
		MinhaListaImp<String> obj = null;
		String prefixo = null;
		
		obj = new MinhaListaImp<String>();
		
		prefixo = "valor para prefixar";
		obj.prefixar(prefixo);
		
		Nodo<String> primeiro = obj.getInicio();
		
		esperado = prefixo;
		resultado = primeiro.getValor();
		
		Assert.assertEquals(
				"O primeiro elemento deve possuir o valor " + esperado + "na lista" 
				+ ", valor retornando: " + resultado,
				esperado, resultado);			
	}
	
	@Test
	public void testSufixar() {
		String esperado = null;
		String resultado = null;
		
		MinhaListaImp<String> obj = null;
		String arg = null;
		String sufixo = null;
		
		Nodo<String> primeiro = null;
		Nodo<String> auxiliar = null;
		Nodo<String> ultimo = null;
		
		arg = "valor inicial";
		obj = new MinhaListaImp<String>(arg);
		
		sufixo = "valor para sufixar";
		obj.sufixar(sufixo);
		
		primeiro = obj.getInicio();
		
		auxiliar = primeiro;
		
		while(auxiliar.getProximo() != null){
			auxiliar = auxiliar.getProximo();			
		}		
		ultimo = auxiliar;
		
		esperado = sufixo;
		resultado = ultimo.getValor();
		
		Assert.assertEquals(
				"O último elemento deve possuir o valor " + esperado + "na lista" 
				+ ", valor retornando: " + resultado,
				esperado, resultado);			
	}
	
	@Test
	public void testSufixarListaVazia() {
		String esperado = null;
		String resultado = null;
		
		MinhaListaImp<String> obj = null;
		String sufixo = null;
		
		Nodo<String> primeiro = null;
		Nodo<String> auxiliar = null;
		Nodo<String> ultimo = null;
		
		obj = new MinhaListaImp<String>();
		
		sufixo = "valor para sufixar";
		obj.sufixar(sufixo);
		
		primeiro = obj.getInicio();
		
		auxiliar = primeiro;
		
		while(auxiliar.getProximo() != null){
			auxiliar = auxiliar.getProximo();			
		}		
		ultimo = auxiliar;
		
		esperado = sufixo;
		resultado = ultimo.getValor();
		
		Assert.assertEquals(
				"O último elemento deve possuir o valor " + esperado + "na lista" 
				+ ", valor retornando: " + resultado,
				esperado, resultado);			
	}
	
	@Test
	public void testInserir() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemover() {
		fail("Not yet implemented");		
	}
	
	@Test
	public void testTamanho() {
		int esperado = 0;
		int resultado = 0;
		int nro_elementos = 0;
		
		MinhaListaImp<String> obj = null;
		String arg = null;
		Nodo<String> nodo = null;		
		Nodo<String> auxiliar = null;
		
		arg = "valor inicial";
		obj = new MinhaListaImp<String>(arg);
		
		nodo = obj.getInicio();
				
		nro_elementos = 3;
		for(int i = 1; i < nro_elementos; i++){
			auxiliar = new Nodo<String>("valor " + i);
			nodo.setProximo(auxiliar);
			nodo = auxiliar;
		}
				
		esperado = nro_elementos;
		resultado = obj.tamanho();
		Assert.assertEquals(
				"Passando-se  " + nro_elementos + " elementos para a lista o tamanho deve ser " + String.valueOf(esperado) 
				+ ", valor retornando: " + String.valueOf(resultado),
				esperado, resultado);		
		
	}
	
	@Test
	public void testTamanhoListaVazia() {
		int esperado = 0;
		int resultado = 0;
		int nro_elementos = 0;
		
		MinhaListaImp<String> obj = null;
		Nodo<String> nodo = null;		
		Nodo<String> auxiliar = null;
		
		obj = new MinhaListaImp<String>();
		
		nodo = obj.getInicio();
				
		nro_elementos = 3;
		for(int i = 1; i <= nro_elementos; i++){
			auxiliar = new Nodo<String>("valor " + i);
			if(nodo == null){
				obj.setInicio(auxiliar);
			} else {
				nodo.setProximo(auxiliar);
			}
			nodo = auxiliar;
		}
				
		esperado = nro_elementos;
		resultado = obj.tamanho();
		Assert.assertEquals(
				"Passando-se  " + nro_elementos + " elementos para a lista o tamanho deve ser " + String.valueOf(esperado) 
				+ ", valor retornando: " + String.valueOf(resultado),
				esperado, resultado);		
		
	}
	
}

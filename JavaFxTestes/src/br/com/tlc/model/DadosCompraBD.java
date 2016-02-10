package br.com.tlc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DadosCompraBD {

	private static DadosCompraBD uniqueInstance;
	
	public DadosCompraBD(){
	}
	
	public static DadosCompraBD getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new DadosCompraBD();
		}
		return uniqueInstance;
	}
	
	public void salvarDados(DadosCompra dados){
		ObjectContainer dadosCompraBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../compra.db4o");
		dadosCompraBD.store(dados);
		dadosCompraBD.close();
	}
	
	public void updateItem(DadosCompra dados){
		ObjectContainer dadosCompraBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../compra.db4o");
		dadosCompraBD.store(dados);		
		dadosCompraBD.close();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<DadosCompra> recuperarItem(){
		ObjectContainer dadosCompraBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../compra.db4o");
		List itemsBanco = dadosCompraBD.query(DadosCompra.class);
		ArrayList<DadosCompra> lista = new ArrayList<DadosCompra>();
		for(Object i: itemsBanco){
			lista.add((DadosCompra)i);
		}
		dadosCompraBD.close();
		return lista;
	}
}

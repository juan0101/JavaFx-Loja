package br.com.tlc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class PessoaBD {
	
	private static PessoaBD uniqueInstance;
	
	public PessoaBD(){
	}
	
	public static PessoaBD getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new PessoaBD();
		}
		return uniqueInstance;
	}
	
	public void salvarPessoa(Pessoa pes){
		ObjectContainer pessoaBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../pessoas.db4o");
		boolean adicionar = true;
		List<Pessoa> pessoasBanco = pessoaBD.query(Pessoa.class);
		for(Pessoa i: pessoasBanco){
			if(i.matches(pes.getNome())){
				adicionar = false;
			}
		}
		if(adicionar){
			pessoaBD.store(pes);
			JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
		}
		pessoaBD.close();
	}
	
	public void updatePessoa(Pessoa pes){
		ObjectContainer pessoaBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../pessoas.db4o");
		List pessoasBanco = pessoaBD.query(Pessoa.class);
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		for(Object i: pessoasBanco){
			Pessoa pessoa = (Pessoa)i;
			if(pessoa.getCodigo() == pes.getCodigo()){
				pessoa.setDevendo(pes.getDevendo());
				pessoaBD.store(pessoa);
			}
		}
		pessoaBD.close();
//		
//		ObjectContainer pessoaBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../pessoas.db4o");
//		pessoaBD.store(pes);		
//		pessoaBD.close();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Pessoa> recuperarPessoa(){
		ObjectContainer pessoaBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../pessoas.db4o");
		List pessoasBanco = pessoaBD.query(Pessoa.class);
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		for(Object i: pessoasBanco){
			lista.add((Pessoa)i);
		}
		pessoaBD.close();
		return lista;
	}

}

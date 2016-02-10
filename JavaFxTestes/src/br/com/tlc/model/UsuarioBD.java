package br.com.tlc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class UsuarioBD {
	
	private static UsuarioBD uniqueInstance;
	
	public UsuarioBD(){
	}
	
	public static UsuarioBD getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new UsuarioBD();
		}
		return uniqueInstance;
	}
	
	public void salvarUsuario(Usuario user){
		ObjectContainer usuarioBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../usuarios.db4o");
		boolean adicionar = true;
		List<Usuario> usuariosBanco = usuarioBD.query(Usuario.class);
		for(Usuario i: usuariosBanco){
			if(i.matches(user.getNome())){
				adicionar = false;
			}
		}
		if(adicionar){
			usuarioBD.store(user);
			JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
		}
		usuarioBD.close();
	}
	
	public void updateUsuario(Usuario user){
		ObjectContainer usuarioBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../usuarios.db4o");
		usuarioBD.store(user);		
		usuarioBD.close();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Usuario> recuperarUsuario(){
		ObjectContainer usuarioBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../usuarios.db4o");
		List usuariosBanco = usuarioBD.query(Usuario.class);
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for(Object i: usuariosBanco){
			lista.add((Usuario)i);
		}
		usuarioBD.close();
		return lista;
	}

}

package br.com.tlc.model;

public class Usuario {
	
	private String nome;
	private int senha;
	
	public Usuario(String nome, int senha){
		this.nome = nome;
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public boolean matches(String nome){
		if(this.nome.equals(nome))
			return true;
		return false;
	}
	

}

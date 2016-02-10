package br.com.tlc.model;

public class Pessoa {

	private String nome;
	private String telefone;
	private double devendo;
	private int codigo;
	
	public Pessoa(){};
	
	public Pessoa(String nome, String telefone, int codigo){
		this.nome = nome;
		this.telefone = telefone;
		this.codigo = codigo;
		this.devendo = 0.0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public double getDevendo() {
		return devendo;
	}

	public void setDevendo(double devendo) {
		this.devendo = devendo;
	}

	public boolean matches(String nome){
		if(this.nome.equals(nome))
			return true;
		return false;
	}
	
}

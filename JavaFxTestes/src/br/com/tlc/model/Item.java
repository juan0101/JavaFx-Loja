package br.com.tlc.model;

public class Item {
	
	private String nome;
	private double valor;
	private int quantidade;
	private int codigo;
	
	public Item(String nome, double valor, int quantidade, int codigo){
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean matches(String nome){
		if(this.nome.equals(nome))
			return true;
		return false;
	}
	
}

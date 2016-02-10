package br.com.tlc.model;

public class DadosCompra {
	
	private int codigoPessoa;
	private String nomePessoa;
	private int codigoItem;
	private String nomeItem;
	private double valorCompra;
	
	public DadosCompra(int codigoPessoa, String nomePessoa, int codigoItem, String nomeItem, double valorCompra){
		this.codigoPessoa = codigoPessoa;
		this.nomePessoa = nomePessoa;
		this.codigoItem = codigoItem;
		this.nomeItem = nomeItem;
		this.valorCompra = valorCompra;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public int getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(int codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

}

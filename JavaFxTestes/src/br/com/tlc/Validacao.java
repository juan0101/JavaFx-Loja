package br.com.tlc;

public class Validacao {

	public static boolean isNumero(String valor){
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String especiais = "!@#$%&*;:+=";
		for(int i = 0; i<valor.length(); i++){
			if(minusculas.contains(valor.substring(i,i+1))){
				System.out.println("Digite um número.");
				return false;
			}else if(maiusculas.contains(valor.subSequence(i, i+1))){
				System.out.println("Digite um número.");
				return false;
			}else if(especiais.contains(valor.subSequence(i, i+1))){
				System.out.println("Digite um número.");
				return false;
			}
		}
		return true;
	}
	
}

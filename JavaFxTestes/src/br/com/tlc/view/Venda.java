package br.com.tlc.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.tlc.Validacao;
import br.com.tlc.model.DadosCompra;
import br.com.tlc.model.DadosCompraBD;
import br.com.tlc.model.Item;
import br.com.tlc.model.ItemBD;
import br.com.tlc.model.Pessoa;
import br.com.tlc.model.PessoaBD;

public class Venda {
	
	PessoaBD bancoPessoa;
	ItemBD bancoItem;
	
	public void compra(){
		boolean comprando = true;
		
		//Pegando a lista de pessoas do banco
		String codigoPessoa = JOptionPane.showInputDialog(null,"Insira o codigo da pessoa/cliente: ","Venda",JOptionPane.PLAIN_MESSAGE);
		if(codigoPessoa != null){
			/*Valida o codigo digitado pela pessoa
			 * e tenta encontrar o cliente em uma lista do banco
			 */
			if(Validacao.isNumero(codigoPessoa)){
				bancoPessoa = PessoaBD.getInstance();
				List<Pessoa> listaPessoasBanco = new LinkedList<Pessoa>();
				listaPessoasBanco = bancoPessoa.recuperarPessoa();
				boolean achouCliente = false;
				Pessoa clienteComprando = new Pessoa();
				for(Pessoa pes: listaPessoasBanco){
					if(pes.getCodigo() == Integer.parseInt(codigoPessoa)){
						clienteComprando = pes;
						achouCliente = true;
					}
				}
				
				/*Verifica se o cliente foi encontrado */
				if(achouCliente){
					/*Se o cliente for encontrado começa o processo de compra
					 * recuperando do banco os items cadastrados.
					 */
					//Pegando a lista de items do banco
					bancoItem = ItemBD.getInstance();
					List<Item> listaItemsBanco = new LinkedList<Item>();
					listaItemsBanco = bancoItem.recuperarItem();
					
					DadosCompraBD dadosCompra = DadosCompraBD.getInstance();
					
					/*Para as compras o programa entra em um loop 
					 *para ser digitado os codigos dos produtos que vao ser vendidos.
					 */
					while(comprando){
						String codigoItem = JOptionPane.showInputDialog(null,"Insira o codigo do produto: ","Venda",JOptionPane.PLAIN_MESSAGE);
						if(codigoItem != null && Validacao.isNumero(codigoItem)){
							boolean achouItem = false;
							for(Item i: listaItemsBanco){
								if(i.getCodigo() == Integer.parseInt(codigoItem)){
									achouItem = true;
									String quantidadeItem = JOptionPane.showInputDialog(null,"Quantidade vendida: ","Venda",JOptionPane.PLAIN_MESSAGE);
									if(quantidadeItem != null && Validacao.isNumero(quantidadeItem)){
										//Calcula e apresenta o preço da compra
										double preco = calcularPreco(i,Integer.parseInt(quantidadeItem));
										JOptionPane.showMessageDialog(null, "Preço da compra: " + preco,"Venda",JOptionPane.PLAIN_MESSAGE);
										//Diminui a quantidade do item e faz update no banco
										i.setQuantidade(i.getQuantidade() - Integer.parseInt(quantidadeItem));
										bancoItem.updateItem(i);
										
										//Salva os dados da compra
										DadosCompra dados = new DadosCompra(clienteComprando.getCodigo(),clienteComprando.getNome(),i.getCodigo(),i.getNome(),preco);
										dadosCompra.salvarDados(dados);
										
										//Verifica se o pagamento é a vista
										int pagamento = JOptionPane.showConfirmDialog(null, "Pagamento a vista?", "Pagamento", JOptionPane.YES_NO_OPTION);
								        if (pagamento == JOptionPane.YES_OPTION) {
								          JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso");
								          comprando = false;
								        }
								        else {
								        	clienteComprando.setDevendo(clienteComprando.getDevendo()+preco);
								        	bancoPessoa.updatePessoa(clienteComprando);
								            JOptionPane.showMessageDialog(null, "Saldo contabilizado na conta do cliente","Venda",JOptionPane.PLAIN_MESSAGE);
								            comprando = false;
								        }
									}else{
										if(quantidadeItem == null){
											JOptionPane.showMessageDialog(null,"Operação Cancelada.","Venda",JOptionPane.PLAIN_MESSAGE);
										}else{
											JOptionPane.showMessageDialog(null,"A quantidade deve ser um número.","Venda",JOptionPane.PLAIN_MESSAGE);
										}
										comprando = false;
									}
								}
							}
							if(!achouItem){
								JOptionPane.showMessageDialog(null,"Item não encontrado.","Venda",JOptionPane.PLAIN_MESSAGE);
								comprando = false;
							}
							
						}else{
							if(codigoItem == null){
								JOptionPane.showMessageDialog(null,"Operação cancelada.","Venda",JOptionPane.PLAIN_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null,"Código incorreto. Digite apenas números.","Venda",JOptionPane.PLAIN_MESSAGE);
							}
							comprando = false;
						}
					}
				}else{
					/*Caso o cliente digitado não tenha sido encontrado
					 * é apresentado uma mensagem.
					 * E o programa retorna ao menu.
					 */
					JOptionPane.showMessageDialog(null,"O cliente digitado não foi encontrado.","Venda",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}else{
			JOptionPane.showMessageDialog(null,"Operação Cancelada.","Venda",JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	public double calcularPreco(Item i, int quantidade){
		double total = i.getValor() * quantidade;
		return total;
	}

}
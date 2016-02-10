package br.com.tlc.view;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.tlc.Validacao;
import br.com.tlc.model.Pessoa;
import br.com.tlc.model.PessoaBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Receber extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private Label lblPessoaNome;
	private Label lblPessoaTelefone;
	private Label lblPessoaCodigo;
	private Label lblPessoaDevendo;
	
	private Label lblPagamento;
	private TextField textPagamento;
	
	private Button pagar;
	private Button voltar;
	
	UsuarioMenu usuarioMenu;
	Pessoa pessoa;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String codigoPessoa = JOptionPane.showInputDialog(null,"Insira o código da Pessoa/Cliente: ","Receber",JOptionPane.PLAIN_MESSAGE);
		if(Validacao.isNumero(codigoPessoa)){
			window = primaryStage;
			window.setTitle("Receber");
			
			List<Pessoa> listaBancoPessoa = recuperaPessoa();
			pessoa = encontrarPessoa(listaBancoPessoa,codigoPessoa);
			if(pessoa != null){
				
				//Criando um grid pane
				GridPane grid = new GridPane();
				grid.setPadding(new Insets(10, 10, 10, 10));
				grid.setVgap(8);
				grid.setHgap(10);
				
				//Label e campo texto para o nome da pessoa
				lblPessoaNome = new Label("Nome: " + pessoa.getNome());
				grid.setConstraints(lblPessoaNome, 1, 0);
				
				//Label e campo texto para o telefone da pessoa
				lblPessoaTelefone = new Label("Telefone: "+pessoa.getTelefone());
				grid.setConstraints(lblPessoaTelefone, 1, 1);
				
				//Label e campo texto para o codigo da pessoa
				lblPessoaCodigo = new Label("Codigo: "+pessoa.getCodigo());
				grid.setConstraints(lblPessoaCodigo, 1, 2);
				
				//Label e campo texto para o codigo da pessoa
				lblPessoaDevendo = new Label("Valor a ser acertado: R$ "+pessoa.getDevendo());
				grid.setConstraints(lblPessoaDevendo, 1, 3);
				
				//label e campo texto para o pagamento da pessoa
				lblPagamento = new Label("Valor pago: R$ ");
				grid.setConstraints(lblPagamento, 1, 5);
				textPagamento = new TextField();
				textPagamento.setPromptText("Pagamento");
				grid.setConstraints(textPagamento,2,5);
				
				//Botao que envia o cadastro
				pagar = new Button("Pagar");
				pagar.setOnAction(e -> pagar());
				grid.setConstraints(pagar, 1, 7);
				
				//Botao para limpar os campos
				voltar = new Button("Voltar");
				voltar.setOnAction(e -> voltarMenu());
				grid.setConstraints(voltar, 2, 7);
				
				grid.getChildren().addAll(lblPessoaCodigo,lblPessoaNome,lblPessoaTelefone,lblPessoaDevendo,
						textPagamento,lblPagamento,pagar,voltar);
				
				Scene scene = new Scene(grid, 400, 200);
				window.setScene(scene);
				window.show();
				
			}else{
				JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma pessoa com o código digitado.","Receber",JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,"Digite um valor válido para o codigo da Pessoa.","Receber",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void voltarMenu(){
		usuarioMenu = new UsuarioMenu();
		try {
			usuarioMenu.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public List<Pessoa> recuperaPessoa(){
		PessoaBD bancoPessoa = PessoaBD.getInstance();
		List<Pessoa> listaPessoasBanco = bancoPessoa.recuperarPessoa();
		return listaPessoasBanco;
	}
	
	public void updateValorPago(Pessoa pes){
		PessoaBD bancoPessoa = PessoaBD.getInstance();
		bancoPessoa.updatePessoa(pes);
		JOptionPane.showMessageDialog(null,"Valor pago com sucesso.","Receber",JOptionPane.PLAIN_MESSAGE);
	}
	
	public Pessoa encontrarPessoa(List<Pessoa> listaPessoa, String codigoPessoa){
		for(Pessoa pes: listaPessoa){
			if(pes.getCodigo() == Integer.parseInt(codigoPessoa)){
				return pes;
			}
		}
		return null;
	}
	
	public void pagar(){
		if(textPagamento.getText() != null){
			if(Validacao.isNumero(textPagamento.getText())){
				double valorPago = Double.parseDouble(textPagamento.getText());
				if(valorPago >= pessoa.getDevendo()){
					double troco = valorPago - pessoa.getDevendo();
					pessoa.setDevendo(0.0);
					updateValorPago(pessoa);
					JOptionPane.showMessageDialog(null,"Troco: R$ "+troco,"Receber",JOptionPane.PLAIN_MESSAGE);
				}else{
					double valorDevendo = pessoa.getDevendo() - valorPago;
					pessoa.setDevendo(valorDevendo);
					updateValorPago(pessoa);
					JOptionPane.showMessageDialog(null,"Valor ainda a ser acertado: R$ "+pessoa.getDevendo(),"Receber",JOptionPane.PLAIN_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null,"Digite um valor válido no campo de pagamento.","Receber",JOptionPane.PLAIN_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,"Campo de pagamento esta vazio.","Receber",JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}

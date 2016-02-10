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

public class CadastroPessoa extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private Label lblPessoaNome;
	private Label lblPessoaTelefone;
	private Label lblPessoaCodigo;
	private TextField textPessoaNome;
	private TextField textPessoaTelefone;
	private TextField textPessoaCodigo;
	
	private Button cadastrar;
	private Button limpar;
	private Button voltar;
	
	AdminMenu adminMenu;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Cadastro de Pessoa");
		
		//Criando um grid pane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Label e campo texto para o nome da pessoa
		lblPessoaNome = new Label("Nome: ");
		grid.setConstraints(lblPessoaNome, 1, 0);
		textPessoaNome = new TextField();
		textPessoaNome.setPromptText("nome");
		grid.setConstraints(textPessoaNome, 2, 0);
		
		//Label e campo texto para o telefone da pessoa
		lblPessoaTelefone = new Label("Telefone: ");
		grid.setConstraints(lblPessoaTelefone, 1, 2);
		textPessoaTelefone = new TextField();
		textPessoaTelefone.setPromptText("(xx)x xxxx-xxxx");
		grid.setConstraints(textPessoaTelefone, 2, 2);
		
		//Label e campo texto para o codigo da pessoa
		lblPessoaCodigo = new Label("Codigo: ");
		grid.setConstraints(lblPessoaCodigo, 1, 4);
		textPessoaCodigo = new TextField();
		textPessoaCodigo.setPromptText("codigo unico");
		grid.setConstraints(textPessoaCodigo, 2, 4);
		
		//Botao que envia o cadastro
		cadastrar = new Button("Cadastrar");
		cadastrar.setOnAction(e -> cadastrarPessoa());
		grid.setConstraints(cadastrar, 1, 6);
		
		//Botao para limpar os campos
		limpar = new Button("Limpar");
		limpar.setOnAction(e -> limparCampos());
		grid.setConstraints(limpar, 2, 6);
		
		//Botao para limpar os campos
		voltar = new Button("Voltar");
		voltar.setOnAction(e -> voltarMenu());
		grid.setConstraints(voltar, 3, 6);
		
		grid.getChildren().addAll(lblPessoaCodigo,lblPessoaNome,lblPessoaTelefone,
				textPessoaCodigo,textPessoaNome,textPessoaTelefone,cadastrar,limpar,voltar);
		
		Scene scene = new Scene(grid, 400, 200);
		window.setScene(scene);
		window.show();
		
	}
	
	public void limparCampos(){
		textPessoaNome.setText("");
		textPessoaTelefone.setText("");
		textPessoaCodigo.setText("");
	}
	
	public void voltarMenu(){
		adminMenu = new AdminMenu();
		try {
			adminMenu.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void cadastrarPessoa(){
		if(!textPessoaCodigo.getText().equals("") && textPessoaCodigo.getText() != null &&
				!textPessoaNome.getText().equals("") && textPessoaNome.getText() != null &&
				!textPessoaTelefone.getText().equals("") && textPessoaTelefone.getText() != null){
			if(Validacao.isNumero(textPessoaCodigo.getText()) && Validacao.isNumero(textPessoaTelefone.getText())){
				PessoaBD bancoPessoa = PessoaBD.getInstance();
				List<Pessoa> listaPessoasBanco = bancoPessoa.recuperarPessoa();
				boolean achouCodigo = false;
				int codigoPessoa = Integer.parseInt(textPessoaCodigo.getText());
				String nome = textPessoaNome.getText();
				String telefone = textPessoaTelefone.getText();
				for(Pessoa pes: listaPessoasBanco){
					if(pes.getCodigo() == codigoPessoa || pes.getNome().equals(textPessoaNome.getText())){
						achouCodigo = true;
						break;
					}
				}	
				if(achouCodigo){
					JOptionPane.showMessageDialog(null,"Já existe uma pessoa com esse codigo/nome.");
				}else{
					try{
						Pessoa pes = new Pessoa(nome,telefone,codigoPessoa);
						bancoPessoa.salvarPessoa(pes);						
					}catch(Exception e){
						System.out.println("Erro na hora de salvar pessoa.");
						System.out.println(e);
					}
				}
			}else{
				JOptionPane.showMessageDialog(null,"Digite numeros no campo codigo e telefone");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Preencha todos os campos.");
		}
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}

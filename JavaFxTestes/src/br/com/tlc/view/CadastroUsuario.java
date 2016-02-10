package br.com.tlc.view;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.tlc.Validacao;
import br.com.tlc.model.Usuario;
import br.com.tlc.model.UsuarioBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CadastroUsuario extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	
	private Label lblUserName;
	private TextField textUserName;
	
	private Label lblPasswordUser;
	private PasswordField textPasswordUser;
	
	private Button cadastrar;
	private Button clean;
	private Button voltar;
	
	AdminMenu adminMenu;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Cadastro de Usuário");
		
		//Criando um grid pane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Label e campo texto para o nome do usuario
		lblUserName = new Label("Usuário: ");
		grid.setConstraints(lblUserName, 1, 0);
		
		textUserName = new TextField();
		textUserName.setPromptText("nome do usuario");
		grid.setConstraints(textUserName, 2, 0);
		
		//Label e campo de senha
		lblPasswordUser = new Label("Senha: ");
		grid.setConstraints(lblPasswordUser, 1, 3);
		
		textPasswordUser = new PasswordField();
		textPasswordUser.setPromptText("senha do usuario");
		grid.setConstraints(textPasswordUser, 2, 3);
		
		//Botao para enviar cadastro
		cadastrar = new Button("Cadastrar");
		cadastrar.setOnAction(e -> cadastrarUsuario());
		grid.setConstraints(cadastrar, 1, 5);
		
		//Botao para limpar os campos
		clean = new Button("Limpar");
		clean.setOnAction(e -> limparCampos());
		grid.setConstraints(clean, 2, 5);
		
		//Botao para limpar os campos
		voltar = new Button("Voltar");
		voltar.setOnAction(e -> voltarMenu());
		grid.setConstraints(voltar, 3, 5);
		
		grid.getChildren().addAll(lblUserName,lblPasswordUser,textUserName,textPasswordUser,cadastrar,clean,voltar);
		
		Scene scene = new Scene(grid, 400, 200);
		window.setScene(scene);
		window.show();
		
	}
	
	public void limparCampos(){
		textUserName.setText("");
		textPasswordUser.setText("");
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
	
	public void cadastrarUsuario(){
		if(!textUserName.getText().equals("") && textUserName.getText() != null &&
				!textPasswordUser.getText().equals("") && textPasswordUser.getText() != null){
			if(Validacao.isNumero(textPasswordUser.getText())){
				String nome = textUserName.getText();
				int senha = Integer.parseInt(textPasswordUser.getText());
				UsuarioBD bancoUsuario = UsuarioBD.getInstance();
				List<Usuario> usuariosBanco = bancoUsuario.recuperarUsuario();
				boolean achouUsuario = false;
				for(Usuario usu: usuariosBanco){
					if(usu.getNome().equals(nome)){
						achouUsuario = true;
						break;
					}
				}
				if(achouUsuario){
					JOptionPane.showMessageDialog(null,"Esse usuario ja existe");
				}else{
					Usuario usuario = new Usuario(nome,senha);
					bancoUsuario.salvarUsuario(usuario);
				}
			}else{
				JOptionPane.showMessageDialog(null,"Digite numeros no campo codigo e telefone");
			}			
		}else{
			JOptionPane.showMessageDialog(null,"Preencha todos os campos.");
		}
	}

	@Override
	public void handle(ActionEvent arg0) {}

}

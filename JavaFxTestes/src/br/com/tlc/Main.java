package br.com.tlc;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.tlc.model.Usuario;
import br.com.tlc.model.UsuarioBD;
import br.com.tlc.view.AdminMenu;
import br.com.tlc.view.UsuarioMenu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{

	Stage window;
	AdminMenu adminMenu;
	UsuarioMenu usuarioMenu;
	UsuarioBD usuarioBD;
	
	Button button;
	
	Label labelUserName;
	TextField textUserName;
	
	Label labelPassword;
	PasswordField textPassword;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("TLC JOVENS UNIDOS PELA FÉ");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Botao para se logar
		button = new Button("Entrar");
		button.setOnAction(this);
		grid.setConstraints(button, 1, 5);
		
		//Label e texto de Username
		labelUserName = new Label("Usuário: ");
		grid.setConstraints(labelUserName, 1, 0);
		
		textUserName = new TextField();
		textUserName.setPromptText("usuario");
		grid.setConstraints(textUserName, 2, 0);
		
		//Label e texto de Password
		labelPassword = new Label("Senha");
		grid.setConstraints(labelPassword, 1, 3);
		
		textPassword = new PasswordField();
		textPassword.setPromptText("senha");
		grid.setConstraints(textPassword, 2, 3);
		
		grid.getChildren().addAll(button,labelUserName,textUserName,labelPassword,textPassword);
		
		Scene scene = new Scene(grid, 400, 200);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == button){
			if(!textUserName.getText().equals("") && textUserName.getText() != null &&
					!textPassword.getText().equals("") && textPassword.getText() != null){
				if(textUserName.getText().equals("Admin") && 
						textPassword.getText().equals("11")){
					adminMenu = new AdminMenu();
					try {
						adminMenu.start(window);
					} catch (Exception e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}
				}else{
					boolean achou = false;
					usuarioBD = UsuarioBD.getInstance();
					List<Usuario> usuarios = usuarioBD.recuperarUsuario();
					for(Usuario usu: usuarios){
						if(usu.getNome().equals(textUserName.getText()) &&
								usu.getSenha() == Integer.parseInt(textPassword.getText())){
							achou = true;
							break;
						}
					}
					if(achou){
						usuarioMenu = new UsuarioMenu();
						try {
							usuarioMenu.start(window);
						} catch (Exception e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null,"Login inválido.");
					}
				}
			}else{
				JOptionPane.showMessageDialog(null,"Usuario ou senha vazio. Verifique os campos");
			}
		}
	}

}

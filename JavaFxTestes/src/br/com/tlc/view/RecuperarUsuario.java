package br.com.tlc.view;

import java.util.List;

import br.com.tlc.model.Usuario;
import br.com.tlc.model.UsuarioBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RecuperarUsuario extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Button recuperar;
	GridPane grid = new GridPane();
	Scene scene;
	Label nome;
	Label senha;
	private boolean recup = false;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("TLC JOVENS UNIDOS PELA FÉ");
		
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Botao para se logar
		recuperar = new Button("Recuperar");
		recuperar.setOnAction(e -> recuperarUsuarios());
		grid.setConstraints(recuperar, 1, 0);
		
		grid.getChildren().addAll(recuperar);
		
		scene = new Scene(grid, 400, 400);
		window.setScene(scene);
		window.show();
	}
	
	public void recuperarUsuarios(){
		if(!recup){
			UsuarioBD banco = UsuarioBD.getInstance();
			List<Usuario> usuariosBanco = banco.recuperarUsuario();
			int coluna = 1;
			int linha = 3;
			for(Usuario usu: usuariosBanco){
				nome = new Label(usu.getNome());
				grid.setConstraints(nome, coluna, linha);
				
				senha = new Label(usu.getSenha()+"");
				grid.setConstraints(senha, coluna, linha+1);
				
				grid.getChildren().addAll(nome,senha);
				
				linha = linha + 2;
			}
		}
		recup = true;
	}

	@Override
	public void handle(ActionEvent arg0) {}

}

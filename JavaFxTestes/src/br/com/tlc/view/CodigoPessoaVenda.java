package br.com.tlc.view;

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

public class CodigoPessoaVenda extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private Label lblCodigo;
	private TextField textCodigo;
	
	private Button continuar;
	private Button voltar;
	
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
		lblCodigo = new Label("Insira o código da Pessoa/Cliente : ");
		grid.setConstraints(lblCodigo, 1, 0);
				
		textCodigo = new TextField();
		textCodigo.setPromptText("codigo da pessoa");
		grid.setConstraints(textCodigo, 1, 1);
		
		//Botao para enviar cadastro
		continuar = new Button("Continuar");
		continuar.setOnAction(e -> System.out.println("continuar"));
		grid.setConstraints(continuar, 1, 3);
				
		//Botao para limpar os campos
		voltar = new Button("Voltar");
		voltar.setOnAction(e -> System.out.println("Voltar"));
		grid.setConstraints(voltar, 2, 3);
		
		grid.getChildren().addAll(lblCodigo,textCodigo,continuar,voltar);
		
		Scene scene = new Scene(grid, 400, 200);
		window.setScene(scene);
		window.show();		
	}
	
	@Override
	public void handle(ActionEvent arg0) {}


}

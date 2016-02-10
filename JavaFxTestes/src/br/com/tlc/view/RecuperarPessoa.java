package br.com.tlc.view;

import java.util.List;

import br.com.tlc.model.Pessoa;
import br.com.tlc.model.PessoaBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RecuperarPessoa extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Button recuperar;
	GridPane grid = new GridPane();
	Scene scene;
	Label nome;
	Label codigo;
	Label telefone;
	Label devendo;
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
		recuperar.setOnAction(e -> recuperarPessoas());
		grid.setConstraints(recuperar, 1, 0);
		
		grid.getChildren().addAll(recuperar);
		
		scene = new Scene(grid, 400, 400);
		window.setScene(scene);
		window.show();
	}
	
	public void recuperarPessoas(){
		if(!recup){
			PessoaBD banco = PessoaBD.getInstance();
			List<Pessoa> pessoasBanco = banco.recuperarPessoa();
			int coluna = 1;
			int linha = 3;
			for(Pessoa pes: pessoasBanco){
				codigo = new Label(pes.getCodigo()+"");
				grid.setConstraints(codigo, coluna, linha);
				
				nome = new Label(pes.getNome());
				grid.setConstraints(nome, coluna, linha+1);
				
				telefone = new Label(pes.getTelefone());
				grid.setConstraints(telefone, coluna, linha+2);
				
				devendo = new Label(pes.getDevendo()+"");
				grid.setConstraints(devendo, coluna, linha+3);
				
				grid.getChildren().addAll(nome,codigo,telefone,devendo);
				
				linha = linha + 5;
			}
		}
		recup = true;
	}

	@Override
	public void handle(ActionEvent arg0) {}

}
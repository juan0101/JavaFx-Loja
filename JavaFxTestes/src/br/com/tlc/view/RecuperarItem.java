package br.com.tlc.view;

import java.util.List;

import br.com.tlc.model.Item;
import br.com.tlc.model.ItemBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RecuperarItem extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Button recuperar;
	GridPane grid = new GridPane();
	Scene scene;
	Label codigo;
	Label nome;
	Label quantidade;
	Label valor;
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
		recuperar.setOnAction(e -> recuperarItems());
		grid.setConstraints(recuperar, 1, 0);
		
		grid.getChildren().addAll(recuperar);
		
		scene = new Scene(grid, 400, 400);
		window.setScene(scene);
		window.show();
	}
	
	public void recuperarItems(){
		if(!recup){
			ItemBD banco = ItemBD.getInstance();
			List<Item> itemsBanco = banco.recuperarItem();
			int coluna = 1;
			int linha = 3;
			for(Item item: itemsBanco){
				codigo = new Label(item.getCodigo()+"");
				grid.setConstraints(codigo, coluna, linha);
				
				nome = new Label(item.getNome());
				grid.setConstraints(nome, coluna, linha+1);
				
				quantidade = new Label(item.getQuantidade()+"");
				grid.setConstraints(quantidade, coluna, linha+2);
				
				valor = new Label(item.getValor()+"");
				grid.setConstraints(valor, coluna, linha+3);
				
				grid.getChildren().addAll(codigo,nome,quantidade,valor);
				
				linha = linha + 5;
			}
		}
		recup = true;
	}

	@Override
	public void handle(ActionEvent arg0) {}

}

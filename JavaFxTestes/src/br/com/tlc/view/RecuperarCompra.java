package br.com.tlc.view;

import java.util.List;

import br.com.tlc.model.DadosCompra;
import br.com.tlc.model.DadosCompraBD;
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

public class RecuperarCompra extends Application implements EventHandler<ActionEvent>{

	Stage window;
	Button recuperar;
	GridPane grid = new GridPane();
	Scene scene;
	Label lblNomePessoa;
	Label lblNomeItem;
	Label codigoPessoa;
	Label codigoItem;
	Label valorCompra;
	
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
			DadosCompraBD banco = DadosCompraBD.getInstance();
			List<DadosCompra> compraBanco = banco.recuperarItem();
			int coluna = 1;
			int linha = 3;
			for(DadosCompra compra: compraBanco){
				lblNomePessoa = new Label(compra.getNomePessoa());
				grid.setConstraints(lblNomePessoa, coluna, linha);
				
				codigoPessoa = new Label(compra.getCodigoPessoa()+"");
				grid.setConstraints(codigoPessoa, coluna+1, linha);
				
				lblNomeItem = new Label(compra.getNomeItem());
				grid.setConstraints(lblNomeItem, coluna+2, linha);
				
				codigoItem = new Label(compra.getCodigoItem()+"");
				grid.setConstraints(codigoItem, coluna+3, linha);
				
				valorCompra = new Label(compra.getValorCompra()+"");
				grid.setConstraints(valorCompra, coluna+4, linha);
				
				grid.getChildren().addAll(lblNomePessoa,codigoPessoa,lblNomeItem,codigoItem,valorCompra);
				
				linha = linha + 1;
			}
		}
		recup = true;
	}

	@Override
	public void handle(ActionEvent arg0) {}

}
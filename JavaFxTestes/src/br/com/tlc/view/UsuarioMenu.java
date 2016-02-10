package br.com.tlc.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UsuarioMenu extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	BorderPane layout;
	Venda venda = new Venda();
	Receber receber;
	RecuperarCompra recuperarCompra;
	RelatorioDevedor relatorioDevedor;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("TLC Jovens Unidos pela Fé");
		
		//Menu
		Menu iniciar = new Menu("Iniciar");
		Menu relatorio = new Menu("Relatório");
		
		//Items do menu
		MenuItem vender = new MenuItem("Vender");
		vender.setOnAction(e -> venda.compra());
		iniciar.getItems().add(vender);
		
		iniciar.getItems().add(new SeparatorMenuItem());
		
		MenuItem receber = new MenuItem("Receber");
		receber.setOnAction(e -> receber());
		iniciar.getItems().add(receber);
		
		//Items do menu RELATORIO
		MenuItem relatorioDevedor = new MenuItem("Devedores");
		relatorioDevedor.setOnAction(e -> relatorioDevedor());
		relatorio.getItems().add(relatorioDevedor);
		
		MenuItem relatorioCompra = new MenuItem("Compras");
		relatorioCompra.setOnAction(e -> relatorioCompra());
		relatorio.getItems().add(relatorioCompra);
		
		//MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(iniciar,relatorio);
		
		layout = new BorderPane();
		layout.setTop(menuBar);
		
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
		
	}
	
	public void relatorioDevedor(){
		relatorioDevedor = new RelatorioDevedor();
		try {
			relatorioDevedor.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void relatorioCompra(){
		recuperarCompra = new RecuperarCompra();
		try {
			recuperarCompra.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void receber(){
		receber = new Receber();
		try {
			receber.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent e) {}
}

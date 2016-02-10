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

public class AdminMenu extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private BorderPane layout;
	private CadastroItem cadastroItem;
	private CadastroPessoa cadastroPessoa;
	private CadastroUsuario cadastroUsuario;
	private RecuperarUsuario recuperarUsuario;
	private RecuperarItem recuperarItem;
	private RecuperarPessoa recuperarPessoa;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("TLC Jovens Unidos pela Fé");
		
		//Menu
		Menu cadastrar = new Menu("Cadastrar");
		Menu recuperar = new Menu("Recuperar");
		
		//Items do menu CADASTRAR
		MenuItem usuario = new MenuItem("Usuario");
		usuario.setOnAction(e -> cadUsu());
		cadastrar.getItems().add(usuario);
		
		cadastrar.getItems().add(new SeparatorMenuItem());
		
		MenuItem pessoa = new MenuItem("Pessoa/Cliente");
		pessoa.setOnAction(e -> cadPes());
		cadastrar.getItems().add(pessoa);
		
		cadastrar.getItems().add(new SeparatorMenuItem());
		
		MenuItem item = new MenuItem("Item");
		item.setOnAction(e -> cadItem());
		cadastrar.getItems().add(item);
		
		//ITEMS DO MENU RECUPERAR
		MenuItem recupUsuario = new MenuItem("Usuarios");
		recupUsuario.setOnAction(e -> recUsu());
		recuperar.getItems().add(recupUsuario);
		
		MenuItem recupPes = new MenuItem("Pessoas/Clientes");
		recupPes.setOnAction(e -> recPes());
		recuperar.getItems().add(recupPes);
		
		MenuItem recupItems = new MenuItem("Items");
		recupItems.setOnAction(e -> recItem());
		recuperar.getItems().add(recupItems);
		
		//MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(cadastrar,recuperar);
		
		layout = new BorderPane();
		layout.setTop(menuBar);
		
		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
		
	}

	@Override
	public void handle(ActionEvent e) {}
	
	public void cadUsu(){
		cadastroUsuario = new CadastroUsuario();
		try {
			cadastroUsuario.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void cadItem(){
		cadastroItem = new CadastroItem();
		try {
			cadastroItem.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void cadPes(){
		cadastroPessoa = new CadastroPessoa();
		try {
			cadastroPessoa.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void recUsu(){
		recuperarUsuario = new RecuperarUsuario();
		try {
			recuperarUsuario.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void recItem(){
		recuperarItem = new RecuperarItem();
		try {
			recuperarItem.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void recPes(){
		recuperarPessoa = new RecuperarPessoa();
		try {
			recuperarPessoa.start(window);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}

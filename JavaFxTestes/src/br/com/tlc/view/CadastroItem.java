package br.com.tlc.view;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.tlc.Validacao;
import br.com.tlc.model.Item;
import br.com.tlc.model.ItemBD;
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

public class CadastroItem extends Application implements EventHandler<ActionEvent>{

	private Stage window;
	private Label lblItemCodigo;
	private TextField textItemCodigo;
	private Label lblItemName;
	private TextField textItemName;
	private Label lblItemValue;
	private TextField textItemValue;
	private Label lblItemQuantidade;
	private TextField textItemQuantidade;
	
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
		window.setTitle("Cadastro de Item");
		
		//Criando um grid pane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		//Label e campo texto para o nome do item
		lblItemCodigo = new Label("Codigo do item: ");
		grid.setConstraints(lblItemCodigo, 1, 0);
		textItemCodigo = new TextField();
		textItemCodigo.setPromptText("codigo");
		grid.setConstraints(textItemCodigo, 2, 0);
		
		//Label e campo texto para o nome do item
		lblItemName = new Label("Nome do item: ");
		grid.setConstraints(lblItemName, 1, 2);
		textItemName = new TextField();
		textItemName.setPromptText("nome");
		grid.setConstraints(textItemName, 2, 2);
		
		//Label e campo texto para o valor do item
		lblItemValue = new Label("Valor do item: ");
		grid.setConstraints(lblItemValue, 1, 4);
		textItemValue = new TextField();
		textItemValue.setPromptText("valor unidade");
		grid.setConstraints(textItemValue, 2, 4);
		
		//Label e campo texto para a quantidade do item
		lblItemQuantidade = new Label("Quantidade do item em estoque: ");
		grid.setConstraints(lblItemQuantidade, 1, 6);
		textItemQuantidade = new TextField();
		textItemQuantidade.setPromptText("quantidade total");
		grid.setConstraints(textItemQuantidade, 2, 6);
		
		//Botao de enviar o cadastro
		cadastrar = new Button("Cadastrar");
		cadastrar.setOnAction(e -> cadastrarItem());
		grid.setConstraints(cadastrar, 1, 8);
		
		//Botao de limpar campos
		limpar = new Button("Limpar");
		limpar.setOnAction(e -> limparCampos());
		grid.setConstraints(limpar, 2, 8);
		
		//Botao de limpar campos
		voltar = new Button("Voltar");
		voltar.setOnAction(e -> voltarMenu());
		grid.setConstraints(voltar, 3, 8);
		
		grid.getChildren().addAll(lblItemCodigo,lblItemName,lblItemQuantidade,lblItemValue,
				textItemCodigo,textItemName,textItemQuantidade,textItemValue,cadastrar,limpar,voltar);
		
		Scene scene = new Scene(grid, 500, 400);
		window.setScene(scene);
		window.show();
		
	}
	
	public void limparCampos(){
		textItemName.setText("");
		textItemValue.setText("");
		textItemQuantidade.setText("");
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
	
	public void cadastrarItem(){
		if(!textItemName.getText().equals("") && textItemName.getText() != null &&
				!textItemValue.getText().equals("") && textItemValue.getText() != null &&
				!textItemQuantidade.getText().equals("") && textItemQuantidade.getText() != null &&
				!textItemCodigo.getText().equals("") && textItemCodigo.getText() != null){
			if(Validacao.isNumero(textItemCodigo.getText()) && Validacao.isNumero(textItemQuantidade.getText()) && 
					Validacao.isNumero(textItemValue.getText())){
				ItemBD bancoItem = ItemBD.getInstance();
				String nome = textItemName.getText();
				int codigo = Integer.parseInt(textItemCodigo.getText());
				double valor = Double.parseDouble(textItemValue.getText());
				int quantidade = Integer.parseInt(textItemQuantidade.getText());
				List<Item> itemsBanco = bancoItem.recuperarItem();
				boolean achouItem = false;
				for(Item i: itemsBanco){
					if(i.getNome().equals(nome)|| i.getCodigo() == codigo){
						achouItem = true;
						break;
					}
				}
				if(achouItem){
					JOptionPane.showMessageDialog(null,"Ja existe um item com esse codigo/nome.");
				}else{
					Item item = new Item(nome,valor,quantidade,codigo);
					try{
						bancoItem.salvarItem(item);	
					}catch(Exception e){
						System.out.println("Erro na hora de salvar");
						System.out.println(e);
					}					
				}
							
			}else{
				JOptionPane.showMessageDialog(null,"Digite um número para a quantidade e para o valor.");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Preencha todos os campos.");
		}
	}

	@Override
	public void handle(ActionEvent event) {}


}

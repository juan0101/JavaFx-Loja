package br.com.tlc.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ItemBD {

	private static ItemBD uniqueInstance;
	
	public ItemBD(){
	}
	
	public static ItemBD getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ItemBD();
		}
		return uniqueInstance;
	}
	
	public void salvarItem(Item item){
		ObjectContainer itemsBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../items.db4o");
		boolean adicionar = true;
		List<Item> itemsBanco = itemsBD.query(Item.class);
		for(Item i: itemsBanco){
			if(i.matches(item.getNome())){
				adicionar = false;
			}
		}
		if(adicionar){
			itemsBD.store(item);
			JOptionPane.showMessageDialog(null, "Adicionado com Sucesso");
		}
		itemsBD.close();
	}
	
	public void updateItem(Item item){
		ObjectContainer itemsBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../items.db4o");
		List itemsBanco = itemsBD.query(Item.class);
		ArrayList<Item> lista = new ArrayList<Item>();
		for(Object i: itemsBanco){
			Item it = (Item)i;
			if(it.getCodigo() == item.getCodigo()){
				it.setQuantidade(item.getQuantidade());
				itemsBD.store(it);
			}
		}
		itemsBD.close();
		
//		ObjectContainer itemsBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../items.db4o");
//		itemsBD.store(item);		
//		itemsBD.close();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<Item> recuperarItem(){
		ObjectContainer itemsBD = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "../items.db4o");
		List itemsBanco = itemsBD.query(Item.class);
		ArrayList<Item> lista = new ArrayList<Item>();
		for(Object i: itemsBanco){
			lista.add((Item)i);
		}
		itemsBD.close();
		return lista;
	}
	
}

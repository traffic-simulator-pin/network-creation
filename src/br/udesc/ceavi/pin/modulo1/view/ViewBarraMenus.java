package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenus;

/**
 *
 * @author Drew
 */
class ViewBarraMenus extends JMenuBar {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3268215150324937540L;
	// CAMPOS
	private List<JMenu> menus;
	
	/**
	 * Cria uma nova Barra de Menus vazia.
	 */
	public ViewBarraMenus () {
		this.menus = new ArrayList<JMenu>();
	}
	
	/**
	 * Adiciona itens a um menu, cria um novo menu caso ainda não exista.
	 * @param nome - nome do menu.
	 * @param itens - Array de itens a serem adicionados ao menu.
	 */
	public void adicionaMenu(String nome, String[] itens) {
		JMenu novoMenu = getMenu(nome);
		if(novoMenu == null) {
			novoMenu = new JMenu(nome);
			novoMenu.setName(nome);
			menus.add(novoMenu);
		}
		for (int i = 0; i < itens.length; i++) {
			JMenuItem item = new JMenuItem(itens[i]);
			item.setName(itens[i]);
			novoMenu.add(item);
		}
		this.add(novoMenu);
	}
	
	/**
	 * Busca um menu caso exista.
	 * @param nome - nome do menu.
	 * @return o menu, null caso não exista.
	 */
	public JMenu getMenu(String nome) {
		for (JMenu menu : menus) {
			if(menu.getName().equals(nome)) {
				return menu;
			}
		}
		return null;
	}
	
	/**
	 * Adiciona um listener um item de um menu.
	 * @param menuNome - nome do menu.
	 * @param itemNome - nome do item.
	 * @param listener - listener a ser adicionado.
	 */
	public void adicionaListener(String menuNome, String itemNome, ViewListenersMenus listener) {
		JMenu menu = getMenu(menuNome);
		if(menu != null) {
			Component[] menuItens = menu.getMenuComponents();
			for (Component item : menuItens) {
				if(item.getName().equals(itemNome) && item instanceof JMenuItem) {
					JMenuItem menuItem = (JMenuItem) item;
					menuItem.addActionListener(listener);
					break;
				}
			}
		}
	}
	
}

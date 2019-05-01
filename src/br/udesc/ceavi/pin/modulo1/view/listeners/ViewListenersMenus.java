package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Drew
 */
public class ViewListenersMenus implements ActionListener {

    protected ViewJanelaSistema janela; // Janela a ser adicionada.
    protected ViewListenersFrame listeners;
    /**     
     * Cria um listener para abrir a janela especificada com o tipo padrão.
     * @param janela - janela a ser aberta ao clique.
     */
    public ViewListenersMenus(ViewJanelaSistema janela, ViewListenersFrame listeners) {
        this.janela = janela;
    }
    
    public ViewListenersMenus(){}

    @Override
    public void actionPerformed(ActionEvent e) {
       janela.abreJanela();
    }
    
}

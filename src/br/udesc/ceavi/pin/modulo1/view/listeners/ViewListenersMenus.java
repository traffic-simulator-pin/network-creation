package br.udesc.ceavi.pin.modulo1.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;

/**
 *
 * @author Drew
 */
public class ViewListenersMenus implements ActionListener {

    protected ViewJanelaSistema janela; // Janela a ser adicionada.

    /**
     * Cria um listener para abrir a janela especificada com o tipo padrão.
     *
     * @param janela - janela a ser aberta ao clique.
     */
    public ViewListenersMenus(ViewJanelaSistema janela) {
        this.janela = janela;
    }

    public ViewListenersMenus() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            janela = janela.getClass().newInstance();
            janela.abrirJanela();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

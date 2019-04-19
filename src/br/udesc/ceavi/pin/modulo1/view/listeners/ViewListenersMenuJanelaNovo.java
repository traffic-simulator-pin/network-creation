/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenerJanelaNovo;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;

/**
 *
 * @author Drew
 */
public class ViewListenersMenuJanelaNovo extends ViewListenersMenus {

    public ViewListenersMenuJanelaNovo(ViewJanelaSistema janela, ViewListenersFrame listeners) {
        super(janela, listeners);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       janela.abreJanela();
       new ViewListenerJanelaNovo(janela);
    }
}

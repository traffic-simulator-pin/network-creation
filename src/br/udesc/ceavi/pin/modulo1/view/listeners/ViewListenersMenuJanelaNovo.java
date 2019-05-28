package br.udesc.ceavi.pin.modulo1.view.listeners;

import java.awt.event.ActionEvent;

import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameJanelaNovo;

/**
 *
 * @author Drew
 */
public class ViewListenersMenuJanelaNovo extends ViewListenersMenus {

    @Override
    public void actionPerformed(ActionEvent e) {
        new ViewFrameJanelaNovo();
    }
}

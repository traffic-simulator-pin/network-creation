package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameJanelaNovo;
import java.awt.event.ActionEvent;

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

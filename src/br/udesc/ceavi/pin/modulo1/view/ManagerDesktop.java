package br.udesc.ceavi.pin.modulo1.view;

import java.util.List;

import javax.swing.DefaultDesktopManager;
import javax.swing.JInternalFrame;

/**
 *
 * @author Drew
 */
public class ManagerDesktop extends DefaultDesktopManager {
	
	private static final long serialVersionUID = -8224427593367195334L;

	@Override
    public void activateFrame(JInternalFrame f) {
        super.activateFrame(f);
        
         //To change body of generated methods, choose Tools | Templates.
        deixarFramesNafrente();
        
    }
    
    private void deixarFramesNafrente() {
        ControllerDesktop c = ControllerDesktop.getInstance();
        List<ViewJanelaSistema> janelas = c.getJanelas();
        
        for(ViewJanelaSistema v : janelas) {
            c.getViewPrincipal().getAreaDesktop().setComponentZOrder(v, 0);
        }
            
    }
}

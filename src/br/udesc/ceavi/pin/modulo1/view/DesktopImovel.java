package br.udesc.ceavi.pin.modulo1.view;

import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;

/**
 *
 * @author Drew
 */
public class DesktopImovel extends DefaultDesktopManager {
    
    public void dragFrame( JComponent f, int x, int y ) {
        if ( !( f instanceof TelaComBotoes ) ) {
            super.dragFrame( f, x, y );
        } 
    }
}

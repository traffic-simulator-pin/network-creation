package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import javax.swing.JFileChooser;

/**
 *
 * @author Drew
 */
public class ViewFrameJanelaSalvar extends ViewFrameModulo1Padrao {
    
    public ViewFrameJanelaSalvar() {
        super("Salvar");
    }
    
     @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaComponente("chooser", "Salvar Arquivo", new JFileChooser());
        return novo;
    }

}

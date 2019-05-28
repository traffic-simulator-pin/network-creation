package br.udesc.ceavi.pin.modulo1.view.frame;

import javax.swing.JFileChooser;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;

/**
 *
 * @author Drew
 */
public class ViewFrameJanelaSalvar extends ViewFrameModulo1Padrao {
    
    public ViewFrameJanelaSalvar() {
    }
    
     @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaComponente("chooser", "Salvar Arquivo", new JFileChooser());
        return novo;
    }

}

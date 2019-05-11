package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import javax.swing.JSlider;

/**
 *
 * @author Drew
 */
public class ViewFramePrecisao extends ViewFrameModulo1Padrao {
    
    public ViewFramePrecisao() {
        super("ViewFramePrecisao");
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaComponente("precisao", "precisão", new JSlider());
        
        return novo;
    }
    
    
}

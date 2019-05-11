package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;

/**
 *
 * @author Drew
 */
public class ViewFrameDadosEdge extends ViewFrameModulo1Padrao {
    
    public ViewFrameDadosEdge() {
        super("ViewFrameDadosEdge");
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
    }
    
    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaCampo("nome", "Nome");
        return novo;
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        acoesPanel.adicionaAcao("Salvar");
        acoesPanel.adicionaAcao("Alterar Type");

    }
}

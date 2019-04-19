package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;

/**
 *
 * @author Drew
 */
public class ViewFrameJanelaNovo extends ViewFrameModulo1Padrao {

    public ViewFrameJanelaNovo() {
        super("Novo");
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaCampo("campo1", "Campo1");
        return novo;
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        acoesPanel.adicionaAcao("Novo");
    }
}

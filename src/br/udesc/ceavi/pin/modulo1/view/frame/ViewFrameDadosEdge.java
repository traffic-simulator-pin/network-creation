package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author Drew
 */
public class ViewFrameDadosEdge extends ViewFrameModulo1Padrao {

    private JButton btnFechar;

    public ViewFrameDadosEdge() {
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
    public void fechaJanela() {
        super.fechaJanela();
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        acoesPanel.adicionaAcao("Alterar Type");
    }

    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        this.dispose();
    }
}

package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 *
 * @author Drew
 */
public class ViewFrameType extends ViewFrameModulo1Padrao {

    private FuntionCreateType funtionCreateType;
    private JButton btnSalvar;
    private JButton btnFechar;
    private JComboBox jcbFluxo;
    private ViewFrameEdge viewFrameEdge;

    public ViewFrameType(FuntionCreateType funtionCreateType, ViewFrameEdge viewFrameEdge) {
        this.funtionCreateType = funtionCreateType;
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
        this.viewFrameEdge = viewFrameEdge;
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();

        novo.adicionaCampo("nomeEdge", "Nome dos edge");
        novo.adicionaCampo("nfaixas", "Número Faixas");
        novo.adicionaCampo("maoUnica", "Mão única");
        novo.adicionaCampo("velocidade", "Velocidade");
        jcbFluxo = new JComboBox(new String[]{"Fluxo Unicio", "Fluxo Duplo"});
        novo.adicionaComponente("", "", jcbFluxo);
        return novo;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
        viewFrameEdge.abrirJanela();
        viewFrameEdge.toFront();
        viewFrameEdge.moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        btnSalvar = acoesPanel.adicionaAcao("Salvar");
        btnFechar = acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> this.distruirInstancia());
    }

    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        ControllerDesktop.getInstance().removerInstanciaJanela(viewFrameEdge);
        viewFrameEdge.dispose();
        this.dispose();
    }
}

package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author Drew
 */
public class ViewFrameType extends ViewFrameModulo1Padrao {

    private FuntionCreateType funtionCreateType;
    private JButton btnSalvar;

    public ViewFrameType(FuntionCreateType funtionCreateType) {
        super("Type");
        this.funtionCreateType = funtionCreateType;
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();

        novo.adicionaCampo("nomeEdge", "Nome dos edge");
        novo.adicionaCampo("nfaixas", "Número Faixas");
        novo.adicionaCampo("maoUnica", "Mão única");
        novo.adicionaCampo("velocidade", "Velocidade");
        novo.adicionaCampo("comprimento", "Comprimento");

        return novo;
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        btnSalvar = acoesPanel.adicionaAcao("Salvar");
        initListener();
    }

    private void initListener() {
    }
}

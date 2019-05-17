package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameModulo1PadraoConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 *
 * @author Drew
 */
public class ViewFrameEdge extends ViewFrameModulo1PadraoConsulta {

    private FuntionCreateType createType;

    public ViewFrameEdge(FuntionCreateType createType) {
        super("ViewFrameEdge");
        this.setSize(new Dimension(226, 200));
        this.setLocation(775, 10);
        this.moveToFront();
        this.createType = createType;
    }

    @Override
    protected JScrollPane criaAreaManutencao() {

        ViewPainelConsulta painel = (ViewPainelConsulta) super.criaAreaManutencao();
        painel.adicionaCampo("ID", "ID", 50);
        painel.adicionaCampo("Nome", "Nome", 50);

        return painel;
    }

    @Override
    protected void criaAreaAcoes() {
        this.acoesPanel = new ViewPanelAcoes();
        this.acoesPanel.adicionaAcao("Alterar Type");
        this.acoesPanel.adicionaAcao("Add Demanda");

    }

    public void desabilitaBotaoAlterarType() {
        JButton botao = this.acoesPanel.getBotao("Alterar Type");
        botao.setEnabled(false);
    }

    public void habilitaBotaoAlterarType() {
        JButton botao = this.acoesPanel.getBotao("Alterar Type");
        botao.setEnabled(true);
    }

    public void desabilitaBotaoAddDemanda() {
        JButton botao = this.acoesPanel.getBotao("Add Demanda");
        botao.setEnabled(false);
    }

    public void habilitaBotaoAddDemanda() {
        JButton botao = this.acoesPanel.getBotao("Add Demanda");
        botao.setEnabled(true);
    }

    public void atualizaListaEdge(List<Egde> listaEdge) {
        ViewPainelConsulta v = getPainelConsulta();

        v.limpaValores();
        v.setListaEdge(listaEdge);
        for (Egde edg : createType.getSeletion()) {
            try {
                v.setValores(edg);
            } catch (Exception ex) {
                Logger.getLogger(ViewFrameEdge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

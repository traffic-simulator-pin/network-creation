package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

/**
 *
 * @author Drew
 */
public class ViewFrameDemandaTelaLateral extends ViewFrameModulo1PadraoConsulta {

    private List<Demanda> nodos;

    public ViewFrameDemandaTelaLateral() {
        this.setSize(new Dimension(226, 200));
        this.setLocation(775, 250);
        this.moveToFront();
    }

    @Override
    protected JScrollPane criaAreaManutencao() {

        ViewPainelConsulta painel = (ViewPainelConsulta) super.criaAreaManutencao();
        painel.setBorder(BorderFactory.createTitledBorder("Demanda"));
        painel.adicionaCampo("ID", "ID", 50);
        painel.adicionaCampo("demanda", "Nome", 50);

        return painel;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        this.acoesPanel = new ViewPanelAcoes();
    }

    public void atualizaListaDemanda(List<Demanda> lista) {
        ViewPainelConsulta v = getPainelConsulta();

        if (lista == null) {
            v.limpaValores();
            return;
        }

        this.nodos = lista;

        v.limpaValores();
        for (Demanda edg : lista) {
            try {
                v.setValores(edg);
            } catch (Exception ex) {
                Logger.getLogger(ViewFrameEdge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<Demanda> getDemandas() {
        return nodos;
    }
}

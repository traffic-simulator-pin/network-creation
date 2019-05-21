package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

/**
 *
 * @author Drew
 */
public class ViewFrameEdge extends ViewFrameModulo1PadraoConsulta {

    private FuntionCreateType createType;
    private List<Egde> egds;
    
    
    public ViewFrameEdge(FuntionCreateType createType) {
        this.setSize(new Dimension(226, 200));
        this.setLocation(600, 10);
        this.createType = createType;
        this.setName("edge");
    }

    @Override
    protected JScrollPane criaAreaManutencao() {

        ViewPainelConsulta painel = (ViewPainelConsulta) super.criaAreaManutencao();
        painel.adicionaCampo("ID", "Id", 50);
        painel.adicionaCampo("Nome", "Nome", 50);

        return painel;
    }

    @Override
    protected void criaAreaAcoes() {
        this.acoesPanel = new ViewPanelAcoes();
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
    }
    

    public void atualizaListaEdge() {
        ViewPainelConsulta v = getPainelConsulta();

        createType.getSeletion().forEach((edg) -> {
            try {
                v.setValores(edg);
            } catch (Exception ex) {
                Logger.getLogger(ViewFrameEdge.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    
     public void atualizaListaEdge(List<Egde> listaEdge) {
        ViewPainelConsulta v = getPainelConsulta();
        
        if(listaEdge == null) {
            v.limpaValores();
            return;
        }
        
        this.egds = listaEdge;
        
        v.limpaValores();
        for(Egde edg : listaEdge) {
            try {
                v.setValores(edg);
            } catch (Exception ex) {
                Logger.getLogger(ViewFrameEdge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public List<Egde> getEgds() {
        return egds;
    }
    
    public void atualizaTela() {
        repaint();
    }

}

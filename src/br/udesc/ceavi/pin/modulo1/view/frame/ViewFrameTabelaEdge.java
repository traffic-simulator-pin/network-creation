package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

/**
 *
 * @author Drew
 */
public class ViewFrameTabelaEdge extends ViewFrameModulo1PadraoConsulta {

    private FuntionCreateType createType;
    private List<Egde> egds;
    
    
    public ViewFrameTabelaEdge(FuntionCreateType createType) {
        setMySide(226, 200);
        this.setLocation(600, 10);
        this.createType = createType;
        createType.setTabela(this);
        this.setName("edge");
    }

    @Override
    protected JScrollPane criaAreaManutencao() {

        ViewPainelConsulta painel = (ViewPainelConsulta) super.criaAreaManutencao();
        painel.adicionaCampo("ID", "Id", 50);
        painel.adicionaCampo("Nome", "Nome", 50);
        painel.adicionaCampo("Width", "Width", 50);

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
                Logger.getLogger(ViewFrameTabelaEdge.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ViewFrameTabelaEdge.class.getName()).log(Level.SEVERE, null, ex);
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

package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import javax.swing.JComponent;

/**
 *
 * @author Drew
 */
public class ViewFrameDemanda extends ViewFrameModulo1Padrao {
    
    Egde nodoOrigem;
    Egde nodoDestino;
    
    public ViewFrameDemanda(String nome) {
        super(nome);
        this.setLocation(300, 10);
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
       
        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaCampo("nodoOrigem" , "Nodo Origem");
        novo.adicionaCampo("nodoDestino", "Nodo Destino");
        novo.adicionaCampo("demanda"    , "Demanda");
        
        return novo;
    }
    
     protected void criaAreaAcoes() {
         super.criaAreaAcoes();
         this.acoesPanel.adicionaAcao("Salvar");
    }

    public void setNodoOrigem(Egde edg) {
        this.nodoOrigem = edg;
    }

    public Egde getNodoOrigem() {
        return nodoOrigem;
    }

    public void setNodoDestino(Egde edg) {
       this.nodoDestino = edg;
    }
    
    public Egde getNodoDestino() {
        return nodoDestino;
    }
    
    public void atualizaTelaDemanda() {
        
        if(this.getNodoOrigem() != null) {
             JComponent[] campo1 = this.getAreaManutencao().getCampo("nodoOrigem");
//             ((JTextField) campo1[1]).setText(this.getNodoOrigem().getID() + "");
        }
        
        if(this.getNodoDestino() != null) {
             JComponent[] campo1 = this.getAreaManutencao().getCampo("nodoDestino");
//             ((JTextField) campo1[1]).setText(this.getNodoDestino().getID() + "");
        }
        
        this.repaint();
    }
}

package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class ViewFrameType extends ViewFrameModulo1Padrao {
    
    private Type modelo;
    private List<Egde> edges;
    
    public ViewFrameType() {
        super("Type");
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
    }
    
    public ViewFrameType(List<Egde> edges) {
        super("Type");
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
        this.edges = edges;
    }
    
    public ViewFrameType(Type m) {
        super("Type");
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
        this.modelo = m;
    }
    
    public ViewFrameType(Type m, List<Egde> edges) {
        super("Type");
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
        this.modelo = m;
        this.edges = edges;
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        
        novo.adicionaCampo("nomeEdge", "Nome dos edge");
        novo.adicionaCampo("nfaixas"   , "Número Faixas");
        novo.adicionaCampo("maoUnica"  , "Mão única");
        novo.adicionaCampo("velocidade", "Velocidade");
        novo.adicionaCampo("comprimento", "Comprimento");
        
        return novo;
    }

    @Override   
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        acoesPanel.adicionaAcao("Salvar");
    }
    
    
    public void preencheCampos() {
        
        if(modelo == null) {
            return;
        }
        
        
        JComponent[] campo1 = this.getAreaManutencao().getCampo("nfaixas");
        JComponent[] campo2 = this.getAreaManutencao().getCampo("maoUnica");
        JComponent[] campo3 = this.getAreaManutencao().getCampo("velocidade");
        JComponent[] campo4 = this.getAreaManutencao().getCampo("comprimento");
        JComponent[] campo5 = this.getAreaManutencao().getCampo("nomeEdge");
        
        
//        ((JTextField) campo1[1]).setText(modelo.getNumLanes() +"");
//        ((JTextField) campo2[1]).setText(modelo.isOneway()+ "");
//        ((JTextField) campo3[1]).setText(modelo.getSpeed()+ "");
//        ((JTextField) campo4[1]).setText(modelo.getWidh() + "");
//        ((JTextField) campo5[1]).setText(modelo.getListDeEgdeQuePertenco().get(0).getNome()+ "");
    }
    
    public void setModelo(Type oModel) {
        this.modelo = oModel;
    }
    
    public void setListaEgdes(List<Egde> lista) {
        this.edges = lista;
    }
    
    public List<Egde> getListaEgdes() {
        return edges;
    }
}

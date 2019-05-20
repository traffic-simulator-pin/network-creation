package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenerFrameType;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

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
        this.setName("type");
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
        btnSalvar.addActionListener(new ViewActionListenerSalvar());
    }

    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        ControllerDesktop.getInstance().removerInstanciaJanela(viewFrameEdge);
        viewFrameEdge.dispose();
        this.dispose();
    }
    
    
     private class ViewActionListenerSalvar implements ActionListener {
         
        private Type modelo;
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerDesktop d = ControllerDesktop.getInstance();
            ViewFrameEdge viewEdg = null;
            
            for(ViewJanelaSistema v : d.getJanelas()) {
                if(v instanceof ViewFrameEdge) {
                    viewEdg = (ViewFrameEdge)v;
                    break;
                }
            
            }
            
//            ViewFrameModulo1Padrao janela = (ViewFrameModulo1Padrao)this;
            JComponent[] campo1 = getAreaManutencao().getCampo("nfaixas");
            JComponent[] campo2 = getAreaManutencao().getCampo("maoUnica");
            JComponent[] campo3 = getAreaManutencao().getCampo("velocidade");
            JComponent[] campo4 = getAreaManutencao().getCampo("comprimento");
            JComponent[] campo5 = getAreaManutencao().getCampo("nomeEdge");

            if(modelo == null) {
                modelo = new Type();
            }
            modelo.setNumLanes(Integer.parseInt(((JTextField) campo1[1]).getText() +""));
            
            if(((JTextField) campo2[1]).getText().equals("true")) {
                modelo.setOneway(true);
            } else {
                modelo.setOneway(false);
            }
            modelo.setSpeed(Float.parseFloat(((JTextField) campo3[1]).getText()));
            
            List<Egde> edges = viewEdg.getEgds();
            
            for(Egde ed : edges) {
                ed.setType(modelo, ((JTextField) campo5[1]).getText());
            }
            
            modelo.setListDeEgdeQuePertenco(edges);
            viewEdg.atualizaListaEdge(edges);
        }
    }
}
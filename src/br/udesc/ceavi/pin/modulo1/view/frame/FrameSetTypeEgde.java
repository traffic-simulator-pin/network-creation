package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.control.funtion.ICreateFuntion;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class FrameSetTypeEgde extends ViewFrameModulo1Padrao {
    
    private ICreateFuntion funtionCreateType;
    private JButton btnSalvar;
    private JButton btnFechar;
    private JComboBox jcbFluxo;
    private ViewFrameTabelaEdge viewFrameEdge;
    private final TelaComBotoes telaComBotoes;
    
    public FrameSetTypeEgde(FuntionCreateType funtionCreateType, ViewFrameTabelaEdge viewFrameEdge, TelaComBotoes telaComBotoes) {
        this.funtionCreateType = funtionCreateType;
        setMySide(255, 200);
        this.setLocation(300, 10);
        this.viewFrameEdge = viewFrameEdge;
        this.telaComBotoes = telaComBotoes;
        this.setTitle("Definição De Type Egde");
    }
    
    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
        
        JTextField nome = novo.adicionaCampo("nomeEdge", "Nome dos edge");
        nome.setEditable(false);
        novo.adicionaCampo("nfaixas", "Número Faixas");
        novo.adicionaCampo("velocidade", "Velocidade");
        novo.adicionaCampo("velocidade", "Capacidade");
        jcbFluxo = new JComboBox(new String[]{"Mão Unica", "Mão Dupla"});
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
        desktop().removerInstanciaJanela(this);
        desktop().removerInstanciaJanela(viewFrameEdge);
        desktop().getViewPrincipal();
        
        viewFrameEdge.dispose();
        telaComBotoes.setFuntion(null);
        this.dispose();
    }
    
    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }
    
    private class ViewActionListenerSalvar implements ActionListener {
        
        private Type modelo;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerDesktop d = desktop();
            ViewFrameTabelaEdge viewEdg = null;
            
            for (ViewJanelaSistema v : d.getJanelas()) {
                if (v instanceof ViewFrameTabelaEdge) {
                    viewEdg = (ViewFrameTabelaEdge) v;
                    break;
                }
                
            }
            
            JComponent[] tfNumFaixas = getAreaManutencao().getCampo("nfaixas");
            JComponent[] tfVelocidade = getAreaManutencao().getCampo("velocidade");
            
            if (modelo == null) {
                modelo = new Type();
            }
            modelo.setNumLanes(Integer.parseInt(((JTextField) tfNumFaixas[1]).getText() + ""));
            
            if (jcbFluxo.getSelectedIndex() == 1) {
                modelo.setOneway(true);
            } else {
                modelo.setOneway(false);
            }
            
            modelo.setSpeed(Float.parseFloat(((JTextField) tfVelocidade[1]).getText()));
            
            List<Egde> edges = viewEdg.getEgds();
            
            for (Egde ed : edges) {
                modelo.associarTypeEgde(ed);
            }
            ControllerDateNetwork.getInstance().offerType(Arrays.asList(modelo));
            
            modelo.setListDeEgdeQuePertenco(edges);
            viewEdg.atualizaListaEdge(edges);
        }
    }
}

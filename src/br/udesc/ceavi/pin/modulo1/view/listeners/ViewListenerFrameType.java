package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameTabelaEdge;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameModulo1Padrao;
import br.udesc.ceavi.pin.modulo1.view.frame.FrameSetTypeEgde;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class ViewListenerFrameType extends ViewListenersFrame {
     private Type modelo;
    
    public ViewListenerFrameType(ViewJanelaSistema view) {
        super(view);
    }
    
    public ViewListenerFrameType(ViewJanelaSistema view, Type modelo) {
        super(view);
        this.modelo = modelo;
    }
    
    /**
    * Adiciona os listeners padrões a tela.
    */
    @Override
    protected void adicionaListenersPadroes() {
        super.adicionaListenersPadroes();
        JButton botaoConsulta;

        botaoConsulta = view.getAreaAcoes().getBotao("Salvar");
        if(botaoConsulta != null) {
                botaoConsulta.addActionListener(new ViewActionListenerSalvar());
        }
    }
    
    private class ViewActionListenerSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerDesktop d = ControllerDesktop.getInstance();
            ViewFrameTabelaEdge viewEdg = null;
            
            for(ViewJanelaSistema v : d.getJanelas()) {
                if(v instanceof ViewFrameTabelaEdge) {
                    viewEdg = (ViewFrameTabelaEdge)v;
                    break;
                }
            
            }
            
            ViewFrameModulo1Padrao janela = (ViewFrameModulo1Padrao)view;
            JComponent[] campo1 = janela.getAreaManutencao().getCampo("nfaixas");
            JComponent[] campo2 = janela.getAreaManutencao().getCampo("maoUnica");
            JComponent[] campo3 = janela.getAreaManutencao().getCampo("velocidade");
            JComponent[] campo4 = janela.getAreaManutencao().getCampo("comprimento");
            JComponent[] campo5 = janela.getAreaManutencao().getCampo("nomeEdge");

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
//            modelo.setWidh(Float.parseFloat(((JTextField) campo4[1]).getText()));
            
            List<Egde> edges = viewEdg.getEgds();
            
            for(Egde ed : edges) {
                ed.setType(modelo);
            }
            
            modelo.setListDeEgdeQuePertenco(edges);
            viewEdg.atualizaTela();
        }
    }

}

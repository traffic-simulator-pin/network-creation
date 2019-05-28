package br.udesc.ceavi.pin.modulo1.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;

/**
 *
 * @author Drew
 */
public class ViewListenersDadosEdge extends ViewListenersFrame {
    
    
    public ViewListenersDadosEdge(ViewJanelaSistema view) {
        super(view);
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
        
        botaoConsulta = view.getAreaAcoes().getBotao("Alterar Type");
        if(botaoConsulta != null) {
                botaoConsulta.addActionListener(new ViewActionAlterarType());
        }
    }
    
    private class ViewActionListenerSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.fechaJanela();
        }
    }
    
    private class ViewActionAlterarType implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            view.fechaJanela();

        }
    }
}

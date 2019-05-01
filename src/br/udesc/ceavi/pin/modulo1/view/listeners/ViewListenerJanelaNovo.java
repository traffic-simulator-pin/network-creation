/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.ControlDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

/**
 *
 * @author Drew
 */
public class ViewListenerJanelaNovo extends ViewListenersFrame {

    public ViewListenerJanelaNovo() {
        super();
    }
    
    public ViewListenerJanelaNovo(ViewJanelaSistema view) {
        super(view);
    }
    
    /**
    * Adiciona os listeners padrões a tela.
    */
    @Override
    protected void adicionaListenersPadroes() {
        super.adicionaListenersPadroes();
        JButton botaoConsulta;

        botaoConsulta = view.getAreaAcoes().getBotao("Novo");
        if(botaoConsulta != null) {
                botaoConsulta.addActionListener(new ViewActionListenerNovo());
        }
    }
    
    private class ViewActionListenerNovo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDesktopPane v = ControlDesktop.getInstance().getViewPrincipal().getAreaDesktop();
            v.add(new TelaComBotoes());
            view.fechaJanela();
        }
    }
}

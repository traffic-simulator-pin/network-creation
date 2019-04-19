/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameModulo1Padrao;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 *
 * @author Drew
 */
public class ViewListenerJanelaSalvar extends ViewListenersFrame {

    public ViewListenerJanelaSalvar() {
        super();
    }
    
    public ViewListenerJanelaSalvar(ViewJanelaSistema janela) {
        super(janela);
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
            ViewFrameModulo1Padrao manutencao   = (ViewFrameModulo1Padrao)view;
            ViewPanelManutencao panelManutencao = manutencao.getAreaManutencao();
            
            panelManutencao.adicionaComponente("chooser", "", new JFileChooser());
            
            JComponent[] comp = panelManutencao.getCampo("chooser");
            
            JFileChooser fileChooser = (JFileChooser) comp[1];
            
            
            
         
            
                String sb = "TEST CONTENT";
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("/Documents"));
                int retrival = chooser.showSaveDialog(null);
                if (retrival == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                        fw.write(sb.toString());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            
            view.fechaJanela();
        }
    }
    
}

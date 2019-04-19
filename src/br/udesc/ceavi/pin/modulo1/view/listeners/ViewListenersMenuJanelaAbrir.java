/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Drew
 */
public class ViewListenersMenuJanelaAbrir extends ViewListenersMenus {
       
    public ViewListenersMenuJanelaAbrir(ViewJanelaSistema janela, ViewListenersFrame listeners) {
        super(janela, listeners);
    }
   
    
    public ViewListenersMenuJanelaAbrir() {}

    @Override
    public void actionPerformed(ActionEvent e) {
//         ViewFrameModulo1Padrao manutencao   = (ViewFrameModulo1Padrao)view;
//            ViewPanelManutencao panelManutencao = manutencao.getAreaManutencao();
//            
//            panelManutencao.adicionaComponente("escolherArquivo", "", new JFileChooser());
//            
//            JComponent[] comp1 = panelManutencao.getCampo("areaTexto");
//            JComponent[] comp2 = panelManutencao.getCampo("escolherArquivo");
//            
//            JTextArea textarea = (JTextArea) comp1[1];
            JFileChooser fileChooser = new JFileChooser();
            
            
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // What to do with the file, e.g. display it in a TextArea
                // textarea.read( new FileReader( file.getAbsolutePath() ), null );
            } else {
                System.out.println("File access cancelled by user.");
            }
    }
}

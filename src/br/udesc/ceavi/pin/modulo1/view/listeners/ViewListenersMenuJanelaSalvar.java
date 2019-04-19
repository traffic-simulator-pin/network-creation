/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;

/**
 *
 * @author Drew
 */
public class ViewListenersMenuJanelaSalvar extends ViewListenersMenus {
    
    public ViewListenersMenuJanelaSalvar() {
        super();
    }
    
    public ViewListenersMenuJanelaSalvar(ViewJanelaSistema janela, ViewListenersFrame listeners) {
        super(janela, listeners);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/Documents"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                fw.write(sb.toString());
                System.out.println("sasasasasasasasasasa");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

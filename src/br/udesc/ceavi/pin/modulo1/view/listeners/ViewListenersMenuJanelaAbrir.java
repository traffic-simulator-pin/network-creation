package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Drew
 */
public class ViewListenersMenuJanelaAbrir extends ViewListenersMenus {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        FileFilter fileFilter = new FileNameExtensionFilter(".xml", ".xml");
        fileChooser.addChoosableFileFilter(fileFilter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //Area De Leitura
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
}

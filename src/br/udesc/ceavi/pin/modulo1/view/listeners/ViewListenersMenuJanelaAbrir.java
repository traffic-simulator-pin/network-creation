package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionAbrir;
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

        FileFilter fileFilterXML = new FileNameExtensionFilter(".xml", "xml");
        FileFilter fileFilterNETXML = new FileNameExtensionFilter(".net.xml", "net.xml");
        fileChooser.addChoosableFileFilter(fileFilterXML);
        fileChooser.addChoosableFileFilter(fileFilterNETXML);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ControllerDateNetwork.getInstance().setLocalDeSalvamento(file);
            new FuntionAbrir();
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
}

package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionSalvar;
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
public class ViewListenersMenuJanelaSalvar extends ViewListenersMenus {

    public ViewListenersMenuJanelaSalvar() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ControlDateNetwork.getInstance().getLocalDeSalvamento() == null) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("/Documents"));
            chooser.removeChoosableFileFilter(chooser.getFileFilter());
            FileFilter fileFilter = new FileNameExtensionFilter(".xml", ".xml");
            chooser.addChoosableFileFilter(fileFilter);
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                ControlDateNetwork.getInstance().setLocalDeSalvamento(new File(chooser.getSelectedFile().getAbsolutePath() + ".xml"));
            }
        }
        new FuntionSalvar();
    }
}

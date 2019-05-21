package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionSalvar;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
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
        if (ControllerDateNetwork.getInstance().getLocalDeSalvamento() == null) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("/Documents"));
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            chooser.removeChoosableFileFilter(chooser.getFileFilter());

            FileFilter fileFilterXML = new FileNameExtensionFilter(".xml", ".xml");
            FileFilter fileFilterTXT = new FileNameExtensionFilter(".net.xml", ".net.xml");
            chooser.addChoosableFileFilter(fileFilterXML);
            chooser.addChoosableFileFilter(fileFilterTXT);

            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {

                String nameDoArquivo = chooser.getSelectedFile().getName();
                String caminhoDoArquivo = chooser.getSelectedFile().getParent();
                StringBuilder caminho = new StringBuilder();
                for (int i = 0; i < nameDoArquivo.length(); i++) {
                    if (nameDoArquivo.charAt(i) == '.') {
                        break;
                    }
                    caminho.append(nameDoArquivo.charAt(i));
                }

                caminho.insert(0, caminhoDoArquivo + "//");
                File file = new File(caminho.toString() + chooser.getFileFilter().getDescription());
                ControllerDateNetwork.getInstance().setLocalDeSalvamento(file);
                new FuntionSalvar();
            }
        } else {
            new FuntionSalvar();
        }
    }
}

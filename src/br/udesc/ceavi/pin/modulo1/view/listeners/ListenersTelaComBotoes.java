package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.funtion.*;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class ListenersTelaComBotoes {

    private final TelaComBotoes view;

    public ListenersTelaComBotoes(TelaComBotoes view, List<JButton> listaButao) {
        this.view = view;
        associateEventToButton(listaButao);
    }

    private void associateEventToButton(List<JButton> asList) {
        asList.forEach((jButton)
                -> jButton.addActionListener((e) -> {
                    try {
                        view.setAllJButtonAtivo();
                        IFuntion funtion
                                = (IFuntion) Class.forName("br.udesc.ceavi.pin.modulo1.control.funtion."
                                        + jButton.getName()).newInstance();
                        view.setMouseListener(funtion.getMouseManeger());
                        view.setFuntion(funtion);
                        funtion.addObservador(view.getObservadorTelaDesenho());
                        jButton.setEnabled(false);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }));
    }

}

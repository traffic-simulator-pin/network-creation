package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionTest;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionRemoverEgde;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateEgdeTipo1;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateEgdeTipo2;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionMoveTela;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionSelecionarEgde;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton btn;
        btn = asList.get(0);
        btn.addActionListener(new EventFuntionCreateEgde());
        btn = asList.get(1);
        btn.addActionListener(new EventSelectionEgde());
        btn = asList.get(2);
        btn.addActionListener(new EventRemoverEgde());
        btn = asList.get(3);
        btn.addActionListener(new EventFuntionTest());
        btn = asList.get(4);
        btn.addActionListener(new EventFuntionMove());
    }
    
    private class EventFuntionMove implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionMoveTela funtion = new FuntionMoveTela();
            view.setMouseListener(funtion.getMouseManeger());
            view.setFuntion(funtion);
            
        }
        
    }
    
    private class EventFuntionTest implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionTest funtion = new FuntionTest();
            view.setMouseListener(null);
            view.setFuntion(funtion);
        }
    }
    
    private class EventRemoverEgde implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionCreateEgdeTipo2 funtion = new FuntionCreateEgdeTipo2();
            view.setMouseListener(funtion.getMouseListener());
            view.setFuntion(funtion);
            funtion.addObservador(view.getObservadorTelaDesenho());
        }
    }
    
    private class EventSelectionEgde implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionSelecionarEgde funtion = new FuntionSelecionarEgde();
            view.setMouseListener(funtion.getMouseListener());
            view.setFuntion(funtion);
        }
    }
    
    private class EventFuntionCreateEgde implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionCreateEgdeTipo1 funtion = new FuntionCreateEgdeTipo1();
            view.setMouseListener(funtion.getMouseListener());
            view.setFuntion(funtion);
            funtion.addObservador(view.getObservadorTelaDesenho());
        }
    }
    
}

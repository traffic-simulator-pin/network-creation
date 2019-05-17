package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.funtion.*;
import br.udesc.ceavi.pin.modulo1.view.ControlDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameDemanda;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameType;
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
        initListener(listaButao);
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

    private void initListener(List<JButton> listaButao) {
        JButton btn = null;

        btn = listaButao.get(0);
        btn.addActionListener(new CreateEgde1Listener(btn));

        btn = listaButao.get(1);
        btn.addActionListener(new CreateEgde2Listener(btn));

        btn = listaButao.get(2);
        btn.addActionListener(new SeletionEgdeListener(btn));

        btn = listaButao.get(3);
        btn.addActionListener(new SeletionNodeListener(btn));

        btn = listaButao.get(4);
        btn.addActionListener(new RemoverEgdeListener(btn));

        btn = listaButao.get(5);
        btn.addActionListener(new MoverTelaListener(btn));

        btn = listaButao.get(6);
        btn.addActionListener(new CreateDemandaListener(btn));

        btn = listaButao.get(7);
        btn.addActionListener(new SetTypeListener(btn));

        btn = listaButao.get(8);
        btn.addActionListener(new TesteListener(btn));
    }

    private class CreateEgde1Listener implements ActionListener {

        private final JButton btn;

        private CreateEgde1Listener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion createEgde = new FuntionCreateEgdeTipo1();
            setFuntionToView(createEgde, btn);
        }

    }

    private class CreateEgde2Listener implements ActionListener {

        private final JButton btn;

        public CreateEgde2Listener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion createEgde2 = new FuntionCreateEgdeTipo2();
            setFuntionToView(createEgde2, btn);
        }
    }

    private class SeletionEgdeListener implements ActionListener {

        private final JButton btn;

        public SeletionEgdeListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion funtoin = new FuntionSelecionarEgde();
            setFuntionToView(funtoin, btn);
        }

    }

    private class SeletionNodeListener implements ActionListener {

        private final JButton btn;

        public SeletionNodeListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion seletionNode = new FuntionSelecionarNode();
            setFuntionToView(seletionNode, btn);
        }

    }

    private class RemoverEgdeListener implements ActionListener {

        private final JButton btn;

        public RemoverEgdeListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion deleteEgde = new FuntionRemoverEgde();
            setFuntionToView(deleteEgde, btn);
        }

    }

    private class MoverTelaListener implements ActionListener {

        private final JButton btn;

        public MoverTelaListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion funtion = new FuntionMoveTela();
            setFuntionToView(funtion, btn);
        }

    }

    private class CreateDemandaListener implements ActionListener {

        private final JButton btn;

        public CreateDemandaListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ViewFrameDemanda telaDemanda = new ViewFrameDemanda();
            ControlDesktop desktop = ControlDesktop.getInstance();
            desktop.adicionaJanela(telaDemanda);
            telaDemanda.abreJanela();
            setFuntionToView(telaDemanda.getFuntion(), btn);
        }

    }

    private class SetTypeListener implements ActionListener {

        private final JButton btn;

        public SetTypeListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion funtion = new FuntionCreateType();
            ViewFrameEdge viewTabela = new ViewFrameEdge((FuntionCreateType) funtion);
            ViewFrameType viewType = new ViewFrameType((FuntionCreateType) funtion);
            setFuntionToView(funtion, btn);
        }

    }

    private class TesteListener implements ActionListener {

        private final JButton btn;

        public TesteListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion teste = new FuntionTest();
            setFuntionToView(teste, btn);
        }

    }

    private void setFuntionToView(IFuntion funtin, JButton btn) {
        view.setAllJButtonAtivo();
        view.setMouseListener(funtin.getMouseManeger());
        view.setFuntion(funtin);
        funtin.addObservador(view.getObservadorTelaDesenho());
        btn.setEnabled(false);
    }

}

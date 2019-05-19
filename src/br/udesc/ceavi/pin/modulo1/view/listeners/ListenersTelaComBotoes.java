package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.funtion.*;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
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
        initListener(listaButao);
    }

    private void initListener(List<JButton> listaButao) {
        JButton btn;

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
            setFuntionToView(createEgde);
            btn.setEnabled(false);
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
            setFuntionToView(createEgde2);
            btn.setEnabled(false);
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
            setFuntionToView(funtoin);
            btn.setEnabled(false);
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
            setFuntionToView(seletionNode);
            btn.setEnabled(false);
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
            setFuntionToView(deleteEgde);
            btn.setEnabled(false);
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
            setFuntionToView(funtion);
            btn.setEnabled(false);
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
            setFuntionToView(telaDemanda.getFuntion().getSeletion());

            ControllerDesktop desktop = desktop();
            desktop.adicionaJanela(telaDemanda).abrirJanela();
        }

    }

    private class SetTypeListener implements ActionListener {

        private final JButton btn;

        public SetTypeListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ViewFrameType janela = (ViewFrameType) desktop().getJanela(ViewFrameType.class.getSimpleName());
            if (janela == null) {
                ViewFrameEdge viewFrameEdge = new ViewFrameEdge();
                ViewFrameType viewFrameType = new ViewFrameType(viewFrameEdge);

                desktop().adicionaJanela(viewFrameEdge).abrirJanela();
                desktop().adicionaJanela(viewFrameType).abrirJanela();

                setFuntionToView(viewFrameType.getFuntionCreateType().getFuntion());
            } else {
                setFuntionToView(((ViewFrameType) janela).getFuntionCreateType().getFuntion());
            }
        }

    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }

    private class TesteListener implements ActionListener {

        private final JButton btn;

        public TesteListener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            IFuntion teste = new FuntionTest();
            setFuntionToView(teste);
        }

    }

    private void setFuntionToView(IFuntion funtin) {
        desktop().setVisibleFalseAll();
        view.setAllJButtonAtivo();
        view.setMouseListener(funtin.getMouseManeger());
        view.setFuntion(funtin);
        funtin.addObservador(view.getObservadorTelaDesenho());
    }

}

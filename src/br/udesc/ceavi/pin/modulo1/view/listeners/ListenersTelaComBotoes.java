package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.control.funtion.*;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.frame.FrameCreateEgde;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameDemanda;
import br.udesc.ceavi.pin.modulo1.view.frame.FrameSetTypeEgde;
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
            FuntionCreateEgdeTipo1 createEgde = new FuntionCreateEgdeTipo1();
            setFuntionToView(createEgde, btn);
            FrameCreateEgde tela = new FrameCreateEgde(createEgde, view);
            desktop().adicionaJanela(tela).abrirJanela();
        }

    }

    private class CreateEgde2Listener implements ActionListener {

        private final JButton btn;

        public CreateEgde2Listener(JButton btn) {
            this.btn = btn;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FuntionCreateEgdeTipo2 createEgde = new FuntionCreateEgdeTipo2();
            setFuntionToView(createEgde, btn);
            FrameCreateEgde tela = new FrameCreateEgde(createEgde, view);
            desktop().adicionaJanela(tela).abrirJanela();
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
            setFuntionToView(
                    telaDemanda.getFuntion().getSeletion(),
                    btn);

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
            IFuntion funtion = new FuntionCreateType();
            setFuntionToView(
                    ((FuntionCreateType) funtion).getFuntion(),
                    btn);

            ControllerDesktop desktop = desktop();
            ViewFrameEdge viewFrameEdge = new ViewFrameEdge((FuntionCreateType) funtion);
            desktop.adicionaJanela(viewFrameEdge).abrirJanela();

            FrameSetTypeEgde v = new FrameSetTypeEgde((FuntionCreateType) funtion, viewFrameEdge);
            desktop.adicionaJanela(v).abrirJanela();
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
            setFuntionToView(teste, btn);
        }

    }

    private void setFuntionToView(IFuntion funtin, JButton btn) {
        desktop().fecharJanelas();
        view.setAllJButtonAtivo();
        view.setMouseListener(funtin.getMouseManeger());
        view.setFuntion(funtin);
        funtin.addObservador(view.getObservadorTelaDesenho());
        btn.setEnabled(false);
    }

}

package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateDemanda;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class ViewFrameDemanda extends ViewFrameModulo1Padrao {

    private FuntionCreateDemanda controller;
    private JButton btnFechar;
    private JTextField tfOrigem;
    private JTextField tfDestino;
    private JTextField jtfDemanda;
    private Node nodoOrigem;
    private Node nodoDestino;

    public ViewFrameDemanda() {
        this.setName("demanda");
        this.controller = new FuntionCreateDemanda();
        this.setLocation(300, 10);
        this.moveToFront();
        this.toFront();
        this.setVisible(false);
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {

        ViewPanelManutencao novo = super.criaAreaManutencao();
        tfOrigem = novo.adicionaCampo("nodoOrigem", "Nodo Origem");
        tfDestino = novo.adicionaCampo("nodoDestino", "Nodo Destino");
        jtfDemanda = novo.adicionaCampo("demanda", "Demanda");
        tfDestino.setEditable(false);
        tfOrigem.setEditable(false);
        return novo;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        this.acoesPanel.adicionaAcao("Salvar");
        btnFechar = this.acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> this.distruirInstancia());
        getAreaAcoes().getBotao("Salvar").addActionListener(new ViewActionListenerSalvar());
    }

    public FuntionCreateDemanda getFuntion() {
        return controller;
    }

    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        this.dispose();
    }

    public Node getNodoOrigem() {
        return nodoOrigem;
    }

    public void setNodoOrigem(Node nodoOrigem) {
        this.nodoOrigem = nodoOrigem;
    }

    public Node getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(Node nodoDestino) {
        this.nodoDestino = nodoDestino;
    }

    public void atualizaTelaDemanda() {

        if (this.getNodoOrigem() != null) {
            tfOrigem.setText(this.getNodoOrigem().getId() + "");
        }

        if (this.getNodoDestino() != null && getNodoOrigem().getId() != getNodoDestino().getId()) {
            tfDestino.setText(this.getNodoDestino().getId() + "");
        }

        this.repaint();
    }

    private class ViewActionListenerSalvar implements ActionListener {

        private Demanda model;

        @Override
        public void actionPerformed(ActionEvent e) {
            int demanda = 0;

            if (!jtfDemanda.getText().equals("")) {
                demanda = Integer.parseInt(jtfDemanda.getText() + "");
            }

            if (getNodoOrigem() != null && getNodoDestino() != null) {
                model = new Demanda(getNodoOrigem(), getNodoDestino(), demanda);
                controller.newDemanda(model);
                limparCampos();

            }
        }

        private void limparCampos() {
            this.model = null;
            tfOrigem.setText("");
            tfDestino.setText("");
            jtfDemanda.setText("");
        }
    }

}

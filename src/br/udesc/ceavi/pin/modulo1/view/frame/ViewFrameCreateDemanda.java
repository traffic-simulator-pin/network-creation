package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateDemanda;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
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
public class ViewFrameCreateDemanda extends ViewFrameModulo1Padrao {

    private FuntionCreateDemanda controller;
    private JTextField tfOrigem;
    private JTextField tfDestino;
    private JTextField jtfDemanda;
    private final TelaComBotoes telaComBotoes;
    private JButton btnFechar;
    private JButton btnLimpar;

    public ViewFrameCreateDemanda(TelaComBotoes telaComBotoes) {
        this.controller = new FuntionCreateDemanda();
        this.telaComBotoes = telaComBotoes;
        controller.setView(this);
        this.setTitle("Adicionar Demandas");

        this.setLocation(300, 10);
        this.moveToFront();
        this.toFront();
        this.setVisible(false);
        this.setMySide(350, 200);
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
        btnLimpar = this.acoesPanel.adicionaAcao("Limpar");
        btnFechar = this.acoesPanel.adicionaAcao("Fechar");

        btnFechar.addActionListener((e) -> this.distruirInstancia());

        btnLimpar.addActionListener((e) -> {
            this.limparCampos();
            controller.clearNode();
        });

        getAreaAcoes().getBotao("Salvar").addActionListener(new ViewActionListenerSalvar());
    }

    public FuntionCreateDemanda getFuntion() {
        return controller;
    }

    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        this.dispose();
        telaComBotoes.setFuntion(null);
    }

    public void atualizaTelaDemanda() {
        limparCampos();
        if (controller.getA() != null) {
            tfOrigem.setText(controller.getA().getId() + "");
        }

        if (controller.getB() != null) {
            tfDestino.setText(controller.getB().getId() + "");
        }

        this.repaint();
    }

    public void setDemandaText(int demanda) {
        jtfDemanda.setText("" + demanda);
    }

    private class ViewActionListenerSalvar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int demanda = 0;
            if (controller.getA() != null && controller.getB() != null) {
                controller.newDemanda(new Demanda(controller.getA(), controller.getB(), demanda));
            } else {
                return;
            }

            if (!jtfDemanda.getText().equals("")) {
                try {
                    demanda = Integer.parseInt(jtfDemanda.getText() + "");
                    if (demanda < -1) {
                        throw new Exception();
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Informe Valores Validos");
                }
            }
            limparCampos();
        }
    }

    private void limparCampos() {
        tfOrigem.setText("");
        tfDestino.setText("");
        jtfDemanda.setText("");
        this.repaint();
    }

    @Override
    public void fechaJanela() {
        super.destruirInstanciaJanela();
    }

}

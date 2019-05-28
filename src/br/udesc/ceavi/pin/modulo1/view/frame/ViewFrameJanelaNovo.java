package br.udesc.ceavi.pin.modulo1.view.frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udesc.ceavi.pin.modulo1.control.ControlTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;

/**
 *
 * @author Gustavo C Santos
 */
public class ViewFrameJanelaNovo extends JFrame {

    private ControlTelaDesenho control;
    private JTextField tfAltura;
    private JTextField tfLargura;
    private JLabel lbAltura;
    private JLabel lbLargura;
    private JButton btnNovo;
    private JButton btnFechar;

    public ViewFrameJanelaNovo() {
        this.control = new ControlTelaDesenho();
        this.setTitle("Nova Network");
        this.setResizable(false);
        Point posicaoDaView = desktop().getViewPrincipal().getLocation();
        this.setLocation(posicaoDaView);
        initComponets();
        setPosicionsComponets();
        initListener();
        setVisible(true);
        this.setAlwaysOnTop(true);
    }

    private void initComponets() {
        tfAltura = new JTextField();
        tfLargura = new JTextField();
        lbAltura = new JLabel("Altuara: ");
        lbLargura = new JLabel("Largura: ");
        btnNovo = new JButton("Criar Area Desenho");
        btnFechar = new JButton("Fechar");
    }

    private void setPosicionsComponets() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints cons;
        this.setSize(350, 250);
        Dimension d = new Dimension(120, 27);
        tfAltura.setSize(d);
        tfAltura.setPreferredSize(d);
        tfAltura.setMinimumSize(d);
        tfAltura.setMaximumSize(d);
        tfLargura.setSize(d);
        tfLargura.setPreferredSize(d);
        tfLargura.setMinimumSize(d);
        tfLargura.setMaximumSize(d);
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(lbAltura, cons);

        cons = new GridBagConstraints();
        cons.gridx = 1;
        cons.gridy = 0;
        cons.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(lbLargura, cons);
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tfAltura, cons);
        cons = new GridBagConstraints();
        cons.gridx = 1;
        cons.gridy = 1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(tfLargura, cons);
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 2;
        contentPane.add(btnNovo, cons);
        cons = new GridBagConstraints();
        cons.gridx = 1;
        cons.gridy = 2;
        contentPane.add(btnFechar, cons);
    }

    private void initListener() {
        btnNovo.addActionListener((e) -> {
            try {
                control.setParametroToTelaDesenho(tfLargura.getText(), tfAltura.getText());
                if (desktop().hasViewPrincipa()) {
                    if (ControllerDateNetwork.getInstance().haveElements()) {
                        ControllerDateNetwork.getInstance().salvar();
                        ControllerDateNetwork.getInstance().reiniciar();
                    }
                }
                TelaComBotoes telaComBotoes = new TelaComBotoes(control.getwSizeTela(), control.gethSizeTela());
                desktop().addViewPrincipa(telaComBotoes);
                telaComBotoes.abrirJanela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        });
        btnFechar.addActionListener((e) -> this.dispose());
    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }
}

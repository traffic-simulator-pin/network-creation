package br.udesc.ceavi.pin.modulo1.view.frame;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;

/**
 *
 * @author Drew
 */
public class ViewFrameModulo1PadraoConsulta extends ViewJanelaSistema {

    //CONSTANTES
    public static final Dimension FRAME_TAMANHO_BASE = new Dimension(200, 200); // Tamanho máximo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MINIMO = new Dimension(500, 250); // Tamanho mínimo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MAXIMO = new Dimension(1000, 500); // Tamanho máximo padrão da Janela.

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    public DefaultTableModel modelo;
    private JScrollPane painelConsulta;

    public ViewFrameModulo1PadraoConsulta() {
        initComponent();
        this.moveToFront();
        this.toFront();
    }

    private void initComponent() {
        this.setMinimumSize(FRAME_TAMANHO_MINIMO);
        this.setMaximumSize(FRAME_TAMANHO_MAXIMO);
        this.setPreferredSize(FRAME_TAMANHO_BASE);
        this.setSize(FRAME_TAMANHO_BASE);
        this.setResizable(false);

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.weightx = 1;
        this.setLayout(layout);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        criaAreaManutencao();
        criaAreaAcoes();
        GridBagConstraints constraintsFill = (GridBagConstraints) constraints.clone();
        constraintsFill.fill = GridBagConstraints.BOTH;
        constraintsFill.weighty = 1;
        this.add(painelConsulta, constraintsFill);
        this.add(acoesPanel, constraints);
    }

    @Override
    public void abrirJanela() {

        this.setVisible(true);
    }

    protected JScrollPane criaAreaManutencao() {
        painelConsulta = new ViewPainelConsulta("Edge");
        ViewPainelConsulta p = (ViewPainelConsulta) painelConsulta;
        this.modelo = p.getModelo();
        return painelConsulta;
    }

    public JScrollPane getAreaManutencao() {
        return this.painelConsulta;
    }

    protected void criaAreaAcoes() {
        this.acoesPanel = new ViewPanelAcoes();
        acoesPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        acoesPanel.adicionaAcao("Fechar");
    }

    @Override
    public void fechaJanela() {
        this.setVisible(false);
    }

    public ViewPainelConsulta getPainelConsulta() {
        return (ViewPainelConsulta) painelConsulta;
    }

    @Override
    public void destruirInstanciaJanela() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
    }
}

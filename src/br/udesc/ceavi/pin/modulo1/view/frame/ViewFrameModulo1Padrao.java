package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 *
 * @author Drew
 */
public class ViewFrameModulo1Padrao extends ViewJanelaSistema {

    //CONSTANTES
    public static final Dimension FRAME_TAMANHO_BASE = new Dimension(200, 200); // Tamanho máximo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MINIMO = new Dimension(500, 250); // Tamanho mínimo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MAXIMO = new Dimension(1000, 500); // Tamanho máximo padrão da Janela.

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private ViewPanelManutencao areaManutencao;
    private boolean aberta;

    public ViewFrameModulo1Padrao(String nome) {
        super(nome);
        this.setMinimumSize(FRAME_TAMANHO_MINIMO);
        this.setMaximumSize(FRAME_TAMANHO_MAXIMO);
        this.setPreferredSize(FRAME_TAMANHO_BASE);
        this.setSize(FRAME_TAMANHO_BASE);
        this.setResizable(true);

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.weightx = 1;
        this.setLayout(layout);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void abreJanela() {
        if (aberta) {
            this.fechaJanela();
        }
        aberta = true;
        criaAreaManutencao();
        criaAreaAcoes();
        GridBagConstraints constraintsFill = (GridBagConstraints) constraints.clone();
        constraintsFill.anchor = GridBagConstraints.NORTH;
        constraintsFill.weighty = 1;
        this.add(areaManutencao, constraintsFill);
        this.add(acoesPanel, constraints);
        this.setVisible(true);
    }

    protected ViewPanelManutencao criaAreaManutencao() {
        areaManutencao = new ViewPanelManutencao();
        return areaManutencao;
    }

    public ViewPanelManutencao getAreaManutencao() {
        return this.areaManutencao;
    }

    protected void criaAreaAcoes() {
        this.acoesPanel = new ViewPanelAcoes();
        acoesPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        acoesPanel.adicionaAcao("Fechar");
    }

    @Override
    public void fechaJanela() {
        aberta = false;
        this.setVisible(false);
        this.remove(areaManutencao);
        this.remove(acoesPanel);
        areaManutencao = null;
        acoesPanel = null;
    }

}

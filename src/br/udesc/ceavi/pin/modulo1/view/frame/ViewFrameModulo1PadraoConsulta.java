package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.view.ControlDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPainelConsulta;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Drew
 */
public class ViewFrameModulo1PadraoConsulta  extends ViewJanelaSistema {
    
    //CONSTANTES
    public static final Dimension FRAME_TAMANHO_BASE = new Dimension(200, 200); // Tamanho máximo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MINIMO = new Dimension(500, 250); // Tamanho mínimo padrão da Janela.
    public static final Dimension FRAME_TAMANHO_MAXIMO = new Dimension(1000, 500); // Tamanho máximo padrão da Janela.

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    public DefaultTableModel modelo;
    private JScrollPane painelConsulta;
    private boolean aberta;

    public ViewFrameModulo1PadraoConsulta(String nome) {
        super(nome);
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
//        constraintsFill.anchor = GridBagConstraints.NORTH;
        constraintsFill.fill = GridBagConstraints.BOTH;
        constraintsFill.weighty = 1;
        this.add(painelConsulta, constraintsFill);
        this.add(acoesPanel, constraints);
        this.setVisible(true);
    }

    protected JScrollPane criaAreaManutencao() {
        painelConsulta =  new ViewPainelConsulta("Edge");
        ViewPainelConsulta p = (ViewPainelConsulta)painelConsulta;
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
        aberta = false;
        this.setVisible(false);
        this.remove(painelConsulta);
        this.remove(acoesPanel);
        painelConsulta = null;
        acoesPanel = null;
    }

    
    public ViewPainelConsulta getPainelConsulta() {
        return (ViewPainelConsulta) painelConsulta;
    }
    
//    public <T> void adicionaDadosPainel(List<T> dados) throws Exception {
//        ViewPainelConsulta areaConsulta = (ViewPainelConsulta)areaManutencao;
//        
//    	areaConsulta.limpaValores();
//    	for (T dado : dados) {
//			areaConsulta.setValores(dado);
//		}
//    }

    @Override
    public void destruirInstanciaJanela() {
        ControlDesktop.getInstance().removerInstanciaJanela(this);
    }
}

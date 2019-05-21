package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

/**
 *
 * @author Drew
 */
public abstract class ViewJanelaSistema extends JInternalFrame {

    protected ViewPanelAcoes acoesPanel;

    public abstract void abrirJanela();

    public abstract void fechaJanela();

    public abstract void destruirInstanciaJanela();

    /**
     * Busca o painel de ações do frame.
     */
    public ViewPanelAcoes getAreaAcoes() {
        return this.acoesPanel;
    }

    protected void setMySide(int width, int height) {
        Dimension d = new Dimension(width, height);
        this.setSize(d);
        this.setPreferredSize(d);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
    }
}

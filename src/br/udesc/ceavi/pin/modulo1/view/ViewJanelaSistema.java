package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;

/**
 *
 * @author Drew
 */
public abstract class ViewJanelaSistema extends JInternalFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9202978203462520950L;
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

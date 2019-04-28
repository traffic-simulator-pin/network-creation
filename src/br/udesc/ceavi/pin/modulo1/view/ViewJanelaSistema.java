package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelAcoes;
import javax.swing.JInternalFrame;

/**
 *
 * @author Drew
 */
public abstract class ViewJanelaSistema extends JInternalFrame {
    
    protected String nome;
    protected ViewPanelAcoes acoesPanel;
    
    public  ViewJanelaSistema(String nome) {
        this.nome = nome;
        this.setTitle(nome);
    }

    public abstract void abreJanela();
    public abstract void fechaJanela();
    
    
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Busca o painel de ações do frame.
     */
    public ViewPanelAcoes getAreaAcoes() {
        return this.acoesPanel;
    }
}

package br.udesc.ceavi.pin.modulo1.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Aqui que se mantem as diferentes telas do sistema
 *
 * @author Drew
 */
public class Desktop {

    //CAMPOS
    private static Desktop desktop; // Referência a instância.
    
    private ViewPrincipal viewPrincipal; // Tela do Desktop.
    private List<ViewJanelaSistema> janelas; // Janelas do Sistema.

    /**
     * Cria um novo desktop.
     */
    private Desktop() {
        viewPrincipal = new ViewPrincipal(this);
        janelas = new ArrayList<ViewJanelaSistema>();
    }

    /**
     * Inicia o desktop caso este ainda não esteja iniciado.
     */
    public void inicia() {
        if (!viewPrincipal.isVisible()) {
            viewPrincipal.criaMenus();
            viewPrincipal.setVisible(true);
        }
    }

    /**
     * Busca a instância do Singleton.
     *
     * @return - A instância de Desktop existente.
     */
    public static Desktop getInstance() {
        if (desktop == null) {
            desktop = new Desktop();
        }
        return desktop;
    }

    /**
     * Adiciona uma janela ao Desktop.
     *
     * @param janela - janela a ser adicionada.
     */
    public void adicionaJanela(ViewJanelaSistema janela) {
        viewPrincipal.getAreaDesktop().add(janela);
        this.janelas.add(janela);
    }

    /**
     * Busca uma janela no sistema.
     *
     * @param nome - nome da janela.
     */
    public ViewJanelaSistema getJanela(String nome) {
        for (ViewJanelaSistema janela : janelas) {
            if (janela.getNome().equals(nome)) {
                return janela;
            }
        }
        return null;
    }

    public ViewPrincipal getViewPrincipal() {
        return this.viewPrincipal;
    }
}

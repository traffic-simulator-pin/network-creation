package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * Aqui que se mantem as diferentes telas do sistema
 *
 * @author Drew
 */
public class ControllerDesktop {

    //CAMPOS
    private static ControllerDesktop desktop; // Referência a instância.

    /**
     * Busca a instância do Singleton.
     *
     * @return - A instância de Desktop existente.
     */
    public static ControllerDesktop getInstance() {
        if (desktop == null) {
            desktop = new ControllerDesktop();
        }
        return desktop;
    }
    private ViewPrincipal viewPrincipal; // Tela do Desktop.

    private List<ViewJanelaSistema> janelas; // Janelas do Sistema.
    private TelaComBotoes areaDesenho;

    /**
     * Cria um novo desktop.
     */
    private ControllerDesktop() {
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
     * Adiciona uma janela ao Desktop.
     *
     * @param janela - janela a ser adicionada.
     * @return Retorna a janela adicionada
     */
    public ViewJanelaSistema adicionaJanela(ViewJanelaSistema janela) {
        ViewJanelaSistema janelaV = getJanela(janela.getClass().getSimpleName());
        if (janelaV == null) {
            viewPrincipal.getAreaDesktop().add(janela);
            this.janelas.add(janela);
            janelaV = janela;
        }
        return janelaV;
    }

    public void addViewPrincipa(TelaComBotoes nArea) {
        if (this.areaDesenho != null) {
            areaDesenho.dispose();
        }
        this.areaDesenho = nArea;
        viewPrincipal.getAreaDesktop().add(nArea);
    }

    public void setVisibleFalseAll() {
        janelas.forEach(j -> j.fechaJanela());
    }

    public boolean hasViewPrincipa() {
        return areaDesenho != null;
    }

    /**
     * Busca uma janela no sistema. getJanela
     *
     * @param classe - nome da janela.
     * @return
     */
    public ViewJanelaSistema getJanela(String classe) {
        for (ViewJanelaSistema janela : janelas) {
            if (janela.getClass().getSimpleName().equals(classe)) {
                return janela;
            }
        }
        return null;
    }

    public ViewPrincipal getViewPrincipal() {
        return this.viewPrincipal;
    }

    public Dimension getSizeTela() {
        return ViewPrincipal.TAMANHO_PADRAO;
    }

    public boolean removerInstanciaJanela(ViewJanelaSistema destruir) {
        for (ViewJanelaSistema janela : janelas) {
            if (janela.getClass().getSimpleName().equals(destruir.getClass().getSimpleName())) {
                janelas.remove(destruir);
                return true;
            }
        }
        return false;
    }

    public List<ViewJanelaSistema> getJanelas() {
        return janelas;
    }
}

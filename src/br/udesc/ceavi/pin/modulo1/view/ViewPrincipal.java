package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameJanelaNovo;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaAbrir;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaNovo;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaSalvar;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/**
 *
 * @author Drew
 */
public class ViewPrincipal extends JFrame {

    public static final String TITULOLO_TELA_PRINCIPAL = "PIN2";//nome da tela
    public static final Dimension TAMANHO_PADRAO = new Dimension(1024, 720);//tamanho tela

    private ControllerDesktop desktop; //  referencia ao desktop
    private JDesktopPane areaDesktop;// area de  trabalho
    private ViewBarraMenus viewMenuPrincipal;

    public ViewPrincipal(ControllerDesktop desktop) {
        this.desktop = desktop;
        this.setTitle(TITULOLO_TELA_PRINCIPAL);
        this.setSize(TAMANHO_PADRAO);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.areaDesktop = new JDesktopPane();
        this.viewMenuPrincipal = new ViewBarraMenus();
        defineGerenciadorDesktop();
        this.criaAreaDesktop();
        this.setLocationRelativeTo(null);
    }

    /**
     * Cria os menus do desktop.
     */
    public void criaMenus() {
        viewMenuPrincipal.adicionaMenu("Arquivo", new String[]{"Abrir", "Novo", "Salvar"});

        this.initListener();
        this.setJMenuBar(viewMenuPrincipal);
    }

    /**
     * Cria as diferentes janelas do sistema.
     */
    private void initListener() {
        viewMenuPrincipal.adicionaListener("Arquivo", "Abrir", 
                new ViewListenersMenuJanelaAbrir());
        viewMenuPrincipal.adicionaListener("Arquivo", "Novo", 
                new ViewListenersMenuJanelaNovo());
        viewMenuPrincipal.adicionaListener("Arquivo", "Salvar",
                new ViewListenersMenuJanelaSalvar());
    }

    public JDesktopPane getAreaDesktop() {
        return this.areaDesktop;
    }

    /**
     * Cria a Área de Trabalho do Desktop.
     */
    public void criaAreaDesktop() {
        areaDesktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
        this.add(areaDesktop);
    }
    
    private void defineGerenciadorDesktop() {
        this.getAreaDesktop().setDesktopManager(new ManagerDesktop());
    }
}

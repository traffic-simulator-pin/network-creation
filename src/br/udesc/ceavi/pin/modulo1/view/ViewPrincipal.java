package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameJanelaNovo;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaAbrir;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaNovo;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaSalvar;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenerJanelaNovo;
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

    private ControlDesktop desktop; //  referencia ao desktop
    private JDesktopPane areaDesktop;// area de  trabalho
    private ViewBarraMenus viewMenuPrincipal;
    private TelaComBotoes telaComBotoes;

    public ViewPrincipal(ControlDesktop desktop) {
        this.desktop = desktop;
        this.setTitle(TITULOLO_TELA_PRINCIPAL);
        this.setSize(TAMANHO_PADRAO);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.areaDesktop = new JDesktopPane();
        this.viewMenuPrincipal = new ViewBarraMenus();
        this.telaComBotoes = new TelaComBotoes();
        this.criaAreaDesktop();
        this.defineGerenciadorDesktop();
        this.addAreaDesenho();
        this.setLocationRelativeTo(null);
    }

    /**
     * Cria os menus do desktop.
     */
    public void criaMenus() {
        viewMenuPrincipal.adicionaMenu("Arquivo", new String[]{"Abrir", "Novo", "Salvar"});

        this.criaJanelas();
        this.setJMenuBar(viewMenuPrincipal);
    }

    /**
     * Cria as diferentes janelas do sistema.
     */
    private void criaJanelas() {
        ViewFrameJanelaNovo janelaNovo = new ViewFrameJanelaNovo();
        desktop.adicionaJanela(janelaNovo);

        viewMenuPrincipal.adicionaListener("Arquivo", "Abrir", new ViewListenersMenuJanelaAbrir());
        viewMenuPrincipal.adicionaListener("Arquivo", "Novo", new ViewListenersMenuJanelaNovo(janelaNovo, new ViewListenerJanelaNovo()));
        viewMenuPrincipal.adicionaListener("Arquivo", "Salvar", new ViewListenersMenuJanelaSalvar());
    }

    private void addAreaDesenho() {
        this.getAreaDesktop().add(telaComBotoes);
    }

    public JDesktopPane getAreaDesktop() {
        return this.areaDesktop;
    }

    public TelaComBotoes getAreaDesenho() {
        return telaComBotoes;
    }

    private void defineGerenciadorDesktop() {
        this.getAreaDesktop().setDesktopManager(new ManagerDesktop());
    }

    /**
     * Cria a Área de Trabalho do Desktop.
     */
    public void criaAreaDesktop() {
        areaDesktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
        this.add(areaDesktop);
    }
}

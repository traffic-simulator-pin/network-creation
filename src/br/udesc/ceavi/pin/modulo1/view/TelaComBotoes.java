package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.IFuntion;
import br.udesc.ceavi.pin.modulo1.view.listeners.ListenersTelaComBotoes;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.funtion.ILoop;
import java.awt.Color;
import java.util.EventListener;

/**
 *
 * @author Gustavo de Carvalho Santos
 */
public class TelaComBotoes extends JInternalFrame {

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private JButton btnCreateEgdeTipo1, btnSelecionaEgde, btnApagaEgde, btnTest;
    private JPanel panelBotao;
    private final ListenersTelaComBotoes listeners;

    private AreaDesenho areaDesenho;
    
    private IFuntion funtion;

    public TelaComBotoes() {
        initComponents();
        initMyComponents();
        this.setVisible(true);
        listeners = new ListenersTelaComBotoes(this,
                Arrays.asList(btnCreateEgdeTipo1,
                        btnSelecionaEgde,
                        btnApagaEgde,
                        btnTest));
        initLoop();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1020, 670));
    }

    private void initMyComponents() {
        panelBotao = new JPanel();

        btnCreateEgdeTipo1 = new JButton();
        btnCreateEgdeTipo1.setIcon(new ImageIcon("imagens/CriarRua.png"));
        btnSelecionaEgde = new JButton();
        btnSelecionaEgde.setIcon(new ImageIcon("imagens/SelecaoNodo.png"));
        btnApagaEgde = new JButton();
        btnApagaEgde.setIcon(new ImageIcon("imagens/Apagar.png"));
        btnTest = new JButton();
        btnTest.setIcon(new ImageIcon("imagens/CriarRua.png"));

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panelBotao.setLayout(new GridBagLayout());

        adicionaComponente("as", "comp1", btnCreateEgdeTipo1, 0, 0);
        adicionaComponente("aszzz", "comp2", btnSelecionaEgde, 1, 0);
        adicionaComponente("aszz", "comp3", btnApagaEgde, 2, 0);
        adicionaComponente("asz", "comp4", btnTest, 3, 500);

        areaDesenho = new AreaDesenho();

        Dimension d = new Dimension(800, 600);

        areaDesenho.setSize(d);
        areaDesenho.setPreferredSize(d);
        areaDesenho.setMaximumSize(d);
        areaDesenho.setMinimumSize(d);

        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());

        content.add(areaDesenho, BorderLayout.CENTER);
        content.add(panelBotao, BorderLayout.WEST);

    }

    /**
     * Adiciona um componente personalizado a tela
     *
     * @param nome - nome a ser adicionado ao label.
     * @param componente - componente a ser adicionado.
     */
    public void adicionaComponente(String nome, String titulo, JComponent componente, int position, int isset) {

        JLabel label = new JLabel(titulo);
        label.setName(nome);
        label.setPreferredSize(new Dimension(100, 20));
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.fill = GridBagConstraints.HORIZONTAL;
        cConstraints.gridy = position;
        cConstraints.weightx = 0.9f;
        if (isset <= 0) {
            cConstraints.insets = new Insets(2, 1, 2, 4);
        } else {
            cConstraints.insets = new Insets(2, 1, isset, 4);
        }
        panelBotao.add(componente, cConstraints);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void setMouseListener(EventListener mouse) {
        areaDesenho.setMouseListener(mouse);
    }

    public void setFuntion(IFuntion funtion) {
        this.funtion = funtion;
    }

    public ObservadorTelaDesenho getObservadorTelaDesenho() {
        return (ObservadorTelaDesenho) areaDesenho;
    }

    private void initLoop() {
        new Thread() {
            @Override
            public void run() {
                // Game Loop: Nystrom, 2014

                long MS_PER_FRAME = 16; //  16 ms/frame = 60 FPS
                long last = System.currentTimeMillis();
                while (true) {

                    long now = System.currentTimeMillis();

                    if (now - last > MS_PER_FRAME) {
                        last = now;

                        processInput();
                        update();
                        render();

                    }

                }
            }

            private synchronized void processInput() {
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).processInput();
                }
            }

            private synchronized void render() {
                areaDesenho.clearListSpriteDateNetwork();
                areaDesenho.clearListSpriteFuntion();
                ControlDateNetwork.getInstance().getAllEgde().stream().forEach(element -> {
                    areaDesenho.addSpriteDateNetwork("NodeView", new float[]{element.de().getX(), element.de().getY()},
                            Color.BLACK);
                    areaDesenho.addSpriteDateNetwork("EgdeView", new float[]{element.de().getX(), element.de().getY(),
                        element.para().getX(), element.para().getY()}, Color.BLACK);
                    areaDesenho.addSpriteDateNetwork("NodeView", new float[]{element.para().getX(), element.para().getY()},
                            Color.BLACK);
                });
                //Verifica se é uma funcao de render
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).render();
                }
                areaDesenho.repaint();
            }

            private synchronized void update() {
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).update();
                }
            }
        }.start();
    }

    public boolean isAFuntionRequiresSuportLoop() {
        Class<?>[] interfaces = funtion.getClass().getInterfaces();
        for (int i = 0; i < funtion.getClass().getInterfaces().length; i++) {
            if (interfaces[i].getName().equals(ILoop.class.getName())) {
                return true;
            }
        }
        return false;
    }
}

package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
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
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Color;
import java.util.EventListener;
import javax.swing.BorderFactory;

/**
 *
 * @author Gustavo de Carvalho Santos
 */
public class TelaComBotoes extends JInternalFrame {

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private JButton btnCreateEgdeTipo1, btnSelecionaEgde, btnApagaEgde, btnTest, btnMove;
    private JPanel jpButao;
    private final ListenersTelaComBotoes listeners;
    private JPanel jpLocation;
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
                        btnTest,
                        btnMove));
        initLoop();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1017, 670));
    }

    private void initMyComponents() {
        jpButao = new JPanel();

        btnCreateEgdeTipo1 = new JButton();
        btnCreateEgdeTipo1.setIcon(new ImageIcon("imagens/CriarRua.png"));
        btnSelecionaEgde = new JButton();
        btnSelecionaEgde.setIcon(new ImageIcon("imagens/SelecaoNodo.png"));
        btnApagaEgde = new JButton();
        btnApagaEgde.setIcon(new ImageIcon("imagens/Apagar.png"));
        btnTest = new JButton();
        btnTest.setIcon(new ImageIcon("imagens/CriarRua.png"));
        btnMove = new JButton("MOVE");

        layout = new GridBagLayout();
        jpButao.setLayout(layout);

        addBTNJPanel("as", "comp1", btnCreateEgdeTipo1, 0, 0);
        addBTNJPanel("aszzz", "comp2", btnSelecionaEgde, 1, 0);
        addBTNJPanel("aszz", "comp3", btnApagaEgde, 2, 0);
        addBTNJPanel("asz", "comp4", btnMove, 3, 0);
        addBTNJPanel("asz", "comp4", btnTest, 4, 250);
        Dimension d = new Dimension(25, 35);
        btnMove.setSize(d);
        btnMove.setPreferredSize(d);
        btnMove.setMinimumSize(d);
        btnMove.setMaximumSize(d);
        areaDesenho = new AreaDesenho();

        jpLocation = new JPLocation(areaDesenho);

        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        areaDesenho.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        jpButao.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        content.add(areaDesenho, BorderLayout.CENTER);
        content.add(jpButao, BorderLayout.WEST);
        content.add(jpLocation, BorderLayout.SOUTH);

    }

    /**
     * Adiciona um componente personalizado a tela
     *
     * @param nome - nome a ser adicionado ao label.
     * @param componente - componente a ser adicionado.
     */
    public void addBTNJPanel(String nome, String titulo, JComponent componente, int position, int isset) {
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
        jpButao.add(componente, cConstraints);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

    public void setMouseListener(MouseManeger mouse) {
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
                try {
                    ControlDateNetwork.getInstance().getAllEgde().forEach(egde -> {
                        //Render Egde
                        areaDesenho.addSpriteDateNetwork("EgdeView",
                                new float[]{egde.x1(), egde.y1(), egde.x2(), egde.y2()},
                                Color.GRAY);

                        //Render Node De
                        areaDesenho.addSpriteDateNetwork("NodeView",
                                new float[]{egde.x1(), egde.y1()},
                                Color.RED);

                        //Render Node Para
                        areaDesenho.addSpriteDateNetwork("NodeView",
                                new float[]{egde.x2(), egde.y2()},
                                Color.RED);
                    });
                    if (funtion != null && isAFuntionRequiresSuportLoop()) {
                        ((ILoop) funtion).render();
                    }
                    areaDesenho.repaint();
                } catch (Exception e) {
                }

            }

            private synchronized void update() {
                if (HelpLocator.getNetworkHeight() == 0 || HelpLocator.getNetworkWidth() == 0) {
                    HelpLocator.setNetworkWidth(areaDesenho.getWidth());
                    HelpLocator.setNetworkHeight(areaDesenho.getHeight());
                }
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).update();
                }
            }
        }.start();
    }

    //Verifica se é uma funcao de render    
    private boolean isAFuntionRequiresSuportLoop() {
        Class<?>[] interfaces = funtion.getClass().getInterfaces();
        for (int i = 0; i < funtion.getClass().getInterfaces().length; i++) {
            if (interfaces[i].getName().equals(ILoop.class.getName())) {
                return true;
            }
        }
        return false;
    }
}

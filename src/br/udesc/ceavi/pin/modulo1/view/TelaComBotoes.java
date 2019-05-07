package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.ControlTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.funtion.IFuntion;
import br.udesc.ceavi.pin.modulo1.view.listeners.ListenersTelaComBotoes;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.funtion.ILoop;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;

/**
 *
 * @author Gustavo de Carvalho Santos
 */
public class TelaComBotoes extends JInternalFrame implements ObservadorDateNetwork{

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private JButton btnCreateEgdeTipo1;
    private JButton btnCreateEgdeTipo2;
    private JButton btnSelecionaEgde;
    private JButton btnSelecionaNode;
    private JButton btnApagaEgde;
    private JButton btnAddDemanda;
    private JButton btnSetTypeEgde;
    private JButton btnCriarAletoriamenteEgde;
    private JButton btnMove;

    private JPanel jpButao;
    private final ListenersTelaComBotoes listeners;
    private JPanel jpLocation;
    private AreaDesenho areaDesenho;

    private IFuntion funtion;
    private ControlDateNetwork dateNetwork;

    public TelaComBotoes() {
        initComponents();
        initMyComponents();
        this.setVisible(true);
        dateNetwork = ControlDateNetwork.getInstance();
        dateNetwork.addObservador(this);
        List<JButton> listBtn = new ArrayList<>();
        for (int i = 0; i < jpButao.getComponents().length; i++) {
            listBtn.add((JButton) jpButao.getComponents()[i]);
        }
        listeners = new ListenersTelaComBotoes(this, listBtn);
        initLoop();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1017, 670));

    }

    private void initMyComponents() {
        jpButao = new JPanel();
        btnCreateEgdeTipo1 = new JButton("Create Egde 1");
        btnCreateEgdeTipo2 = new JButton("Create Egde 2");
        btnSelecionaEgde = new JButton("Selecionar Egde");
        btnSelecionaNode = new JButton("Selecionar Node");
        btnApagaEgde = new JButton("Deletar Egde");
        btnMove = new JButton("Mover Tela");
        btnAddDemanda = new JButton("Create Demanda");
        btnSetTypeEgde = new JButton("Set Egde Type");
        btnCriarAletoriamenteEgde = new JButton("Teste");

        layout = new GridBagLayout();
        jpButao.setLayout(layout);

        addBTNJPanel("FuntionCreateEgdeTipo1", btnCreateEgdeTipo1, 0, 0);
        addBTNJPanel("FuntionCreateEgdeTipo2", btnCreateEgdeTipo2, 1, 0);
        addBTNJPanel("FuntionSelecionarEgde", btnSelecionaEgde, 2, 0);
        addBTNJPanel("FuntionSelecionarNode", btnSelecionaNode, 3, 0);
        addBTNJPanel("FuntionRemoverEgde", btnApagaEgde, 4, 0);
        addBTNJPanel("FuntionMoveTela", btnMove, 5, 0);
        addBTNJPanel("FuntionCreateDemanda", btnAddDemanda, 6, 0);
        addBTNJPanel("FuntionCreateType", btnSetTypeEgde, 7, 0);
        addBTNJPanel("FuntionCriarAletoriamenteEgde", btnCriarAletoriamenteEgde, 8, 250);

        Dimension d = new Dimension(25, 35);
        btnMove.setSize(d);
        btnMove.setPreferredSize(d);
        btnMove.setMinimumSize(d);
        btnMove.setMaximumSize(d);
        areaDesenho = new AreaDesenho();
        int size = 1;
        ControlTelaDesenho achaSize = new ControlTelaDesenho(size * 800, size * 600);
        System.out.println("Escala : " + achaSize.getEscala());
        d = new Dimension(achaSize.getwSizeTela(), achaSize.gethSizeTela());
        HelpLocator.setEscala(achaSize.getEscala());
        areaDesenho.setSize(d);
        areaDesenho.setPreferredSize(d);
        areaDesenho.setMinimumSize(d);
        areaDesenho.setMaximumSize(d);
        jpLocation = new JPLocation(areaDesenho);

        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());
        areaDesenho.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        content.setLayout(layout);
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        content.add(jpButao, cons);
        cons = new GridBagConstraints();
        cons.gridx = 1;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        content.add(areaDesenho, cons);
        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 1;
        cons.insets = new Insets(5, 5, 5, 5);
        cons.gridwidth = 2;
        content.add(jpLocation, cons);
    }

    /**
     * Adiciona um componente personalizado a tela
     *
     * @param nome - nome a ser adicionado ao label.
     * @param componente - componente a ser adicionado.
     */
    private GridBagConstraints cons;

    public void addBTNJPanel(String nome, JComponent componente, int position, int isset) {
        cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridy = position;
        cons.weightx = 0.9f;
        componente.setName(nome);
        if (isset <= 0) {
            cons.insets = new Insets(2, 1, 2, 4);
        } else {
            cons.insets = new Insets(2, 1, isset, 4);
        }
        jpButao.add(componente, cons);
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

            private void processInput() {
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).processInput();
                }
            }

            private void render() {
                areaDesenho.clearListSpriteFuntion();
                try {

                    if (funtion != null && isAFuntionRequiresSuportLoop()) {
                        ((ILoop) funtion).render();
                    }
                    areaDesenho.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private synchronized void update() {
                if(HelpLocator.getNetworkHeight() == 0){
                    HelpLocator.setNetworkHeight(areaDesenho.getHeight());
                    HelpLocator.setNetworkWidth(areaDesenho.getWidth());
                }
                if (funtion != null && isAFuntionRequiresSuportLoop()) {
                    ((ILoop) funtion).update();
                }
            }
        }.start();
    }

    //Verifica se é uma funcao de render    
    private boolean isAFuntionRequiresSuportLoop() {
        for (Class<?> isALoop : funtion.getClass().getInterfaces()) {
            if (isALoop.getName().equals(ILoop.class.getName())) {
                return true;
            }
        }
        return false;
    }

    public void setAllJButtonAtivo() {
        for (Component component : jpButao.getComponents()) {
            component.setEnabled(true);
        }
    }

    @Override
    public void notifyAlteracao() {
        areaDesenho.clearListSpriteDateNetwork();
        dateNetwork.getAllEgde().forEach(egde -> {
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
    }
}

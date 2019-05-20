package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
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
import javax.swing.JButton;
import javax.swing.JComponent;
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
public class TelaComBotoes extends ViewJanelaSistema implements ObservadorDateNetwork {

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
    private ListenersTelaComBotoes listeners;
    private JPanel jpLocation;
    private AreaDesenho areaDesenho;

    private IFuntion funtion;
    private ControlDateNetwork dateNetwork;

    public TelaComBotoes(int largura, int altura) {
        initAreaDesenho(largura, altura);
        initComponents();
        initMyComponents();
        initListener();
        dateNetwork = ControlDateNetwork.getInstance();
        dateNetwork.addObservador(this);
        abrirJanela();
        initLoop();
    }

    private void initListener() {
        List<JButton> listBtn = new ArrayList<>();
        for (Component component : jpButao.getComponents()) {
            listBtn.add((JButton) component);
        }
        listeners = new ListenersTelaComBotoes(this, listBtn);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(ViewPrincipal.TAMANHO_PADRAO);
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
        addBTNJPanel("FuntionTest", btnCriarAletoriamenteEgde, 8, 250);

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

    private void initAreaDesenho(int largura, int alura) {
        Dimension d;
        areaDesenho = new AreaDesenho();
        d = new Dimension(largura, alura);
        areaDesenho.setSize(d);
        areaDesenho.setPreferredSize(d);
        areaDesenho.setMinimumSize(d);
        areaDesenho.setMaximumSize(d);
        jpLocation = new JPLocation(areaDesenho);
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
                    ControllerDesktop c = ControllerDesktop.getInstance();
                    for(ViewJanelaSistema v : c.getJanelas()) {
                        System.out.println(v.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            private synchronized void update() {
                if (HelpLocator.getTelaDesenhoHeight() == 0) {
                    HelpLocator.setSizeTelaDesenhoHeight(areaDesenho.getHeight());
                    HelpLocator.setSizeTelaDesenhoWidth(areaDesenho.getWidth());
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

    @Override
    public void abrirJanela() {
        setVisible(true);
        this.toBack();
        this.moveToBack();
    }

    @Override
    public void fechaJanela() {
        setVisible(false);
    }

    @Override
    public void destruirInstanciaJanela() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        this.dispose();
    }
}

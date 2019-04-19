package br.udesc.ceavi.pin.modulo1.view;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Gustavo de Carvalho Santos
 */
public class TelaComBotoes extends JInternalFrame {

    private GridBagConstraints constraints;
    private GridBagLayout layout;

    private JButton btnCria, btnSeleciona, btnApaga, btnTest;
    private JPanel jPanel;
    private TelaDesenho frame;

    public TelaComBotoes() {
        initComponents();
        initMyComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1020, 670));
    }

    private void initMyComponents() {
        jPanel = new JPanel();

        btnCria = new JButton();
        btnCria.setIcon(new ImageIcon("imagens/Nodo.png"));
        btnSeleciona = new JButton();
        btnSeleciona.setIcon(new ImageIcon("imagens/SelecaoNodo.png"));
        btnApaga = new JButton();
        btnApaga.setIcon(new ImageIcon("imagens/Apagar.png"));
        btnTest = new JButton();
        btnTest.setIcon(new ImageIcon("imagens/CriarRua.png"));

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        jPanel.setLayout(new GridBagLayout());

        adicionaComponente("as", "comp1", btnCria, 0, 0);
        adicionaComponente("aszzz", "comp2", btnSeleciona, 1, 0);
        adicionaComponente("aszz", "comp3", btnApaga, 2, 0);
        adicionaComponente("asz", "comp4", btnTest, 3, 500);

        frame = new TelaDesenho();

        Dimension d = new Dimension(800, 600);

        frame.setSize(d);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);

        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());

        content.add(frame, BorderLayout.CENTER);
        content.add(jPanel, BorderLayout.WEST);

        btnCria.addActionListener((e) -> frame.criarTrecho());
        btnSeleciona.addActionListener((e) -> frame.selecionarTrecho());
        btnApaga.addActionListener((e) -> frame.apagarTrecho());
        btnTest.addActionListener((e) -> frame.test());
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
        jPanel.add(componente, cConstraints);
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(null);
    }

}

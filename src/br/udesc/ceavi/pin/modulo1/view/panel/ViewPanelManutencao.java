package br.udesc.ceavi.pin.modulo1.view.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Drew
 */
public class ViewPanelManutencao extends JPanel {

    public static final int TIPO_CAMPO_TEXTO = 1;
    public static final int TIPO_CAMPO_INTEIRO = 2;
    public static final int TIPO_CAMPO_FLUTUANTE = 3;
    public static final int TIPO_CAMPO_DATA = 4;
    public static final int TIPO_CAMPO_DATA_HORA = 5;
    public static final int TIPO_CAMPO_HORA = 6;

    // CAMPOS
    private GridBagLayout layout;
    private List<JComponent[]> campos;
    private List<Integer> tipos;

    /**
     * Cria um novo paínel de manutenção
     *
     * @param title - titulo do painel.
     */
    public ViewPanelManutencao() {
        super();
        layout = new GridBagLayout();
        this.setLayout(layout);
        campos = new ArrayList<JComponent[]>();
        tipos = new ArrayList<Integer>();
    }

    /**
     * Adiciona um campo padrão com o nome especificado na tela.
     *
     * @param nome - nome do campo.
     * @return 
     */
    public JTextField adicionaCampo(String nome, String titulo) {
        JLabel label = new JLabel(titulo);
        label.setName(nome);
        label.setPreferredSize(new Dimension(100, 20));
        JTextField campo = new JTextField();

        GridBagConstraints lConstraints = new GridBagConstraints();
        lConstraints.fill = GridBagConstraints.HORIZONTAL;
        lConstraints.gridx = 0;
        lConstraints.insets = new Insets(2, 10, 2, 4);
        this.add(label, lConstraints);
        
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.fill = GridBagConstraints.HORIZONTAL;
        cConstraints.gridx = 1;
        cConstraints.weightx = 0.5f;
        cConstraints.insets = new Insets(2, 1, 2, 10);
        this.add(campo, cConstraints);

        campos.add(new JComponent[]{label, campo});
        return campo;
    }

    /**
     * Adiciona um componente personalizado a tela
     *
     * @param nome - nome a ser adicionado ao label.
     * @param componente - componente a ser adicionado.
     */
    public void adicionaComponente(String nome, String titulo, JComponent componente) {
        JLabel label = new JLabel(titulo);
        label.setName(nome);
        label.setPreferredSize(new Dimension(100, 20));

        GridBagConstraints lConstraints = new GridBagConstraints();
        lConstraints.fill = GridBagConstraints.HORIZONTAL;
        lConstraints.gridx = 0;
        lConstraints.insets = new Insets(2, 10, 2, 4);
        this.add(label, lConstraints);

        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.fill = GridBagConstraints.HORIZONTAL;
        cConstraints.gridx = 1;
        cConstraints.weightx = 0.5f;
        cConstraints.insets = new Insets(2, 1, 2, 10);
        this.add(componente, cConstraints);

        campos.add(new JComponent[]{label, componente});
    }

    public void adicionaComponenteAreaTexto(String nome, String titulo, JComponent componente) {
        JLabel label = new JLabel(titulo);
        label.setName(nome);
        label.setPreferredSize(new Dimension(100, 20));

        GridBagConstraints lConstraints = new GridBagConstraints();
        lConstraints.fill = GridBagConstraints.HORIZONTAL;
        lConstraints.gridx = 0;
        lConstraints.weightx = 0.1f;
        lConstraints.insets = new Insets(2, 1, 2, 4);
        GridBagConstraints cConstraints = new GridBagConstraints();
        cConstraints.fill = GridBagConstraints.HORIZONTAL;
        cConstraints.gridx = 1;
        cConstraints.weightx = 0.9f;
        cConstraints.weighty = 20f;
        cConstraints.ipadx = 50;
        cConstraints.ipady = 500;
        cConstraints.insets = new Insets(2, 1, 2, 4);

        this.add(label, lConstraints);
        this.add(componente, cConstraints);
        campos.add(new JComponent[]{label, componente});
    }

    /**
     * Busca um campo pelo nome.
     *
     * @param nome - nome do campo.
     */
    public JComponent[] getCampo(String nome) {
        for (JComponent[] campo : campos) {
            if (campo[0].getName().equals(nome)) {
                return campo;
            }
        }
        return new JComponent[]{};
    }

    /**
     * Retorna o modelo com os valores da tela.
     *
     * @param model - modelo para armazenar os dados.
     * @return Objeto com os valores da tela.
     */
    public void getValores(Object model) throws Exception {
        Class cls = model.getClass();
        for (int i = 0; i < campos.size(); i++) {
            JComponent[] campo = campos.get(i);
            char[] nm = campo[0].getName().toCharArray();

            nm[0] = Character.toUpperCase(nm[0]);
            Object val;
            Method mtd;
            if (campo[1] instanceof JTextField) {
                String txt = ((JTextField) campo[1]).getText();
                switch (tipos.get(i)) {
                    case ViewPanelManutencao.TIPO_CAMPO_INTEIRO:
                        val = Integer.parseInt(txt);
                        break;
                    case ViewPanelManutencao.TIPO_CAMPO_FLUTUANTE:
                        val = Float.parseFloat(txt);
                        break;
                    case ViewPanelManutencao.TIPO_CAMPO_DATA:
                        try {
                            val = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(txt);
                        } catch (Exception ex) {
                            throw new Exception("deu ruim");
                        }
                        break;
                    case ViewPanelManutencao.TIPO_CAMPO_TEXTO:
                    default:
                        val = txt;
                        break;
                }
            } else if (campo[1] instanceof JCheckBox) {
                val = ((JCheckBox) campo[1]).isSelected();
            } else if (campo[1] instanceof JList<?>) {
                JList c = (JList) campo[1];
                if (c.getSelectionMode() == ListSelectionModel.SINGLE_SELECTION) {
                    val = c.getSelectedValue();
                } else {
                    val = new HashSet(c.getSelectedValuesList());
                }
            } else {
                throw new Exception("Campo não implementado.");
            }
            try {
                mtd = cls.getMethod("set" + new String(nm), new Class[]{val.getClass()});
                mtd.invoke(model, new Object[]{val});
            } catch (Exception ex) {
                throw new Exception("Deu Ruim");
            }
        }
    }
}

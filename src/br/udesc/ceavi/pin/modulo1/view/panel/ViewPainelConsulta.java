package br.udesc.ceavi.pin.modulo1.view.panel;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameDadosEdge;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersDadosEdge;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Drew
 */
public class ViewPainelConsulta extends JScrollPane {

    //CONSTANTES
    public static final int PAINEL_CONSULTA_COLUNA_TAMANHO_MINIMO = 20;

    // CAMPOS
    private JTable tabela;
    private DefaultTableModel modelo;
    private List<String> campos;
    private List<Integer> tamanho;
    private List<Egde> edge;

    /**
     * Cria um novo paínel de consulta
     *
     * @param title - titulo do painel.
     */
    public ViewPainelConsulta(String title) {
        super();
        this.setBorder(BorderFactory.createTitledBorder(title));
        modelo = new DefaultTableModel(0, 0);
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setViewportView(tabela);
        tamanho = new ArrayList<Integer>();
        campos = new ArrayList<String>();
    }

    /**
     * Adiciona um campo padrão com o nome especificado na consulta.
     *
     * @param titulo
     * @param nome - nome do campo.
     * @param tamanho - tamanho do campo, -1 para calcular automaticamente.
     */
    public void adicionaCampo(String titulo, String nome, int tamanho) {
        campos.add(nome);
        modelo.addColumn(titulo);
        this.tamanho.add(tamanho);

    }

    /**
     * Efetua o ajuste do tamanho da tabela.
     */
    public void ajustaTabela() {
        for (int i = 0; i < tamanho.size(); i++) {
            if (tamanho.get(i) > -1) {
                tabela.getColumnModel().getColumn(i).setPreferredWidth(tamanho.get(i));
            }
        }
    }

    /**
     * Limpa os dados da tela.
     */
    public void limpaValores() {
        modelo.setRowCount(0);
    }

    /**
     * Retorna valores da seleção
     *
     * @return
     */
    public String[][] getValoresSelecao() {
        int linha = tabela.getSelectedRow();
        List<String[]> val = new ArrayList<String[]>();
        if (linha != -1) {
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                if (modelo.getValueAt(linha, i) != null) {
                    val.add(new String[]{campos.get(i), modelo.getValueAt(linha, i).toString()});
                }
            }
        }
        return val.toArray(new String[0][]);
    }

    /**
     * Adiciona uma linha a tela.
     *
     * @param model Objeto com os valores.
     */
    public void setValores(Object model) throws Exception {
        Class cls = model.getClass();
        String[] val = new String[campos.size()];
        int j = 0;
        for (int i = 0; i < campos.size(); i++) {
            char[] nm = campos.get(i).toCharArray();
            nm[0] = Character.toUpperCase(nm[0]);
            Method mtd;
            try {
                mtd = cls.getMethod("get" + new String(nm), new Class[]{});
                val[j] = mtd.invoke(model, new Object[]{}).toString();
                j++;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        modelo.addRow(val);

    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setListaEdge(List<Egde> lista) {
        edge = lista;
    }

    private Egde getEdge(String id) {
//            for (Egde edg : edge) {
////                if(edg.getID().equals(id)) {
////                    return edg;
////                }
//            }

        return null;
    }

}

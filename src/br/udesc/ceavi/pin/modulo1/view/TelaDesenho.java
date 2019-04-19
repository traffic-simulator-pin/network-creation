package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.control.ControlDesenho;
import br.udesc.ceavi.pin.modulo1.control.Function;
import br.udesc.ceavi.pin.modulo1.control.IControlDesenho;
import br.udesc.ceavi.pin.modulo1.control.Observador;
import br.udesc.ceavi.pin.modulo1.help.LineHelp;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 04/04/2019
 *
 */
public class TelaDesenho extends JComponent implements Observador {

    private IControlDesenho control;
    private List<Linha> listLinha;
    private List<Ponto> listPonto;
    private MouseAdapter mouseAdapter;
    private String egdeSize = "";

    public TelaDesenho() {
        setDoubleBuffered(false);
        control = new ControlDesenho();
        control.addObservador(this);
        listLinha = new ArrayList<>();
        listPonto = new ArrayList<>();
        criarTrecho();
    }

    public void criarTrecho() {
        removeMouseListener(mouseAdapter);
        control.setFuncao(Function.CREATE);
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.criarNodo(getMousePosition());
            }
        };
        addMouseListener(mouseAdapter);
    }

    public void selecionarTrecho() {
        removeMouseListener(mouseAdapter);
        control.setFuncao(Function.SELECT);
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.selecionarEgde(getMousePosition());
            }
        };
        addMouseListener(mouseAdapter);
    }

    public void apagarTrecho() {
        try {
            control.setFuncao(Function.REMOVE);
            control.apagarEgde();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            selecionarTrecho();
        }
    }

    public void test() {
        control.test();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        List<Linha> l = new ArrayList<>();
        l.addAll(listLinha);
        List<Ponto> p = new ArrayList<>();
        p.addAll(listPonto);
        for (Linha linha : l) {
            linha.draw(g);
        }
        for (Ponto ponto : p) {
            ponto.draw(g);
        }
        Font font = new Font("Consolas", Font.BOLD, 14);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        g.drawString(egdeSize, getWidth() - (metrics.stringWidth(egdeSize) + 5),
                20);
    }

    @Override
    public void addLine(float x1, float y1, float x2, float y2, Color cor) {
        listLinha.add(new Linha(x1, y1, x2, y2, cor));
    }

    @Override
    public void addPoint(int x, int y, Color cor) {
        listPonto.add(new Ponto(x, y, cor));
    }

    @Override
    public void clear() {
        listLinha.clear();
        listPonto.clear();
    }

    @Override
    public void repaitTela() {
        repaint();
    }

    @Override
    public void apagarEgdes() {
        int a = JOptionPane.showConfirmDialog(this,
                "Deseja Apagar O(s) Rua(s) Selecionados",
                "Apagar Rua(s)?", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            control.deleteEgdeSelecionados();
        } else {
            control.deleteSelection();
        }
    }

    @Override
    public void mousePositionResquest(float x1, float y1, boolean ativo) {
        try {
            if (ativo) {
                final int x2 = getMousePosition().x;
                final int y2 = getMousePosition().y;
                this.listLinha.add(new Linha(x1, y1, x2, y2,
                        Color.BLUE));
                if (!control.isAExtremePointToEgde(x2, y2)) {
                    this.listPonto.add(new Ponto(x2, y2, Color.BLUE));
                }
                egdeSize = "" + (LineHelp.getSizeLine(x1, x2, y1, y2) / 2.5) + " m";
            } else {
                egdeSize = "";
            }
        } catch (Exception e) {
        }
    }

}

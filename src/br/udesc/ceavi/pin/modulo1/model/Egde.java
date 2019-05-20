package br.udesc.ceavi.pin.modulo1.model;

import br.udesc.ceavi.pin.modulo1.help.HelpLine;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import static br.udesc.ceavi.pin.modulo1.model.Node.SIZE;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Egde {

    public static int idNaoUsuado = 1;

    private final String ID;
    private final Node de;
    private final Node para;
    private final float width;
    private String nome;
    private Type type;
    private Line2D linha;
    private double conficienteAngula, conficienteLiner;

    public Egde(Node de, Node para) {
        this.ID = "" + idNaoUsuado;
        Egde.idNaoUsuado++;
        this.de = de;
        this.para = para;
        this.width = HelpLine.getSizeLine(x1(), x2(), y1(), y2());
        initLinha();
        initAssociacao();
    }

    public Egde(String id, Node de, Node para, float width) {
        this.ID = id;
        this.de = de;
        this.para = para;
        this.width = width;
        initLinha();
        initAssociacao();
    }

    public void initAssociacao() {
        de.associarNodeEgde(this);
        para.associarNodeEgde(this);
    }

    private void initLinha() {
        this.linha = new Line2D.Double(x1(), y1(), x2(), y2());
        this.conficienteAngula = HelpLine.getConficienteAngula(linha);
        this.conficienteLiner = HelpLine.getConficienteLiner(conficienteAngula, linha);
    }

    public boolean havePointInLine(Point p) {
        int size = (int) ((8 / HelpLocator.getZOOM()));
        Rectangle emBusca = new Rectangle(p.x - size / 2, p.y - size / 2, size, size);
        return linha.intersects(emBusca);
    }

    @Override
    public String toString() {
        return "id: " + ID + " {" + "\n        De:" + de + ",\n        Para:" + para + "\n    } + tamanho: " + width + "\n";
    }

    public float x1() {
        return de.getX();
    }

    public float x2() {
        return para.getX();
    }

    public float y1() {
        return de.getY();
    }

    public float y2() {
        return para.getY();
    }

    public Node de() {
        return de;
    }

    public Node para() {
        return para;
    }

    public Line2D getLinha() {
        return linha;
    }

    public double getConficienteAngula() {
        return conficienteAngula;
    }

    public double getConficienteLiner() {
        return conficienteLiner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Egde other = (Egde) obj;
        return other.getLinha().equals(this.getLinha());
    }

    public void setType(Type type, String nome) {
        this.type = type;
        this.nome = nome;
    }

    public Type getType() {
        return type;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return ID;
    }

    public float getWidth() {
        return width;
    }

}

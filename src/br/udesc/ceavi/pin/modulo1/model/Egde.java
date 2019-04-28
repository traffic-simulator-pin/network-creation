package br.udesc.ceavi.pin.modulo1.model;

import br.udesc.ceavi.pin.modulo1.help.HelpLine;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Egde {

    private static int idNaoUsado = 1;

    private final String ID;
    private final Node de;
    private final Node para;
    private final float tamanho;
    private String nome;
    private Type type;
    private Line2D linha;
    private double conficienteAngula, conficienteLiner;

    public Egde(Node de, Node para) {
        this.ID = "" + idNaoUsado;
        Egde.idNaoUsado++;
        this.de = de;
        this.para = para;
        this.tamanho = 0;
        initLinha();
        initAssociacao();
    }

    public void initAssociacao() {
        de.associarNodeEgde(this);
        para.associarNodeEgde(this);
    }

    public Egde(Node de, Node para, float tamanho) {
        this.ID = "" + idNaoUsado;
        Egde.idNaoUsado++;
        this.de = de;
        this.para = para;
        this.tamanho = tamanho;
        initLinha();
        initAssociacao();
    }

    private void initLinha() {
        this.linha = new Line2D.Double(x1(), y1(), x2(), y2());
        this.conficienteAngula = HelpLine.getConficienteAngula(linha);
        this.conficienteLiner = HelpLine.getConficienteLiner(conficienteAngula, linha);
    }

    public boolean havePointInLine(Point p) {
        Rectangle emBusca = new Rectangle(p.x - Node.SIZE / 2, p.y - Node.SIZE / 2, Node.SIZE, Node.SIZE);
        return linha.intersects(emBusca);
    }

    @Override
    public String toString() {
        return "id: " + ID + " {" + "\n        De:" + de + ",\n        Para:" + para + "\n    }";
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

}

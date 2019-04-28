package br.udesc.ceavi.pin.modulo1.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Node {

    public static final int SIZE = 6;

    private final String ID;
    private final float x;
    private final float y;

    private static int idNaoUsuado = 1;
    private final Rectangle area;

    private List<Egde> listDeEgdeQuePertenco = new ArrayList<>();

    public Node(float x, float y) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.x = x;
        this.y = y;
        this.area = new Rectangle((int) this.x, (int) this.y, SIZE, SIZE);
    }

    public Node(Node n) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.x = n.getX();
        this.y = n.getY();
        this.area = new Rectangle((int) this.x, (int) this.y, SIZE, SIZE);
    }

    public Node(float x, float y, int sizeNodo) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.x = x;
        this.y = y;
        this.area = new Rectangle((int) this.x, (int) this.y, sizeNodo, sizeNodo);
    }

    @Override
    public String toString() {
        return x + " : " + y;
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
        final Node other = (Node) obj;
        if (this.x != other.getX()) {
            return false;
        }
        if (this.y != other.getY()) {
            return false;
        }
        return this.ID == other.getID();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getID() {
        return ID;
    }

    public Rectangle getArea() {
        return new Rectangle(area);
    }

    public boolean collideWithMyArea(float x2, float y2) {
        x2 -= 3;
        y2 -= 3;
        for (int emX = 0; emX < 5; emX++) {
            for (int emY = 0; emY < 5; emY++) {
                if ((x2 + emX) == this.x && (y2 + emY) == this.y) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean IamInArea(int X, int Y, int W, int H) {
        return new Rectangle(X, Y, W, H).intersects(area);
    }

    public void associarNodeEgde(Egde e) {
        this.listDeEgdeQuePertenco.add(e);
    }

    public void quebrarAssociarNodeEgde(Egde e) {
        this.listDeEgdeQuePertenco.remove(e);
    }

    public boolean haveLinkEgde() {
        return !listDeEgdeQuePertenco.isEmpty();
    }
}

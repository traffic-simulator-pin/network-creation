package br.udesc.ceavi.pin.modulo1.model;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Node {

    public static final int SIZE = 8;

    private final String ID;
    private final float x;
    private final float y;

    private static int idNaoUsuado = 1;

    private List<Egde> listDeEgdeQuePertenco = new ArrayList<>();

    public Node(float x, float y) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.x = x;
        this.y = y;
    }

    public Node(float x, float y, int sizeNodo) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.x = x;
        this.y = y;
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
        return this.ID.equals(other.getID());
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

    public boolean collideWithMyArea(float x2, float y2) {
        //Transformaçoes necessarias para ajustar area 
        float w = (SIZE / HelpLocator.getZOOM()) * 2;
        float h = (SIZE / HelpLocator.getZOOM()) * 2;
        //Verificação da Intersseção de areas
        return new Rectangle2D.Float(x2 - (w / 2), y2 - (w / 2), w, h)
                .intersects(new Rectangle2D.Float(x - (w / 2), y - (w / 2), w, h));
    }

    public boolean IamInArea(int X, int Y, int W, int H) {
        float w = SIZE / HelpLocator.getZOOM();
        float h = SIZE / HelpLocator.getZOOM();
        return new Rectangle(X, Y, W, H).intersects(new Rectangle2D.Float(x - (w / 2), y - (w / 2), w, h));
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

package br.udesc.ceavi.pin.modulo1.model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;

public class Node {

    public static final int SIZE = 8;

    private static long idNaoUsuado = 1;
    private final long id;

    private final float x;
    private final float y;

    private List<Egde> listDeEgdeQuePertenco = new ArrayList<>();

    public Node(float x, float y) {
        this.id = idNaoUsuado;
        idNaoUsuado++;
        this.x = x;
        this.y = y;
    }

    public Node(long id, float x, float y) {
        this.id = id;
        if (idNaoUsuado <= id) {
            idNaoUsuado = (id + 1);
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " : " + y;
    }


    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public long getId() {
        return id;
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

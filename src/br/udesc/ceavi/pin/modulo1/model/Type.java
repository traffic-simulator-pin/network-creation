package br.udesc.ceavi.pin.modulo1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public class Type {

    private static int idNaoUsuado = 1;

    private final String ID;
    private final List<Egde> listDeEgdeQuePertenco;
    private int numLanes;
    private boolean oneway;
    private float speed;
    private float width;

    public Type(List<Egde> listDeEgdeQuePertenco, int numLanes, boolean oneway, float speed, float width) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.listDeEgdeQuePertenco = new ArrayList<>();
        this.listDeEgdeQuePertenco.addAll(listDeEgdeQuePertenco);
        this.numLanes = numLanes;
        this.oneway = oneway;
        this.speed = speed;
        this.width = width;
    }

    public int getNumLanes() {
        return numLanes;
    }

    public void setNumLanes(int numLanes) {
        this.numLanes = numLanes;
    }

    public boolean isOneway() {
        return oneway;
    }

    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.ID);
        hash = 83 * hash + this.numLanes;
        hash = 83 * hash + (this.oneway ? 1 : 0);
        hash = 83 * hash + Float.floatToIntBits(this.speed);
        hash = 83 * hash + Float.floatToIntBits(this.width);
        return hash;
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
        final Type other = (Type) obj;
        if (this.numLanes != other.numLanes) {
            return false;
        }
        if (this.oneway != other.oneway) {
            return false;
        }
        if (Float.floatToIntBits(this.speed) != Float.floatToIntBits(other.speed)) {
            return false;
        }
        if (Float.floatToIntBits(this.width) != Float.floatToIntBits(other.width)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }

    public void quebrarAssociarTypeEgde(Egde e) {
        this.listDeEgdeQuePertenco.remove(e);
    }

    public boolean haveLinkEgde() {
        return !listDeEgdeQuePertenco.isEmpty();
    }

    public void associarTypeEgde(Egde e) {
        if (listDeEgdeQuePertenco == null) {
            listDeEgdeQuePertenco.add(e);
        }
        this.listDeEgdeQuePertenco.add(e);
    }
}

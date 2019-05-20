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

    public static int idNaoUsuado = 1;

    private final String ID;
    private List<Egde> listDeEgdeQuePertenco;
    private int numLanes;
    private boolean oneway;
    private float speed;

    public Type() {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.listDeEgdeQuePertenco = new ArrayList<>();
        this.numLanes = 0;
        this.oneway = false;
        this.speed = 0;
    }

    public Type(List<Egde> listDeEgdeQuePertenco, int numLanes, boolean oneway, float speed) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.listDeEgdeQuePertenco = new ArrayList<>();
        this.listDeEgdeQuePertenco.addAll(listDeEgdeQuePertenco);
        this.numLanes = numLanes;
        this.oneway = oneway;
        this.speed = speed;
    }

    public Type(String id,int numLanes, boolean oneway, float speed) {
        this.ID = id;
        this.listDeEgdeQuePertenco = new ArrayList<>();
        this.numLanes = numLanes;
        this.oneway = oneway;
        this.speed = speed;
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

    public void setListDeEgdeQuePertenco(List<Egde> edgs) {
        this.listDeEgdeQuePertenco = edgs;
    }

    public String getId() {
        return ID;
    }
}

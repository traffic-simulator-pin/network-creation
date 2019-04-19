package br.udesc.ceavi.pin.modulo1.model;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public class Type {

    private static int idNaoUsuado = 1;

    private final String ID;
    private int numLanes;
    private boolean oneway;
    private float speed;
    private float width;

    public Type(int numLanes, boolean oneway, float speed, float width) {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
        this.numLanes = numLanes;
        this.oneway = oneway;
        this.speed = speed;
        this.width = width;
    }

    public Type() {
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
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

}

package br.udesc.ceavi.pin.modulo1.model;

import java.util.Objects;

/**
 *
 * @author GustavoSantos
 * @since 16/04/2019
 *
 */
public class Demanda {

    public static int idNaoUsuado = 1;
    private final Node A, B;
    private final int demanda;
    private final String ID;

    public Demanda(Node A, Node B, int demanda) {
        this.A = A;
        this.B = B;
        this.demanda = demanda;
        this.ID = "" + idNaoUsuado;
        idNaoUsuado++;
    }

    public Demanda(String id, Node A, Node B, int demanda) {
        this.A = A;
        this.B = B;
        this.demanda = demanda;
        this.ID = id;
    }

    public Node getA() {
        return A;
    }

    public Node getB() {
        return B;
    }

    public String getId() {
        return ID;
    }

    public int getDemanda() {
        return demanda;
    }

    public boolean visivel(int x, int y, int w, int h) {
        return A.collideWithMyArea(x, y) || B.collideWithMyArea(x, y);
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
        final Demanda other = (Demanda) obj;
        if (!Objects.equals(this.A, other.A)) {
            return false;
        }
        if (!Objects.equals(this.B, other.B)) {
            return false;
        }
        return true;
    }

}

package br.udesc.ceavi.pin.modulo1.model;

import java.awt.Rectangle;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Node {

    public static final int SIZE = 6;

    private final String ID;
    private final float x;
    private final float y;

    private static int idNaoUsuado = 1;
    private List<Egde> egdes = new LinkedList<>();
    private final Rectangle area;

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

    public void associarEgde(Egde egde) {
        this.egdes.add(egde);
    }

    public Egde removerAssociaoEgde(Egde egde) {
        if (this.egdes.remove(egde)) {
            return egde;
        }
        return null;
    }

    /**
     * Verifica Se o Node passado pelo parametro esta invadindo a area do nodo
     * da chamada do metodos
     *
     * @param nodeAux nodo em anasise
     * @param size tamanho da para a nova area de interseção
     * @return true se houver subesposição, false se não
     */
    public boolean inMyArea(Node nodeAux, int size) {
        return nodeAux.getArea().intersects(new Rectangle((int) x - (size / 2), (int) y - (size / 2), size, size));
    }

    /**
     * Verifica Se o Node passado pelo parametro esta invadindo a area do nodo
     * da chamada do metodos
     *
     * @param nodeAux nodo em anasise
     * @return true se houver subesposição, false se não
     */
    public boolean inMyArea(Node nodeAux) {
        return inMyArea(nodeAux, Node.SIZE);
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
        return true;
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

    public List<Egde> getAllEgdes() {
        return egdes.stream().collect(Collectors.toList());
    }

    public Rectangle getArea() {
        return new Rectangle(area);
    }

    public boolean inMyArea(int x2, int y2) {
        return this.area.intersects(new Rectangle(x2 - (SIZE / 2), y2 - (SIZE / 2), SIZE, SIZE));
    }

}

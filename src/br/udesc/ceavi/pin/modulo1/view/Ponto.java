package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GustavoSantos
 * @since 18/04/2019
 *
 */
public class Ponto {

    private final int x, y;
    private final Color cor;

    public Ponto(int x, int y, Color cor) {
        this.x = x - Node.SIZE / 2;
        this.y = y - Node.SIZE / 2;
        this.cor = cor;
    }

    public void draw(Graphics g) {
        g.setColor(cor);
        g.fillOval(x, y, Node.SIZE, Node.SIZE);
    }
}

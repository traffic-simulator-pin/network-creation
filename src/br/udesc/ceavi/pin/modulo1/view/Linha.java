package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GustavoSantos
 * @since 18/04/2019
 *
 */
public class Linha {

    private final float x1, y1, x2, y2;
    private final Color cor;

    public Linha(float x1, float y1, float x2, float y2, Color cor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.cor = cor;
    }

    public void draw(Graphics g) {
        g.setColor(cor);
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }
}

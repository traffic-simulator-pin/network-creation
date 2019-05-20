package br.udesc.ceavi.pin.modulo1.view.sprites;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author GustavoSantos
 * @since 27/04/2019
 *
 */
public abstract class Sprite implements ISprite {

    protected float x, y;
    protected Color cor;
    protected float width, height;
    protected Dimension areaDaTelaDesenho;

    public Sprite(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setAreaDaTelaDesenho(Dimension areaDaTelaDesenho) {
        this.areaDaTelaDesenho = areaDaTelaDesenho;
    }

    @Override
    public void setColor(Color cor) {
        this.cor = cor;
    }

    @Override
    public boolean inAreaRender() {
        return new Rectangle.Float(0, 0,
                (areaDaTelaDesenho.width),
                (areaDaTelaDesenho.height)
        ).contains(x, y, width, height);
    }

}

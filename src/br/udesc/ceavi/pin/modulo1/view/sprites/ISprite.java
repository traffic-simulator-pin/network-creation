package br.udesc.ceavi.pin.modulo1.view.sprites;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author GustavoSantos
 * @since 23/04/2019
 *
 */
public interface ISprite {

    public abstract void draw(Graphics g);

    public void setDateLocation(float[] position);

    public void setColor(Color cor);
    
    public boolean inAreaRender(Dimension areaDaTelaDesenho);

}

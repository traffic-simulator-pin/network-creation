package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
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
    
    protected int x, y;
    protected Color cor;
    protected int width, height;

    public Sprite(int width,int height) {
        this.width = width;
        this.height = height;
    }


    @Override
    public void setColor(Color cor) {
        this.cor = cor;
    }

    @Override
    public boolean inAreaRender(Dimension areaDaTelaDesenho) {
        return new Rectangle(x, y, width, height).intersects(HelpLocator.getGuideX(), HelpLocator.getGuideY(),
                areaDaTelaDesenho.height / HelpLocator.getZOOM(), areaDaTelaDesenho.height / HelpLocator.getZOOM());
    }

}

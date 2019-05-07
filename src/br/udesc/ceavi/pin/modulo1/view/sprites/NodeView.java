package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.util.UtilNumeros;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author GustavoSantos
 * @since 18/04/2019
 *
 */
public class NodeView extends Sprite {

    public NodeView() {
        super(8, 8);
    }

    @Override
    public void draw(Graphics g) {
        if (inAreaRender()) {
            float xR = ((x - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
            float yR = ((y - HelpLocator.getGuideY()) * HelpLocator.getZOOM());
            g.setColor(cor);
            float size = super.width / HelpLocator.getEscala();
            g.fillOval((int) (xR - (size / 2)),
                    (int) (yR - (size / 2)),
                    (int) size, (int) size);
        }
    }

    @Override
    public void setDateLocation(float[] position) {
        x = position[0];
        y = -position[1];
    }

    @Override
    public void setColor(Color cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "NodeView{" + super.x + " " + super.y;
    }

}

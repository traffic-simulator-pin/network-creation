package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
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
        super(6, 6);
    }

    @Override
    public void draw(Graphics g) {
        int xRender = (x - HelpLocator.getGuideX()) * HelpLocator.getZOOM();
        int yRender = (y - HelpLocator.getGuideY()) * HelpLocator.getZOOM();

        g.setColor(cor);
        g.fillOval(xRender - (height / 2), yRender - (height / 2),
                super.width * HelpLocator.getZOOM(), super.height * HelpLocator.getZOOM());
    }

    @Override
    public void setDateLocation(float[] position) {
        x = (int) position[0];
        y = (int) position[1];
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

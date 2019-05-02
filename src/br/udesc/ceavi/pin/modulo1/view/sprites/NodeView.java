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
        super(8, 8);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cor);
        g.fillOval((int) (x - ((super.width * HelpLocator.getZOOM()) / 2)),
                (int) (y - ((super.height * HelpLocator.getZOOM()) / 2)),
                (int) (super.width * HelpLocator.getZOOM()),
                (int) (super.height * HelpLocator.getZOOM()));
    }

    @Override
    public void setDateLocation(float[] position) {
        x = (int) ((position[0] - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        y = (int) -((position[1] + HelpLocator.getGuideY()) * HelpLocator.getZOOM());
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

package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;

/**
 *
 * @author GustavoSantos
 * @since 18/04/2019
 *
 */
public class EgdeView extends Sprite {

    private float x1, y1, x2, y2;
    private final Line2D.Double linha;

    public EgdeView() {
        super(0, 0);
        linha = new Line2D.Double(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(cor);
        int x1Render = (int) ((x1 - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        int y1Render = (int) ((y1 - HelpLocator.getGuideY()) * HelpLocator.getZOOM());
        int x2Render = (int) ((x2 - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        int y2Render = (int) ((y2 - HelpLocator.getGuideY()) * HelpLocator.getZOOM());
//        System.out.println(x1Render == x1);
//        System.out.println(x2Render == x2);
//        System.out.println(y1Render == y1);
//        System.out.println(y2Render == y2);
        g.setColor(cor);
        g.drawLine(x1Render, y1Render, x2Render, y2Render);
    }

    @Override
    public void setDateLocation(float[] position) {
        this.x1 = position[0];
        this.y1 = position[1];
        this.x2 = position[2];
        this.y2 = position[3];
    }

    @Override
    public boolean inAreaRender(Dimension areaDaTelaDesenho) {
        return linha.intersects(HelpLocator.getGuideX(), HelpLocator.getGuideY(),
                areaDaTelaDesenho.width, areaDaTelaDesenho.height);
    }

    @Override
    public void setColor(Color cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "EgdeView{" + "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + '}';
    }
    
}

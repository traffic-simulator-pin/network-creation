package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLine;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author GustavoSantos
 * @since 18/04/2019
 *
 */
public class EgdeView extends Sprite {

    private float x1, y1, x2, y2;
    private Line2D.Float linha;

    public EgdeView() {
        super(0, 0);
    }

    @Override
    public void draw(Graphics g) {
        desenhaGrosso(g, 4, Color.GRAY);
        desenhaGrosso(g, 0.5f, Color.yellow);
    }

    private void desenhaGrosso(Graphics g, float largura, Color cor) {
        float size = HelpLine.getMag(x1, x2, y1, y2);
        float zoom = HelpLocator.clamp((largura * HelpLocator.getZOOM()), 1, Integer.MAX_VALUE);
        float xNormalizado = (x2 - x1) / size * zoom;
        float yNormalizado = (y2 - y1) / size * zoom;
        float xEsquerdo = -xNormalizado;
        float yEsquerdo = -yNormalizado;
        g.setColor(cor);
        g.fillPolygon(new Polygon(
                new int[]{(int) (x1 + yEsquerdo), (int) (x1 + yNormalizado), (int) (x2 + yNormalizado), (int) (x2 + yEsquerdo)},
                new int[]{(int) (y1 + xNormalizado), (int) (y1 + xEsquerdo), (int) (y2 + xEsquerdo), (int) (y2 + xNormalizado)},
                4));
    }

    @Override
    public void setDateLocation(float[] position) {
        this.x1 = ((position[0] - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        this.y1 = ((position[1] - HelpLocator.getGuideY()) * HelpLocator.getZOOM());

        this.x2 = ((position[2] - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        this.y2 = ((position[3] - HelpLocator.getGuideY()) * HelpLocator.getZOOM());
        linha = new Line2D.Float(x1, y1, x2, y2);
    }

    @Override
    public boolean inAreaRender(Dimension areaDaTelaDesenho) {
        return new Rectangle2D.Float(0, 0,
                areaDaTelaDesenho.width, areaDaTelaDesenho.height).intersectsLine(linha);
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

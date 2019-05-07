package br.udesc.ceavi.pin.modulo1.view.sprites;

import br.udesc.ceavi.pin.modulo1.help.HelpLine;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.Color;
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
        super(8, 8);
    }

    @Override
    public void draw(Graphics g) {
        float x1R = ((x1 - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        float y1R = ((y1 - HelpLocator.getGuideY()) * HelpLocator.getZOOM());

        float x2R = ((x2 - HelpLocator.getGuideX()) * HelpLocator.getZOOM());
        float y2R = ((y2 - HelpLocator.getGuideY()) * HelpLocator.getZOOM());

        linha = new Line2D.Float(x1R, y1R, x2R, y2R);

        if (inAreaRender()) {
            g.setColor(Color.YELLOW);
            g.drawLine((int) x1R, (int) y1R, (int) x2R, (int) y2R);
            //Rua
            drawPoliginoGenerico(((super.width / HelpLocator.getEscala()) / 2),
                    g, cor, x1R, y1R, x2R, y2R);
            //Trasejado
            drawPoliginoGenerico(((super.width / HelpLocator.getEscala()) * 0.2f) / 2,
                    g, Color.YELLOW, x1R, y1R, x2R, y2R);
        }
    }

    private void drawPoliginoGenerico(float largura, Graphics g, Color cor1,
            float x1R, float y1R, float x2R, float y2R) {
        float size = HelpLine.getMag(x1R, x2R, y1R, y2R);
        float zoom = largura;
        float xNormalizado = (x2R - x1R) / size * zoom;
        float yNormalizado = (y2R - y1R) / size * zoom;
        float xEsquerdo = -xNormalizado;
        float yEsquerdo = -yNormalizado;
        g.setColor(cor1);
        g.fillPolygon(new Polygon(
                new int[]{(int) (x1R + yEsquerdo), (int) (x1R + yNormalizado), 
                    (int) (x2R + yNormalizado), (int) (x2R + yEsquerdo)},
                
                new int[]{(int) (y1R + xNormalizado), (int) (y1R + xEsquerdo), 
                    (int) (y2R + xEsquerdo), (int) (y2R + xNormalizado)},
                4));
    }

    @Override
    public void setDateLocation(float[] position) {
        this.x1 = position[0];
        this.y1 = -position[1];

        this.x2 = position[2];
        this.y2 = -position[3];
    }

    @Override
    public boolean inAreaRender() {
        return new Rectangle2D.Float(0, 0,
                areaDaTelaDesenho.width, areaDaTelaDesenho.height)
                .intersectsLine(linha);
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

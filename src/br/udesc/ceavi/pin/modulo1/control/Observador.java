package br.udesc.ceavi.pin.modulo1.control;

import java.awt.Color;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public interface Observador {

    public void addLine(float x1, float y1, float x2, float y2, Color cor);

    public void addPoint(int x, int y, Color cor);

    public void clear();

    public void repaitTela();

    public void apagarEgdes();

    public void mousePositionResquest(float x1, float y1, boolean ativo);
}

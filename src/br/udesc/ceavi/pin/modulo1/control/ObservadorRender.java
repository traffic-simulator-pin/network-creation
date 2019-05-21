package br.udesc.ceavi.pin.modulo1.control;

import java.awt.Color;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public interface ObservadorRender {

    public void addSpriteDateNetwork(String nome, float[] position, Color cor);

    public void addSpriteFuntion(String nome, float[] position, Color cor);

}

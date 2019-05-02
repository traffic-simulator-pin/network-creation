package br.udesc.ceavi.pin.modulo1.util;

/**
 *
 * @author GustavoSantos
 * @since 02/05/2019
 *
 */
public class RegraDeTres {

    public static int regraDeTres(int x1, int y1, int x2) {
        return (int) ((y1 * x2) / x1);
    }

    public static float regraDeTres(float x1, float y1, float x2) {
        return ((y1 * x2) / x1);
    }
}

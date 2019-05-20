package br.udesc.ceavi.pin.modulo1.util;

/**
 *
 * @author GustavoSantos
 * @since 01/05/2019
 *
 */
public class UtilNumeros {

    public static int arrendorParaCima(float t) {
        if (t > (int) t) {
            return (int) ++t;
        }
        return (int) t;
    }

    public static int clamp(int x, int min, int max) {
        if (x < min) {
            return min;
        }
        return x > max ? max : x;
    }

    public static float clamp(float x, float min, float max) {
        if (x < min) {
            return min;
        }
        return x > max ? max : x;
    }

    public static long clamp(long x, long min, long max) {
        if (x < min) {
            return min;
        }
        return x > max ? max : x;
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) {
            return min;
        }
        return x > max ? max : x;
    }
}

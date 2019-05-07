package br.udesc.ceavi.pin.modulo1.help;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class HelpLocator {

    private static int guiaX, guiaY;
    private static float zoom = 1;
    private static int[] scaleInicial;
    private static int networkWidth, networkHeight;

    public static void setEscala(int escala) {
        scaleInicial = new int[]{1, escala};
    }

    private HelpLocator() {
        //Não deve se instanceado
    }

    public static int getNetworkWidth() {
        return networkWidth;
    }

    public static int getNetworkHeight() {
        return networkHeight;
    }

    public static int getGuideX() {
        return guiaX;
    }

    public static int getGuideY() {
        return guiaY;
    }

    public static void setGuiaX(int GuiaX) {
        HelpLocator.guiaX = GuiaX;
    }

    public static void setGuiaY(int GuiaY) {
        HelpLocator.guiaY = GuiaY;
    }

    public static void zoomIn() {
        if (((scaleInicial[1] * 37) / zoom) > 8) {
            zoom++;
        }
    }

    public static float getEscala() {
        return (scaleInicial[1] / zoom);
    }

    public static void zoomOut() {
        if (zoom > 1) {
            zoom--;
        }
    }

    public static float[] getScaleInfor() {
        return new float[]{1, (scaleInicial[1] * 37) / zoom};
    }

    public static float[] getNetworkRealLocation(float x, float y) {
        return new float[]{guiaX + x / zoom, -(guiaY + y / zoom)};
    }

    public static float getZOOM() {
        return zoom;
    }

    public static void setNetworkHeight(int networkHeight) {
        HelpLocator.networkHeight = networkHeight;
    }

    public static void setNetworkWidth(int networkWidth) {
        HelpLocator.networkWidth = networkWidth;
    }

}

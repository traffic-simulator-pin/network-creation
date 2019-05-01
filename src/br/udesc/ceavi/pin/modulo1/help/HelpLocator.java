package br.udesc.ceavi.pin.modulo1.help;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class HelpLocator {

    private static int GuiaX, GuiaY;
    private static float ZOMM = 1;
    private static float[] scale;
    private static int networkWidth, networkHeight;

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
        return GuiaX;
    }

    public static int getGuideY() {
        return GuiaY;
    }

    public static void setGuiaX(int GuiaX) {
        HelpLocator.GuiaX = GuiaX;
    }

    public static void setGuiaY(int GuiaY) {
        HelpLocator.GuiaY = GuiaY;
    }

    public static void zoomIn() {
        ZOMM++;
    }

    public static void zoomOut() {
        ZOMM--;
        if (ZOMM == 0) {
            ZOMM = 1;
        }
    }

    public static float[] getScale() {

        return null;
    }

    public static float[] getNetworkRealLocation(float x, float y) {
        return new float[]{GuiaX + (x / ZOMM), -(GuiaY + (y / ZOMM))};
    }

    public static float getZOOM() {
        return ZOMM;
    }

    public static void setNetworkHeight(int networkHeight) {
        HelpLocator.networkHeight = networkHeight;
    }

    public static void setNetworkWidth(int networkWidth) {
        HelpLocator.networkWidth = networkWidth;
    }

}

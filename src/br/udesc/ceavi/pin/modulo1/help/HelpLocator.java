package br.udesc.ceavi.pin.modulo1.help;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class HelpLocator {

    private static int GuiaX, GuiaY, ZOMM = 1;
    private static float[] scale;
    private static int networkWidth, networkHeight;

    private HelpLocator() {
    }

    public static int getNetworkWidth() {
        return new Integer(networkWidth);
    }

    public static int getNetworkHeight() {
        return new Integer(networkHeight);
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
        if (ZOMM < 1) {
            ZOMM = 1;
        }
    }

    public static float[] getScale() {
        System.err.println("Não Há Suporte Ainda Para essa operação");
        return null;
    }

    public static float[] getRealLocation(float x, float y) {
        return new float[]{GuiaX + (x / ZOMM), GuiaY + (y / ZOMM)};
    }

    public static int getZOOM() {
        return ZOMM;
    }

    public static void setNetworkHeight(int networkHeight) {
        HelpLocator.networkHeight = networkHeight;
    }

    public static void setNetworkWidth(int networkWidth) {
        HelpLocator.networkWidth = networkWidth;
    }

}

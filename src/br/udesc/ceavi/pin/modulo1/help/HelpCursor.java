package br.udesc.ceavi.pin.modulo1.help;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author GustavoSantos
 * @since 20/04/2019
 *
 */
public class HelpCursor {

    private HelpCursor() {
        //Não deve instanciar
    }

    public static Cursor createCustomCursor(String imag, Point p) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image image = kit.createImage(imag);
        Cursor customCursor = kit.createCustomCursor(image, p, imag);
        return customCursor;
    }
}

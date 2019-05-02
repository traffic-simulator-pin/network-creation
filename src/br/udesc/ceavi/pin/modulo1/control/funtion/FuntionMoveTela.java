package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.util.Clamp;
import java.awt.event.MouseEvent;

/**
 *
 * @author GustavoSantos
 * @since 28/04/2019
 *
 */
public class FuntionMoveTela extends Funtion implements IMoveTelaFuntion {

    public FuntionMoveTela() {
        System.out.println("FuntionMoveTela");
        initMouse();
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            int xAnterio = 0;
            int yAnterio = 0;

            @Override
            public void mousePressed(MouseEvent e) {
                xAnterio = e.getX();
                yAnterio = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int xMove = (int) (e.getX() - xAnterio);
                int yMove = (int) (e.getY() - yAnterio);

                int newX = (int) (HelpLocator.getGuideX() + xMove * 0.2);
                int newY = (int) (HelpLocator.getGuideY() + yMove * 0.2);

                HelpLocator.setGuiaX(Clamp.clamp(newX, 0,
                        (int) (HelpLocator.getNetworkWidth() - e.getComponent().getWidth() / HelpLocator.getZOOM()))
                );
                HelpLocator.setGuiaY(Clamp.clamp(newY, 0,
                        (int) (HelpLocator.getNetworkHeight() - e.getComponent().getHeight() / HelpLocator.getZOOM()))
                );
            }
        };

    }
}

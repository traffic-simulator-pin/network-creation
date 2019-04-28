package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.event.MouseEvent;

/**
 *
 * @author GustavoSantos
 * @since 28/04/2019
 *
 */
public class FuntionMoveTela implements IMoveTelaFuntion {

    private MouseManeger mouse;

    public FuntionMoveTela() {
        System.out.println("FuntionMoveTela");
        initMouse();
    }

    @Override
    public MouseManeger getMouseManeger() {
        return mouse;
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

                /// (-tela.size / (2 * Zoom) <-> tela.size / (2 * Zoom))   
                //Não Precisão Na Porção da Tela A ser motrado pelo sistema Como ZOOM Aplicado
                int limiteWidth = HelpLocator.getNetworkWidth() / (2 * HelpLocator.getZOOM());
                boolean test1Width = (HelpLocator.getGuideX() + xMove * 0.2) > -limiteWidth;
                boolean test2Width = (HelpLocator.getGuideX() + xMove * 0.2) < limiteWidth;
                if (test1Width && test2Width) {
                    HelpLocator.setGuiaX((int) (HelpLocator.getGuideX() + xMove * 0.2));
                }

                int limiteHeight = HelpLocator.getNetworkHeight() / (2 * HelpLocator.getZOOM());
                boolean test1Height = (HelpLocator.getGuideY() + yMove * 0.2) > -limiteHeight;
                boolean test2Height = (HelpLocator.getGuideY() + yMove * 0.2) < limiteHeight;
                if (test1Height && test2Height) {
                    HelpLocator.setGuiaY((int) (HelpLocator.getGuideY() + yMove * 0.2));
                }
            }
        };
    }

}

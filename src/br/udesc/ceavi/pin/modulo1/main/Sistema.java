package br.udesc.ceavi.pin.modulo1.main;

import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;

/**
 *
 * @author Drew
 */
public class Sistema implements Runnable {

    public static void main(String[] args) {
        Thread mainThread = new Thread(new Sistema());
        mainThread.start();
    }

    @Override
    public void run() {
        ControllerDesktop desktop = ControllerDesktop.getInstance();
        desktop.inicia();
    }

}

package br.udesc.ceavi.pin.modulo1.main;

import br.udesc.ceavi.pin.modulo1.view.ControlDesktop;

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
        ControlDesktop desktop = ControlDesktop.getInstance();
        desktop.inicia();
    }

}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;

/**
 *
 * @author GustavoSantos
 * @since 01/05/2019
 *
 */
public abstract class Funtion implements IFuntion {

    protected MouseManeger mouse;

    @Override
    public MouseManeger getMouseManeger() {
        return mouse;
    }

    @Override
    public  void initMouse(){}

    @Override
    public void addObservador(ObservadorTelaDesenho obs) {
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho obs) {
    }
}

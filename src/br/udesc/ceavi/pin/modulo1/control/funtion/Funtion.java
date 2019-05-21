package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;

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
    public void addObservador(ObservadorRender obs) {
    }

    @Override
    public void removeObservador(ObservadorRender obs) {
    }
}

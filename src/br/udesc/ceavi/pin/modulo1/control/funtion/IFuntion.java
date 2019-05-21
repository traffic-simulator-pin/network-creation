package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public interface IFuntion extends Observado<ObservadorRender>{

    public MouseManeger getMouseManeger();

    void initMouse();
}

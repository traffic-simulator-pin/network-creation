package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public interface IMoveTelaFuntion extends IFuntion {

    public MouseManeger getMouseManeger();

    public void initMouse();
}

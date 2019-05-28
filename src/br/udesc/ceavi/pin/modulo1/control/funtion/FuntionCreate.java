package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;

/**
 *
 * @author GustavoSantos
 * @param <T>
 * @since 26/04/2019
 *
 */
public abstract class FuntionCreate<T> extends Funtion implements ICreateFuntion<T> {

    protected List<T> lista = new ArrayList<>();
    protected ControllerDateNetwork date = ControllerDateNetwork.getInstance();
}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @param <T>
 * @since 26/04/2019
 *
 */
public abstract class FuntionCreate<T> extends Funtion implements ICreateFuntion<T> {

    protected List<T> lista = new ArrayList<>();

    @Override
    public abstract void offer() throws Exception;

    @Override
    public abstract void force(List<Exception> listException);


}

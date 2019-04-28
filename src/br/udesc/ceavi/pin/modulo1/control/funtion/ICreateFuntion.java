package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.util.List;

/**
 *
 * @author GustavoSantos
 * @param <T>
 * @since 24/04/2019
 *
 */
public interface ICreateFuntion<T> extends IFuntion {

    public void offer() throws Exception;

    public void force(List<Exception> listException);

}

package br.udesc.ceavi.pin.modulo1.control;

/**
 *
 * @author GustavoSantos
 * @since 27/04/2019
 *
 */
public interface Observado<T> {

    public void addObservador(T obs);
    public void removeObservador(T obs);
}

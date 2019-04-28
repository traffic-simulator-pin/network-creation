package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public interface ISelectionFuntion<T> extends IFuntion{

    public T selecionar(int x, int y);

    public void addSelecionado(T t);

    public void removeSelecionado(T t);

    public void clearSelecion();

    public List<T> getSeletion();
}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public abstract class FuntionSelection<T> implements ISelectionFuntion<T> {

    private List<T> listaSelecionado = new ArrayList<>();
    protected MouseManeger mouse;

    @Override
    public void addSelecionado(T t) {
        listaSelecionado.add(t);
    }

    @Override
    public void removeSelecionado(T t) {
        listaSelecionado.remove(t);
    }

    @Override
    public void clearSelecion() {
        listaSelecionado.clear();
    }

    public List<T> getListaSelecionado() {
        return listaSelecionado;
    }

    @Override
    public List<T> getSeletion() {
        return listaSelecionado;
    }

    protected abstract void initMouse();

    public MouseManeger getMouseListener() {
        return mouse;
    }
    
}

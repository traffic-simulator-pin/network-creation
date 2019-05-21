package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateType extends FuntionCreate<Type> {

    private FuntionSelecionarEgde seletion;

    public FuntionCreateType(FuntionSelecionarEgde selecionarEgde) {
        this.seletion = selecionarEgde;
        System.out.println("FuntionCreateType");
        seletion = new FuntionSelecionarEgde();
    }

    public FuntionCreateType() {
        System.out.println("FuntionCreateType");
        seletion = new FuntionSelecionarEgde();
    }

    public List<Egde> getSeletion() {
        return seletion.getListaSelecionado();
    }

    public IFuntion getFuntion() {
        return seletion;
    }

    @Override
    public void offer() {
        date.offerType(lista);
    }

}

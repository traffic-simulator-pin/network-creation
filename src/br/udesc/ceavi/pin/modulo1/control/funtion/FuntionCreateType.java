package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.exception.EgdeAlreadyHasAssociationWithTypeException;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import java.util.List;
import jdk.nashorn.internal.objects.annotations.Function;

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
    
    public void offerType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        ControlDateNetwork.getInstance().offerType(rua, numLanes, oneway, speed, nome);
    }

    public void forceSetType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, float width, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        ControlDateNetwork.getInstance().forceSetType(rua, numLanes, oneway, speed, nome);
    }

    public List<Egde> getSeletion() {
        return seletion.getListaSelecionado();
    }

    public IFuntion getFuntion() {
        return seletion;
    }

}
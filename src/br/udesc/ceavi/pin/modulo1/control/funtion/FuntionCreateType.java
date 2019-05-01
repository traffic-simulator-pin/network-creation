package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.exception.EgdeAlreadyHasAssociationWithTypeException;
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

    private final FuntionSelecionarEgde seletion;

    public FuntionCreateType() {
        System.out.println("FuntionCreateType");
        seletion = new FuntionSelecionarEgde();
    }

    public void offerType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, float width, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        ControlDateNetwork.getInstance().offerType(rua, numLanes, oneway, speed, width, nome);
    }

    public void forceSetType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, float width, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        ControlDateNetwork.getInstance().forceSetType(rua, numLanes, oneway, speed, width, nome);
    }

    @Override
    public void offer() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void force(List<Exception> listException) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MouseManeger getMouseManeger() {
        return seletion.getMouseManeger();
    }

    @Override
    public void initMouse() {
       
    }

    @Override
    public void addObservador(ObservadorTelaDesenho obs) {
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho obs) {
    }

}

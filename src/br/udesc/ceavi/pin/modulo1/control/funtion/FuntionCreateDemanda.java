package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.exception.DemandAlreadyExistException;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateDemanda extends FuntionCreate<Demanda> {

    private final FuntionSelecionarNode seletion;
    private Node A, B;

    public FuntionCreateDemanda() {
        seletion = new FuntionSelecionarNode();
    }

    @Override
    public void offer() throws DemandAlreadyExistException {
        ControlDateNetwork.getInstance().offerDemanda(lista);
    }

    @Override
    public void force(List<Exception> listException) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void newDemanda(int demanda) throws DemandAlreadyExistException {
        lista.add(new Demanda(A, B, demanda));
        clearNode();
    }


    private void setNode(Node selecionar) {
        if (selecionar != null) {
            if (A == null) {
                A = selecionar;
            } else if (B == null) {
                B = selecionar;
            }
        }
    }

    private void clearNode() {
        A = null;
        B = null;
    }

}

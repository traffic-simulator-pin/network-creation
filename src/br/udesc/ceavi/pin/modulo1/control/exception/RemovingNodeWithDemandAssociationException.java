package br.udesc.ceavi.pin.modulo1.control.exception;

import br.udesc.ceavi.pin.modulo1.model.Demanda;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 25/04/2019
 *
 */
public class RemovingNodeWithDemandAssociationException extends Exception {

    private final List<Demanda> listDemanda;

    public RemovingNodeWithDemandAssociationException(List<Demanda> demanda) {
        super("Existem Demanda Associadas Aos Node Que Deseja Remover");
        this.listDemanda = demanda;
    }

    public List<Demanda> getListDemanda() {
        return listDemanda;
    }

}

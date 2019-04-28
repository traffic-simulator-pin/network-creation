package br.udesc.ceavi.pin.modulo1.control.exception;

import br.udesc.ceavi.pin.modulo1.model.Demanda;

/**
 *
 * @author GustavoSantos
 * @since 25/04/2019
 *
 */
public class DemandAlreadyExistException extends Exception {

    private final Demanda demanda;

    public DemandAlreadyExistException(Demanda demanda) {
        super("Demanda : " + demanda + "Ja existe");
        this.demanda = demanda;
    }

    public Demanda getDemanda() {
        return demanda;
    }

    
}

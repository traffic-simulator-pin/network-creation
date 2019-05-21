package br.udesc.ceavi.pin.modulo1.exception;

import br.udesc.ceavi.pin.modulo1.model.Node;

/**
 *
 * @author Gustavo C Santos
 * @since 21/05/2019
 *
 */
public class NaoHaCaminhoParaADemandaException extends Exception {

    public NaoHaCaminhoParaADemandaException(Node A, Node B) {
        super("Não há um Caminho Valido entre os Pontos A:" + A.getId() + " e B:" + B.getId());
    }

}

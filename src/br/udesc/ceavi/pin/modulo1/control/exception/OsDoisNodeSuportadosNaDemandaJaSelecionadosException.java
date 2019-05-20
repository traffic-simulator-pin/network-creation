package br.udesc.ceavi.pin.modulo1.control.exception;


import br.udesc.ceavi.pin.modulo1.model.Node;

/**
 *
 * @author GustavoSantos
 * @since 30/04/2019
 *
 */
public class OsDoisNodeSuportadosNaDemandaJaSelecionadosException extends Exception {

    private Node A, B;

    public OsDoisNodeSuportadosNaDemandaJaSelecionadosException(Node A, Node B) {
        super("Uma Demanda So Suporta Dois Nodes");
    }

    public Node getA() {
        return A;
    }

    public Node getB() {
        return B;
    }

}

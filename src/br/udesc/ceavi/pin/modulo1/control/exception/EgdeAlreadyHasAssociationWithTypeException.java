package br.udesc.ceavi.pin.modulo1.control.exception;

/**
 *
 * @author GustavoSantos
 * @since 25/04/2019
 *
 */
public class EgdeAlreadyHasAssociationWithTypeException extends Exception {
    
    public EgdeAlreadyHasAssociationWithTypeException() {
        super("Pelo Menos Um Dos Egde Já Tem Type Definido");
    }
    
}

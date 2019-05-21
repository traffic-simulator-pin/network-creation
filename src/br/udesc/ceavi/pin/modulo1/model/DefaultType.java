package br.udesc.ceavi.pin.modulo1.model;

/**
 *
 * @author Gustavo C Santos
 * @since 20/05/2019
 *
 */
public enum DefaultType {
    //long id, int numLanes, boolean oneway, float speed, int capacity
    SIMPLES(new Type(1, 1, true, 50, 10)),
    
    SIMPLESDUPLA_WAY(new Type(2, 2, true, 50, 27)),
    
    SIMPLESONE_WAY(new Type(3, 1, false, 50, 15)),
    
    ONE_WAY_FAST(new Type(4, 2, false, 80, 35)),
    
    DUBLE_WAY_FAST(new Type(5, 2, true, 80, 70)),
    
    FAST(new Type(6, 2, true, 120, 50));

    private Type type;

    public Type getType() {
        return type;
    }

    private DefaultType(Type type) {
        this.type = type;
    }

}

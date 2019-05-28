package br.udesc.ceavi.pin.modulo1.model;

/**
 *
 * @author Gustavo C Santos
 * @since 20/05/2019
 *
 */
public enum DefaultType {
	// long id, int numLanes, boolean oneway, float speed, int capacity
	LENTO(new Type("LENTO", 1, true, 35, 25)),

	SIMPLES(new Type("SIMPLES", 1, true, 50, 25)),

	SIMPLESDUPLA_WAY(new Type("SIMPLESDUPLA_WAY", 2, true, 50, 53)),

	ONE_WAY_NFAST(new Type("ONE_WAY_FAST", 2, false, 80, 200)),

	DUBLE_WAY_NFAST(new Type("DUBLE_WAY_FAST", 2, true, 60, 275)),

	ONE_WAY_FAST(new Type("ONE_WAY_FAST", 2, false, 60, 200)),

	DUBLE_WAY_FAST(new Type("DUBLE_WAY_FAST", 2, true, 80, 275)),

	FAST(new Type("FAST", 2, true, 120, 350)),

	BIG_FAST(new Type("BIG_FAST", 4, true, 120, 700));

	private Type type;

	public Type getType() {
		return type;
	}

	private DefaultType(Type type) {
		this.type = type;
	}

}

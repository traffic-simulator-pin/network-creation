package br.udesc.ceavi.pin.modulo1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public class Type {

	private static long idNaoUsuado = 1;
	private final String id;

	private List<Egde> listDeEgdeQuePertenco;
	private int numLanes;
	private boolean oneway;
	private float speed;
	private int capacity;

	public Type() {
		this.id = "" + idNaoUsuado;
		idNaoUsuado++;
		this.listDeEgdeQuePertenco = new ArrayList<>();
		this.numLanes = 0;
		this.oneway = false;
		this.speed = 0;
		this.capacity = 0;
	}

	public Type(String id, int numLanes, boolean oneway, float speed, int capacity) {
		this.id = id;

		if (id.matches("[a-z]")) {
			if (idNaoUsuado <= Integer.parseInt(id)) {
				idNaoUsuado = Integer.parseInt((id + 1));
			}
		}
		this.listDeEgdeQuePertenco = new ArrayList<>();

		this.numLanes = numLanes;
		this.oneway = oneway;
		this.speed = speed;
		this.capacity = capacity;
	}

	public Type(List<Egde> lista, int numLanes, boolean oneway, float speed, int capacity) {
		this.id = "" + idNaoUsuado;
		idNaoUsuado++;

		this.listDeEgdeQuePertenco = new ArrayList<>();
		this.listDeEgdeQuePertenco.addAll(lista);

		this.numLanes = numLanes;
		this.oneway = oneway;
		this.speed = speed;
		this.capacity = capacity;
	}

	public int getNumLanes() {
		return numLanes;
	}

	public void setNumLanes(int numLanes) {
		this.numLanes = numLanes;
	}

	public boolean isOneway() {
		return oneway;
	}

	public void setOneway(boolean oneway) {
		this.oneway = oneway;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 83 * hash + Objects.hashCode(this.id);
		hash = 83 * hash + this.numLanes;
		hash = 83 * hash + (this.oneway ? 1 : 0);
		hash = 83 * hash + Float.floatToIntBits(this.speed);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Type other = (Type) obj;
		if (this.numLanes != other.numLanes) {
			return false;
		}
		if (this.oneway != other.oneway) {
			return false;
		}
		if (Float.floatToIntBits(this.speed) != Float.floatToIntBits(other.speed)) {
			return false;
		}
		return Objects.equals(this.id, other.id);
	}

	public void quebrarAssociarTypeEgde(Egde e) {
		this.listDeEgdeQuePertenco.remove(e);
	}

	public List<Egde> getListDeEgdeQuePertenco() {
		return Collections.unmodifiableList(listDeEgdeQuePertenco);
	}

	public boolean haveLinkEgde() {
		return !listDeEgdeQuePertenco.isEmpty();
	}

	public void associarTypeEgde(Egde e) {
		if (listDeEgdeQuePertenco == null) {
			listDeEgdeQuePertenco.add(e);
		}
		this.listDeEgdeQuePertenco.add(e);
	}

	public void setListDeEgdeQuePertenco(List<Egde> edgs) {
		this.listDeEgdeQuePertenco = edgs;
	}

	public String getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return "Type [numLanes=" + numLanes + ", oneway=" + oneway + ", speed=" + speed + ", capacity=" + capacity
				+ "]";
	}

}

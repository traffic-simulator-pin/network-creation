package br.udesc.ceavi.pin.modulo1.model;

import java.util.Objects;

/**
 *
 * @author GustavoSantos
 * @since 16/04/2019
 *
 */
public class Conection {

	private final Node source, target;
	private int flow;
	private String name;
	

	public Conection(Node source, Node target, int flow) {
		this.source = source;
		this.target = target;
		this.name = source.getId() + "-" + target.getId();
		this.flow = flow;
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public int getFlow() {
		return flow;
	}

	public boolean visivel(int x, int y, int w, int h) {
		return source.collideWithMyArea(x, y) || target.collideWithMyArea(x, y);
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
		final Conection other = (Conection) obj;
		if (!Objects.equals(this.source, other.source)) {
			return false;
		}
		if (!Objects.equals(this.target, other.target)) {
			return false;
		}
		return true;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public String getName() {
		return name;
	}
}

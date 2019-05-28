package br.udesc.ceavi.pin.modulo1.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Random;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;

public class Egde {

	private static long idNaoUsuado = 1;
	private final long id;

	private final Node de;
	private final Node para;

	private final float width;
	private final String nome;
	private Type type;
	private Line2D linha;
	private float constantANumber;
	private float constantBNumber;

	public Egde(Node de, Node para) {
		this.id = idNaoUsuado;
		Egde.idNaoUsuado++;

		this.de = de;
		this.para = para;

		this.width = getSizeLine(x1(), x2(), y1(), y2());
		this.nome = de.getId() + "-" + para.getId();

		initLinha();
		initAssociacao();
		createConstants();
	}

	public Egde(long id, Node de, Node para, float width, String nome, 
			float constantANumber, float constantBNumber) {
		super();
		this.id = id;
		if (idNaoUsuado <= id) {
			idNaoUsuado = (id + 1);
		}
		this.de = de;
		this.para = para;
		this.width = width;
		this.nome = nome;
		this.constantANumber = constantANumber;
		this.constantBNumber = constantBNumber;

		initLinha();
		initAssociacao();
	}
	
	private void createConstants() {
		Random r = new Random();
		this.constantANumber = r.nextInt(101);
		this.constantANumber = constantANumber / 100;

		this.constantBNumber = r.nextInt(301);
		this.constantBNumber = constantBNumber + 100;
		this.constantBNumber = constantBNumber / 100;
	}

	private float getSizeLine(float x1, float x2, float y1, float y2) {
		return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}

	public void initAssociacao() {
		de.associarNodeEgde(this);
		para.associarNodeEgde(this);
	}

	private void initLinha() {
		this.linha = new Line2D.Double(x1(), y1(), x2(), y2());
	}

	public boolean havePointInLine(Point p) {
		int size = (int) ((8 / HelpLocator.getZOOM()));
		Rectangle emBusca = new Rectangle(p.x - size / 2, p.y - size / 2, size, size);
		return linha.intersects(emBusca);
	}

	@Override
	public String toString() {
		return "Egde{" + "id=" + id + ", de=" + de.getId() + ", para=" + para.getId() + ", width=" + width + ", nome="
				+ nome + ", type=" + type.getId() + '}';
	}

	public float x1() {
		return de.getX();
	}

	public float x2() {
		return para.getX();
	}

	public float y1() {
		return de.getY();
	}

	public float y2() {
		return para.getY();
	}

	public Node de() {
		return de;
	}

	public Node para() {
		return para;
	}

	public Line2D getLinha() {
		return linha;
	}

	public float getConstantANumber() {
		return constantANumber;
	}

	public float getConstantBNumber() {
		return constantBNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(constantANumber);
		result = prime * result + Float.floatToIntBits(constantBNumber);
		result = prime * result + ((de == null) ? 0 : de.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((para == null) ? 0 : para.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + Float.floatToIntBits(width);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Egde other = (Egde) obj;
		if (Float.floatToIntBits(constantANumber) != Float.floatToIntBits(other.constantANumber))
			return false;
		if (Float.floatToIntBits(constantBNumber) != Float.floatToIntBits(other.constantBNumber))
			return false;
		if (de == null) {
			if (other.de != null)
				return false;
		} else if (!de.equals(other.de))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (Float.floatToIntBits(width) != Float.floatToIntBits(other.width))
			return false;
		return true;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}

	public float getWidth() {
		return width;
	}

}

package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public interface IControlEgde {

    void addEgde(Egde t);

    public List<Egde> getListEgde();

    public void removeEgde(Egde egde);

    public void removeEgde(List<Egde> listEgde);

    public List<Node> controlGetIntersectsLinePoint(Egde egde1);

    public List<Node> getListExtremosNodesEgde();
}

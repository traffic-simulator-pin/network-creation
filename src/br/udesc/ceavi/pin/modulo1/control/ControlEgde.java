package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.help.LineHelp;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public class ControlEgde implements IControlEgde {

    private final List<Egde> listEgde = new ArrayList<>();

    @Override
    public void addEgde(Egde t) {
        listEgde.add(t);
    }

    @Override
    public List<Egde> getListEgde() {
        return listEgde.stream().collect(Collectors.toList());
    }

    @Override
    public void removeEgde(Egde egde) {
        listEgde.remove(egde);
        egde.para().removerAssociaoEgde(egde);
        egde.de().removerAssociaoEgde(egde);
    }

    @Override
    public List<Node> controlGetIntersectsLinePoint(Egde pEgde) {
        List<Node> lista = new ArrayList<>();
        listEgde.forEach(nEgde -> {
            if (nEgde.getLinha().intersectsLine(pEgde.getLinha())) {
                Node intersectNode = LineHelp.getIntersectNodeLine(nEgde.getLinha(), pEgde.getLinha());
                if (!intersectNode.equals(nEgde.de())
                        && !intersectNode.equals(nEgde.para())
                        && !intersectNode.equals(pEgde.de())
                        && !intersectNode.equals(pEgde.para())
                        && !lista.contains(intersectNode)) {
                    if (intersectNode.getX() != -3) {
                        lista.add(intersectNode);
                    }
                }
            }
        });
        return lista;
    }

    @Override
    public List<Node> getListExtremosNodesEgde() {
        List<Node> lista = new ArrayList<>();
        listEgde.forEach(t -> {
            if (!lista.contains(t.de())) {
                lista.add(t.de());
            }
            if (!lista.contains(t.para())) {
                lista.add(t.para());
            }
        });
        return lista;
    }

    @Override
    public void removeEgde(List<Egde> listEgde) {
        listEgde.forEach(e -> {
            e.para().removerAssociaoEgde(e);
            e.de().removerAssociaoEgde(e);
            this.listEgde.remove(e);
        });
    }

    private void addEgde(Node de, Node para) {
        Egde n = new Egde(de, para);
        listEgde.add(n);
    }

}

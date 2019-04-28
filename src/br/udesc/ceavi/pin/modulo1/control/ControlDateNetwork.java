package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.control.exception.DemandAlreadyExistException;
import br.udesc.ceavi.pin.modulo1.control.exception.EgdeAlreadyHasAssociationWithTypeException;
import br.udesc.ceavi.pin.modulo1.control.exception.RemovingNodeWithDemandAssociationException;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class ControlDateNetwork {

    private static final ControlDateNetwork instance = new ControlDateNetwork();

    public static ControlDateNetwork getInstance() {
        return instance;
    }

    private final List<Egde> listEgde;
    private final List<Demanda> listDemanda;

    private ControlDateNetwork() {
        this.listEgde = new ArrayList<>();
        this.listDemanda = new ArrayList<>();
    }

    public void StartByFile(List<Egde> listEgde, List<Demanda> listDemanda) {
        this.listDemanda.clear();
        this.listEgde.clear();

        this.listDemanda.addAll(listDemanda);
        this.listEgde.addAll(listEgde);
    }

    private void addEgde(Egde egde) {
        this.listEgde.add(egde);
        System.err.println("ControlDateNetwork" + "novo egde");
    }

    private void addDemanda(Demanda newDemanda) throws DemandAlreadyExistException {
        if (!listDemanda.contains(newDemanda)) {
            this.listDemanda.add(newDemanda);
        } else {
            throw new DemandAlreadyExistException(newDemanda);
        }
    }

    public void offerEgde(List<Egde> lista) {
        lista.forEach(egdeADD -> addEgde(egdeADD));
    }

    public void offerDemanda(List<Demanda> lista) throws DemandAlreadyExistException {
        for (Demanda newDemanda : lista) {
            addDemanda(newDemanda);
        }
    }

    public List<Egde> getAllEgde() {
        return listEgde;
    }

    public List<Node> getAllNode() {
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

    public List<Type> getAllType() {
        List<Type> lista = new ArrayList<>();
        listEgde.forEach(egde -> {
            if (egde.getType() != null && !lista.contains(egde.getType())) {
                lista.add(egde.getType());
            }
        });
        return lista;
    }

    public List<Demanda> getAllDemanda() {
        return listDemanda.stream().collect(Collectors.toList());
    }

    public void removeDemanda(List<Demanda> listDemandasARemove) {
        listDemandasARemove.removeAll(listDemandasARemove);
    }

    public void tryRemoveEgde(List<Egde> listEgdeRemove) throws RemovingNodeWithDemandAssociationException {
        List<Demanda> listException = new ArrayList<>();
        listEgdeRemove
                .stream()
                .map((egde) -> nodeHaveLinkWithDemand(egde.de(), egde.para()))
                .filter((demanda) -> (demanda != null && !listException.contains(demanda)))
                .forEachOrdered((demanda) -> {
                    listException.add(demanda);
                });

        if (!listException.isEmpty()) {
            throw new RemovingNodeWithDemandAssociationException(listException);
        } else {
            this.listEgde.removeAll(listEgdeRemove);
        }
    }

    public void forceRemoveEgde(List<Egde> lista, RemovingNodeWithDemandAssociationException ex) {
        removeDemanda(ex.getListDemanda());
        this.listEgde.removeAll(lista);
    }

    public void offerType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, float width, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        for (Egde egde : rua) {
            if (egde.getType() != null) {
                throw new EgdeAlreadyHasAssociationWithTypeException();
            }
        }
        atribuirTrype(rua, numLanes, oneway, speed, width, nome);
    }

    private void atribuirTrype(List<Egde> rua, int numLanes, boolean oneway, float speed, float width, String nome) {
        Type type = new Type(rua, numLanes, oneway, speed, width);
        rua.forEach((egde) -> {
            egde.setType(type, nome);
        });
    }

    public void forceSetType(List<Egde> rua, int numLanes, boolean oneway, float speed, float width, String nome) {
        atribuirTrype(rua, numLanes, oneway, speed, width, nome);
    }

    private Demanda nodeHaveLinkWithDemand(Node de, Node para) {
        for (Demanda demanda : listDemanda) {
            if (demanda.getA().equals(de) || demanda.getA().equals(para)
                    || demanda.getB().equals(de) || demanda.getB().equals(para)) {
                return demanda;
            }
        }
        return null;
    }

    public void removeTypeAndName(List<Egde> listaRemover) {
        listaRemover.forEach(egde -> egde.setType(null, ""));
    }

    public void removeTypeOnly(List<Egde> listaRemover) {
        listaRemover.forEach(egde -> egde.setType(null, egde.getNome()));
    }

}

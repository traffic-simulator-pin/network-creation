package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.control.exception.DemandAlreadyExistException;
import br.udesc.ceavi.pin.modulo1.control.exception.EgdeAlreadyHasAssociationWithTypeException;
import br.udesc.ceavi.pin.modulo1.control.exception.RemovingNodeWithDemandAssociationException;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionSalvar;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaSalvar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class ControlDateNetwork implements Observado<ObservadorDateNetwork> {

    private static ControlDateNetwork instance;

    public static synchronized ControlDateNetwork getInstance() {
        if (instance == null) {
            instance = new ControlDateNetwork();
        }
        return instance;
    }

    private final List<Egde> listEgde;
    private final List<Demanda> listDemanda;
    private List<ObservadorDateNetwork> listaObservador = new ArrayList<>();
    private File localDeSalvamento;

    private ControlDateNetwork() {
        this.listEgde = new ArrayList<>();
        this.listDemanda = new ArrayList<>();
    }

    public synchronized void StartByFile(List<Egde> listEgde, List<Demanda> listDemanda) {
        this.listDemanda.clear();
        this.listEgde.clear();

        this.listDemanda.addAll(listDemanda);
        this.listEgde.addAll(listEgde);
        notificarAlteracaoNaEstruturaDeDados();
    }

    private void notificarAlteracaoNaEstruturaDeDados() {
        listaObservador.forEach(obs -> obs.notifyAlteracao());
    }

    private synchronized void addEgde(Egde egde) {
        this.listEgde.add(egde);
    }

    private synchronized void addDemanda(Demanda newDemanda) throws DemandAlreadyExistException {
        if (!listDemanda.contains(newDemanda)) {
            this.listDemanda.add(newDemanda);
            notificarAlteracaoNaEstruturaDeDados();
        } else {
            throw new DemandAlreadyExistException(newDemanda);
        }

    }

    public synchronized void offerEgde(List<Egde> lista) {
        lista.forEach(egdeADD -> addEgde(egdeADD));
        notificarAlteracaoNaEstruturaDeDados();
    }

    public synchronized void offerDemanda(List<Demanda> lista) throws DemandAlreadyExistException {
        for (Demanda newDemanda : lista) {
            addDemanda(newDemanda);
        }
        notificarAlteracaoNaEstruturaDeDados();
    }

    public synchronized void removeDemanda(List<Demanda> listDemandasARemove) {
        listDemandasARemove.removeAll(listDemandasARemove);
        notificarAlteracaoNaEstruturaDeDados();
    }

    public synchronized void tryRemoveEgde(List<Egde> listEgdeRemove) throws RemovingNodeWithDemandAssociationException {
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
            notificarAlteracaoNaEstruturaDeDados();
        }
    }

    public synchronized void forceRemoveEgde(List<Egde> lista, RemovingNodeWithDemandAssociationException ex) {
        removeDemanda(ex.getListDemanda());
        this.listEgde.removeAll(lista);
        notificarAlteracaoNaEstruturaDeDados();
    }

    public synchronized void offerType(List<Egde> rua, int numLanes, boolean oneway,
            float speed, float width, String nome) throws EgdeAlreadyHasAssociationWithTypeException {
        for (Egde egde : rua) {
            if (egde.getType() != null) {
                throw new EgdeAlreadyHasAssociationWithTypeException();
            }
        }
        atribuirType(rua, numLanes, oneway, speed, width, nome);
        notificarAlteracaoNaEstruturaDeDados();
    }

    private synchronized void atribuirType(List<Egde> rua, int numLanes, boolean oneway, float speed, float width, String nome) {
        Type type = new Type(rua, numLanes, oneway, speed, width);
        rua.forEach((egde) -> {
            egde.setType(type, nome);
        });
        notificarAlteracaoNaEstruturaDeDados();
    }

    public synchronized void forceSetType(List<Egde> rua, int numLanes, boolean oneway, float speed, float width, String nome) {
        atribuirType(rua, numLanes, oneway, speed, width, nome);
        notificarAlteracaoNaEstruturaDeDados();
    }

    private synchronized Demanda nodeHaveLinkWithDemand(Node de, Node para) {
        for (Demanda demanda : listDemanda) {
            if (demanda.getA().equals(de) || demanda.getA().equals(para)
                    || demanda.getB().equals(de) || demanda.getB().equals(para)) {
                return demanda;
            }
        }
        return null;
    }

    public synchronized void removeTypeAndName(List<Egde> listaRemover) {
        listaRemover.forEach(egde -> egde.setType(null, ""));
    }

    public synchronized void removeTypeOnly(List<Egde> listaRemover) {
        listaRemover.forEach(egde -> egde.setType(null, egde.getNome()));
    }

    public synchronized List<Egde> getAllEgde() {
        return listEgde.stream().collect(Collectors.toList());
    }

    public synchronized List<Node> getAllNode() {
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

    public synchronized List<Type> getAllType() {
        List<Type> lista = new ArrayList<>();
        listEgde.forEach(egde -> {
            if (egde.getType() != null && !lista.contains(egde.getType())) {
                lista.add(egde.getType());
            }
        });
        return lista;
    }

    public synchronized List<Demanda> getAllDemanda() {
        return listDemanda.stream().collect(Collectors.toList());
    }

    @Override
    public void addObservador(ObservadorDateNetwork obs) {
        this.listaObservador.add(obs);
    }

    @Override
    public void removeObservador(ObservadorDateNetwork obs) {
        this.listaObservador.remove(obs);
    }

    public void setLocalDeSalvamento(File localDeSalvamento) {
        this.localDeSalvamento = localDeSalvamento;
    }

    public File getLocalDeSalvamento() {
        return localDeSalvamento;
    }

    public void salvar() {
        if (localDeSalvamento == null) {
            int a = JOptionPane.showConfirmDialog(null, "Deseja Salvar?", "Salvar", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                new ViewListenersMenuJanelaSalvar().actionPerformed(null);
            }
        } else {
            new FuntionSalvar();
        }
    }

    public synchronized void reiniciar() {
        System.out.println("oi");
        instance = new ControlDateNetwork();
    }

    public boolean haveElements() {
        return !listDemanda.isEmpty() || !listEgde.isEmpty();
    }
}

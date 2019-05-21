package br.udesc.ceavi.pin.modulo1.control;

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
public class ControllerDateNetwork implements Observado<ObservadorDateNetwork> {
    
    private static ControllerDateNetwork instance;
    
    public static synchronized ControllerDateNetwork getInstance() {
        if (instance == null) {
            instance = new ControllerDateNetwork();
        }
        return instance;
    }
    
    private final List<Egde> listEgde;
    private final List<Demanda> listDemanda;
    private List<ObservadorDateNetwork> listaObservador = new ArrayList<>();
    private File localDeSalvamento;
    
    private ControllerDateNetwork() {
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
    
    public synchronized void offerEgde(List<Egde> lista) {
        this.listEgde.addAll(lista);
        notificarAlteracaoNaEstruturaDeDados();
    }
    
    public synchronized void offerDemanda(List<Demanda> lista) {
        for (Demanda newDemanda : lista) {
            boolean alterado = false;
            for (Demanda demanda : listDemanda) {
                if (demanda.equals(newDemanda)) {
                    demanda = newDemanda;
                    alterado = true;
                }
            }
            if (!alterado) {
                this.listDemanda.add(newDemanda);
            }
        }
        notificarAlteracaoNaEstruturaDeDados();
    }
    
    public synchronized void offerType(List<Type> listType) {
        listType.forEach(type -> {
            type.getListDeEgdeQuePertenco().forEach(egde -> egde.setType(type));
        });
        notificarAlteracaoNaEstruturaDeDados();
    }
    
    public synchronized void removeDemanda(List<Demanda> listDemandasARemove) {
        listDemanda.removeAll(listDemandasARemove);
        notificarAlteracaoNaEstruturaDeDados();
    }
    
    public synchronized void tryRemoveEgde(List<Egde> listEgdeRemove) {
        List<Demanda> demandasRemover = new ArrayList<>();
        listEgdeRemove
                .stream()
                .map((egde) -> nodeHaveLinkWithDemand(egde.de(), egde.para()))
                .filter((demanda) -> (demanda != null && !demandasRemover.contains(demanda)))
                .forEachOrdered((demanda) -> {
                    demandasRemover.add(demanda);
                });
        removeDemanda(demandasRemover);
        this.listEgde.removeAll(listEgdeRemove);
        notificarAlteracaoNaEstruturaDeDados();
    }
    
    public synchronized void removeType(List<Egde> listaRemover) {
        listaRemover.forEach(egde -> egde.setType(null));
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
        instance = new ControllerDateNetwork();
    }
    
    public boolean haveElements() {
        return !listDemanda.isEmpty() || !listEgde.isEmpty();
    }
}

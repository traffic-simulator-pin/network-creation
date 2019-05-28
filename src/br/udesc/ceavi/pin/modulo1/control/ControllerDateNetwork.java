package br.udesc.ceavi.pin.modulo1.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionSalvar;
import br.udesc.ceavi.pin.modulo1.model.Conection;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.listeners.ViewListenersMenuJanelaSalvar;

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
	private final List<Conection> listConection;
	private List<ObservadorDateNetwork> listaObservador = new ArrayList<>();
	private File localDeSalvamento;

	private ControllerDateNetwork() {
		this.listEgde = new ArrayList<>();
		this.listConection = new ArrayList<>();
	}

	public synchronized void StartByFile(List<Egde> listEgde, List<Conection> listDemanda) {
		this.listConection.clear();
		this.listEgde.clear();
		this.listConection.addAll(listDemanda);
		this.listEgde.addAll(listEgde);

		notificarAlteracaoNaEstruturaDeDados();
	}

	private void notificarAlteracaoNaEstruturaDeDados() {
		listaObservador.forEach(obs -> obs.notifyAlteracao());
	}

	public synchronized void offerEgde(List<Egde> lista) {
		if (listEgde.isEmpty()) {
			this.listEgde.addAll(lista);
		} else {
			for (Egde egde : lista) {
				if (!listEgde.contains(egde)) {
					this.listEgde.add(egde);
				}
			}
		}
		lista.forEach(egde -> criarConection(egde));
		notificarAlteracaoNaEstruturaDeDados();
	}

	private void criarConection(Egde egde) {
		Node de = egde.de();
		Node para = egde.para();

		List<Conection> newListaConection = new ArrayList<>();

		List<Node> listaNode = getAllNode();
		listaNode.remove(de);
		listaNode.remove(para);

		listaNode.forEach(node -> {
			newListaConection.add(new Conection(de, node, getDemandaAleatoria()));
			newListaConection.add(new Conection(node, de, getDemandaAleatoria()));

			newListaConection.add(new Conection(para, node, getDemandaAleatoria()));
			newListaConection.add(new Conection(node, para, getDemandaAleatoria()));
		});
		newListaConection.add(new Conection(de, para, getDemandaAleatoria()));
		newListaConection.add(new Conection(para, de, getDemandaAleatoria()));

		offerDemanda(newListaConection, false);
	}

	private int getDemandaAleatoria() {
		return (int) (Math.random() * 100);
	}

	public synchronized void offerDemanda(List<Conection> lista, boolean premitirAtlteracao) {
		for (Conection newConection : lista) {
			boolean alterado = false;
			for (Conection conectionSystem : this.listConection) {
				if (conectionSystem.equals(newConection)) {
					if (premitirAtlteracao) {
						conectionSystem.setFlow(newConection.getFlow());
					}
					alterado = true;
					break;
				}
			}
			if (!alterado) {
				this.listConection.add(newConection);
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

	public synchronized void removeDemanda(List<Conection> listDemandasARemove) {
		listConection.removeAll(listDemandasARemove);
		notificarAlteracaoNaEstruturaDeDados();
	}

	public synchronized void tryRemoveEgde(List<Egde> listEgdeRemove) {
		List<Conection> demandasRemover = new ArrayList<>();
		listEgdeRemove.stream().map((egde) -> nodeHaveLinkWithDemand(egde.de(), egde.para()))
				.filter((demanda) -> (demanda != null && !demandasRemover.contains(demanda)))
				.forEachOrdered((demanda) -> {
					demandasRemover.add(demanda);
				});
		removeDemanda(demandasRemover);

		listEgdeRemove.forEach(egde -> {
			egde.de().quebrarAssociarNodeEgde(egde);
			egde.para().quebrarAssociarNodeEgde(egde);
		});

		this.listEgde.removeAll(listEgdeRemove);
		notificarAlteracaoNaEstruturaDeDados();
	}

	public synchronized void removeType(List<Egde> listaRemover) {
		listaRemover.forEach(egde -> egde.setType(null));
	}

	private synchronized Conection nodeHaveLinkWithDemand(Node de, Node para) {
		for (Conection demanda : listConection) {
			if (demanda.getSource().equals(de) || demanda.getSource().equals(para) || demanda.getTarget().equals(de)
					|| demanda.getTarget().equals(para)) {
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

	public synchronized List<Conection> getAllDemanda() {
		return listConection.stream().collect(Collectors.toList());
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
		return !listConection.isEmpty() || !listEgde.isEmpty();
	}
}

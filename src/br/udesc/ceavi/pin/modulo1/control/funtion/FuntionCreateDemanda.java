package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.awt.event.MouseEvent;
import java.util.List;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.model.Conection;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameCreateDemanda;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateDemanda extends FuntionCreate<Conection> implements Observado<ObservadorRender>, ILoop {

    private final FuntionSelecionarNode seletion;
    private Node A, B;
    private ViewFrameCreateDemanda view;

    public FuntionCreateDemanda() {
        System.out.println("FuntionCreateDemanda");
        seletion = new FuntionSelecionarNode();
        initMouse();
    }

    @Override
    public void removeObservador(ObservadorRender obs) {
        seletion.removeObservador(obs);
    }

    @Override
    public void addObservador(ObservadorRender obs) {
        seletion.addObservador(obs);
    }

    @Override
    public void offer() {
        ControllerDateNetwork.getInstance().offerDemanda(lista,true);
    }

    public void newDemanda(Conection demanda)  {
            lista.add(demanda);
            offer();
            clearNode();
    }

    public void clearNode() {
        A = null;
        B = null;
        lista.clear();
    }

    private void setNodes(Node aux) {
        if (A != null && A.getId() == aux.getId()) {
            A = null;
            view.atualizaTelaDemanda();
            return;
        }
        if (B != null && B.getId() == aux.getId()) {
            B = null;
            view.atualizaTelaDemanda();
            return;
        }

        if (A == null) {
            A = aux;
        } else if (A.getId() != aux.getId() && B == null) {
            B = aux;
        }

        view.atualizaTelaDemanda();
        if (A != null && B != null) {
            List<Conection> allDemanda = date.getAllDemanda();
            allDemanda.forEach(demanda -> {
                if (demanda.getSource().getId() == A.getId() && demanda.getTarget().getId() == B.getId()) {
                    view.setDemandaText(demanda.getFlow());
                }
            });
        }
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Node aux = seletion.selecionar(e.getX(), e.getY());
                if (aux != null) {
                    setNodes(aux);
                }
            }
        };
    }

    @Override
    public MouseManeger getMouseManeger() {
        return super.getMouseManeger();
    }

    public void setView(ViewFrameCreateDemanda view) {
        this.view = view;
    }

    public Node getB() {
        return B;
    }

    public Node getA() {
        return A;
    }

    @Override
    public void render() {
        seletion.render();
    }

    @Override
    public void update() {
        seletion.clearSelecion();
        if (A != null) {
            seletion.addSelecionado(A);
        }
        if (B != null) {
            seletion.addSelecionado(B);
        }
    }

    @Override
    public void processInput() {
    }

}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameAdicionarDemanda;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateDemanda extends FuntionCreate<Demanda> implements Observado<ObservadorRender>, ILoop {

    private final FuntionSelecionarNode seletion;
    private Node A, B;
    private ViewFrameAdicionarDemanda view;
    private final List<ObservadorRender> observers;

    public FuntionCreateDemanda() {
        System.out.println("FuntionCreateDemanda");
        seletion = new FuntionSelecionarNode();
        observers = new ArrayList<>();
        initMouse();
    }

    @Override
    public void removeObservador(ObservadorRender obs) {
        this.observers.remove(obs);
    }

    @Override
    public void addObservador(ObservadorRender obs) {
        this.observers.add(obs);
    }

    @Override
    public void offer() {
        ControllerDateNetwork.getInstance().offerDemanda(lista);
    }

    public void newDemanda(Demanda demanda) {
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

        if (A != null && B != null) {
            List<Demanda> allDemanda = date.getAllDemanda();
            allDemanda.forEach(demanda -> {
                if (demanda.getA().getId() == A.getId() && demanda.getB().getId() == B.getId()) {
                    view.setDemandaText(demanda.getDemanda());
                }
            });
        }
        view.atualizaTelaDemanda();
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

    public void setView(ViewFrameAdicionarDemanda view) {
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
        observers.forEach(obs -> {
            if (A != null) {
                obs.addSpriteFuntion("NodeView", new float[]{A.getX(), A.getY()}, Color.green);
            }
            if (B != null) {
                obs.addSpriteFuntion("NodeView", new float[]{B.getX(), B.getY()}, Color.green);
            }
        });
    }

    @Override
    public void update() {
    }

    @Override
    public void processInput() {
    }

}

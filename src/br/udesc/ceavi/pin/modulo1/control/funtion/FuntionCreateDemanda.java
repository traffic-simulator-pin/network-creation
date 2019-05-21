package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.exception.NaoHaCaminhoParaADemandaException;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameCreateDemanda;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.rmi.CORBA.Util;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateDemanda extends FuntionCreate<Demanda> implements Observado<ObservadorRender>, ILoop {

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
        ControllerDateNetwork.getInstance().offerDemanda(lista);
    }

    public void newDemanda(Demanda demanda) throws NaoHaCaminhoParaADemandaException {
        if (verificarExistenciaCaminho(A, B)) {
            lista.add(demanda);
            offer();
            clearNode();
        }
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

    private boolean verificarExistenciaCaminho(Node A, Node B) {
        //A Busca de ve partir de A e verificar se tem uma caminho até B
        
        return true;
    }

}

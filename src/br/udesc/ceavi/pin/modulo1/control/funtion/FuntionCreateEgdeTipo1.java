package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta Ferramenta Cria Egde Indepdentes
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateEgdeTipo1 extends FuntionCreate<Egde> implements Observado<ObservadorTelaDesenho>, ILoop {

    private Node de;
    private Node para;
    private Node mesmoNode;
    private MouseManeger mouse;
    private List<ObservadorTelaDesenho> listaObservado;
    private int xLoop, yLoop;

    public FuntionCreateEgdeTipo1() {
        this.listaObservado = new ArrayList<>();
        intMouse();
        System.out.println("FuntionCreateEgdeTipo1");
    }

    @Override
    public void offer() {
        ControlDateNetwork.getInstance().offerEgde(lista);
        lista.clear();
        clearNode();
    }

    @Override
    public void force(List<Exception> listException) {
    }

    private boolean haveTwoPoint() {
        return de != null && para != null;
    }

    private void clearNode() {
        de = null;
        para = null;
        mesmoNode = null;
    }

    public void createNode(int x, int y) {
        if (mesmoNode != null) {
            if (de == null) {
                de = mesmoNode;
                mesmoNode = null;
            } else {
                para = mesmoNode;
                mesmoNode = null;
            }
        } else {
            float[] realLocation = HelpLocator.getRealLocation(x, y);
            Node novo = new Node(realLocation[0], realLocation[1]);
            if (de == null) {
                de = novo;
            } else {
                para = novo;
            }
        }
        update();
    }

    private Node checkExistingNode(float x, float y) {
        float[] realLocation = HelpLocator.getRealLocation(x, y);
        for (Node node : ControlDateNetwork.getInstance().getAllNode()) {
            if (node.collideWithMyArea(realLocation[0], realLocation[1])) {
                return node;
            }
        }
        return null;
    }

    private void intMouse() {
        mouse = new MouseManeger() {

            @Override
            public void mouseMoved(MouseEvent e) {
                try {
                    xLoop = e.getComponent().getMousePosition().x;
                    yLoop = e.getComponent().getMousePosition().y;
                } catch (Exception ex) {
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                createNode(e.getComponent().getMousePosition().x, e.getComponent().getMousePosition().y);
            }

        };
    }

    public MouseManeger getMouseListener() {
        return mouse;
    }

    @Override
    public void addObservador(ObservadorTelaDesenho obs) {
        this.listaObservado.add(obs);
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho obs) {
        this.listaObservado.remove(obs);
    }

    @Override
    public void render() {
        listaObservado.forEach(obs -> {
            if (de != null) {
                obs.addSpriteFuntion("NodeView", new float[]{de.getX(), de.getY()}, Color.red);
                if (mesmoNode != null) {
                    obs.addSpriteFuntion("NodeView", new float[]{mesmoNode.getX(), mesmoNode.getY()}, Color.YELLOW);
                } else {
                    obs.addSpriteFuntion("NodeView", new float[]{xLoop, yLoop}, Color.red);
                }
                obs.addSpriteFuntion("EgdeView", new float[]{de.getX(), de.getY(), xLoop, yLoop}, Color.blue);
            } else if (de == null) {
                if (mesmoNode != null) {
                    obs.addSpriteFuntion("NodeView", new float[]{mesmoNode.getX(), mesmoNode.getY()}, Color.YELLOW);
                }
            }
        });
    }

    @Override
    public void processInput() {
    }

    @Override
    public void update() {
        if (haveTwoPoint()) {
            lista.add(new Egde(de, para));
            offer();
        } else {
            mesmoNode = checkExistingNode(xLoop, yLoop);
        }
    }

}

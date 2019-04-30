package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta Ferramenta Cria Egde Com O Arrastar do Mouse
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateEgdeTipo2 extends FuntionCreate<Egde> implements ILoop {

    private MouseManeger mouse;
    private List<ObservadorTelaDesenho> listaObservado;
    private Node de;
    private Node para;
    private Rectangle2D ultimaArea;
    private int precisionOfTool = 50;

    public FuntionCreateEgdeTipo2() {
        this.listaObservado = new ArrayList<>();
        intMouse();
        System.out.println("FuntionCreateEgdeTipo2");
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

    public void createNode(int x, int y) {
        float[] realLocation = HelpLocator.getRealLocation(x, y);
        if (de != null && de.collideWithMyArea(realLocation[0], realLocation[1])) {
            return;
        }
        if (ultimaArea != null && ultimaArea.intersects(realLocation[0], realLocation[1], precisionOfTool, precisionOfTool)) {
            return;
        }
        newNodeInstance(realLocation);
    }

    private void newNodeInstance(float[] realLocation) {
        Node novo = new Node(realLocation[0], realLocation[1]);
        if (de == null) {
            de = novo;
        } else {
            para = novo;
        }
        ultimaArea = new Rectangle2D.Float(realLocation[0], realLocation[1], 25, 25);
        update();
    }

    private void intMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    createNode(e.getComponent().getMousePosition().x, e.getComponent().getMousePosition().y);
                } catch (Exception ex) {
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lista.clear();
                clearNode();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                createNode(e.getComponent().getMousePosition().x, e.getComponent().getMousePosition().y);
                offer();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
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
        try {
            listaObservado.forEach(obs -> {
                for (Egde egde : lista) {
                    obs.addSpriteFuntion("NodeView",
                            new float[]{egde.x1(), egde.y1()}, Color.red);
                    obs.addSpriteFuntion("EgdeView",
                            new float[]{egde.x1(), egde.y1(), egde.x2(), egde.y2()},
                            Color.BLACK);
                    obs.addSpriteFuntion("NodeView",
                            new float[]{egde.x2(), egde.y2()}, Color.red);
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void processInput() {
    }

    @Override
    public void update() {
        if (haveTwoNode()) {
            lista.add(new Egde(de, para));
            de = para;
            para = null;
        }
    }

    private boolean haveTwoNode() {
        return de != null && para != null;
    }

    private void clearNode() {
        de = null;
        para = null;
        ultimaArea = null;
    }

    public void setPresision(int presision) {
        this.precisionOfTool = presision;
        clearNode();
    }

}

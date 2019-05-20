package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameDemanda;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionSelecionarNode extends FuntionSelection<Node> implements Observado<ObservadorTelaDesenho>, ILoop {

    private List<ObservadorTelaDesenho> listaObs;

    public FuntionSelecionarNode() {
        listaObs = new ArrayList<>();
        System.out.println("FuntionSelecionarNode");
        initMouse();
    }

    @Override
    public void addObservador(ObservadorTelaDesenho oObs) {
        this.listaObs.add(oObs);
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho oObs) {
        this.listaObs.remove(oObs);
    }

    @Override
    public void processInput() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        if (!super.getListaSelecionado().isEmpty()) {

            listaObs.forEach(oObs -> {
                for (Node node : getListaSelecionado()) {
                    oObs.addSpriteFuntion("NodeView", new float[]{node.getX(), node.getY()}, Color.green);
                }
            });
        }
    }

    @Override
    public Node selecionar(int x, int y) {
        //Transformando o Ponto da tela de desenho em um ponto da estrutura de dados.
        float[] localizacaReal = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Node
        for (Node node : ControlDateNetwork.getInstance().getAllNode()) {
            //Verificando se o node tem a area a baixo
            if (node.collideWithMyArea(localizacaReal[0], localizacaReal[1])) {

                //Logica de adicionar quando não tem e remover quando tem
                if (super.getListaSelecionado().contains(node)) {
                    removeSelecionado(node);
                    return null;
                } else {
                    addSelecionado(node);
                    return node;
                }
            }
        }
        return null;
    }

    @Override
    public List<Node> getListaSelecionado() {
        return super.getListaSelecionado(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atualizaTelaDemanda(selecionar(e.getPoint().x, e.getPoint().y));
            }
        };
    }
    
    
    private void atualizaTelaDemanda(Node node) {
        ControllerDesktop d = ControllerDesktop.getInstance();
        ViewFrameDemanda viewDemanda = null;
        
        for(ViewJanelaSistema v : d.getJanelas()) {
            if(v instanceof ViewFrameDemanda) {
                viewDemanda = (ViewFrameDemanda)v;
                break;
            }
        }
        
        if(viewDemanda == null || !viewDemanda.isVisible()) {
            return;
        }
        
        Node nodoOrigem = viewDemanda.getNodoOrigem();
        if(nodoOrigem == null) {
            viewDemanda.setNodoOrigem(node);
        } else {
            viewDemanda.setNodoDestino(node);
        }
        
        viewDemanda.atualizaTelaDemanda();
    }
}

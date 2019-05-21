package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameAdicionarDemanda;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionSelecionarNode extends FuntionSelection<Node> implements Observado<ObservadorRender>, ILoop {

    private List<ObservadorRender> listaObs;

    public FuntionSelecionarNode() {
        listaObs = new ArrayList<>();
        System.out.println("FuntionSelecionarNode");
        initMouse();
    }

    @Override
    public void addObservador(ObservadorRender oObs) {
        this.listaObs.add(oObs);
    }

    @Override
    public void removeObservador(ObservadorRender oObs) {
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
    public void selecionarAddList(int x, int y) {
        //Transformando o Ponto da tela de desenho em um ponto da estrutura de dados.
        float[] localizacaReal = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Node
        for (Node node : ControllerDateNetwork.getInstance().getAllNode()) {
            //Verificando se o node tem a area a baixo
            if (node.collideWithMyArea(localizacaReal[0], localizacaReal[1])) {

                //Logica de adicionar quando não tem e remover quando tem
                if (super.getListaSelecionado().contains(node)) {
                    removeSelecionado(node);
                } else {
                    addSelecionado(node);
                }
            }
        }
    }

    @Override
    public Node selecionar(int x, int y) {
        //Transformando o Ponto da tela de desenho em um ponto da estrutura de dados.
        float[] localizacaReal = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Node
        for (Node node : ControllerDateNetwork.getInstance().getAllNode()) {
            //Verificando se o node tem a area a baixo
            if (node.collideWithMyArea(localizacaReal[0], localizacaReal[1])) {
                return node;
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
                Node selecionado = selecionar(e.getPoint().x, e.getPoint().y);
                if (getListaSelecionado().contains(selecionado)) {
                    removeSelecionado(selecionado);
                }else{
                    addSelecionado(selecionado);
                }
            }
        };
    }
 
}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionSelecionarNode extends FuntionSelection<Node> {

    public FuntionSelecionarNode() {
        System.out.println("FuntionSelecionarNode");
        initMouse();
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
                selecionar(e.getPoint().x, e.getPoint().y);
            }
        };
    }

}

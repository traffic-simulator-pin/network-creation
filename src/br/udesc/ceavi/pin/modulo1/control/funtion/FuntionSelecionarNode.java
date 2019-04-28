package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.event.MouseAdapter;
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
        initMouse();
    }

    @Override
    public Node selecionar(int x, int y) {
        float[] localizacaReal = HelpLocator.getRealLocation(x, y);
        for (Node node : ControlDateNetwork.getInstance().getAllNode()) {
            if (node.collideWithMyArea(localizacaReal[0], localizacaReal[1])) {
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
    protected void initMouse() {
        mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionar(e.getPoint().x, e.getPoint().y);
            }
        };
    }
}

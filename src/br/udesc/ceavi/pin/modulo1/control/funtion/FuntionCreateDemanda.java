package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.event.MouseEvent;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateDemanda extends FuntionCreate<Demanda> {

    private final FuntionSelecionarNode seletion;
    private Node A, B;

    public FuntionCreateDemanda() {
        System.out.println("FuntionCreateDemanda");
        seletion = new FuntionSelecionarNode();
        initMouse();
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

    private void clearNode() {
        A = null;
        B = null;
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Node aux = seletion.selecionar(e.getX(), e.getY());
                if (aux != null) {
                }
            }

        };
    }

    public FuntionSelecionarNode getSeletion() {
        return seletion;
    }

}

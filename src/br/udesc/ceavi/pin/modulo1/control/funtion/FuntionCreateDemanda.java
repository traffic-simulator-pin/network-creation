package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.exception.DemandAlreadyExistException;
import br.udesc.ceavi.pin.modulo1.control.exception.OsDoisNodeSuportadosNaDemandaJaSelecionadosException;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void offer() throws DemandAlreadyExistException {
        ControlDateNetwork.getInstance().offerDemanda(lista);
    }

    @Override
    public void force(List<Exception> listException) {
    }

    public void newDemanda(int demanda) throws DemandAlreadyExistException {
        lista.add(new Demanda(A, B, demanda));
        offer();
        clearNode();
    }

    private void setNodeDemanda(Node nodeSelecionado) throws Exception {
        
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
                    try {
                        setNodeDemanda(aux);
                    } catch (Exception ex) {
                        Logger.getLogger(FuntionCreateDemanda.class.getName()).log(Level.SEVERE, null, ex);
                        //Tratamento Visual
                    }
                }
            }

        };
    }
}

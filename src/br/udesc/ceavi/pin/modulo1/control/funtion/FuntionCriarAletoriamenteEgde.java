package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionCriarAletoriamenteEgde extends Funtion implements IFuntion {

    public FuntionCriarAletoriamenteEgde() {
        System.out.println("FuntionTest");
        List<Egde> lista = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            lista.add(new Egde(new Node((float) Math.random() * 600, (float) Math.random() * 500),
                    new Node((float) Math.random() * 800, (float) Math.random() * 800)));
        }
        ControlDateNetwork.getInstance().offerEgde(lista);
    }

}

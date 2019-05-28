package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionTest extends Funtion implements IFuntion {

    private ControllerDateNetwork date = ControllerDateNetwork.getInstance();
    private List<Egde> lista = new ArrayList<>();

    public FuntionTest() {
        System.out.println("FuntionTest");
        imprimirEgde();
    }

    private void criarEgdeAletorios() {
        for (int i = 0; i < 50; i++) {
            lista.add(new Egde(new Node((float) Math.random() * 600, -(float) Math.random() * 500),
                    new Node((float) Math.random() * 800, -(float) Math.random() * 800)));
        }
        date.offerEgde(lista);
    }

    private void imprimirEgde() {
        date.getAllEgde().forEach(e -> System.out.println(e));

    }

    private void criarEgdeExpecifico() {
        lista.add(new Egde(new Node(50, -150), new Node(600, -150)));
        date.offerEgde(lista);
    }

    private void getTamanhoDaEstrutura() {
        System.out.printf("W : %s ,H : %s\n", HelpLocator.getNetworkSize()[0], HelpLocator.getNetworkSize()[1]);
    }

}

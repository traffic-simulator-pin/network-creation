package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.exception.DemandAlreadyExistException;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionTest extends Funtion implements IFuntion {

    private ControlDateNetwork date = ControlDateNetwork.getInstance();
    private List<Egde> lista = new ArrayList<>();

    public FuntionTest() {
        System.out.println("FuntionTest");
        createRede();
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

    private void createRede() {
        Node nI = new Node(240.0f, -38.0f);
        Node nII = new Node(476.0f, -164.0f);
        Node nIII = new Node(550.0f, -72.0f);
        Node nIV = new Node(706.0f, -26.0f);
        Node nV = new Node(842.0f, -22.0f);
        Node nVI = new Node(934.0f, -216.0f);
        Node nVII = new Node(808.0f, -376.0f);
        Node nVIII = new Node(978.0f, -524.0f);
        Node nIX = new Node(666.0f, -504.0f);
        Node nX = new Node(478.0f, -340.0f);
        Node nXI = new Node(26.0f, -128.0f);
        Node nXII = new Node(664.0f, -340.0f);
        Node nXIII = new Node(690.0f, -166.0f);
        Node nXIV = new Node(1082.0f, -318.0f);
        Node nXV = new Node(1096.0f, -472.0f);

        Demanda A = new Demanda(nI, nX, 135);
        Demanda B = new Demanda(nI, nXI, 150);
        Demanda C = new Demanda(nII, nX, 50);
        Demanda D = new Demanda(nI, nII, 10);
        try {
            date.offerDemanda(Arrays.asList(A, B, C, D));
        } catch (DemandAlreadyExistException ex) {
            Logger.getLogger(FuntionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        Egde I = new Egde(nI, nII);
        Egde II = new Egde(nII, nIII);
        Egde III = new Egde(nIII, nIV);
        Egde IV = new Egde(nIV, nV);
        Egde V = new Egde(nV, nVI);
        Egde VI = new Egde(nVI, nVII);
        Egde VII = new Egde(nVII, nVIII);
        Egde VIII = new Egde(nVIII, nIX);
        Egde IX = new Egde(nX, nIX);
        Egde X = new Egde(nII, nXI);
        Egde XI = new Egde(nIX, nII);
        Egde XII = new Egde(nIX, nXII);
        Egde XIII = new Egde(nIX, nXII);
        Egde XIV = new Egde(nXII, nXIII);
        Egde XV = new Egde(nXIII, nV);
        Egde XVI = new Egde(nVI, nXIV);
        Egde XVII = new Egde(nXIV, nXV);
        Egde XVIII = new Egde(nXV, nVIII);
        lista.add(I);
        lista.add(II);
        lista.add(III);
        lista.add(IV);
        lista.add(V);
        lista.add(VI);
        lista.add(VII);
        lista.add(VIII);
        lista.add(IX);
        lista.add(X);
        lista.add(XI);
        lista.add(XII);
        lista.add(XIII);
        lista.add(XIV);
        lista.add(XV);
        lista.add(XVI);
        lista.add(XVII);
        lista.add(XVIII);

        List<Egde> type1 = Arrays.asList(I, III, IV, V, VII);
        List<Egde> type2 = Arrays.asList(II, VI, X, XI);
        List<Egde> type3 = Arrays.asList(VIII, IX, XII, XIII, XIV);
        List<Egde> type4 = Arrays.asList(XV, XVI, XVII);

        Type TI = new Type(type1, 2, true, 90);
        type1.forEach(t -> t.setType(TI, ""));

        Type TII = new Type(type2, 1, true, 50);
        type2.forEach(t -> t.setType(TII, ""));

        Type TIII = new Type(type3, 1, true, 40);
        type3.forEach(t -> t.setType(TIII, ""));

        Type TIV = new Type(type4, 3, true, 120);
        type4.forEach(t -> t.setType(TIV, ""));

        date.offerEgde(lista);
    }
}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionRemoverEgde extends Funtion implements ILoop {

    private FuntionSelecionarEgde seletionEgde;
    private Egde egdeMousePassouPorCimaRemove;
    private List<ObservadorRender> listaDeObservador;

    public FuntionRemoverEgde() {
        seletionEgde = new FuntionSelecionarEgde();
        System.out.println("FuntionRemoverEgde");
        this.listaDeObservador = new ArrayList<>();
        initMouse();
    }

    @Override
    public void render() {
        if (egdeMousePassouPorCimaRemove != null) {  //aqui pinta as bordas de vermelho
            listaDeObservador.forEach(obs
                    -> obs.addSpriteFuntion("EgdeView",
                            new float[]{egdeMousePassouPorCimaRemove.x1(), egdeMousePassouPorCimaRemove.y1(),
                                egdeMousePassouPorCimaRemove.x2(), egdeMousePassouPorCimaRemove.y2()},
                            Color.red)
            );
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void processInput() {
    }

    @Override
    public void addObservador(ObservadorRender obs) {
        this.listaDeObservador.add(obs);
    }

    @Override
    public void removeObservador(ObservadorRender obs) {
        this.listaDeObservador.remove(obs);
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Egde egde = seletionEgde.selecionar(e.getX(), e.getY());
                if (egde != null) {
                    ControllerDateNetwork.getInstance().tryRemoveEgde(Arrays.asList(egde));
                    egdeMousePassouPorCimaRemove = null;
                    seletionEgde.getSeletion().clear();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                try {
                    egdeMousePassouPorCimaRemove = seletionEgde.selecionar(e.getX(), e.getY());
                } catch (Exception ex) {
                    //Tratamento Visual Aqui
                    ex.printStackTrace();
                }
            }
        };
    }

}

package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.exception.RemovingNodeWithDemandAssociationException;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionRemoverEgde implements ILoop {

    private FuntionSelecionarEgde seletionEgde;
    private Egde egdeMousePassouPorCimaRemove;
    private MouseManeger mouse;
    private List<ObservadorTelaDesenho> listaDeObservador;

    public FuntionRemoverEgde() {
        this.listaDeObservador = new ArrayList<>();
        initMouse();
    }

    @Override
    public void render() {
        if (egdeMousePassouPorCimaRemove != null) {
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
    public void addObservador(ObservadorTelaDesenho obs) {
        this.listaDeObservador.add(obs);
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho obs) {
        this.listaDeObservador.remove(obs);
    }

    protected void initMouse() {
        mouse = new MouseManeger() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Egde egde = seletionEgde.selecionar(e.getX(), e.getY());
                if (egde != null) {
                    try {
                        ControlDateNetwork.getInstance().tryRemoveEgde(Arrays.asList(egde));
                    } catch (RemovingNodeWithDemandAssociationException ex) {
                        //Tratamento Visual Aqui
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                egdeMousePassouPorCimaRemove = seletionEgde.selecionar(e.getX(), e.getY());
            }
        };
    }

}

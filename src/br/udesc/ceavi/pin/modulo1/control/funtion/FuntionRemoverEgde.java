package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.control.exception.RemovingNodeWithDemandAssociationException;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 *
 */
public class FuntionRemoverEgde extends Funtion implements ILoop {

    private FuntionSelecionarEgde seletionEgde;
    private Egde egdeMousePassouPorCimaRemove;
    private List<ObservadorTelaDesenho> listaDeObservador;

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
    public void addObservador(ObservadorTelaDesenho obs) {
        this.listaDeObservador.add(obs);
    }

    @Override
    public void removeObservador(ObservadorTelaDesenho obs) {
        this.listaDeObservador.remove(obs);
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Egde egde;
                if (egdeMousePassouPorCimaRemove != null) {
                    egde = egdeMousePassouPorCimaRemove;
                    egdeMousePassouPorCimaRemove = null;
                } else {
                    egde = seletionEgde.selecionar(e.getX(), e.getY());
                }
                if (egde != null) {
                    try {
                        seletionEgde.addSelecionado(egde);
                        ControlDateNetwork.getInstance().tryRemoveEgde(seletionEgde.getSeletion());
                        seletionEgde.getSeletion().clear();
                    } catch (RemovingNodeWithDemandAssociationException ex) {
                        ex.printStackTrace();
                    }
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

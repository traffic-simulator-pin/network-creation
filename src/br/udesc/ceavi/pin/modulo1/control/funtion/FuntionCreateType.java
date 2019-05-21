package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameTabelaEdge;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateType extends FuntionCreate<Type> implements ILoop, Observado<ObservadorRender> {

    private FuntionSelecionarEgde seletion;

    public FuntionCreateType() {
        System.out.println("FuntionCreateType");
        seletion = new FuntionSelecionarEgde();
        initMouse();
    }

    @Override
    public void removeObservador(ObservadorRender obs) {
        seletion.removeObservador(obs);
    }

    @Override
    public void addObservador(ObservadorRender obs) {
        seletion.addObservador(obs);
    }

    @Override
    public MouseManeger getMouseManeger() {
        return mouse;
    }

    public List<Egde> getSeletion() {
        return seletion.getListaSelecionado();
    }

    @Override
    public void offer() {
        date.offerType(lista);
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seletion.selecionarAddList(e.getPoint().x, e.getPoint().y);
                atualizarTela();
            }
        };
    }

    private void atualizarTela() {
        ControllerDesktop d = ControllerDesktop.getInstance();

        for (ViewJanelaSistema j : d.getJanelas()) {
            if (j.getClass() == ViewFrameTabelaEdge.class) {
                ((ViewFrameTabelaEdge) j).atualizaListaEdge(this.getSeletion());
                break;
            }
        }
    }

    @Override
    public void render() {
        seletion.render();
    }

    @Override
    public void update() {
    }

    @Override
    public void processInput() {
    }
}

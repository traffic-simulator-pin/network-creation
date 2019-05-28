package br.udesc.ceavi.pin.modulo1.control.funtion;

import java.awt.event.MouseEvent;
import java.util.List;

import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.frame.FrameSetTypeEgde;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameTabelaEdge;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionCreateType extends FuntionCreate<Type> implements ILoop, Observado<ObservadorRender> {

    private FuntionSelecionarEgde seletion;
    private FrameSetTypeEgde view;
    private ViewFrameTabelaEdge tabela;

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
        lista.clear();
        atualizarTela();
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
        view.atualizaListaEdge(this.getSeletion());
        tabela.atualizaListaEdge(this.getSeletion());
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

    public void setView(FrameSetTypeEgde view) {
        this.view = view;
    }

    public void setTabela(ViewFrameTabelaEdge tabela) {
        this.tabela = tabela;
    }

    public void createNewType(Type modelo) {
        lista.add(modelo);
    }
}

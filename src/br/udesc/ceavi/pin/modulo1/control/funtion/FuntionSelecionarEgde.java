package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionSelecionarEgde extends FuntionSelection<Egde> implements Observado<ObservadorTelaDesenho>, ILoop {

    private List<ObservadorTelaDesenho> listaObs;

    public FuntionSelecionarEgde() {
        listaObs = new ArrayList<>();
        System.out.println("FuntionSelecionarEgde");
        initMouse();
    }

    @Override
    public void addObservador(ObservadorTelaDesenho oObs) {
        this.listaObs.add(oObs);
    }

    public void removeObservador(ObservadorTelaDesenho oObs) {
        this.listaObs.remove(oObs);
    }

    @Override
    public void processInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        if (!getListaSelecionado().isEmpty()) {
            listaObs.forEach(oObs -> {
                for (Egde oEgde : getListaSelecionado()) {
                    // System.out.println("chamou aqui e pintou");
                    oObs.addSpriteFuntion("EgdeView", new float[]{oEgde.x1(), oEgde.y1(), oEgde.x2(), oEgde.y2()}, Color.blue);
                }
            });
        }
    }

    @Override
    public void selecionarAddList(int x, int y) {
        //Transformando o Ponto da tela de desenho em um ponto da estrutura de dados.
        float[] realLocation = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Egde
        for (Egde egde : ControllerDateNetwork.getInstance().getAllEgde()) {
            //Verificando se o egde tem a area a baixo
            if (egde.havePointInLine(
                    //Area criada apartir do ponto de clique
                    new Point((int) realLocation[0], (int) realLocation[1]))) {

                //Logica de adicionar quando não tem e remover quando tem
                if (super.getListaSelecionado().contains(egde)) {
                    removeSelecionado(egde);
                } else {
                    addSelecionado(egde);
                }
            }
        }
    }

    @Override
    public Egde selecionar(int x, int y) {
        float[] realLocation = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Egde
        for (Egde egde : ControllerDateNetwork.getInstance().getAllEgde()) {
            //Verificando se o egde tem a area a baixo
            if (egde.havePointInLine(new Point((int) realLocation[0], (int) realLocation[1]))) {
                return egde;
            }
        }
        return null;
    }

    @Override
    public List<Egde> getSeletion() {
        return super.getSeletion(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initMouse() {
        mouse = new MouseManeger() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarAddList(e.getPoint().x, e.getPoint().y);
                atualizarTela();
            }

        };
    }

    private void atualizarTela() {
        ControllerDesktop d = ControllerDesktop.getInstance();
        ViewFrameEdge view = null;

        for (ViewJanelaSistema j : d.getJanelas()) {
            if (j.getClass() == ViewFrameEdge.class) {
                view = (ViewFrameEdge) j;
                break;
            }
        }

        if (view == null) {
            return;
        }
        view.atualizaListaEdge(this.getSeletion());
    }
}

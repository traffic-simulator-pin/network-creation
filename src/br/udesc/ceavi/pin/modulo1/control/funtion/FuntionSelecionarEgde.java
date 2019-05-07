package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.MouseManeger;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author GustavoSantos
 * @since 24/04/2019
 *
 */
public class FuntionSelecionarEgde extends FuntionSelection<Egde> {

    public FuntionSelecionarEgde() {
        System.out.println("FuntionSelecionarEgde");
        initMouse();
    }

    @Override
    public Egde selecionar(int x, int y) {
        //Transformando o Ponto da tela de desenho em um ponto da estrutura de dados.
        float[] realLocation = HelpLocator.getNetworkRealLocation(x, y);
        //Varendo a lista de Egde
        for (Egde egde : ControlDateNetwork.getInstance().getAllEgde()) {
            //Verificando se o egde tem a area a baixo
            if (egde.havePointInLine(
                    //Area criada apartir do ponto de clique
                    new Point((int) realLocation[0], (int) realLocation[1]))) {
                
                //Logica de adicionar quando não tem e remover quando tem
                if (super.getListaSelecionado().contains(egde)) {
                    removeSelecionado(egde);
                    return null;
                } else {
                    addSelecionado(egde);
                    return egde;
                }
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
                selecionar(e.getPoint().x, e.getPoint().y);
            }

        };
    }
}

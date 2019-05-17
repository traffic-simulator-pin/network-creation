package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateDemanda;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;

/**
 *
 * @author Drew
 */
public class ViewFrameDemanda extends ViewFrameModulo1Padrao {

    private FuntionCreateDemanda controller;

    public ViewFrameDemanda() {
        super("");
        this.controller = new FuntionCreateDemanda();
        this.setLocation(300, 10);
        this.moveToFront();
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {

        ViewPanelManutencao novo = super.criaAreaManutencao();
        novo.adicionaCampo("nodoOrigem", "Nodo Origem");
        novo.adicionaCampo("nodoDestino", "Nodo Destino");
        novo.adicionaCampo("demanda", "Demanda");

        return novo;
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        this.acoesPanel.adicionaAcao("Salvar");
    }

    public FuntionCreateDemanda getFuntion() {
        return controller;
    }

}

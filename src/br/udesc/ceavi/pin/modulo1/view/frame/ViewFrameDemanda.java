package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateDemanda;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class ViewFrameDemanda extends ViewFrameModulo1Padrao {
    
    private FuntionCreateDemanda controller;
    private JButton btnFechar;
    private JTextField tfOrigem;
    private JTextField tfDestino;
    private JTextField jtfDemanda;
    
    public ViewFrameDemanda() {
        this.controller = new FuntionCreateDemanda();
        this.setLocation(300, 10);
        this.moveToFront();
        this.toFront();
    }
    
    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        
        ViewPanelManutencao novo = super.criaAreaManutencao();
        tfOrigem = novo.adicionaCampo("nodoOrigem", "Nodo Origem");
        tfDestino = novo.adicionaCampo("nodoDestino", "Nodo Destino");
        jtfDemanda = novo.adicionaCampo("demanda", "Demanda");
        tfDestino.setEditable(false);
        tfOrigem.setEditable(false);
        return novo;
    }
    
    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
    }
    
    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        this.acoesPanel.adicionaAcao("Salvar");
        btnFechar = this.acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> this.distruirInstancia());
    }
    
    public FuntionCreateDemanda getFuntion() {
        return controller;
    }
    
    private void distruirInstancia() {
        ControllerDesktop.getInstance().removerInstanciaJanela(this);
        this.dispose();
    }
    
}

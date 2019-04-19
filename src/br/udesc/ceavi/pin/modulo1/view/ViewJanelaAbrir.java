/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameModulo1Padrao;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 *
 * @author Drew
 */
public class ViewJanelaAbrir extends ViewFrameModulo1Padrao {
    
    public static final Dimension FRAME_TAMANHO_BASE = new Dimension(700, 300); // Tamanho máximo padrão da Janela.
    
    public ViewJanelaAbrir() {
        super("abrir");
        this.setSize(FRAME_TAMANHO_BASE);
    }
    
     @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();
//        novo.adicionaCampo("campo1", "Campo1");
//        novo.adicionaCampo("campo2", "Campo2");
//        novo.adicionaCampo("campo3", "Campo3");
        novo.adicionaComponenteAreaTexto("areaTexto", "Arquivo", new JTextArea());
//        novo.adicionaComponente("escolherArquivo", "", new JFileChooser());
         trataComponente(novo);
        return novo;
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes();
        
//        ViewPanelAcoes acoes = new ViewPanelAcoes();
//        acoesPanel.adicionaAcao("Cancelar");
//        acoesPanel.adicionaAcao("Novo");
        acoesPanel.adicionaAcao("Abrir");
        
    }
    
    private void trataComponente(ViewPanelManutencao pM) {
        JComponent[] t = pM.getCampo("areaTexto");
        JTextArea j = (JTextArea) t[1];
        j.setColumns(20);
        j.setRows(50);
//        t[0].
//        textarea.setColumns(20);
//        textarea.setRows(5);
    }
}

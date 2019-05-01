package br.udesc.ceavi.pin.modulo1.view.panel;


import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Drew
 */
public class ViewPanelAcoes extends JPanel {
    /**
    * Adiciona uma ação ao painel.
    * @param nome - nome da ação.
    */
   public void adicionaAcao(String nome) {
        JButton botaoAcao = new JButton(nome);
        botaoAcao.setName(nome);
        this.add(botaoAcao);
   }

   /**
    * Busca um botão pelo nome.
    * @param nome - nome do botão.
    */
   public JButton getBotao(String nome) {
        Component[] botoes = this.getComponents();
        for (Component botao : botoes) {
                if(botao.getName().equals(nome) && botao instanceof JButton) {
                        return (JButton) botao;
                }
        }
        return null;
   }
}

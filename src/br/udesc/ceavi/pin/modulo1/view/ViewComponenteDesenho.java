/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.pin.modulo1.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Drew
 */
public class ViewComponenteDesenho extends JComponent {
    private JButton btnCria, btnSeleciona, btnApaga, btnCriarRua;
    private JPanel jPanel;
    private TelaDesenho frame;

    
    public ViewComponenteDesenho() {
//        initComponents();
        initMyComponents();
        this.setVisible(true);
    }
    
    // Variables declaration - do not modify                     
    // End of variables declaration                   
    private void initMyComponents() {
        jPanel = new JPanel();

        btnCria = new JButton("Criar");
        btnSeleciona = new JButton("Selecionar");
        btnApaga = new JButton("Apagar");   
        btnCriarRua = new JButton("Criar Rua");

        jPanel.add(btnCria);
        jPanel.add(btnSeleciona);
        jPanel.add(btnApaga);
        jPanel.add(btnCriarRua);

        frame = new TelaDesenho();

        Dimension d = new Dimension(800, 600);

        frame.setSize(d);
        frame.setPreferredSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);

//        Container content = this.getContentPane();
//        content.setLayout(new BorderLayout());

//        content.add(frame, BorderLayout.CENTER);
//        content.add(jPanel, BorderLayout.NORTH);

        btnCria.addActionListener((e) -> frame.criarTrecho());
        btnSeleciona.addActionListener((e) -> frame.selecionarTrecho());
        btnApaga.addActionListener((e) -> frame.apagarTrecho());
    }
}

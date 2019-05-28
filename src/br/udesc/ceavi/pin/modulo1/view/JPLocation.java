package br.udesc.ceavi.pin.modulo1.view;

		
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.util.UtilNumeros;

/**
 *
 * @author GustavoSantos
 * @since 27/04/2019
 *
 */
public class JPLocation extends JPanel {

	private static final long serialVersionUID = 5439954247300207664L;

    private JButton btnZoomIn;
    private JButton btnZoomOut;
    private JLabel lbEscala;
    private JLabel lbZoom;

	
	private final AreaDesenho areaDesenho;

    public JPLocation(AreaDesenho areaDesenho) {
        initComponents();
        this.areaDesenho = areaDesenho;
    }

    private void initComponents() {

        btnZoomIn = new JButton();
        btnZoomOut = new JButton();
        lbZoom = new JLabel();
        lbEscala = new JLabel();

        setMaximumSize(new java.awt.Dimension(900, 54));
        setMinimumSize(new java.awt.Dimension(900, 54));

        btnZoomIn.setText("+");
        btnZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomInActionPerformed(evt);
            }
        });

        btnZoomOut.setText("-");
        btnZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZoomOutActionPerformed(evt);
            }
        });

        lbZoom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbZoom.setHorizontalAlignment(SwingConstants.CENTER);
        lbZoom.setText(renderZoomInfor());

        lbEscala.setText(renderEscalaInfor());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(lbEscala, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(btnZoomOut)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbZoom, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZoomIn)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbEscala)
                    .addComponent(btnZoomOut)
                    .addComponent(lbZoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnZoomIn))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        HelpLocator.zoomIn();
        lbZoom.setText(renderZoomInfor());
        lbEscala.setText(renderEscalaInfor());
        setLocalizaoDaTela();
        repaint();
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        HelpLocator.zoomOut();
        lbZoom.setText(renderZoomInfor());
        lbEscala.setText(renderEscalaInfor());
        setLocalizaoDaTela();
        repaint();
    }

    //Tem como objetivo reajustar a localização da Tela conforme o zomm é aplicado
    private void setLocalizaoDaTela() {
        HelpLocator.setGuiaX(UtilNumeros.clamp(HelpLocator.getGuideX(), 0, (int) (HelpLocator.getTelaDesenhoWidth() - areaDesenho.getWidth() / HelpLocator.getZOOM())));
        HelpLocator.setGuiaY(UtilNumeros.clamp(HelpLocator.getGuideY(), 0, (int) (HelpLocator.getTelaDesenhoHeight() - areaDesenho.getHeight() / HelpLocator.getZOOM())));
    }

    private static String renderZoomInfor() {
        return "Zoom :  " + (int) (HelpLocator.getZOOM() * 100) + "%";
    }

    private static String renderEscalaInfor() {
        return "Escala: " + HelpLocator.getScaleInfor()[0] + "cm : " + HelpLocator.getScaleInfor()[1] + " m";
    }

}

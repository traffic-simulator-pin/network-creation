package br.udesc.ceavi.pin.modulo1.view;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.util.Clamp;
import javax.swing.JPanel;

/**
 *
 * @author GustavoSantos
 * @since 27/04/2019
 *
 */
public class JPLocation extends JPanel {

    private final AreaDesenho areaDesenho;

    public JPLocation(AreaDesenho areaDesenho) {
        initComponents();
        this.areaDesenho = areaDesenho;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnZoomIn = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        lbZoom = new javax.swing.JLabel();

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
        lbZoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbZoom.setText(renderZoomInfor());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(609, Short.MAX_VALUE)
                .addComponent(btnZoomOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnZoomIn)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnZoomIn)
                    .addComponent(btnZoomOut))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbZoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomInActionPerformed
        HelpLocator.zoomIn();
        lbZoom.setText(renderZoomInfor());
        setLocalizaoDaTela();
        repaint();
    }//GEN-LAST:event_btnZoomInActionPerformed

    private void btnZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZoomOutActionPerformed
        HelpLocator.zoomOut();
        lbZoom.setText(renderZoomInfor());
        setLocalizaoDaTela();
        repaint();
    }//GEN-LAST:event_btnZoomOutActionPerformed

    //Tem como objetivo reajustar a localização da Tela conforme o zomm é aplicado
    private void setLocalizaoDaTela() {
        HelpLocator.setGuiaX(Clamp.clamp(HelpLocator.getGuideX(), 0, (int) (HelpLocator.getNetworkWidth() - areaDesenho.getWidth() / HelpLocator.getZOOM())));
        HelpLocator.setGuiaY(Clamp.clamp(HelpLocator.getGuideY(), 0, (int) (HelpLocator.getNetworkHeight() - areaDesenho.getHeight() / HelpLocator.getZOOM())));
    }

    private static String renderZoomInfor() {
        return "Zoom :  " + (int) (HelpLocator.getZOOM() * 100) + "%";
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnZoomIn;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JLabel lbZoom;
    // End of variables declaration//GEN-END:variables

}

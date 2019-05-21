package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class FrameCreateDefaultType extends ViewFrameModulo1Padrao {

    private JButton btnSalvar;
    private JButton btnFechar;
    private JComboBox jcbFluxo;
    private FrameCreateEgde view;
    private JTextField tfNumFaixa;
    private JTextField tfVelocidade;
    private JTextField tfCapacidade;

    public FrameCreateDefaultType(FrameCreateEgde view) {
        this.setMySide(255, 200);
        this.setLocation(view.getLocation().x + (view.getWidth() / 2) - (getWidth() / 2), view.getLocation().y + 90);
        this.view = view;
        this.setTitle("Type Personalizado");
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();

        tfNumFaixa = novo.adicionaCampo("nfaixas", "Número Faixas");
        tfVelocidade = novo.adicionaCampo("velocidade", "Velocidade");
        tfCapacidade = novo.adicionaCampo("velocidade", "Capacidade");
        jcbFluxo = new JComboBox(new String[]{"Mão Unica", "Mão Dupla"});
        novo.adicionaComponente("", "Fluxo", jcbFluxo);
        return novo;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
        view.abrirJanela();
        view.toFront();
        view.moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        btnSalvar = acoesPanel.adicionaAcao("Salvar");
        btnFechar = acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> super.destruirInstanciaJanela());
        btnSalvar.addActionListener(new ViewActionListenerSalvar());
    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }

    private class ViewActionListenerSalvar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerDesktop d = desktop();
            String capacidade = tfCapacidade.getText();
            String numFaixas = tfNumFaixa.getText();
            String velocidade = tfVelocidade.getText();

            if (capacidade.isEmpty()) {
                try {
                    throw new Exception("Informe Uma Capacidade Valida");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

            if (numFaixas.isEmpty()) {
                try {
                    throw new Exception("Informe Um Numero de Faixas Valido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

            if (velocidade.isEmpty()) {
                try {
                    throw new Exception("Informe Uma Velocidade Valida");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            int cap = 0;
            float v = 0;
            int nuF = 0;
            try {
                cap = Integer.parseInt(capacidade);
                nuF = Integer.parseInt(numFaixas);
                velocidade = velocidade.replace(',', '.');
                v = Float.parseFloat(velocidade);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Informe Dados Validos");
                return;
            }
            Type modelo = new Type();
            if (jcbFluxo.getSelectedIndex() == 1) {
                modelo.setOneway(true);
            } else {
                modelo.setOneway(false);
            }
            modelo.setNumLanes(nuF);
            modelo.setSpeed(v);
            modelo.setCapacity(cap);
            view.setTypePersonalizado(modelo);
            destruirInstanciaJanela();
        }
    }
}

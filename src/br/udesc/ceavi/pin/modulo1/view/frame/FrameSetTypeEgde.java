package br.udesc.ceavi.pin.modulo1.view.frame;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.control.funtion.ICreateFuntion;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class FrameSetTypeEgde extends ViewFrameModulo1Padrao {

    private ICreateFuntion funtionCreateType;
    private JButton btnSalvar;
    private JButton btnFechar;
    private JComboBox jcbFluxo;
    private ViewFrameEdge viewFrameEdge;

    public FrameSetTypeEgde(FuntionCreateType funtionCreateType, ViewFrameEdge viewFrameEdge) {
        this.funtionCreateType = funtionCreateType;
        this.setSize(new Dimension(255, 200));
        this.setLocation(300, 10);
        this.viewFrameEdge = viewFrameEdge;
        this.setName("type");
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();

        novo.adicionaCampo("nomeEdge", "Nome dos edge");
        novo.adicionaCampo("nfaixas", "Número Faixas");
        novo.adicionaCampo("velocidade", "Velocidade");
        novo.adicionaCampo("velocidade", "Capacidade");
        jcbFluxo = new JComboBox(new String[]{"Mão Unica", "Mão Dupla"});
        novo.adicionaComponente("", "", jcbFluxo);
        return novo;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
        viewFrameEdge.abrirJanela();
        viewFrameEdge.toFront();
        viewFrameEdge.moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        btnSalvar = acoesPanel.adicionaAcao("Salvar");
        btnFechar = acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> this.distruirInstancia());
        btnSalvar.addActionListener(new ViewActionListenerSalvar());
    }

    private void distruirInstancia() {
        desktop().removerInstanciaJanela(this);
        desktop().removerInstanciaJanela(viewFrameEdge);
        desktop().getViewPrincipal();

        viewFrameEdge.dispose();

        this.dispose();
    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }

    private class ViewActionListenerSalvar implements ActionListener {

        private Type modelo;

        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerDesktop d = desktop();
            ViewFrameEdge viewEdg = null;

            for (ViewJanelaSistema v : d.getJanelas()) {
                if (v instanceof ViewFrameEdge) {
                    viewEdg = (ViewFrameEdge) v;
                    break;
                }

            }

            JComponent[] tfNumFaixas = getAreaManutencao().getCampo("nfaixas");
            JComponent[] tfVelocidade = getAreaManutencao().getCampo("velocidade");
            JComponent[] tfNomeEgde = getAreaManutencao().getCampo("nomeEdge");

            if (modelo == null) {
                modelo = new Type();
            }
            modelo.setNumLanes(Integer.parseInt(((JTextField) tfNumFaixas[1]).getText() + ""));

            if (jcbFluxo.getSelectedIndex() == 1) {
                modelo.setOneway(true);
            } else {
                modelo.setOneway(false);
            }

            modelo.setSpeed(Float.parseFloat(((JTextField) tfVelocidade[1]).getText()));

            List<Egde> edges = viewEdg.getEgds();

            for (Egde ed : edges) {
                ed.setType(modelo);
            }

            modelo.setListDeEgdeQuePertenco(edges);
            viewEdg.atualizaListaEdge(edges);
        }
    }
}

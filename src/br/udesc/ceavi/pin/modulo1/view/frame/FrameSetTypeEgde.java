package br.udesc.ceavi.pin.modulo1.view.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.panel.ViewPanelManutencao;

/**
 *
 * @author Drew
 */
public class FrameSetTypeEgde extends ViewFrameModulo1Padrao {

    private FuntionCreateType funtionCreateType;
    private JButton btnSalvar;
    private JButton btnFechar;
    private JComboBox jcbFluxo;
    private ViewFrameTabelaEdge tabela;
    private final TelaComBotoes telaComBotoes;
    private JTextField tfCapacidade;
    private JTextField tfVelocidade;
    private JTextField tfNumFaixa;
    private JTextField tfNome;
    private JTextField tfLength;

    public FrameSetTypeEgde(FuntionCreateType funtionCreateType, ViewFrameTabelaEdge viewFrameEdge, TelaComBotoes telaComBotoes) {
        this.funtionCreateType = funtionCreateType;
        funtionCreateType.setView(this);
        setMySide(255, 235);
        this.setLocation(300, 10);
        this.tabela = viewFrameEdge;
        this.telaComBotoes = telaComBotoes;
        this.setTitle("Definição De Type Egde");
    }

    @Override
    protected ViewPanelManutencao criaAreaManutencao() {
        ViewPanelManutencao novo = super.criaAreaManutencao();

        tfNome = novo.adicionaCampo("nomeEdge", "Nome dos Edge");
        tfNome.setEditable(false);
        tfLength = novo.adicionaCampo("length", "Length");
        tfLength.setEditable(false);
        tfNumFaixa = novo.adicionaCampo("nfaixas", "Numero Faixas");
        tfVelocidade = novo.adicionaCampo("velocidade", "Velocidade");
        tfCapacidade = novo.adicionaCampo("capacidade", "Capacidade");
        jcbFluxo = new JComboBox(new String[]{"Mão Unica", "Mão Dupla"});
        novo.adicionaComponente("", "Fluxo", jcbFluxo);
        return novo;
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
        toFront();
        moveToFront();
        tabela.abrirJanela();
        tabela.toFront();
        tabela.moveToFront();
    }

    @Override
    protected void criaAreaAcoes() {
        super.criaAreaAcoes(); //To change body of generated methods, choose Tools | Templates.
        btnSalvar = acoesPanel.adicionaAcao("Salvar");
        btnFechar = acoesPanel.adicionaAcao("Fechar");
        btnFechar.addActionListener((e) -> this.distruirInstancia());
        btnSalvar.addActionListener(new ViewActionListenerSalvar());
    }

    @Override
    public void fechaJanela() {
        super.fechaJanela();
        distruirInstancia();
    }

    private void distruirInstancia() {
        desktop().removerInstanciaJanela(this);
        desktop().removerInstanciaJanela(tabela);
        desktop().getViewPrincipal();

        tabela.dispose();
        telaComBotoes.setFuntion(null);
        this.dispose();
    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }

    public void atualizaListaEdge(List<Egde> seletion) {
        if (seletion.isEmpty()) {
            limparCampos();
        }
        String nome = "";
        String capacidade = "";
        String velocidade = "";
        String length = "";
        String numFaixas = "";
        for (Egde egde : seletion) {
            if (nome.equals("Valores Diversos")
                    && capacidade.equals("Valores Diversos")
                    && velocidade.equals("Valores Diversos")
                    && numFaixas.equals("Valores Diversos")
                    && length.equals("Valores Diversos")) {
                break;
            }
            nome = setCamposAll(egde.getNome(), nome);
            length = setCamposAll(egde.getWidth() + "", length);
            capacidade = setCamposAll(egde.getType().getCapacity() + "", capacidade);
            numFaixas = setCamposAll(egde.getType().getNumLanes() + "", numFaixas);
            velocidade = setCamposAll(egde.getType().getSpeed() + "", velocidade);
        }
        for (Egde egde : seletion) {
            System.out.println(egde.toString());
        }
        tfCapacidade.setText(capacidade);
        tfNome.setText(nome);
        tfNumFaixa.setText(numFaixas);
        tfLength.setText(length);
        tfVelocidade.setText(velocidade);
        this.repaint();
    }

    private String setCamposAll(String campoEgde, String campo) {
        if (!campoEgde.equals(campo) && campo.equals("")) {
            campo = campoEgde;
        } else if (!campoEgde.equals(campo)) {
            campo = "Valores Diversos";
        }
        return campo;
    }

    private void limparCampos() {
        tfCapacidade.setText("");
        tfLength.setText("");
        tfNome.setText("");
        tfNumFaixa.setText("");
        tfVelocidade.setText("");
    }

    private class ViewActionListenerSalvar implements ActionListener {

        private Type modelo;

        @Override
        public void actionPerformed(ActionEvent e) {
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
            funtionCreateType.getSeletion().forEach(egde -> modelo.associarTypeEgde(egde));
            funtionCreateType.createNewType(modelo);
            funtionCreateType.offer();
        }
    }
}

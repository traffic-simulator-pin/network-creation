package br.udesc.ceavi.pin.modulo1.view.frame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateEgdeTipo1;
import br.udesc.ceavi.pin.modulo1.control.funtion.FuntionCreateEgdeTipo2;
import br.udesc.ceavi.pin.modulo1.control.funtion.ICreateFuntion;
import br.udesc.ceavi.pin.modulo1.model.DefaultType;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;

/**
 *
 * @author Gustavo C Santos
 * @since 20/05/2019
 *
 */
public class FrameCreateEgde extends ViewFrameModulo1Padrao {

	private static final long serialVersionUID = 81777440265538155L;
	private JComboBox<String> cbType;
    private final ICreateFuntion<Egde> controller;
    private final TelaComBotoes areaDesenho;
    private JButton btnCreateTypePersonalizado;
    private List<Type> listaType = new ArrayList<>();

    public FrameCreateEgde(FuntionCreateEgdeTipo1 createEgde, TelaComBotoes areaDesenho) {
        this.controller = createEgde;
        ((FuntionCreateEgdeTipo1) controller).setCreate(this);
        this.areaDesenho = areaDesenho;
        configuracoes();
    }

    private void configuracoes() {
        this.setTitle("Escolha O Type Para Os Seus Egde");
        this.setLocation(300, 10);
        this.moveToFront();
        this.toFront();
        this.setVisible(false);
        setResizable(false);
        setMySide(390, 85);
        intiComponent();
    }

    public FrameCreateEgde(FuntionCreateEgdeTipo2 createEgde, TelaComBotoes areaDesenho) {
        this.controller = createEgde;
        ((FuntionCreateEgdeTipo2) controller).setCreate(this);
        this.areaDesenho = areaDesenho;
        configuracoes();
    }

    @Override
    public void destruirInstanciaJanela() {
        super.destruirInstanciaJanela();
        areaDesenho.setFuntion(null);
    }

    @Override
    public void fechaJanela() {
        super.fechaJanela();
    }

    @Override
    public void abrirJanela() {
        super.abrirJanela();
    }

    private void intiComponent() {
        cbType = new JComboBox<>();
        for (int i = 0; i < DefaultType.values().length; i++) {
            listaType.add(DefaultType.values()[i].getType());
        }
        setModeloCombox();
        btnCreateTypePersonalizado = new JButton("Create Type");
        this.getContentPane().setLayout(new BorderLayout());
        this.add(cbType, BorderLayout.NORTH);
        this.add(btnCreateTypePersonalizado, BorderLayout.SOUTH);
        initListener();
    }

    private void setModeloCombox() {
        cbType.removeAllItems();
        for (int i = 0; i < listaType.size(); i++) {
            Type type = listaType.get(i);
            cbType.addItem(
                    ("Faixas: " + type.getNumLanes()
                    + (type.isOneway() ? "Mão Dupla" : "Mão Unica")
                    + ",Capacidade: " + type.getCapacity()
                    + ",Velocidade: " + type.getSpeed())
            );
        }
        cbType.repaint();
        this.repaint();
    }

    public void initListener() {
        btnCreateTypePersonalizado.addActionListener((e) -> {
            FrameCreateDefaultType frameCreateDefaultType = new FrameCreateDefaultType(this);
            ControllerDesktop.getInstance().adicionaJanela(frameCreateDefaultType);
            frameCreateDefaultType.abrirJanela();
        });
    }

    public Type getTypeSelecionado() {
    	System.out.println("Estilo Selecionado: " + listaType.get(cbType.getSelectedIndex()).toString());
        return listaType.get(cbType.getSelectedIndex());
    }

    public void setTypePersonalizado(Type modelo) {
        listaType.add(modelo);
        setModeloCombox();
        cbType.setSelectedIndex(cbType.getMaximumRowCount() - 2);
    }
}

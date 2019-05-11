package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.view.ControlDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import br.udesc.ceavi.pin.modulo1.view.ViewFrameEdge;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameModulo1Padrao;
import br.udesc.ceavi.pin.modulo1.view.frame.ViewFrameType;
import com.sun.javafx.geom.Edge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;

/**
 *
 * @author Drew
 */
public class ViewListenersDadosEdge extends ViewListenersFrame {
    
    private Egde modelo;
    
    public ViewListenersDadosEdge(ViewJanelaSistema view) {
        super(view);
    }
    
    public ViewListenersDadosEdge(ViewJanelaSistema view, Egde modelo) {
        super(view);
        this.modelo = modelo;
    }
    
    /**
    * Adiciona os listeners padrões a tela.
    */
    @Override
    protected void adicionaListenersPadroes() {
        super.adicionaListenersPadroes();
        JButton botaoConsulta;

        botaoConsulta = view.getAreaAcoes().getBotao("Salvar");
        if(botaoConsulta != null) {
                botaoConsulta.addActionListener(new ViewActionListenerSalvar());
        }
        
        botaoConsulta = view.getAreaAcoes().getBotao("Alterar Type");
        if(botaoConsulta != null) {
                botaoConsulta.addActionListener(new ViewActionAlterarType());
        }
    }
    
    private class ViewActionListenerSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ViewFrameModulo1Padrao janela = (ViewFrameModulo1Padrao)view;
            JComponent[] campo = janela.getAreaManutencao().getCampo("nome");
            String txt = ((JTextField) campo[1]).getText();
//            modelo.setNome(txt);
            view.fechaJanela();
        }
    }
    
    private class ViewActionAlterarType implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(modelo.getType() == null) {// verifica se algo no type, se não tem poe um modelo novo
//                modelo.setType(new Type(), (modelo.getNome() == null)?"":modelo.getNome());
            }
            
            ControlDesktop d = ControlDesktop.getInstance();
            ViewFrameType viewType = new ViewFrameType(modelo.getType());//instancia a view do frame

            if(d.getJanela(viewType.getNome()) != null) {//verifica se já havia sido criada antes
                viewType = (ViewFrameType)d.getJanela(viewType.getNome());
            } else {
                d.adicionaJanela(viewType);
            }

            viewType.abreJanela();//torna a janela visivel
            viewType.preencheCampos();//preenche os campos caso haja algo no modelo

//            new ViewListenerFrameType(viewType, modelo.getType());//poe listeners na tela
 
            view.fechaJanela();

        }
    }
}

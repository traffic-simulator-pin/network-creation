package br.udesc.ceavi.pin.modulo1.view.listeners;

import br.udesc.ceavi.pin.modulo1.view.Desktop;
import br.udesc.ceavi.pin.modulo1.view.ViewJanelaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Drew
 */
public class ViewListenersFrame {
    //CAMPOS
	protected Desktop desktop; //Referência ao Desktop.
	protected ViewJanelaSistema view; // Tela de consulta dos listeners.
	
	public ViewListenersFrame() {
	    super();
	}
	/**
	 * Cria uma nova serie de listeners padrões de consulta e os adiciona a consulta especificada.
	 * @param view - tela de consulta para adicionar os listeners.
	 */
	public ViewListenersFrame(ViewJanelaSistema view) {
            this.desktop = Desktop.getInstance();
            this.view = view;
            adicionaListenersPadroes();
	}
        
	
	/**
	 * Efetua a alteração da view;
	 * @param view
	 */
	public void setView(ViewJanelaSistema view) {
            this.view = view;
            adicionaListenersPadroes();
	}
	
	/**
	 * Adiciona os listeners padrões a tela de consulta.
	 */
	protected void adicionaListenersPadroes() {
            JButton botaoConsulta;
            botaoConsulta = view.getAreaAcoes().getBotao("Fechar");
            if(botaoConsulta != null) {
                    botaoConsulta.addActionListener(new ViewActionListenerFecharJanela());
            }
	}
    
    private class ViewActionListenerFecharJanela implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            desktop.getJanela(view.getNome()).fechaJanela();
        }
    }
}

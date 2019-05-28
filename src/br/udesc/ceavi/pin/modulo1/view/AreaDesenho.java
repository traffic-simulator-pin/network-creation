package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JComponent;

import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.view.sprites.ISprite;

/**
 *
 * @author GustavoSantos
 * @since 04/04/2019
 *
 */
public class AreaDesenho extends JComponent implements ObservadorRender {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1956702247277346089L;
	private final List<ISprite> listaSpriteDate;
    private final List<ISprite> listaSpriteContruction;

    public AreaDesenho() {
        listaSpriteDate = new ArrayList<>();
        listaSpriteContruction = new ArrayList<>();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        apagarTela(g);
        pintarLimites(g);
        desenharSprites(g);
    }

    private void desenharSprites(Graphics g) {
        try {
            List<ISprite> desenha = new ArrayList<>();
            desenha.addAll(listaSpriteDate);
            desenha.addAll(listaSpriteContruction);
            desenha.forEach((sprite) -> sprite.draw(g));
        } catch (Exception ex) {
            new RuntimeException(ex.getMessage());
        }
    }

    private void pintarLimites(Graphics g) {
        int sizeLimiteNetwork = (int) HelpLocator.getZOOM() + 1;
        if (sizeLimiteNetwork > 10) {
            sizeLimiteNetwork = 10;
        }
        //Pita Limite na Esquerda
        if (HelpLocator.getGuideX() == 0) {
            g.fillRect(0, 0, sizeLimiteNetwork, getHeight());
        }
        //Pita Limite na Direta
        if (HelpLocator.getGuideX() + getWidth() / HelpLocator.getZOOM() + 1 >= HelpLocator.getTelaDesenhoWidth()) {
            g.fillRect(getWidth() - sizeLimiteNetwork, 0, sizeLimiteNetwork, getHeight());
        }
        //Pita Limite em Cima
        if (HelpLocator.getGuideY() == 0) {
            g.fillRect(0, 0, getWidth(), sizeLimiteNetwork);
        }

        //Pita Limite em Baixo
        if (HelpLocator.getGuideY() + getHeight() / HelpLocator.getZOOM() + 1 >= HelpLocator.getTelaDesenhoHeight()) {
            g.fillRect(0, getHeight() - sizeLimiteNetwork, getWidth(), sizeLimiteNetwork);
        }
    }

    private void apagarTela(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
    }

    public void clearListSpriteDateNetwork() {
        listaSpriteDate.clear();
    }

    public void setMouseListener(EventListener mouse) {
        //Get
        MouseListener[] mouseListenrerLista = getMouseListeners();
        MouseMotionListener[] mouseMotionListenersLista = getMouseMotionListeners();

        //remove
        for (MouseListener mouseListener : mouseListenrerLista) {
            removeMouseListener(mouseListener);
        }
        for (MouseMotionListener mouseMotionListener : mouseMotionListenersLista) {
            removeMouseMotionListener(mouseMotionListener);
        }

        //Add
        addMouseListener((MouseListener) mouse);
        addMouseMotionListener((MouseMotionListener) mouse);
    }

    @Override
    public void addSpriteDateNetwork(String nome, float[] position, Color cor) {
        newSprite(nome, position, cor, listaSpriteDate);
    }

    private synchronized void newSprite(String nome, float[] position, Color cor, List<ISprite> list) {
        try {
            ISprite newSprite = (ISprite) Class.forName("br.udesc.ceavi.pin.modulo1.view.sprites." + nome).newInstance();
            newSprite.setDateLocation(position);
            newSprite.setColor(cor);
            list.add(newSprite);
            newSprite.setAreaDaTelaDesenho(getSize());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addSpriteFuntion(String nome, float[] position, Color cor) {
        newSprite(nome, position, cor, listaSpriteContruction);
    }

    public void clearListSpriteFuntion() {
        this.listaSpriteContruction.clear();
    }
}

package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import br.udesc.ceavi.pin.modulo1.view.sprites.ISprite;

/**
 *
 * @author GustavoSantos
 * @since 04/04/2019
 *
 */
public class AreaDesenho extends JComponent implements ObservadorTelaDesenho {

    private final List<ISprite> listaSpriteDate;
    private final List<ISprite> listaSpriteContruction;

    public AreaDesenho() {
        listaSpriteDate = new ArrayList<>();
        listaSpriteContruction = new ArrayList<>();
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        //Tudo Branco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);

        int sizeLimiteNetwork = (int) HelpLocator.getZOOM() + 1;
        if (sizeLimiteNetwork > 10) {
            sizeLimiteNetwork = 10;
        }
        //Pita Limite na Esquerda
        if (HelpLocator.getGuideX() == 0) {
            g.fillRect(0, 0, sizeLimiteNetwork, getHeight());
        }
        //Pita Limite na Direta
        if (HelpLocator.getGuideX() + getWidth() / HelpLocator.getZOOM() + 1 >= HelpLocator.getNetworkWidth()) {
            g.fillRect(getWidth() - sizeLimiteNetwork, 0, sizeLimiteNetwork, getHeight());
        }
        //Pita Limite em Cima
        if (HelpLocator.getGuideY() == 0) {
            g.fillRect(0, 0, getWidth(), sizeLimiteNetwork);
        }

        //Pita Limite em Baixo
        if (HelpLocator.getGuideY() + getHeight() / HelpLocator.getZOOM() + 1 >= HelpLocator.getNetworkHeight()) {
            g.fillRect(0, getHeight() - sizeLimiteNetwork, getWidth(), sizeLimiteNetwork);
        }

        //Desenho das Sprite
        try {
            List<ISprite> desenha = new ArrayList<>();
            desenha.addAll(listaSpriteDate);
            desenha.addAll(listaSpriteContruction);
            desenha.forEach((sprite) -> sprite.draw(g));
        } catch (Exception ex) {
            new RuntimeException(ex.getMessage());
        }
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
            if (newSprite.inAreaRender(getSize())) {
                list.add(newSprite);
            }
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

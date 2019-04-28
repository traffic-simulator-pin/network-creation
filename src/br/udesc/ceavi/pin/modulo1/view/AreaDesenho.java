package br.udesc.ceavi.pin.modulo1.view;

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import br.udesc.ceavi.pin.modulo1.control.ObservadorTelaDesenho;
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
    private Thread thread;

    public AreaDesenho() {
        listaSpriteDate = new ArrayList<>();
        listaSpriteContruction = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        listaSpriteDate.stream().forEach((sprite) -> sprite.draw(g));
        listaSpriteContruction.stream().forEach((sprite) -> sprite.draw(g));
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

    private void newSprite(String nome, float[] position, Color cor, List<ISprite> list) {
        try {
            ISprite newSprite = (ISprite) Class.forName("br.udesc.ceavi.pin.modulo1.view.sprites." + nome).newInstance();
            newSprite.setDateLocation(position);
            newSprite.setColor(cor);
            list.add(newSprite);
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

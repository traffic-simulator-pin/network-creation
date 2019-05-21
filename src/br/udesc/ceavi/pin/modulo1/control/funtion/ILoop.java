package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.Observado;
import br.udesc.ceavi.pin.modulo1.control.ObservadorRender;

/**
 *
 * @author GustavoSantos
 * @since 26/04/2019
 * 
 */
public interface ILoop extends Observado<ObservadorRender>{

    public void render();
    public void update();
    public void processInput();

}

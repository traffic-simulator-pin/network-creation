package br.udesc.ceavi.pin.modulo1.control;

import java.awt.Point;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public interface IControlDesenho {

    public void criarNodo(Point mousePosition);

    public void addObservador(ObservadorRender obs);

    public void selecionarEgde(Point mousePosition);

    public void apagarEgde() throws Exception;

    public void deleteEgdeSelecionados();
    
    public void test();
    
    public void deleteSelection();

    public boolean isAExtremePointToEgde(int x2, int y2);

}

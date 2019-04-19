package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author GustavoSantos
 * @since 05/04/2019
 *
 */
public class ControlDesenho implements IControlDesenho {

    private Function ativa;
    private Thread thread;
    private Node de;
    private Node para;

    //Lista de Todos Os Trechos Ja Criados
    private List<Egde> egdeSelecionados;
    private List<Node> listNodeIntersects;
    private List<Observador> listObservador;
    private IControlEgde controlEgde;
    private Node paraTest;

    @Override
    public void addObservador(Observador obs) {
        this.listObservador.add(obs);
    }

    public ControlDesenho() {
        this.listNodeIntersects = new ArrayList<>();
        this.egdeSelecionados = new ArrayList<>();
        listObservador = new ArrayList<>();
        controlEgde = new ControlEgde();

        thread = new Thread() {

            @Override
            public void run() {
                long MS_PER_FRAME = 16; //  16 ms/frame = 60 FPS
                long last = System.currentTimeMillis();
                while (true) {
                    long now = System.currentTimeMillis();
                    if (now - last > MS_PER_FRAME) {
                        last = now;
                        update();
                        render();
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * Cria Os Nodos Na Possição Indicada Pelo Usuario
     *
     * @param mousePosition possição do mouse ao click
     */
    @Override
    public void criarNodo(Point mousePosition) {
        if (paraTest != null) {
            para = paraTest;
        } else {
            Node t = new Node(mousePosition.x, mousePosition.y);

            Node t2 = checkValideExistingPoint(t);

            if (t2 != null) {
                if (de == null) {
                    de = t2;
                } else {
                    para = t2;
                }
            }
        }
    }

    /**
     * Verifica A Validade Do Ponto, Interseção ou Novo Ponto para Igual Ao
     * Ponto de
     *
     * @param nodoEmAnalise
     * @return Se de == nodoEmAnalise retuna null, Se nodoEmAnalise igual a
     * ponto do extremo de um trecho retorno ponto extreno trecho, Se não
     * Renorna nodoEmAnalise;
     */
    private Node checkValideExistingPoint(Node nodoEmAnalise) {
        if (de != null && de.getArea().intersects(nodoEmAnalise.getArea())) {
            return null;
        }

        for (Node node : controlEgde.getListExtremosNodesEgde()) {
            if (node.inMyArea(nodoEmAnalise)) {
                return node;
            }
        }

        return nodoEmAnalise;
    }

    /**
     * Cria Trechos, chama verificação de interseção e pintura
     */
    private void criarEgde() {
        Egde t = new Egde(de, para);
        listNodeIntersects = controlEgde.controlGetIntersectsLinePoint(t);
        controlEgde.addEgde(t);
        de = null;
        para = null;
        paraTest = null;
        listObservador.forEach(obs -> obs.mousePositionResquest(0, 0, false));
    }

    /**
     * Seleção de Trecho
     *
     * @param mousePosition possição do mouse ao click
     */
    @Override
    public void selecionarEgde(Point mousePosition) {
        //Verifica se ouve uma seleção
        controlEgde.getListEgde().forEach((t) -> {
            if (t.havePointInLine(mousePosition)) {
                //Se ja selecionado Exclui da lista
                if (!egdeSelecionados.contains(t)) {
                    egdeSelecionados.add(t);
                } else {
                    //Se nao selecionado add a lista
                    egdeSelecionados.remove(t);
                }
            }
        });
    }

    /**
     * Apagar Trecho Selecionado
     *
     * @throws java.lang.Exception
     */
    @Override
    public void apagarEgde() throws Exception {
        if (egdeSelecionados.isEmpty()) {
            throw new Exception("Realiaze A Seleceção Dos Trechos");
        }
        listObservador.forEach(obs -> obs.apagarEgdes());
    }

    private boolean haveTwoPoint() {
        return de != null && para != null;
    }

    @Override
    public void deleteEgdeSelecionados() {
        controlEgde.removeEgde(egdeSelecionados);
        deleteSelection();
    }

    @Override
    public void test() {
        final int x = 820;
        final int y = 580;
        Random num = new Random();
        for (int i = 0; i < 100; i++) {
            controlEgde.addEgde(new Egde(
                    new Node(num.nextInt(x) + 10, num.nextInt(y) + 10),
                    new Node(num.nextInt(x) + 10, num.nextInt(y) + 10)));
        }
        System.out.println(controlEgde.getListEgde().size());
    }

    private synchronized void update() {
        listObservador.forEach(o -> {
            o.clear();
            if (haveTwoPoint()) {
                criarEgde();
            }
            controlEgde.getListEgde().forEach((egde) -> {
                if (ativa != Function.CREATE && egdeSelecionados.contains(egde)) {
                    if (ativa == Function.REMOVE) {
                        paintLine(o, egde, Color.RED);
                    }
                    if (ativa == Function.SELECT) {
                        paintLine(o, egde, Color.BLUE);
                    }
                } else {
                    paintLine(o, egde, Color.BLACK, Color.RED);
                }
            });
            if (ativa == Function.CREATE) {
                if (de != null) {
                    o.addPoint((int) de.getX(), (int) de.getY(), Color.BLUE);
                }
                if (de != null) {
                    o.mousePositionResquest(de.getX(), de.getY(), true);
                }
                if (para != null) {
                    o.addPoint((int) para.getX(), (int) para.getY(), Color.BLUE);
                }
            }
        });
    }

    private void paintLine(Observador o, Egde egde, Color cor) {
        o.addLine(egde.x1(), egde.y1(), egde.x2(), egde.y2(), cor);
        o.addPoint((int) egde.de().getX(), (int) egde.de().getY(), cor);
        o.addPoint((int) egde.para().getX(), (int) egde.para().getY(), cor);
    }

    private void paintLine(Observador o, Egde egde, Color corL, Color corP) {
        o.addLine(egde.x1(), egde.y1(), egde.x2(), egde.y2(), corL);
        o.addPoint((int) egde.de().getX(), (int) egde.de().getY(), corP);
        o.addPoint((int) egde.para().getX(), (int) egde.para().getY(), corP);
    }

    private synchronized void render() {
        listObservador.forEach(o -> o.repaitTela());
    }

    @Override
    public void setFuncao(Function ativa) {
        this.ativa = ativa;
        breakCreate();
        if (this.ativa == Function.CREATE) {
            deleteSelection();
        }
    }

    private void breakCreate() {
        de = null;
        para = null;
    }

    @Override
    public void deleteSelection() {
        this.egdeSelecionados.clear();
    }

    @Override
    public boolean isAExtremePointToEgde(int x2, int y2) {
        if (!de.inMyArea(x2, y2)) {
            for (Node node : controlEgde.getListExtremosNodesEgde()) {
                if (node.inMyArea(x2, y2)) {
                    listObservador.forEach(obs -> obs.addPoint((int) node.getX(), (int) node.getY(), Color.YELLOW));
                    paraTest = node;
                    return true;
                }
            }
        }
        paraTest = null;
        return false;
    }

}

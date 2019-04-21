package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.Color;
import java.awt.Cursor;
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

    private Function function;
    private Thread thread;
    private Node de;
    private Node para;

    //Lista de Todos Os Trechos Ja Criados
    private List<Egde> egdeSelecionados;
    private List<Node> listNodeIntersects;
    private List<Observador> listObservador;
    private IControlEgde controlEgde;
    private Node auxSe;

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
        if (auxSe != null) {
            if (de == null) {
                de = auxSe;
                auxSe = null;
            } else if (para == null) {
                para = auxSe;
                auxSe = null;
            }
        } else {
            Node t = new Node(mousePosition.x, mousePosition.y);
            if (de == null) {
                de = t;
            } else {
                para = t;
            }
        }
    }

    private Node checkExistingNode(Point point) {
        Node n = new Node(point.x, point.y);
        for (Node node : controlEgde.getListExtremosNodesEgde()) {
            if (node.inMyArea(n)) {
                return node;
            }
        }
        return null;
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
        auxSe = null;
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
        setFuncao(Function.CREATE);
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
                if (function != Function.CREATE && egdeSelecionados.contains(egde)) {
                    if (function == Function.REMOVE) {
                        paintLine(o, egde, Color.RED);
                    }
                    if (function == Function.SELECT) {
                        paintLine(o, egde, Color.BLUE);
                    }
                } else {
                    paintLine(o, egde, Color.BLACK, Color.RED);
                }
            });
            if (function == Function.CREATE) {
                if (de == null) {
                    o.mousePositionResquest();
                }
                if (de != null) {
                    o.addPoint((int) de.getX(), (int) de.getY(), Color.BLUE);
                }
                if (de != null && para == null) {
                    o.mousePositionResquest(de.getX(), de.getY(), true);
                }
                if (para != null) {
                    o.addPoint((int) para.getX(), (int) para.getY(), Color.BLUE);
                }
            }
        });
    }

    private void paintLine(Observador o, Egde egde, Color corTudo) {
        o.addLine(egde.x1(), egde.y1(), egde.x2(), egde.y2(), corTudo);
        o.addPoint((int) egde.de().getX(), (int) egde.de().getY(), corTudo);
        o.addPoint((int) egde.para().getX(), (int) egde.para().getY(), corTudo);
    }

    private void paintLine(Observador o, Egde egde, Color corLinha, Color corPonto) {
        o.addLine(egde.x1(), egde.y1(), egde.x2(), egde.y2(), corLinha);
        o.addPoint((int) egde.de().getX(), (int) egde.de().getY(), corPonto);
        o.addPoint((int) egde.para().getX(), (int) egde.para().getY(), corPonto);
    }

    private synchronized void render() {
        listObservador.forEach(o -> o.repaitTela());
    }

    @Override
    public void setFuncao(Function ativa) {
        breakCreate();
        if (ativa == Function.CREATE) {
            deleteSelection();
            listObservador.forEach(obs -> obs.notifyChangeOfCursorCustomer("imagens/mouse/create.png", 0, 23));
        }
        if (ativa == Function.SELECT) {
            listObservador.forEach(obs -> obs.notifyChangeOfCursorNative(Cursor.DEFAULT_CURSOR));
        }
        if (this.function == Function.REMOVE && ativa == Function.SELECT) {
            listObservador.forEach(obs -> obs.notifyChangeOfCursorCustomer("imagens/mouse/delete.png", 1, 29));
        }
        this.function = ativa;
    }

    private void breakCreate() {
        de = null;
        para = null;
        auxSe = null;
    }

    @Override
    public void deleteSelection() {
        this.egdeSelecionados.clear();
    }

    @Override
    public boolean isAExtremePointToEgde(int x, int y) {
        if (de == null) {
            if (encontraNodeJaExistente(x, y)) {
                return true;
            }
        } else {
            if (para == null) {
                if (!de.inMyArea(x, y)) {
                    if (encontraNodeJaExistente(x, y)) {
                        return true;
                    }
                }
            }
        }
        auxSe = null;
        return false;
    }

    private boolean encontraNodeJaExistente(int x, int y) {
        auxSe = checkExistingNode(new Point(x, y));
        if (auxSe != null) {
            listObservador.forEach(obs -> obs.addPoint((int) auxSe.getX(), (int) auxSe.getY(), Color.YELLOW));
            return true;
        }
        return false;
    }
}

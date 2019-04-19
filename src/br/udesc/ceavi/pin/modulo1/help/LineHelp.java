package br.udesc.ceavi.pin.modulo1.help;

import br.udesc.ceavi.pin.modulo1.model.Node;
import java.awt.geom.Line2D;

/**
 *
 * @author GustavoSantos
 * @since 07/04/2019
 *
 */
public class LineHelp {

    private LineHelp() {
    }

    public static float getSizeLine(float x1, float x2, float y1, float y2) {
        return (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    /**
     * Encontra a Interseção da Linha e Retorna Um Node Nessa Possição
     *
     * @param linha1 linha do trecho
     * @param linha2 linha do trecho
     * @return o Node Na Possição correspondem a Interseção das Linhas
     */
    public static Node getIntersectNodeLine(Line2D linha1, Line2D linha2) {
        double m1 = getConficienteAngula(linha1);
        double n1 = getConficienteLiner(m1, linha1);

        double m2 = getConficienteAngula(linha2);
        double n2 = getConficienteLiner(m2, linha2);

        double x = getXIntersectPorSubracao(n1, m1, n2, m2);
        double y = getYIntersectSubracao(n1, m1, x);
        return new Node((int) x - (Node.SIZE / 2), (int) y - (Node.SIZE / 2), Node.SIZE);
    }

    /**
     * Realiza a Subtração de Funçoes Para Obter O X Do Ponto de interseção
     *
     * @param n1 Conficiente Liner da linha 1
     * @param m1 Conficiente Angular da linha 1
     * @param n2 Conficiente Liner da linha 2
     * @param m2 Conficiente Angular da linha 3
     * @return X do ponto de interseção
     */
    private static double getXIntersectPorSubracao(double n1, double m1, double n2, double m2) {
        return -(n1 - n2) / (m1 - m2);
    }

    /**
     * Resolve a Função da linha com o X de interseção
     *
     * @param n Conficiente Liner da linha
     * @param m Conficiente Angular da linha
     * @param x do ponto de interseção
     * @return y do ponto de interseção
     */
    private static double getYIntersectSubracao(double n, double m, double x) {
        return m * x + n;
    }

    /**
     * Obtem o ConficienteAngula da Linha
     *
     * @param linha
     * @return Conficiente Angula
     */
    public static double getConficienteAngula(Line2D linha) {
        double x1 = linha.getX1();
        double y1 = linha.getY1();
        double x2 = linha.getX2();
        double y2 = linha.getY2();
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Obtem o getConficiente Liner da Linha
     *
     * @param conficienteAngula
     * @param linha
     * @return Conficiente Liner
     */
    public static double getConficienteLiner(double conficienteAngula, Line2D linha) {
        double x = linha.getP1().getX();
        double y = linha.getP1().getY();
        return y - (conficienteAngula * x);
    }

    /**
     * Obtem a Função da Linha
     *
     * @param m Conficiente Angula
     * @param n Conficiente Liner
     * @param linha
     * @return Funçao
     */
    private static String getFuncaoDaReta(Line2D linha, int m, int n) {
        return m + "." + "x" + " -y " + " + " + n;
    }
}

package br.udesc.ceavi.pin.modulo1.control;

/**
 *
 * @author GustavoSantos
 * @since 02/05/2019
 *
 */
public class ControlTelaDesenho {

    final int wPretendido, hPretendido;
    int wSizeTela, hSizeTela;
    private final int escala;

    public int gethSizeTela() {
        return hSizeTela;
    }

    public int getwSizeTela() {
        return wSizeTela;
    }

    public int getEscala() {
        return escala;
    }

    
    public ControlTelaDesenho(int w, int h) {
        this.wPretendido = w;
        this.hPretendido = h;

        escala = paso1();
        System.out.println("Escala " + escala);

        wSizeTela = paso3(escala, wPretendido, 800f);
        hSizeTela = paso3(escala, hPretendido, 600f);

        System.out.printf("Telas Medidas W : %s, H: %s\n", wSizeTela, hSizeTela);
        System.out.printf("Pretendidos w %s , h %s\n", wPretendido, hPretendido);
    }

    private int paso1() {
        float tH = paso2(hPretendido, 600f);
        float tW = paso2(wPretendido, 800f);
        if (tH > tW) {
            if (((int) tH) < tH) {
                return (int) ++tH;
            }
            return (int) tH;
        } else {
            if (((int) tW) < tW) {
                return (int) ++tW;
            }
            return (int) tW;
        }
    }

    private float paso2(float test, float telaSize) {
        return test / telaSize;
    }

    private int paso3(int escalaInicial, int tamanhoP, float telaS) {
        float maxST = escalaInicial * telaS;
        if (maxST >= tamanhoP) {
            return regraDeTres(maxST, tamanhoP, telaS);
        }
        return (int) maxST;
    }

    private int regraDeTres(float maxST, int tamanhoP, float telaS) {
        return (int) ((tamanhoP * telaS) / maxST);
    }

}

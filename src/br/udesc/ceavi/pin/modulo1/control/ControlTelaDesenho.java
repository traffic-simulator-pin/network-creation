package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.util.RegraDeTres;

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

        escala = setEscala();
        wSizeTela = definerTamanhoTelaDesenho(escala, wPretendido, wGuia);
        hSizeTela = definerTamanhoTelaDesenho(escala, hPretendido, hGuia);
    }

        final float wGuia = 800f;
        final float hGuia = 600f;
    private int setEscala() {
        float tW = quantaTela(wPretendido, wGuia);
        float tH = quantaTela(hPretendido, hGuia);
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

    private float quantaTela(float test, float telaSize) {
        return test / telaSize;
    }

    private int definerTamanhoTelaDesenho(int escalaInicial, int tamanhoP, float telaS) {
        float maxST = escalaInicial * telaS;
        if (maxST >= tamanhoP) {
            return (int) RegraDeTres.regraDeTres(maxST, tamanhoP, telaS);
        }
        return (int) maxST;
    }

}

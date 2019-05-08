package br.udesc.ceavi.pin.modulo1.control;

import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.util.RegraDeTres;

/**
 *
 * @author GustavoSantos
 * @since 02/05/2019
 *
 */
public class ControlTelaDesenho {

    private final int wPretendido, hPretendido;
    private int wSizeTela, hSizeTela;
    final float wGuia, hGuia;
    private final int escala;

    public ControlTelaDesenho(int w, int h, int wGuia, int hGuia) {
        this.wPretendido = w;
        this.hPretendido = h;
        this.wGuia = wGuia;
        this.hGuia = hGuia;
        escala = setEscala();
        wSizeTela = definerTamanhoTelaDesenho(escala, wPretendido, wGuia);
        hSizeTela = definerTamanhoTelaDesenho(escala, hPretendido, hGuia);
        HelpLocator.setSizeTelaDesenhoWidth(wSizeTela);
        HelpLocator.setSizeTelaDesenhoHeight(hSizeTela);
        HelpLocator.setEscala(escala);
    }

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

    public int gethSizeTela() {
        return hSizeTela;
    }

    public int getwSizeTela() {
        return wSizeTela;
    }

    public int getEscala() {
        return escala;
    }

    public int getwPretendido() {
        return wPretendido;
    }

    public int gethPretendido() {
        return hPretendido;
    }

    public float getwGuia() {
        return wGuia;
    }

    public float gethGuia() {
        return hGuia;
    }

}

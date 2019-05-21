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

    private int wPretendido, hPretendido;
    private int wRenderTela, hRenderTela;
    float wGuia, hGuia;
    private int escala;

    public ControlTelaDesenho() {
        this.wGuia = 800;
        this.hGuia = 600;
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
        return hRenderTela;
    }

    public int getwSizeTela() {
        return wRenderTela;
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

    public void setParametroToTelaDesenho(String largura, String altura) throws Exception {
        try {
            this.wPretendido = Integer.parseInt(largura);
            this.hPretendido = Integer.parseInt(altura);
            escala = setEscala();
            wRenderTela = definerTamanhoTelaDesenho(escala, wPretendido, wGuia);
            hRenderTela = definerTamanhoTelaDesenho(escala, hPretendido, hGuia);
            HelpLocator.setSizeTelaDesenhoWidth(wRenderTela);
            HelpLocator.setSizeTelaDesenhoHeight(hRenderTela);
            System.out.println("Escala " + escala);
            System.out.println("Guias " + wGuia + " " + hGuia);
            System.out.println("Tamanho pretendido: " + wPretendido + " " + hPretendido);
            System.out.println("Tamanho Render: " + wRenderTela + " " + hRenderTela);
            HelpLocator.setEscala(escala);
        } catch (Exception e) {
            throw new Exception("Insira Nos Respetivos Campos Largura e Altura Valores Validos");
        }
    }

}

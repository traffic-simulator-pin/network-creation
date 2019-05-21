package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.control.ControlTelaDesenho;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.view.ControllerDesktop;
import br.udesc.ceavi.pin.modulo1.view.TelaComBotoes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author paulohenrique
 */
public class FuntionAbrir {

    private ControlTelaDesenho control;

    public FuntionAbrir() {
        this.control = new ControlTelaDesenho();
        System.out.println("FuntionAbrir");
        gerarMalha();
    }

    public void gerarMalha() {

        try {
            List<Node> listaNodes = new ArrayList<>();
            List<Type> listaTypes = new ArrayList<>();
            List<Demanda> listaDemanda = new ArrayList<>();
            List<Egde> listaEdges = new ArrayList<>();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document documentoXML = documentBuilder.parse(dataBase().getLocalDeSalvamento());

            //Gera uma lista com todos os nodes do XML
            NodeList listaNodesXML = documentoXML.getElementsByTagName("node");

            for (int i = 0; i < listaNodesXML.getLength(); i++) {
                org.w3c.dom.Node node = listaNodesXML.item(i);
                Element noNodes = (Element) node;
                Node no = new Node(
                        Integer.parseInt(noNodes.getAttribute("id")),
                        Float.parseFloat(noNodes.getAttribute("x")),
                        Float.parseFloat(noNodes.getAttribute("y"))
                );
                listaNodes.add(no);
            }

            //Gera uma lista com todos os types do XML
            NodeList listaTypesXML = documentoXML.getElementsByTagName("type");

            for (int i = 0; i < listaTypesXML.getLength(); i++) {
                org.w3c.dom.Node type = listaTypesXML.item(i);

                Element noType = (Element) type;

                Type t = new Type(
                        Integer.parseInt(noType.getAttribute("id")),
                        Integer.parseInt(noType.getAttribute("numLanes")),
                        Boolean.getBoolean(noType.getAttribute("oneway")),
                        Float.parseFloat(noType.getAttribute("speed")),
                        Integer.parseInt(noType.getAttribute("capacit"))
                );
                listaTypes.add(t);
            }

            NodeList listaDemandasXML = documentoXML.getElementsByTagName("demanda");

            for (int i = 0; i < listaDemandasXML.getLength(); i++) {
                org.w3c.dom.Node demanda = listaDemandasXML.item(i);

                Element noDemanda = (Element) demanda;
                int inodeA = -1;
                int inodeB = -1;
                for (int j = 0; j < listaNodes.size(); j++) {
                    if (listaNodes.get(j).getId() == Integer.parseInt(noDemanda.getAttribute("nodeA"))) {
                        inodeA = j;
                    }
                    if (listaNodes.get(j).getId() == Integer.parseInt(noDemanda.getAttribute("nodeB"))) {
                        inodeB = j;
                    }

                }

                if (inodeA >= 0 && inodeB >= 0) {
                    Demanda d = new Demanda(
                            Integer.parseInt(noDemanda.getAttribute("id")),
                            listaNodes.get(inodeA),
                            listaNodes.get(inodeB),
                            Integer.parseInt(noDemanda.getAttribute("valor"))
                    );
                    listaDemanda.add(d);
                }
                inodeA = -1;
                inodeB = -1;
            }

            // Gera lista de edges do xml
            NodeList listaEdgesXML = documentoXML.getElementsByTagName("edge");

            for (int i = 0; i < listaEdgesXML.getLength(); i++) {
                org.w3c.dom.Node edge = listaEdgesXML.item(i);

                Element noEdge = (Element) edge;
                int noDe = -1;
                int noPara = -1;

                for (int j = 0; j < listaNodes.size(); j++) {
                    if (listaNodes.get(j).getId() == Integer.parseInt(noEdge.getAttribute("from"))) {
                        noDe = j;
                    }
                    if (listaNodes.get(j).getId() == Integer.parseInt(noEdge.getAttribute("to"))) {
                        noPara = j;
                    }

                }

                Egde e = new Egde(
                        Integer.parseInt(noEdge.getAttribute("id")),
                        listaNodes.get(noDe),
                        listaNodes.get(noPara),
                        Float.parseFloat(noEdge.getAttribute("length")),
                        noEdge.getAttribute("nome")
                );

                int iType = -1;
                for (int j = 0; j < listaTypes.size(); j++) {
                    if (listaTypes.get(j).getId() == Integer.parseInt(noEdge.getAttribute("type"))) {
                        iType = j;
                    }
                }

                if (iType >= 0) {
                    e.setType(listaTypes.get(iType));
                }

                listaEdges.add(e);

                noDe = -1;
                noPara = -1;
                iType = -1;
            }

            //Gera configuração de tamanho de malha
            NodeList listaMalhaXML = documentoXML.getElementsByTagName("malha");
            String largura = "";
            String altura = "";
            for (int j = 0; j < listaMalhaXML.getLength(); j++) {
                org.w3c.dom.Node malha = listaMalhaXML.item(j);
                Element noMalha = (Element) malha;

                largura = noMalha.getAttribute("width");

                altura = noMalha.getAttribute("height");

            }

            try {
                control.setParametroToTelaDesenho(largura, altura);
                if (desktop().hasViewPrincipa()) {
                    if (dataBase().haveElements()) {
                        dataBase().salvar();
                        dataBase().reiniciar();
                    }
                }
                TelaComBotoes telaComBotoes = new TelaComBotoes(control.getwSizeTela(), control.gethSizeTela());
                desktop().addViewPrincipa(telaComBotoes);
                telaComBotoes.abrirJanela();
            } catch (Exception ex) {
            }
            //Agora chama método para iniciar aplicação com arquivo XML
            dataBase().StartByFile(listaEdges, listaDemanda);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FuntionAbrir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(FuntionAbrir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FuntionAbrir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ControllerDateNetwork dataBase() {
        return ControllerDateNetwork.getInstance();
    }

    private static ControllerDesktop desktop() {
        return ControllerDesktop.getInstance();
    }
}

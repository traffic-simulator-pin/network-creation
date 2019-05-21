package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Demanda;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author paulohenrique
 */
public class FuntionSalvar {

    public FuntionSalvar() {
        System.out.println("FuntionSalvar");
        gerarXML();
    }

    public void gerarXML() {

        try {

            List<Node> listaNodes = ControllerDateNetwork.getInstance().getAllNode();
            List<Type> listaTypes = ControllerDateNetwork.getInstance().getAllType();
            List<Egde> listaEdges = ControllerDateNetwork.getInstance().getAllEgde();
            List<Demanda> listaDemandas = ControllerDateNetwork.getInstance().getAllDemanda();
            System.out.println(listaDemandas.size());

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document documentoXML = documentBuilder.newDocument();

            Element data = documentoXML.createElement("data");

            //Salva tamanho malha
            Element malha = documentoXML.createElement("malha");

            Attr heightMalha = documentoXML.createAttribute("height");
            heightMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[1]));
            malha.setAttributeNode(heightMalha);

            Attr widthMalha = documentoXML.createAttribute("width");
            widthMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[0]));
            malha.setAttributeNode(widthMalha);

            data.appendChild(malha);

            //Fim Tamanho Malha
            Element nodes = documentoXML.createElement("nodes");

            // For para buscar os nodes, e gerar em xml
            for (Node nodeN : listaNodes) {
                Element node = documentoXML.createElement("node");

                Attr id = documentoXML.createAttribute("id");
                id.setValue("" + nodeN.getId());
                node.setAttributeNode(id);

                Attr x = documentoXML.createAttribute("x");
                x.setValue(String.valueOf(nodeN.getX()));
                node.setAttributeNode(x);

                Attr y = documentoXML.createAttribute("y");
                y.setValue(String.valueOf(nodeN.getY()));
                node.setAttributeNode(y);

                nodes.appendChild(node);
            }
            data.appendChild(nodes);

            Element types = documentoXML.createElement("types");
            //for para buscar os types, e gerar o xml

            for (Type typeN : listaTypes) {
                Element type = documentoXML.createElement("type");

                Attr id = documentoXML.createAttribute("id");
                id.setValue("" + typeN.getId());
                type.setAttributeNode(id);

                Attr numLanes = documentoXML.createAttribute("numLanes");
                numLanes.setValue(String.valueOf(typeN.getNumLanes()));
                type.setAttributeNode(numLanes);

                Attr oneway = documentoXML.createAttribute("oneway");
                oneway.setValue(String.valueOf(typeN.isOneway()));
                type.setAttributeNode(oneway);

                Attr speed = documentoXML.createAttribute("speed");
                speed.setValue(String.valueOf(typeN.getSpeed()));
                type.setAttributeNode(speed);
               
                Attr capacity = documentoXML.createAttribute("capacity");
                capacity.setValue(String.valueOf(typeN.getCapacity()));
                type.setAttributeNode(capacity);

                types.appendChild(type);
            }
            data.appendChild(types);

            Element edges = documentoXML.createElement("edges");
            //for para buscar os edges, e gerar o xml

            for (Egde edgeN : listaEdges) {
                Element edge = documentoXML.createElement("edge");

                Attr id = documentoXML.createAttribute("id");
                id.setValue("" + edgeN.getId());
                edge.setAttributeNode(id);

                Attr source = documentoXML.createAttribute("source");
                source.setValue("" + edgeN.de().getId());
                edge.setAttributeNode(source);

                Attr target = documentoXML.createAttribute("target");
                target.setValue("" + edgeN.para().getId());
                edge.setAttributeNode(target);

                //verifica se há type setado
                if (edgeN.getType() != null) {
                    Attr type = documentoXML.createAttribute("type");
                    type.setValue(String.valueOf(edgeN.getType().getId()));
                    edge.setAttributeNode(type);
                }

                Attr length = documentoXML.createAttribute("length");
                length.setValue(String.valueOf(edgeN.getWidth()));
                edge.setAttributeNode(length);

                Attr name = documentoXML.createAttribute("name");
                name.setValue(edgeN.getNome());
                edge.setAttributeNode(name);

                edges.appendChild(edge);
            }
            data.appendChild(edges);

            Element conections = documentoXML.createElement("conections");
            //for para buscar os edges, e gerar o xml de conections

            for (Egde edgeN : listaEdges) {

                for (Egde edgeAux : listaEdges) {

                    //verifica se o começo de um edge é igual ao começo de outro edge
                    if (edgeN.de().getId() == edgeAux.de().getId() && edgeAux.para().getId() != edgeN.para().getId()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr source = documentoXML.createAttribute("source");
                        source.setValue("" + edgeN.getId());
                        conection.setAttributeNode(source);

                        Attr target = documentoXML.createAttribute("target");
                        target.setValue("" + edgeAux.getId());
                        conection.setAttributeNode(target);

                        conections.appendChild(conection);

                    } //verifica se um final de um edge é igual ao começo de outro
                    else if (edgeN.para().getId() == edgeAux.de().getId()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr source = documentoXML.createAttribute("source");
                        source.setValue("" + edgeN.getId());
                        conection.setAttributeNode(source);

                        Attr target = documentoXML.createAttribute("target");
                        target.setValue("" + edgeAux.getId());
                        conection.setAttributeNode(target);

                    } else if (edgeN.de().getId() == edgeAux.para().getId()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr source = documentoXML.createAttribute("source");
                        source.setValue("" + edgeAux.getId());
                        conection.setAttributeNode(source);

                        Attr target = documentoXML.createAttribute("target");
                        target.setValue("" + edgeN.getId());
                        conection.setAttributeNode(target);
                        conections.appendChild(conection);
                    } else if (edgeN.para().getId() == edgeAux.para().getId() && edgeN.de().getId() != edgeAux.de().getId()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr source = documentoXML.createAttribute("source");
                        source.setValue("" + edgeAux.getId());
                        conection.setAttributeNode(source);

                        Attr target = documentoXML.createAttribute("target");
                        target.setValue("" + edgeN.getId());
                        conection.setAttributeNode(target);
                        conections.appendChild(conection);
                    }
                }

            }
            data.appendChild(conections);

            Element demandas = documentoXML.createElement("demandas");

            //For para gerar xml de demandas
            for (Demanda demandaN : listaDemandas) {
                Element demanda = documentoXML.createElement("demanda");

                Attr nodeA = documentoXML.createAttribute("nodeA");
                nodeA.setValue("" + demandaN.getA().getId());
                demanda.setAttributeNode(nodeA);

                Attr nodeB = documentoXML.createAttribute("nodeB");
                nodeB.setValue("" + demandaN.getB().getId());
                demanda.setAttributeNode(nodeB);

                Attr valor = documentoXML.createAttribute("valor");
                valor.setValue(String.valueOf(demandaN.getDemanda()));
                demanda.setAttributeNode(valor);

                Attr id = documentoXML.createAttribute("id");
                id.setValue(String.valueOf(demandaN.getId()));
                demanda.setAttributeNode(id);

                demandas.appendChild(demanda);

            }

            data.appendChild(demandas);

            documentoXML.appendChild(data);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource documentoFonte = new DOMSource(documentoXML);

            StreamResult documentoFinal = new StreamResult(ControllerDateNetwork.getInstance().getLocalDeSalvamento());

            transformer.transform(documentoFonte, documentoFinal);

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(FuntionSalvar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

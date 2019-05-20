package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
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

            List<Node> listaNodes = ControlDateNetwork.getInstance().getAllNode();
            List<Type> listaTypes = ControlDateNetwork.getInstance().getAllType();
            List<Egde> listaEdges = ControlDateNetwork.getInstance().getAllEgde();
            List<Demanda> listaDemandas = ControlDateNetwork.getInstance().getAllDemanda();

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document documentoXML = documentBuilder.newDocument();

            Element data = documentoXML.createElement("data");

            //Salva tamanho malha
            Element malha = documentoXML.createElement("malha");

            Attr heightMalha = documentoXML.createAttribute("height");
            heightMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[0]));
            malha.setAttributeNode(heightMalha);

            Attr widthMalha = documentoXML.createAttribute("width");
            widthMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[1]));
            malha.setAttributeNode(widthMalha);

            data.appendChild(malha);

            //Fim Tamanho Malha
            Element nodes = documentoXML.createElement("nodes");

            // For para buscar os nodes, e gerar em xml
            for (Node nodeN : listaNodes) {
                Element node = documentoXML.createElement("node");

                Attr id = documentoXML.createAttribute("id");
                id.setValue(nodeN.getID());
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
                id.setValue(typeN.getId());
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
                
                types.appendChild(type);
            }
            data.appendChild(types);

            Element edges = documentoXML.createElement("edges");
            //for para buscar os edges, e gerar o xml

            for (Egde edgeN : listaEdges) {
                Element edge = documentoXML.createElement("edge");

                Attr id = documentoXML.createAttribute("id");
                id.setValue(edgeN.getId());
                edge.setAttributeNode(id);

                Attr from = documentoXML.createAttribute("from");
                from.setValue(edgeN.de().getID());
                edge.setAttributeNode(from);

                Attr to = documentoXML.createAttribute("to");
                to.setValue(edgeN.para().getID());
                edge.setAttributeNode(to);

                Attr type = documentoXML.createAttribute("type");
                type.setValue("01");
                edge.setAttributeNode(type);

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
                    if (edgeN.de().getID() == edgeAux.de().getID() && edgeAux.para().getID() != edgeN.para().getID()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr from = documentoXML.createAttribute("from");
                        from.setValue(edgeN.getId());
                        conection.setAttributeNode(from);

                        Attr to = documentoXML.createAttribute("to");
                        to.setValue(edgeAux.getId());
                        conection.setAttributeNode(to);

                        conections.appendChild(conection);

                    } //verifica se um final de um edge é igual ao começo de outro
                    else if (edgeN.para().getID() == edgeAux.de().getID()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr from = documentoXML.createAttribute("from");
                        from.setValue(edgeN.getId());
                        conection.setAttributeNode(from);

                        Attr to = documentoXML.createAttribute("to");
                        to.setValue(edgeAux.getId());
                        conection.setAttributeNode(to);

                    } else if (edgeN.de().getID() == edgeAux.para().getID()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr from = documentoXML.createAttribute("from");
                        from.setValue(edgeAux.getId());
                        conection.setAttributeNode(from);

                        Attr to = documentoXML.createAttribute("to");
                        to.setValue(edgeN.getId());
                        conection.setAttributeNode(to);
                        conections.appendChild(conection);
                    } else if (edgeN.para().getID() == edgeAux.para().getID() && edgeN.de().getID() != edgeAux.de().getID()) {
                        Element conection = documentoXML.createElement("conection");

                        Attr from = documentoXML.createAttribute("from");
                        from.setValue(edgeAux.getId());
                        conection.setAttributeNode(from);

                        Attr to = documentoXML.createAttribute("to");
                        to.setValue(edgeN.getId());
                        conection.setAttributeNode(to);
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
                        nodeA.setValue(demandaN.getA().getID());
                        demanda.setAttributeNode(nodeA);
                        
                        Attr nodeB = documentoXML.createAttribute("nodeB");
                        nodeB.setValue(demandaN.getB().getID());
                        demanda.setAttributeNode(nodeB);
                        
                        Attr valor = documentoXML.createAttribute("valor");
                        valor.setValue(String.valueOf(demandaN.getDemanda()));
                        demanda.setAttributeNode(valor);
                        
                        demandas.appendChild(demanda);

            }
            
            data.appendChild(demandas);
            
            documentoXML.appendChild(data);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource documentoFonte = new DOMSource(documentoXML);

            StreamResult documentoFinal = new StreamResult(ControlDateNetwork.getInstance().getLocalDeSalvamento());

            transformer.transform(documentoFonte, documentoFinal);

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(FuntionSalvar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

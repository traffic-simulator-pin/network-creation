package br.udesc.ceavi.pin.modulo1.control.funtion;

import br.udesc.ceavi.pin.modulo1.control.ControlDateNetwork;
import br.udesc.ceavi.pin.modulo1.model.Node;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
            List<Node> lista = ControlDateNetwork.getInstance().getAllNode();
            System.out.println(lista.size());

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document documentoXML = documentBuilder.newDocument();

            Element element = documentoXML.createElement("nodes");
            // For para buscar os nodes, e gerar em xml
            for (Node nodeN : lista) {
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

                element.appendChild(node);
            }

            documentoXML.appendChild(element);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource documentoFonte = new DOMSource(documentoXML);

            StreamResult documentoFinal = new StreamResult(ControlDateNetwork.getInstance().getLocalDeSalvamento());

            transformer.transform(documentoFonte, documentoFinal);

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(FuntionSalvar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

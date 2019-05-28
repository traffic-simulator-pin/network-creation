package br.udesc.ceavi.pin.modulo1.control.funtion;

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

import br.udesc.ceavi.pin.modulo1.control.ControllerDateNetwork;
import br.udesc.ceavi.pin.modulo1.help.HelpLocator;
import br.udesc.ceavi.pin.modulo1.model.Conection;
import br.udesc.ceavi.pin.modulo1.model.Egde;
import br.udesc.ceavi.pin.modulo1.model.Node;
import br.udesc.ceavi.pin.modulo1.model.Type;

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
			List<Conection> listaDemandas = ControllerDateNetwork.getInstance().getAllDemanda();
			listaDemandas.sort((d1, d2) -> {
				if (d1.getSource().getId() > d2.getSource().getId()) {
					return 1;
				} else if (d1.getSource().getId() < d2.getSource().getId()) {
					return -1;
				}
				return 0;
			});

			System.out.println(listaDemandas.size());

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document documentoXML = documentBuilder.newDocument();

			Element data = documentoXML.createElement("data");

			// Salva tamanho malha
			Element malha = documentoXML.createElement("malha");

			Attr heightMalha = documentoXML.createAttribute("height");
			heightMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[1]));
			malha.setAttributeNode(heightMalha);

			Attr widthMalha = documentoXML.createAttribute("width");
			widthMalha.setValue(String.valueOf(HelpLocator.getNetworkSize()[0]));
			malha.setAttributeNode(widthMalha);

			data.appendChild(malha);

			// Fim Tamanho Malha
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
			// for para buscar os types, e gerar o xml

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
			// for para buscar os edges, e gerar o xml

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

				// verifica se há type setado
				if (edgeN.getType() != null) {

					Attr type = documentoXML.createAttribute("type");
					type.setValue(String.valueOf(edgeN.getType().getId()));
					edge.setAttributeNode(type);

					Attr numLanes = documentoXML.createAttribute("numLanes");
					numLanes.setValue(String.valueOf(edgeN.getType().getNumLanes()));
					edge.setAttributeNode(numLanes);

					Attr oneway = documentoXML.createAttribute("oneway");
					oneway.setValue(String.valueOf(edgeN.getType().isOneway()));
					edge.setAttributeNode(oneway);

					Attr speed = documentoXML.createAttribute("speed");
					speed.setValue(String.valueOf(edgeN.getType().getSpeed()));
					edge.setAttributeNode(speed);

				}

				Attr length = documentoXML.createAttribute("length");
				length.setValue(String.valueOf(edgeN.getWidth()));
				edge.setAttributeNode(length);

				Attr name = documentoXML.createAttribute("name");
				name.setValue(edgeN.getNome());
				edge.setAttributeNode(name);

				Attr capacity = documentoXML.createAttribute("capacity");
				capacity.setValue("" + edgeN.getType().getCapacity());
				edge.setAttributeNode(capacity);

				
				Attr constantA = documentoXML.createAttribute("constantA");
				constantA.setValue(String.valueOf(edgeN.getConstantANumber()));
				edge.setAttributeNode(constantA);

				Attr constantB = documentoXML.createAttribute("constantB");
				constantB.setValue(String.valueOf(edgeN.getConstantBNumber()));
				edge.setAttributeNode(constantB);

				edges.appendChild(edge);
			}
			data.appendChild(edges);

			Element conections = documentoXML.createElement("conections");
			// for para buscar os edges, e gerar o xml de conections

			for (Conection edgeN : listaDemandas) {

					Element conection = documentoXML.createElement("conection");

					Attr name = documentoXML.createAttribute("name");
					name.setValue(edgeN.getName());
					conection.setAttributeNode(name);

					Attr source = documentoXML.createAttribute("source");
					source.setValue(""+edgeN.getSource().getId());
					conection.setAttributeNode(source);

					Attr target = documentoXML.createAttribute("target");
					target.setValue("" + edgeN.getTarget().getId());
					conection.setAttributeNode(target);

					Attr flow = documentoXML.createAttribute("flow");
						flow.setValue("" + edgeN.getFlow());
					conection.setAttributeNode(flow);

					conections.appendChild(conection);

			}
			data.appendChild(conections);

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

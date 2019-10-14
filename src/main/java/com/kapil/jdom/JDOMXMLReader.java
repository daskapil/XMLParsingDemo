package com.kapil.jdom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class JDOMXMLReader {
	public final String serviceAccount = "";
	
	public void prepareServerList(final String fileName) {
		Document jdomDoc = null;

		try {
			jdomDoc = useDOMParser(fileName);
			Element root = jdomDoc.getRootElement();
			final String serviceAccount = root.getChildTextTrim("serviceAccount");
			
			List<Element> serverNodes = root.getChildren("server");			
			List<ServerDetails> servers = new ArrayList<>();
			for (Element serverNode : serverNodes) {
				System.out.println("Server node name: " + serverNode.getName());

				ServerDetails serverDetails = new ServerDetails();
				serverDetails.setId(serverNode.getAttributeValue("id"));
				serverDetails.setServerName(serverNode.getChildTextTrim("serverName"));
				serverDetails.setSecondsToWait(serverNode.getChildTextTrim("secondsToWait"));

				List<String> serviceList = new ArrayList<>();

				serverNode.getChildren("services").forEach(service -> {
					serviceList.add(service.getChildTextTrim("service"));
				});

				System.out.println("Service List size for " + serverDetails.getServerName() + ": " + serviceList.size());
				serverDetails.setServices(serviceList);
				servers.add(serverDetails);
//				serverNode.getChildren("services").forEach(JDOMXMLReader::readServiceNode);
			}

			for (ServerDetails server : servers) {
				System.out.println(server.toString());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private Document useDOMParser(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		org.w3c.dom.Document w3cDocument = documentBuilder.parse(new File(fileName));
		DOMBuilder domBuilder = new DOMBuilder();
		return domBuilder.build(w3cDocument);
	}

	private void readServiceNode(Element servicesNode) {
		System.out.println("Service Name : " + servicesNode.getChildText("service"));
	}

	/**
	 * Get the next sibling with the same name and type
	 */
	public static Node getNext(Node current) {
		String name = current.getNodeName();
		int type = current.getNodeType();
		return getNext(current, name, type);
	}

	public static Node getNext(Node current, String name, int type) {
		Node first = current.getNextSibling();
		if (first == null)
			return null;

		for (Node node = first; node != null; node = node.getNextSibling()) {

			if (type >= 0 && node.getNodeType() != type)
				continue;
			// System.out.println("getNode: " + name + " " + node.getNodeName());
			if (name == null)
				return node;
			if (name.equals(node.getNodeName())) {
				return node;
			}
		}
		return null;
	}

}

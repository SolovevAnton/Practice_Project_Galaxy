package com.solovev.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static void transform(File fileIn, File fileOut) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fileIn);
        NodeList nodeListPlanets = document.getElementsByTagName("Planet");
        Map<String, ArrayList<Element>> planets = new HashMap<>();
        for (int i = 0; i < nodeListPlanets.getLength(); i++) {
            Element planet = (Element) nodeListPlanets.item(i);
            String name = planet.getElementsByTagName("Name").item(0).getTextContent();
            planets.putIfAbsent(name, new ArrayList<>());
            planets.get(name).add(planet);
        }

        Document documentOut = documentBuilder.newDocument();
        Element planetsOut = documentOut.createElement("Planets");
        documentOut.appendChild(planetsOut);
        int i = 1;
        for (Map.Entry<String, ArrayList<Element>> e : planets.entrySet()) {
            Element equalGroup = documentOut.createElement("EqualGroup");
            equalGroup.setAttribute("number", String.valueOf(i++));
            equalGroup.setAttribute("name", e.getKey());

            for( Element element : e.getValue()) {
                element = (Element) documentOut.importNode(element, true);
                equalGroup.appendChild(element);
            }

            planetsOut.appendChild(equalGroup);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documentOut);
        StreamResult result = new StreamResult(fileOut);
        transformer.transform(source, result);
    }
}

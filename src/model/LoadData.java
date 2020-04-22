package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadData {
	private final static String DATA_XML = "sportsmans.xml";

	public static void loadSportsmans() {
		File xmlFile = new File(DATA_XML);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("sportsman");
			List<Sportsman> sportsmans = new ArrayList<Sportsman>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				sportsmans.add(getSportsman(nodeList.item(i)));
			}

			DataHandler.setSpartsmanList(sportsmans);
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private static Sportsman getSportsman(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			Person people = new Person(getTagValue("Фамилия", element), getTagValue("Имя", element),
					getTagValue("Отчество", element));
			Sportsman man = new Sportsman(people, getTagValue("Состав", element), getTagValue("Позиция", element),
					Integer.valueOf(getTagValue("Титулы", element)), getTagValue("Вид_спорта", element),
					getTagValue("Разряд", element));
			return man;
		}

		return null;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
}
package util.xml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import model.DataHandler;
import model.Sportsman;
import view.table.MainContainer;

public class SaveData {
	private final static String DATA_XML = "sportsmans.xml";
	private static Document doc;

	public static void saveSportsmans() {
		List<Sportsman> sportsmans = DataHandler.getSpartsman();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element rootElement = doc.createElementNS("", "Sportsmans");
			doc.appendChild(rootElement);

			for (int i = 0; i < sportsmans.size(); i++) {
				rootElement.appendChild(getSportsman(sportsmans.get(i)));
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			StreamResult file = new StreamResult(new File(DATA_XML));
			transformer.transform(source, file);

			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("Сохранение");
			errorMessege.setMessage("Сохранение базы данных в " + DATA_XML + " завершено успешно!");		
			errorMessege.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getSportsman(Sportsman sportsman) {
		Element man = doc.createElement("sportsman");

		man.appendChild(getElements(man, "Фамилия", sportsman.getSurname()));
		man.appendChild(getElements(man, "Имя", sportsman.getName()));
		man.appendChild(getElements(man, "Отчество", sportsman.getMiddleName()));
		man.appendChild(getElements(man, "Состав", sportsman.getLineup()));
		man.appendChild(getElements(man, "Позиция", sportsman.getPosition()));
		man.appendChild(getElements(man, "Титулы", Integer.toString(sportsman.getTitlesQuantity())));
		man.appendChild(getElements(man, "Вид_спорта", sportsman.getSport()));
		man.appendChild(getElements(man, "Разряд", sportsman.getCategory()));
		return man;
	}

	private static Node getElements(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}

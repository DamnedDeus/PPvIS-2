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

import com.view.DialogContainer;

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
			
			DialogContainer container = new DialogContainer(MainContainer.getDisplay());
			String path = container.open() + "\\" + DATA_XML;
			
			StreamResult file = new StreamResult(path);
			transformer.transform(source, file);

			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("����������");
			errorMessege.setMessage("���������� ���� ������ � " + path + " ��������� �������!");		
			errorMessege.open();
		} catch (Exception e) {
			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("����������");
			errorMessege.setMessage("������� ������ ���� ����������!");		
			errorMessege.open();
		}
	}

	private static Node getSportsman(Sportsman sportsman) {
		Element man = doc.createElement("sportsman");

		man.appendChild(getElements(man, "�������", sportsman.getSurname()));
		man.appendChild(getElements(man, "���", sportsman.getName()));
		man.appendChild(getElements(man, "��������", sportsman.getMiddleName()));
		man.appendChild(getElements(man, "������", sportsman.getLineup()));
		man.appendChild(getElements(man, "�������", sportsman.getPosition()));
		man.appendChild(getElements(man, "������", Integer.toString(sportsman.getTitlesQuantity())));
		man.appendChild(getElements(man, "���_������", sportsman.getSport()));
		man.appendChild(getElements(man, "������", sportsman.getCategory()));
		return man;
	}

	private static Node getElements(Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}

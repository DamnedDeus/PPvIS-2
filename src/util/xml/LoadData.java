package util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.widgets.MessageBox;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.view.DialogContainer;

import model.DataHandler;
import model.Person;
import model.Sportsman;
import view.form.TablePanelManage;
import view.table.MainContainer;

public class LoadData {
	private final static String DATA_XML = "sportsmans.xml";

	public static void loadSportsmans() {
		try {
		DialogContainer container = new DialogContainer(MainContainer.getDisplay());
		String path = container.open();
		File xmlFile = new File(path);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("sportsman");
			List<Sportsman> sportsmans = new ArrayList<Sportsman>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				sportsmans.add(getSportsman(nodeList.item(i)));
			}

			DataHandler.setSpartsmanList(sportsmans);
			MainContainer.tableConfig(DataHandler.configPage(TablePanelManage.getPage(), TablePanelManage.getPageCount(), TablePanelManage.getPageSize()), MainContainer.getTable());
		} catch (Exception exc) {
			MessageBox errorMessege = new MessageBox(MainContainer.getTable().getShell());
			errorMessege.setText("Загрузка");
			errorMessege.setMessage("Неверно выбран файл!");		
			errorMessege.open();
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
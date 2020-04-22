package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import model.Sportsman;
import model.DataHandler;
import view.table.MainContainer;

public class SearchForm {
	private static Combo text2;
	private static Table table;

	public static void searchSportsman() {
		Shell shell = new Shell(MainContainer.getDisplay());
		shell.setText("Search");
		shell.setSize(800, 700);

		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Informatoins");

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;
		shell.setLayout(rowLayout);

		Group groupe = new Group(shell, SWT.NONE);
		groupe.setLayout(new RowLayout(SWT.VERTICAL));

		Group groupeRadio = new Group(groupe, SWT.NONE);
		groupeRadio.setLayout(new RowLayout(SWT.VERTICAL));
		List<Button> buttons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Button button1 = new Button(groupeRadio, SWT.RADIO);
			buttons.add(button1);
		}
		buttons.get(0).setText("по фио или виду спорта");
		buttons.get(1).setText("по количеству завоеваний титула (макс и мин)");
		buttons.get(2).setText("по фио или разряду");

		Button button = new Button(groupe, SWT.PUSH);
		button.setText("Search");
		button.pack();

		Group groupe1 = new Group(groupe, SWT.NONE);
		groupe1.setLayout(new RowLayout(SWT.HORIZONTAL));

		Text text1 = new Text(groupe1, SWT.BORDER);
		RowData layoutData1 = new RowData();
		layoutData1.width = 150;
		text1.setLayoutData(layoutData1);
		text1.setText("");
		text1.pack();
		Label label1 = new Label(groupe1, SWT.NONE);
		label1.setText("Критерий №1");

		Group groupe2 = new Group(groupe, SWT.NONE);
		groupe2.setLayout(new RowLayout(SWT.HORIZONTAL));

		text2 = new Combo(groupe2, SWT.DROP_DOWN);
		updateListCombobox();
		text2.pack();

		Label label2 = new Label(groupe2, SWT.NONE);
		label2.setText("Критерий №2");

		table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);

		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {

				Integer mod = 0;
				for (int i = 0; i < 3; i++) {
					if (buttons.get(i).getSelection() == true)
						mod = i;
				}
				List<Sportsman> rezult = DataHandler.searchSportsmanProcessing(mod, text1.getText(), text2.getText());
				if (rezult.size() == 0) {
					errorMessege.setMessage("Подходящих элементов не найдено");
					errorMessege.open();
				} else
					tableConfig(rezult);
				MainContainer.tableConfig();

			}
		});
		shell.open();
	}

	private static void updateListCombobox() {
		List<String> updateCombobox = new ArrayList<>();
		List<Sportsman> spartsmans = DataHandler.getSpartsman();

		updateCombobox.add(spartsmans.get(0).getSport());
		updateCombobox.add(spartsmans.get(0).getCategory());

		for (int i = 1; i < spartsmans.size(); i++) {
			Boolean checkSport = false;
			Boolean checkCategory = false;
			for (int j = 0; j < updateCombobox.size(); j++) {
				if (updateCombobox.get(j).equals(spartsmans.get(i).getSport()))
					checkSport = true;
				if (updateCombobox.get(j).equals(spartsmans.get(i).getCategory()))
					checkCategory = true;
			}
			if (checkSport == false)
				updateCombobox.add(spartsmans.get(i).getSport());
			if (checkCategory == false)
				updateCombobox.add(spartsmans.get(i).getCategory());
		}

		String[] items = updateCombobox.toArray(new String[0]);
		text2.setItems(items);
	}

	public static void tableConfig(List<Sportsman> sportsmans) {
		table.setVisible(false);
		table.clearAll();
		table.removeAll();

		String[] titles = { "Полное имя спортсмена", "Состав", "Позиция", "Титулы", "Вид спорта", "Разряд" };
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setAlignment(SWT.CENTER);
			column.setText(titles[loopIndex]);
		}

		for (int loopIndex = 0; loopIndex < sportsmans.size(); loopIndex++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, sportsmans.get(loopIndex).getFullName());
			item.setText(1, sportsmans.get(loopIndex).getLineup());
			item.setText(2, sportsmans.get(loopIndex).getPosition());
			item.setText(3, Integer.toString(sportsmans.get(loopIndex).getTitlesQuantity()));
			item.setText(4, sportsmans.get(loopIndex).getSport());
			item.setText(5, sportsmans.get(loopIndex).getCategory());
		}

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++)
			table.getColumn(loopIndex).pack();
		table.setVisible(true);
	}
}

package view.form;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import model.DataHandler;
import view.table.MainContainer;

public class DeleteForm {

	public static void deleteSportsman() {
		Shell shell = new Shell(MainContainer.getDisplay());
		shell.setText("Delete");
		shell.setSize(350, 400);

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
		button.setText("Delete");
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

		Text text2 = new Text(groupe2, SWT.BORDER);
		RowData layoutData2 = new RowData();
		layoutData2.width = 150;
		text2.setLayoutData(layoutData2);
		text2.setText("");
		text2.pack();
		Label label2 = new Label(groupe2, SWT.NONE);
		label2.setText("Критерий №2");


		button.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Integer mod = 0;
				for (int i = 0; i < 3; i++) {
					if (buttons.get(i).getSelection() == true)
						mod = i;
				}
				Integer rezult = DataHandler.deleteSportsmanProcessing(mod, text1.getText(), text2.getText());
				if (rezult == 0)
					errorMessege.setMessage("Подходящих элементов не найдено");
				else
					errorMessege.setMessage("Из списка удалено " + rezult + " элемента");
				MainContainer.tableConfig();
				errorMessege.open();
			}
		});
		shell.open();
	}
}

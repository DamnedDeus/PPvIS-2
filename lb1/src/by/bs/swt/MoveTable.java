package by.bs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class MoveTable extends GraficBuffer {
	MoveTable() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {

		// Buttons
		Button button1 = new Button(group, SWT.PUSH);
		button1.setText("Save");
		button1.pack();
		Button button2 = new Button(group, SWT.PUSH);
		button2.setText("<---");
		button2.pack();
		Button button3 = new Button(group, SWT.PUSH);
		button3.setText("--->");
		button3.pack();

		// table
		Table table = new Table(group, SWT.FULL_SELECTION | SWT.BORDER);
		table.setHeaderVisible(true);
		String[] titles = { "Col 1", "Col 2" };

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}
		for (int loopIndex = 0; loopIndex < 8; loopIndex++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, "");
			item.setText(1, "");
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++)
			table.getColumn(loopIndex).pack();

		// Text
		Text text = new Text(group, SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		text.setLayoutData(layoutData);
		text.setText("");
		text.setSize(40, 50);
		text.pack();

		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for (int i = 0; i < table.getItemCount(); i++) {
					if (table.getItem(i).getText().equals("")) {
						table.getItem(i).setText(text.getText());
						break;
					}
				}
			}

		});

		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (table.getItem(table.getSelectionIndex()).getText(1).equals("") == false
						|| table.getItem(table.getSelectionIndex()).getText(0).equals("") == false) {
					String str = table.getItem(table.getSelectionIndex()).getText(0)
							+ table.getItem(table.getSelectionIndex()).getText(1);
					table.getItem(table.getSelectionIndex()).setText(0, str);
					table.getItem(table.getSelectionIndex()).setText(1, "");
				}
			}

		});
		button3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (table.getItem(table.getSelectionIndex()).getText(1).equals("") == false
						|| table.getItem(table.getSelectionIndex()).getText(0).equals("") == false) {
					String str = table.getItem(table.getSelectionIndex()).getText(0)
							+ table.getItem(table.getSelectionIndex()).getText(1);
					table.getItem(table.getSelectionIndex()).setText(0, "");
					table.getItem(table.getSelectionIndex()).setText(1, str);
				}
			}

		});

	}

}

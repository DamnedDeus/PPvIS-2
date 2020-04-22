package by.bs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class Save extends GraficBuffer {
	Save() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {
		errorMessege.setMessage("Such an item already exists");

		Button button = new Button(group, SWT.PUSH);
		button.setText("Save");
		button.pack();

		// Text
		Text text = new Text(group, SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		text.setLayoutData(layoutData);
		text.setText("");
		text.setSize(20, 50);
		text.pack();

		// Combo
		Combo combo = new Combo(group, SWT.DROP_DOWN);
		String[] items = new String[] { "LB1", "LB2" };
		combo.setItems(items);
		combo.pack();

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int buf = 0;
				for (int i = 0; i < combo.getItemCount(); i++) {
					if (text.getText().equals(combo.getItem(i))) {
						errorMessege.open();
						buf++;
						break;
					}
				}
				if (buf == 0) {
					combo.setText(text.getText());
					combo.add(text.getText());
					combo.pack();
				}
			}

		});

	}

}
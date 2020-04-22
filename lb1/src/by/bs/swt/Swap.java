package by.bs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class Swap extends GraficBuffer {
	Swap() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {

		// Buttons
		Button button1 = new Button(group, SWT.PUSH);
		button1.setText("Save");
		button1.pack();

		Button button2 = new Button(group, SWT.PUSH);
		button2.setText("Swap");
		button2.pack();

		// Text
		Text text = new Text(group, SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		text.setLayoutData(layoutData);
		text.setText("");
		text.setSize(20, 50);
		text.pack();

		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				button2.setText(text.getText());
			}

		});

		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String buf = button1.getText();
				button1.setText(button2.getText());
				button2.setText(buf);
			}

		});

	}
}

package by.bs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class CheckCheck extends GraficBuffer {
	CheckCheck() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {

		errorMessege.setMessage("This item is already active");

		// Buttons
		Button button = new Button(group, SWT.PUSH);
		button.setText("Check");
		button.pack();

		Group GroupCheck = new Group(group, SWT.NONE);
		GroupCheck.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button button1 = new Button(GroupCheck, SWT.CHECK);
		button1.setText("R1");
		Button button2 = new Button(GroupCheck, SWT.CHECK);
		button2.setText("R2");
		Button button3 = new Button(GroupCheck, SWT.CHECK);
		button3.setText("R3");

		// Text
		Text text = new Text(group, SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		text.setLayoutData(layoutData);
		text.setText("");
		text.setSize(20, 50);
		text.pack();

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (text.getText().equals("R1")) {
					if (button1.getSelection() == true)
						button1.setSelection(false);
					else
						button1.setSelection(true);
				} else if (text.getText().equals("R2")) {
					if (button2.getSelection() == true)
						button2.setSelection(false);
					else
						button2.setSelection(true);
				} else if (text.getText().equals("R3")) {
					if (button3.getSelection() == true)
						button3.setSelection(false);
					else
						button3.setSelection(true);
				} else
					errorMessege.open();
			}

		});

	}
}

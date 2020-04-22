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

public class RadioCheck extends GraficBuffer {
	RadioCheck() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {
		errorMessege.setMessage("This item is already active");
		
		// Buttons
		Button button = new Button(group, SWT.PUSH);
		button.setText("Check");
		button.pack();

		Group GroupRadio = new Group(group, SWT.NONE);
		GroupRadio.setLayout(new RowLayout(SWT.HORIZONTAL));

		Button button1 = new Button(GroupRadio, SWT.RADIO);
		button1.setText("R1");
		Button button2 = new Button(GroupRadio, SWT.RADIO);
		button2.setText("R2");
		Button button3 = new Button(GroupRadio, SWT.RADIO);
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
					button1.setFocus();
				} else if (text.getText().equals("R2")) {
					button2.setFocus();
				} else if (text.getText().equals("R3")) {
					button3.setFocus();
				} else
					errorMessege.open();
			}

		});

	}
}

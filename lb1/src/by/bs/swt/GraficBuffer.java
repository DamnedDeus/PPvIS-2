package by.bs.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class GraficBuffer {
	public GraficBuffer() {
	}

	public void run(Shell shell) {
		MessageBox errorMessege = new MessageBox(shell);
		errorMessege.setText("Informatoins");
		
		Composite group = new Composite(shell, SWT.BORDER);
		group.setLayout(new RowLayout(SWT.HORIZONTAL));
		processing(group, errorMessege);
	}
	
	protected abstract void processing(Composite group, MessageBox errorMessege);
}

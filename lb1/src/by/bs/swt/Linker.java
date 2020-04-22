package by.bs.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Linker {
	public int CicleNumber;
	public boolean CicleCheck = false;
	private Display display;
	private Shell shell;
	private List<Shell> shells;

	Linker() {
	}

	public void run() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("SWT");
		shell.setSize(700, 700);

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;

		shell.setLayout(rowLayout);

		// Expansion of the interface
		List<GraficBuffer> linkers = new ArrayList<>();
		linkers.add(new Save());
		linkers.add(new Swap());
		linkers.add(new RadioCheck());
		linkers.add(new CheckCheck());
		linkers.add(new MoveTable());

		shells = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			shells.add(new Shell(display));
			shells.get(i).setLayout(rowLayout);
			shells.get(i).setText("SWT " + (i + 1));
			shells.get(i).setSize(400, 400);

			linkers.get(i).run(shells.get(i));
			linkers.get(i).run(shell);

			shells.get(i).open();
			shells.get(i).setVisible(false);
		}
		shell.open();

		// Expansion of a stream
		Thread engin = new Thread(processingStream(), "MyThread");
		engin.start();

		display.addFilter(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.CTRL) != 0 & event.keyCode == 114)
					CicleCheck = true;

				if ((event.stateMask & SWT.CTRL) != 0 & event.keyCode == 115)
					CicleCheck = false;
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
		display.dispose();
	}

	public Runnable processingStream() {
		Runnable r = () -> {
			while (true) {
				try {
					if (CicleCheck == true & CicleNumber == 5) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								shells.forEach(x -> x.setVisible(false));
								shell.setVisible(true);
								CicleNumber++;

							}
						});
					}
					Thread.sleep(1000);

					if (CicleCheck == true & CicleNumber == 6) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								shell.setVisible(false);
								CicleNumber = 0;
							}
						});
					}

					Thread.sleep(1000);

					if (CicleCheck == true & CicleNumber != 5) {
						for (; CicleNumber < 5;) {
							display.asyncExec(new Runnable() {
								@Override
								public void run() {
									if (CicleCheck == true) {
										shells.get(CicleNumber).setVisible(true);
										CicleNumber++;
									}
								}
							});
							Thread.sleep(1000);
						}
					}

				} catch (InterruptedException e) {
				}
			}
		};
		return r;
	}
}

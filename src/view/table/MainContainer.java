package view.table;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import model.Sportsman;
import model.DataHandler;
import util.factories.MenuBar;
import view.form.TablePanelManage;

public class MainContainer {
	private static Display display;
	private static Shell shell;
	private static Table table;
	
	public static Table getTable() {
		return table;
	}

	public void configContainer() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("SWT");
		shell.setSize(1200, 700);

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;

		shell.setLayout(rowLayout);
		
		MenuBar.getInstance(shell);
		TablePanelManage.processingPanel(shell);
		MainContainer.table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);	
		tableConfig(DataHandler.configPage(TablePanelManage.getPage(), TablePanelManage.getPageCount(), TablePanelManage.getPageSize()), table);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
		display.dispose();
	}

	public static void tableConfig(List<Sportsman> sportsmans, Table table) {
		if(MainContainer.getTable().equals(table))TablePanelManage.countProcessed();
		table.removeAll();

		String[] titles = { "������ ��� ����������", "������", "�������", "������", "��� ������", "������" };
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setAlignment(SWT.CENTER);
			column.setText(titles[loopIndex]);
		}

		if (sportsmans != null) {
			for (int loopIndex = 0; loopIndex < sportsmans.size(); loopIndex++) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(0, sportsmans.get(loopIndex).getFullName());
				item.setText(1, sportsmans.get(loopIndex).getLineup());
				item.setText(2, sportsmans.get(loopIndex).getPosition());
				item.setText(3, Integer.toString(sportsmans.get(loopIndex).getTitlesQuantity()));
				item.setText(4, sportsmans.get(loopIndex).getSport());
				item.setText(5, sportsmans.get(loopIndex).getCategory());
			}
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++)
			table.getColumn(loopIndex).pack();
		table.getShell().layout();
		TablePanelManage.informationProcessed();
	}

	public static Display getDisplay() {
		return display;
	}
}

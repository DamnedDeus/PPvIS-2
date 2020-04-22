package controller;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;

import model.LoadData;
import model.SaveData;
import view.form.AddForm;
import view.form.DeleteForm;
import view.form.SearchForm;

public class ApplicationContainerController {

	public static void listenerProcessin(List<MenuItem> listeners) {

		listeners.get(0).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				LoadData.loadSportsmans();
			}
		});

		listeners.get(1).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SaveData.saveSportsmans();
			}
		});

		listeners.get(2).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				AddForm.addSportsman();
			}
		});

		listeners.get(3).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				DeleteForm.deleteSportsman();
			}
		});

		listeners.get(4).addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				SearchForm.searchSportsman();
			}
		});
	}
}

package model;

import java.util.ArrayList;
import java.util.List;

import util.xml.SportsmanGenerator;
import view.form.TablePanelManage;
import view.table.MainContainer;

public class DataHandler {
	private static List<Sportsman> sportsmans;
	private static Integer sportsmansCount = 50;
	static {
		sportsmans = SportsmanGenerator.generateRecords(sportsmansCount);
	}

	public static List<Sportsman> getSpartsman() {
		return sportsmans;
	}

	public static void setSpartsmanList(List<Sportsman> sportsmansList) {
		sportsmans = sportsmansList;
		MainContainer.tableConfig();
	}

	public static List<Sportsman> configPage() {
		Integer page = TablePanelManage.getPage();
		Integer pageCount = TablePanelManage.getPageCount();
		Integer pageSize = TablePanelManage.getPageSize();
		sportsmans = DataHandler.getSpartsman();
		if (sportsmans.isEmpty() == true)
			return null;

		List<List<Sportsman>> sportsmansPage = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			sportsmansPage.add(new ArrayList<Sportsman>());
			for (int j = 0; j < pageSize; j++) {
				if (i * pageSize + j < sportsmans.size())
					sportsmansPage.get(i).add(sportsmans.get(i * pageSize + j));
			}
		}

		return sportsmansPage.get(page);
	}

	public static Integer getSportsmansCount() {
		return DataHandler.sportsmansCount;
	}

	public static Integer deleteSportsmanProcessing(Integer mod, String parameter1, String parameter2) {
		// List<Integer> indexList = new ArrayList<>();
		Integer deleteCount = 0;
		if (mod == 0) {
			for (int i = sportsmans.size() - 1; i >= 0; i--) {
				if (sportsmans.get(i).getFullName().equals(parameter1)
						| sportsmans.get(i).getSport().equals(parameter2)) {
					sportsmans.remove(i);
					deleteCount++;
				}
			}
		} else if (mod == 1) {
			for (int i = sportsmans.size() - 1; i >= 0; i--) {
				try {
					if (Integer.valueOf(parameter1) >= sportsmans.get(i).getTitlesQuantity()
							& sportsmans.get(i).getTitlesQuantity() >= Integer.valueOf(parameter2)) {
						sportsmans.remove(i);
						deleteCount++;
					}
				} catch (NumberFormatException e) {
					System.err.println("Неверный формат строки!");
				}
			}
		} else if (mod == 2) {
			for (int i = sportsmans.size() - 1; i >= 0; i--) {
				if (sportsmans.get(i).getFullName().equals(parameter1)
						| sportsmans.get(i).getCategory().equals(parameter2)) {
					sportsmans.remove(i);
					deleteCount++;
				}
			}
		}
		/*
		 * for (int i = sportsmans.size() - 1; i >= 0; i--) { for (int j = 0; j >
		 * indexList.size(); j++) { if (indexList.get(j) == i) { sportsmans.remove(i); }
		 * } }
		 */
		sportsmansCount = sportsmans.size();
		return deleteCount;
	}

	public static List<Sportsman> searchSportsmanProcessing(Integer mod, String parameter1, String parameter2) {
		List<Sportsman> chosenSportsmans = new ArrayList<>();
		if (mod == 0) {
			for (int i = 0; i < sportsmans.size(); i++) {
				if (sportsmans.get(i).getFullName().equals(parameter1)
						| sportsmans.get(i).getSport().equals(parameter2)) {
					chosenSportsmans.add(sportsmans.get(i));
				}
			}
		} else if (mod == 1) {
			for (int i = 0; i < sportsmans.size(); i++) {
				try {
					if (Integer.valueOf(parameter1) >= sportsmans.get(i).getTitlesQuantity()
							& sportsmans.get(i).getTitlesQuantity() >= Integer.valueOf(parameter2)) {
						chosenSportsmans.add(sportsmans.get(i));
					}
				} catch (NumberFormatException e) {
					System.err.println("Неверный формат строки!");
				}
			}
		} else if (mod == 2) {
			for (int i = 0; i < sportsmans.size(); i++) {
				if (sportsmans.get(i).getFullName().equals(parameter1)
						| sportsmans.get(i).getCategory().equals(parameter2)) {
					chosenSportsmans.add(sportsmans.get(i));
				}
			}
		}
		return chosenSportsmans;
	}

	public static Boolean addSportsmanProcessing(Sportsman man) {
		Boolean check = true;
		for (int i = 0; i < sportsmans.size(); i++) {
			if (sportsmans.get(i).getFullName().equals(man.getFullName()))
				check = false;
		}
		if (check == true)
			sportsmans.add(man);
		return check;
	}
}

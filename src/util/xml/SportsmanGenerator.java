package util.xml;

import model.Sportsman;
import model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SportsmanGenerator {
	public static final Integer ZERO = 0;

	public static List<String> names;
	public static List<String> surnames;
	public static List<String> middleNames;
	public static List<String> lineups;
	public static List<String> positions;
	public static List<Integer> titlesQuantitys;
	public static List<String> sports;
	public static List<String> categorys;

	static {
		names = new ArrayList<String>(){{
		add("Оксана");
		add("Жанна");
		add("Нона");
		add("Эдуард");
		add("Измаил");
		add("Дина");
		add("Ольга");
		add("Илья");
		add("Фридрих");
		add("Евдоким");
		add("Давид");
		add("Татьяна");
		add("Модест");
		add("Ника");
		add("Герман");
		add("Никон");
		add("Анастасия");
		add("Всеволод");
		add("Владимир");
		add("Игнис");
		add("Вух");
		add("Уй");
		add("Сергей");
		add("Ким");
		add("Роберт");
		add("Матильда");
		add("Изабелла");
		add("Модест");
	}};
	}

	static {
		surnames = new ArrayList<String>(){{
		add("Науменко");
		add("Болокан(-а)");
		add("Чадов(-а)");
		add("Мишин(-а)");
		add("Гринин(-а)");
		add("Кузинков(-а)");
		add("Николаев(-а)");
		add("Богоносцев(-а)");
		add("Соломин(-а)");
		add("Палюлин(-а)");
		}};
	}

	static {
		middleNames = new ArrayList<String>(){{
		add("Игнатиевич(-на)");
		add("Феоктистович(-на)");
		add("Ипатович(-на)");
		add("Валерьянович(-на)");
		add("Андрианович(-на)");
		add("Валерьянович(-на)");
		add("Моисеевич(-на)");
		add("Фролович(-на)");
		add("Ипполитович(-на)");
		add("Валерьянович(-на)");
		}};
	}

	static {
		lineups = new ArrayList<String>(){{
		add("Основной");
		add("Запасной");
		add("n/a");
		}};
	}

	static {
		positions = new ArrayList<String>(){{
			add("Форвард");
			add("Защитник");
		}};
	}
	static {
		sports = new ArrayList<String>(){{
			add("Футбол");
			add("Хокей");
			add("Пляж. Футбол");
			add("Баскетбол");
			add("Волейбол");
		}};
	}
	
	static {
		titlesQuantitys = new ArrayList<Integer>(){{
		for (int i = 0; i < 80; i++) {
			add(i);
		}
		}};
	}

	static {
		categorys = new ArrayList<String>(){{
		add("1-й юношеский");
		add("2-й разряд");
		add("3-й разряд");
		add("КМС");
		add("мастер спорта");
		}};
	}

	public static <T> T getRandomData(List<T> elements) {
		return elements.get(ThreadLocalRandom.current().nextInt(0, elements.size()));
	}

	public static Person generatePerson() {
		return new Person(getRandomData(surnames), getRandomData(names), getRandomData(middleNames));
	}

	public static Sportsman generateSportsman() {
		return new Sportsman(generatePerson(), getRandomData(lineups), getRandomData(positions),
				getRandomData(titlesQuantitys),getRandomData(sports) ,getRandomData(categorys));
	}

	public static List<Sportsman> generateRecords(int amount) {
		List<Sportsman> sportsmans = new ArrayList<>();
		while (amount != 0) {
			amount--;
			sportsmans.add(generateSportsman());
		}
		return sportsmans;
	}
}

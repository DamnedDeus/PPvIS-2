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
		add("������");
		add("�����");
		add("����");
		add("������");
		add("������");
		add("����");
		add("�����");
		add("����");
		add("�������");
		add("�������");
		add("�����");
		add("�������");
		add("������");
		add("����");
		add("������");
		add("�����");
		add("���������");
		add("��������");
		add("��������");
		add("�����");
		add("���");
		add("��");
		add("������");
		add("���");
		add("������");
		add("��������");
		add("��������");
		add("������");
	}};
	}

	static {
		surnames = new ArrayList<String>(){{
		add("��������");
		add("�������(-�)");
		add("�����(-�)");
		add("�����(-�)");
		add("������(-�)");
		add("��������(-�)");
		add("��������(-�)");
		add("����������(-�)");
		add("�������(-�)");
		add("�������(-�)");
		}};
	}

	static {
		middleNames = new ArrayList<String>(){{
		add("����������(-��)");
		add("������������(-��)");
		add("��������(-��)");
		add("������������(-��)");
		add("�����������(-��)");
		add("������������(-��)");
		add("���������(-��)");
		add("��������(-��)");
		add("�����������(-��)");
		add("������������(-��)");
		}};
	}

	static {
		lineups = new ArrayList<String>(){{
		add("��������");
		add("��������");
		add("n/a");
		}};
	}

	static {
		positions = new ArrayList<String>(){{
			add("�������");
			add("��������");
		}};
	}
	static {
		sports = new ArrayList<String>(){{
			add("������");
			add("�����");
			add("����. ������");
			add("���������");
			add("��������");
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
		add("1-� ���������");
		add("2-� ������");
		add("3-� ������");
		add("���");
		add("������ ������");
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

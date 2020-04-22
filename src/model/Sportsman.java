package model;

public class Sportsman extends Person {
	private String lineup;
	private String position;
	private Integer titlesQuantity;
	private String sport;
	private String category;

	public Sportsman() {
	}
	public Sportsman(Person person, String lineup, String position, Integer titlesQuantity,String sport ,String category) {
		super(person);
		this.lineup = lineup;
		this.position = position;
		this.titlesQuantity = titlesQuantity;
		this.sport = sport;
		this.category = category;
	}

	public String getLineup() {
		return this.lineup;
	}

	public String getPosition() {
		return this.position;
	}

	public Integer getTitlesQuantity() {
		return this.titlesQuantity;
	}
	public String getSport() {
		return this.sport;
	}
	
	public String getCategory() {
		return this.category;
	}
}

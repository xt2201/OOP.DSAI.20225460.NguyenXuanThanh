package hust.soict.dsai.aims.media;

public abstract class Disc extends Media implements Playable{
	// Disc is abstract class since we don't need to create any Disc instances
	
	private String director;
	private int length;
	
	public Disc() {
		// TODO Auto-generated constructor stub
	}
	
	public Disc(String title, String category, float cost, String director, int length) {
		super(title, category, cost);
		this.director = director;
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public int getLength() {
		return length;
	}

}

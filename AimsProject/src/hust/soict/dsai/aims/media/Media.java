package hust.soict.dsai.aims.media;
import java.util.Comparator;

public abstract class Media {
	
	private int id;
	private String title;
	private String category;
	private float cost;
	private static int counter = 0; // a counter variable which is auto-incremented for id 

	public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
	public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
	
	public Media() {
		// TODO Auto-generated constructor stub
	}
	
	public Media(String title, String category, float cost) {
		super();
		counter += 1;
		this.id = counter;
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
            return false;
        if(!(obj instanceof Media))
            return false;
        return (((Media) obj).title == this.title); 
	}
	
	public boolean isMatch(int id) {
		return (this.id == id);
	}
	
	public boolean isMatch(String title) {
		return this.getTitle().equals(title);
	}
}

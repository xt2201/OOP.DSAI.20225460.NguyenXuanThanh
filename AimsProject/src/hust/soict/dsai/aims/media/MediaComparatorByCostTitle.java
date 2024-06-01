package hust.soict.dsai.aims.media;
import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
	public MediaComparatorByCostTitle() {
		// TODO Auto-generated constructor stub
	}

	public int compare(Media m1, Media m2) {
        // Comparing media
        int TitleCompare = m1.getTitle().compareTo(m2.getTitle());
        int CostCompare = Float.compare(m1.getCost(), m2.getCost());
 
        // 2nd level comparison
        return (CostCompare == 0) ? TitleCompare
                                  : -CostCompare;
    }
}

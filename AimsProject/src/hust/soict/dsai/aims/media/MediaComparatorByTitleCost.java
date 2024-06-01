package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {

	public MediaComparatorByTitleCost() {
		// TODO Auto-generated constructor stub
	}
	
	public int compare(Media m1, Media m2) {
        // Comparing media
        int TitleCompare = m1.getTitle().compareTo(m2.getTitle());
        int CostCompare = Float.compare(m1.getCost(), m2.getCost());
 
        // 2nd level comparison
        return (TitleCompare == 0) ? -CostCompare 
                                  : TitleCompare;
    }
}

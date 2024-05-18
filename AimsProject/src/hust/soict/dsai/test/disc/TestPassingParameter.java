package hust.soict.dsai.test.disc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestPassingParameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
		DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
		
		swap(jungleDVD, cinderellaDVD);
		System.out.println("jungle dvd title: " + jungleDVD.getTitle());
		System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());
		
		changeTitle(jungleDVD, cinderellaDVD.getTitle());
		System.out.println("jungle dvd title: " + jungleDVD.getTitle());
	}
	
	//Implement the swap method that can change 2 object correctly
	public static void swap(Object o1, Object o2) {
		//check if two object is instance of DigitalVideoDisc, 
		if ((o1 instanceof DigitalVideoDisc) && (o2 instanceof DigitalVideoDisc)){
			//cast Object type to DigitalVideoDisc
			String tmp = ((DigitalVideoDisc) o1).getTitle();
			changeTitle(((DigitalVideoDisc) o1), ((DigitalVideoDisc) o2).getTitle());
			changeTitle(((DigitalVideoDisc) o2), tmp);
		}//if not then do not execute 
	}
	
	public static void changeTitle(DigitalVideoDisc dvd, String title) {
		String oldTitle = dvd.getTitle();
		dvd.setTitle(title);
		dvd = new DigitalVideoDisc(oldTitle);
	}
}

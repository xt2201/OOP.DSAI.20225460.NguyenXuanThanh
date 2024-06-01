package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable{
	
	private String title;
	private int length;
	
	public Track() {
		// TODO Auto-generated constructor stub
	}
	
	public Track(String title, int length) {
		super();
		this.title = title;
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public int getLength() {
		return length;
	}
	
	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("Playing track: " + this.getTitle());
			System.out.println("Track length: " + this.getLength()); 
		} else {
			System.err.println("ERROR at track");
			throw new PlayerException("ERROR: Track length is non-positive!");
		}	
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
            return false;
        if(!(obj instanceof Track))
            return false;
        return (((Track) obj).title == this.title)&&(((Track) obj).length == this.length); 
	}
}

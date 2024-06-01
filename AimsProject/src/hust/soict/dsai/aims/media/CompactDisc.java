package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc {
	// DVD also implements Playable but through Disc
	
	private String artist;
	private List<Track> tracks = new ArrayList<>();
	
	public CompactDisc() {
		// TODO Auto-generated constructor stub
	}
	
	public CompactDisc(String title, String category, float cost, String director, String artist) {
		super(title, category, cost, director, 0);
		this.artist = artist;
		// length is temporarily be 0
	}
	
	public boolean addTrack(Track track) {
		if (tracks.contains(track)) //since using contains, the .equals() method of Track also included
			return false;
		tracks.add(track);
		return true;
	}

	public boolean removeTrack(Track track) {
		if (tracks.contains(track)) {
			tracks.remove(track);
			return true;
		}
		return false;
	}
	
	public int getLength() {
		int s = 0;
		for (Track t: tracks)
			s+=t.getLength();
		return s;
	}
	
	public String getArtist() {
		return artist;
	}

	public void play() throws PlayerException {
		if (this.getLength() > 0) {
			System.out.println("CD: "+ this.getTitle()+" genre: "+this.getCategory()+" by: "+this.getDirector());
			java.util.Iterator iter = tracks.iterator();
			Track nextTrack;
			while (iter.hasNext()) {
				nextTrack = (Track) iter.next();
				try {
					nextTrack.play();
				} catch (PlayerException e) {
					throw e;
				}	 
			}
			
		} else {
			throw new PlayerException("ERROR: CD length is non-positive!");
		}
	}
	
	@Override 
	public String toString() {
	//to String method for printing out in Ex6 lab03
		return "CD - "+this.getId()+" - "+this.getTitle()+" - "+this.getCategory()+" - "+this.getDirector()+" - "+this.getArtist()+" - "+this.getLength()+": "+this.getCost()+" $";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
            return false;
        if(!(obj instanceof CompactDisc))
            return false;
        return (((Media) obj).getTitle() == this.getTitle()); 
	}
	
}

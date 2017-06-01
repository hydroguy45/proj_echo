package Map;

import java.util.ArrayList;
public class Room implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<Pickups> interactables = new ArrayList<Pickups>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	String background;
	
	public Room(){
		System.out.println("Instantiating a room...");
	}
	void addPickup(Pickups p){
		this.interactables.add(p);
	}
	
	void removePickup(Pickups p){
		this.interactables.remove(p);
	}
	
	void addPlatform(Platform p){
		this.platforms.add(p);
	}
	
	void removePlatform(Platform p){
		this.platforms.remove(p);
	}
	
	void draw(){
		//TODO: interate through the arrays and draw everything... 
		//also draw background and pass context as an arg for everything (change all the draw definitions)
	}
}
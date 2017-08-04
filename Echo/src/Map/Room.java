package Map;

import java.util.ArrayList;
public class Room implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<AudioLog> interactables = new ArrayList<AudioLog>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	public String background = null;
	public Room(){
		System.out.println("Instantiating a room...");
	}
}

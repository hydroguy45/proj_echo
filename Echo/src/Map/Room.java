package Map;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Room implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<AudioLog> interactables = new ArrayList<AudioLog>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	public static BufferedImage background = null;
	public Room(){
		System.out.println("Instantiating a room...");
	}
}

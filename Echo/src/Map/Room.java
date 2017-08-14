package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Room implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<AudioLog> interactables = new ArrayList<AudioLog>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	public String background = null;
	boolean canGoNorth = false;
	boolean canGoSouth = false;
	boolean canGoEast = false;
	boolean canGoWest = false;
	int width;
	int height;
	public Room(int w, int h){
		System.out.println("Instantiating a room...");
		width = w;
		height = h;
	}
	void draw(Graphics g, int scale){
	//TODO: factor in scale
		if(this.background == null){
			g.setColor(Color.white);
			g.clearRect(0, 0, width, height);
		} else {
			File backgroundFile = new File(this.background);
			BufferedImage background;
			try {
				background = ImageIO.read(backgroundFile);
				g.drawImage(background, 0, 0, width, height, 0, 0, width, height, null);	
			} catch (IOException e) {
				System.out.println("Did you move the location of the background image :(");
			}
		}
		for(Pickups p: this.interactables){
			p.draw(g);
		}
		for(Platform p: this.platforms){
			p.draw(g);
		}
	}
}

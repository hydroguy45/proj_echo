package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

public class Platform implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int width;
	int height;
	String name;
	String pictureFile;
	BufferedImage img = null;
	Set<mapBuilder.View> visibleRange = new HashSet<mapBuilder.View>();
	
	public Platform(int x, int y, String PictureFileName, String name){
		this.x = x;
		this.y = y;
		this.width = 75; 
		this.height = 25; 
		this.name = name;
		this.pictureFile = PictureFileName;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void draw(Graphics g) {
		if(visibleRange.contains(mapBuilder.currentVision)){
			BufferedImage img;
			try {
				img = ImageIO.read(new File(this.pictureFile));
				width = img.getWidth();
				height = img.getHeight();
			} catch (IOException e) {
				System.out.println("File not loaded");
				img = null;
			}
			if(img == null){
				g.setColor(Color.blue);
				g.fillRect(x, y, width, height);
				g.setColor(Color.white);
			} else {
				g.drawImage(img, x, y, x+img.getWidth(), y+img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);				
			}
		} else {
			g.setColor(Color.blue);
			g.drawRect(x, y, width, height);
			g.setColor(Color.white);
		}
	}

}

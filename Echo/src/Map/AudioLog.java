package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

public class AudioLog implements Pickups {
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int width;
	int height;
	String name;
	boolean onGround;
	String audioFile;
	String pictureFile;
	BufferedImage img = null;
	Set<mapBuilder.View> visibleRange = new HashSet<mapBuilder.View>();
	
	public AudioLog(int x, int y, String AudioFileName, String PictureFileName, String name){
		this.x = x;
		this.y = y;
		this.width = 25; 
		this.height = 25;
		this.audioFile = AudioFileName;
		this.pictureFile = PictureFileName;
		this.name = name;
	}
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void onPickup() {
		this.onGround = false;
		//TODO: play audio file...
	}
	
	@Override
	public void draw(Graphics g, int scale) {
		if(visibleRange.contains(mapBuilder.currentVision)){
			BufferedImage img;
			try {
				img = ImageIO.read(new File(this.pictureFile));
				width = img.getWidth();
				height = img.getHeight();
			} catch (IOException e) {
				System.out.println("Audiolog texture file not loaded");
				img = null;
			}
			if(img == null){
				g.setColor(Color.green);
				g.fillRect(x/scale, y/scale, width/scale, height/scale);
				g.setColor(Color.white);
			} else {
				g.drawImage(img, x/scale, y/scale, (x+img.getWidth())/scale, (y+img.getHeight())/scale, 0, 0, img.getWidth(), img.getHeight(), null);				
			}
		} else {
			g.setColor(Color.green);
			g.drawRect(x/scale, y/scale, width/scale, height/scale);
			g.setColor(Color.white);
		}
		
	}

	@Override
	public boolean onGround() {
		return onGround;
	}

}

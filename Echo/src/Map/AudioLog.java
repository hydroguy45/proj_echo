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
	
	Set<mapBuilder.View> visibleRange = new HashSet<mapBuilder.View>();
	File audio;
	private BufferedImage img;
	
	public AudioLog(int x, int y, String AudioFileName, String PictureFileName, String name){
		this.x = x;
		this.y = y;
		this.width = 25; 
		this.height = 25;
		this.audioFile = AudioFileName;
		this.pictureFile = PictureFileName;
		this.name = name;
		this.audio = new File(AudioFileName);
		try {
			this.img = ImageIO.read( new File(PictureFileName));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			this.img = null;
		}
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
	
	public void updatePicture(File f){
		try {
			this.img = ImageIO.read(f);
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("File not loaded");
			this.img = null;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(visibleRange.contains(mapBuilder.currentVision)){
			if(img == null){
				g.setColor(Color.green);
				g.fillRect(x, y, width, height);
				g.setColor(Color.white);
			} else {
				g.drawImage(img, x, y, x+img.getWidth(), y+img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);				
			}
		} else {
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
			g.setColor(Color.white);
		}
		
	}

	@Override
	public boolean onGround() {
		return onGround;
	}

}

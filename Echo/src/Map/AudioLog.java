package Map;

public class AudioLog implements Pickups {
	int x;
	int y;
	boolean onGround;
	String audioFile;
	String pictureFile;
	
	public AudioLog(int x, int y, String AudioFileName, String PictureFileName){
		this.x = x;
		this.y = y;
		this.audioFile = AudioFileName;
		this.pictureFile = PictureFileName;
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
	public void draw() {
		// TODO: add a draw method
		
	}

	@Override
	public boolean onGround() {
		return onGround;
	}

}

package Map;

public class Platform implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	String name;
	String pictureFile;
	
	public Platform(int x, int y, String PictureFileName, String name){
		this.x = x;
		this.y = y;
		this.name = name;
		this.pictureFile = PictureFileName;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw() {
		// TODO: add a draw method
	}

}
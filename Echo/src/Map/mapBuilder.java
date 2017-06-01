package Map;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapBuilder {
	//Adding or changing a vision type here is sufficient, the rest of the app will update accordingly 
	enum View {
		Xray,
		UltraViolet,
		Radio,
		VisibleSpectrum
	}
	public static View currentVision = View.VisibleSpectrum;
	public static Room level;
	public static final int width = 600;
	public static final int height = 400;
	public static void main(String[] args) {
		//Instantiate abstractions
		level = new Room();
		//Load GUI
		//TODO: make a menu selector or button for doing room to room arrangements instead of just withing room arrangements
		JFrame frame = new JFrame("Level Editor");
		JPanel render = new Render();//Rendering
		JPanel elements = new Elements();//Elements
		JPanel visions = new Visions(); //Visions
		frame.setLayout(new BorderLayout());
		frame.add(visions, BorderLayout.WEST);
		frame.add(render, BorderLayout.CENTER);
		frame.add(elements, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width + 300, height);
		frame.setVisible(true);
	}

}

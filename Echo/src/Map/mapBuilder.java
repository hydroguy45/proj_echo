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
	public static final int width = 920;//CHANGE WIDTH AND HEIGHT HERE
	public static final int height = 760;
	public static JFrame frame;
	public static JPanel render;//Rendering
	public static JPanel elements;//Elements
	public static JPanel visions; //Visions
	public static void main(String[] args) {
		//Instantiate abstractions
		level = new Room();
		//Load GUI
		//TODO: make a menu selector or button for doing room to room arrangements instead of just within room arrangements
		frame = new JFrame("Level Editor");
		render = new Render();//Rendering
		elements = new Elements();//Elements
		visions = new Visions(); //Visions
		frame.setLayout(new BorderLayout());
		frame.add(visions, BorderLayout.WEST);
		frame.add(render, BorderLayout.CENTER);
		frame.add(elements, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width + 586, height + 49);//This is an approximation of the space that the other menus take up
		frame.setVisible(true);
	}

}

package Map;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapBuilder {
	//TODO: when you swap from room to map layout you need to change the width and height to reflect the new room scaling
	//Adding or changing a vision type here is sufficient, the rest of the app will update accordingly 
	enum View {
		Xray,
		UltraViolet,
		Radio,
		VisibleSpectrum //Shouldn't everything be in the visible spectrum... Plot convenience? Maybe one big reveal at the end?
	}
	public static View currentVision = View.VisibleSpectrum;
	public static Room level;
	public static Map map;
	public static final int width = 920;//CHANGE WIDTH AND HEIGHT HERE
	public static final int height = 760;
	public static JFrame frame;
	public static JPanel render;//Rendering
	public static JPanel elements;//Elements
	public static JPanel visions; //Visions
	public static void main(String[] args) {
		//roomEdit();
		mapEdit();
	}
	static void mapEdit(){
		map = new Map();
		frame = new JFrame("Map Editor");
		visions = new Visions(); //This allow us to see the effect of different spectrums on the render
		render = new mapRender();//Rendering
		elements = new mapElements();//Elements
		frame.setLayout(new BorderLayout());
		frame.add(visions, BorderLayout.WEST);
		frame.add(render, BorderLayout.CENTER);
		frame.add(elements, BorderLayout.EAST);
		frame.setSize(width + 586, height + 49); //May need to rework this approximation when the elements tab changes
		frame.setVisible(true);
	}
	static void roomEdit(){
		//Instantiate abstractions
		level = new Room(width, height);
		//Load GUI
		//TODO: make a menu selector or button for doing room to room arrangements instead of just within room arrangements
		frame = new JFrame("Room Editor");
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

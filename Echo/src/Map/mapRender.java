package Map;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class mapRender extends JPanel {
	//TODO: draw and scale relatively
	//--maybe add a scale variable to every draw method's arg slot... then zoom and pan could work too
	public static int xOffset;
	public static int yOffset;
	public static int scale;
	public static int selectedX;
	public static int selectedY;
	public mapRender(){
		setSize(mapBuilder.width, mapBuilder.height);
		xOffset = 0;
		yOffset = 0;
		scale = 1;
		selectedX = 0;
		selectedY = 0;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Should take in an x, y, and scale
		mapBuilder.map.draw(g, xOffset, yOffset, scale);
	}
}

package Map;

import java.awt.Graphics;

import javax.swing.JPanel;
/*Overall TODO:
 * 8) getting saving working for pictures and audio
 * 6) After all of that you can get started on a map-wide customizer
 * 7) Consider another panel with the actual render (no semi-transparent platforms and with a shader)
 * */
@SuppressWarnings("serial")
public class Render extends JPanel {
	public Render(){
		setSize(mapBuilder.width, mapBuilder.height);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		mapBuilder.level.draw(g);
	}
}

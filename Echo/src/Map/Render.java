package Map;

import java.awt.Color;
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
		int width = mapBuilder.width;
		int height = mapBuilder.height;
		Room rm = mapBuilder.level;
		if(mapBuilder.level.background == null){
			g.setColor(Color.white);
			g.clearRect(0, 0, width, height);
		} else {
			g.drawImage(mapBuilder.level.background, 0, 0, width, height, 0, 0, width, height, null);	
		}
		for(Pickups p: rm.interactables){
			p.draw(g);
		}
		for(Platform p: rm.platforms){
			p.draw(g);
		}
	}
}

package Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Render extends JPanel {
	public Render(){
		JLabel tempText = new JLabel("Testing...", JLabel.CENTER);
		add(tempText);
		setSize(400, 600);
	}
}

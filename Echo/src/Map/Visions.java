package Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Visions extends JPanel {
	public Visions(){
		setBorder(BorderFactory.createTitledBorder("Vision Types"));
		JLabel tempText = new JLabel("<html>Add Button's for <br> every render view</html>", JLabel.CENTER);
		add(tempText);
	}
}

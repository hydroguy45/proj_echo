package Map;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Elements extends JPanel {
	public Elements(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Map Elements"));
		JLabel tempText = new JLabel("<html>Add Frame about Platforms<br> and another frame about pickups</html>", JLabel.CENTER);
		JButton export = new JButton("Export Level");
		
		//TODO: add click behavoir... should just save the serialized room object to a specific file
		
		add(tempText, BorderLayout.CENTER);
		add(export, BorderLayout.SOUTH);
	}
}

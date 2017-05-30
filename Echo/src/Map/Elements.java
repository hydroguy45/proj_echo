package Map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Elements extends JPanel {
	public Elements(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Map Elements"));
		JButton add = new JButton("Add");
		JLabel tempText = new JLabel("<html>Add Frame about Platforms<br> and another frame about pickups</html>", JLabel.CENTER);
		JButton export = new JButton("Export Level");
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Write addition functions
				//TODO
				//TODO
			}
		});
		
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					FileOutputStream out = new FileOutputStream("test.ser");
					ObjectOutputStream outObj = new ObjectOutputStream(out);
					outObj.writeObject(mapBuilder.level);
					outObj.close();
					out.close();
					System.out.println("Saved to test.ser...");
				} catch (IOException i){
					
				}
			}
		});
		//TODO: add click behavoir... should just save the serialized room object to a specific file
		add(add, BorderLayout.NORTH);
		add(tempText, BorderLayout.CENTER);
		add(export, BorderLayout.SOUTH);
	}
}

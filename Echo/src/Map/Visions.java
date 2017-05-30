package Map;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Map.mapBuilder.View;

@SuppressWarnings("serial")
public class Visions extends JPanel {
	public Visions(){
		setLayout(new GridLayout(View.values().length, 1));
		setBorder(BorderFactory.createTitledBorder("Vision Types"));
		for(View v: View.values()){
			JButton x = new JButton(v.toString());
			add(x);
			x.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mapBuilder.currentVision = v;
					System.out.println("Changing vision type to "+v.toString());
				}
			});
		}
	}
}

package Map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

@SuppressWarnings("serial")
public class mapElements extends JPanel {
	//TODO:
	//-Import
	//-Export
	//-Add room (x,y)
	//-Delete Room (x,y)
	//-Panning and potentially zooming
	public mapElements(){
		//Panning controls
		JButton panUp = new BasicArrowButton(BasicArrowButton.NORTH);
		panUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.yOffset--;
			}
		});
		JButton panDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		panDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.yOffset++;
			}
		});
		JButton panEast = new BasicArrowButton(BasicArrowButton.EAST);
		panEast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.xOffset++;
			}
		});
		JButton panWest = new BasicArrowButton(BasicArrowButton.WEST);
		panWest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.xOffset--;
			}
		});
		JPanel panControls = new JPanel();
		panControls.setLayout(new BorderLayout());
		panControls.add(panUp, BorderLayout.NORTH);
		panControls.add(panDown, BorderLayout.SOUTH);
		panControls.add(panEast, BorderLayout.EAST);
		panControls.add(panWest, BorderLayout.WEST);
		//Add and Delete Rooms
			//TODO: get a x and y text input that highlights a room
			//TODO: file chooser add
			//TODO: delete whatever room is highlighted
		//Import and Export Data
		add(panControls);
	}
}

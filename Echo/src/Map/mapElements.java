package Map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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
		JLabel xLabel = new JLabel("X:");
		add(xLabel);
		JSpinner x = new JSpinner();
		add(x);
		JLabel yLabel = new JLabel("Y:");
		add(yLabel);
		JSpinner y = new JSpinner();
		add(y);
		JFileChooser addFile = new JFileChooser();
		addFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					FileInputStream f = new FileInputStream(addFile.getSelectedFile().getAbsolutePath());
					ObjectInputStream in = new ObjectInputStream(f);
					Room room = (Room) in.readObject();
					if(mapBuilder.map.RoomLayout.size() > (Integer) x.getValue()){
						//If the x coordinate has other values
						ArrayList<Room> xColumn = mapBuilder.map.RoomLayout.get((Integer) x.getValue());
						xColumn.set((Integer) y.getValue(), room);
					} else {
						//If this is a first room for that x coord
						ArrayList<Room> list = new ArrayList<Room>();
						list.set((Integer) y.getValue(), room);
						mapBuilder.map.RoomLayout.set((Integer) x.getValue(), list);
					}
					in.close();
					f.close();
				} catch (IOException | ClassNotFoundException e1) {
					System.out.println("Failed import");
				}
				//TODO: configure this to actually add stuff
				//mapBuilder.level.background = f.getAbsolutePath();
				mapBuilder.render.repaint();
			}
		});
		
		JButton addChooser = new JButton("Add");
		addChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFile.showOpenDialog(null);
			}
		});
		add(addChooser);
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.map.RoomLayout.get((Integer) x.getValue()).remove((Integer) y.getValue());
			}
		});
		//Import and Export Data
		add(panControls);
	}
}

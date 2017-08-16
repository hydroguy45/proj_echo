package Map;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
				if(mapRender.yOffset != 0){
					mapRender.yOffset--;
				}
				mapBuilder.render.repaint();
			}
		});
		JButton panDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		panDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.yOffset++;
				mapBuilder.render.repaint();
			}
		});
		JButton panEast = new BasicArrowButton(BasicArrowButton.EAST);
		panEast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapRender.xOffset++;
				mapBuilder.render.repaint();
			}
		});
		JButton panWest = new BasicArrowButton(BasicArrowButton.WEST);
		panWest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mapRender.xOffset != 0){
					mapRender.xOffset--;
				}
				mapBuilder.render.repaint();
			}
		});
		JPanel panControls = new JPanel();
		panControls.setLayout(new BorderLayout());
		panControls.add(panUp, BorderLayout.NORTH);
		panControls.add(panDown, BorderLayout.SOUTH);
		panControls.add(panEast, BorderLayout.EAST);
		panControls.add(panWest, BorderLayout.WEST);
		
		//Add and Delete Rooms
		JPanel beginning = new JPanel();
		beginning.setLayout(new GridLayout(3,2));
		JLabel xLabel = new JLabel("X:");
		beginning.add(xLabel);
		JSpinner x = new JSpinner();
		x.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				mapRender.selectedX = (Integer) x.getValue();
				mapBuilder.render.repaint();
			}
		});
		beginning.add(x);
		JLabel yLabel = new JLabel("Y:");
		beginning.add(yLabel);
		JSpinner y = new JSpinner();
		y.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				mapRender.selectedY = (Integer) y.getValue();
				mapBuilder.render.repaint();
			}
		});
		beginning.add(y);
		JFileChooser addFile = new JFileChooser("../MapData/Room");
		addFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					FileInputStream f = new FileInputStream(addFile.getSelectedFile().getAbsolutePath());
					ObjectInputStream in = new ObjectInputStream(f);
					Room room = (Room) in.readObject();
					if(mapBuilder.map.RoomLayout.size() > (Integer) x.getValue()){
						if(mapBuilder.map.RoomLayout.get((Integer) x.getValue()) == null){
							//The map hasn't instantiated this x coord's y column
							ArrayList<Room> xColumn = mapBuilder.map.RoomLayout.get((Integer) x.getValue());
							xColumn = new ArrayList<Room>();
							xColumn.add((Integer) y.getValue(), room);
						} else {
							//If the x coordinate has other values
							ArrayList<Room> xColumn = mapBuilder.map.RoomLayout.get((Integer) x.getValue());
							try{
								//Another room for that y coord exists
								xColumn.set((Integer) y.getValue(), room);
							} catch (java.lang.IndexOutOfBoundsException e1){
								//This is the first room for that y coord
								xColumn.add((Integer) y.getValue(), room);
							}
						}
					} else {
						//If this is a first room for that x coord 
						ArrayList<Room> list = new ArrayList<Room>((Integer) y.getValue()+1);
						for(int i=0; i<(Integer) y.getValue(); i++){
							list.add(null);
						}
						list.add((Integer) y.getValue(), room);
						mapBuilder.map.RoomLayout.add((Integer) x.getValue(), list);
					}
					in.close();
					f.close();
				} catch (IOException | ClassNotFoundException e1) {
					System.out.println("Failed import");
				}
				//TODO: configure this to actually add stuff
				//mapBuilder.level.background = f.getAbsolutePath();
				mapBuilder.map.printRoomStruct();
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
		beginning.add(addChooser);
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.map.RoomLayout.get((Integer) x.getValue()).remove((Integer) y.getValue());
			}
		});
		beginning.add(remove);
		setLayout(new BorderLayout());
		add(beginning, BorderLayout.NORTH);
		//Import and Export Data
		//Scaling
		JLabel scaleLabel = new JLabel("Scale:");
		JSpinner scale = new JSpinner();
		scale.setValue(1);
		scale.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				mapRender.scale = (Integer) scale.getValue();
				mapBuilder.render.repaint();
			}
		});
		JPanel middle = new JPanel();
		middle.setBorder(BorderFactory.createTitledBorder("Moving Around"));
		middle.add(scaleLabel);
		middle.add(scale);
		middle.add(panControls);
		add(middle, BorderLayout.CENTER);
		JPanel end = new JPanel();
		JButton swap = new JButton("Room Editor");
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.frame.setVisible(false);
				mapBuilder.frame.disable();
				mapBuilder.roomEdit();
			}
		});
		end.add(swap);
		JButton importer = new JButton("Import Map");
		
		JFileChooser importFile = new JFileChooser("../MapData/Map");
		importFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//De-serialize, and setequal to mapBuilder.level
				FileInputStream f;
				try {
					f = new FileInputStream(importFile.getSelectedFile().getAbsolutePath());
					ObjectInputStream in = new ObjectInputStream(f);
					Map map = (Map) in.readObject();
					in.close();
					f.close();
					mapBuilder.map = map;
					mapBuilder.render.repaint();
				} catch (IOException | ClassNotFoundException e1) {
					System.out.println("Failed import");
				}
			}
		});
		importer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile.showOpenDialog(null);
				
			}
		});
		end.add(importer);
		JButton export = new JButton("Export Level");
		
		JFileChooser exportFile = new JFileChooser("../MapData/Map");
		exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String f = exportFile.getSelectedFile().getAbsolutePath();
				try {
					FileOutputStream out = new FileOutputStream(f);
					ObjectOutputStream outObj;
					outObj = new ObjectOutputStream(out);
					outObj.writeObject(mapBuilder.map);
					outObj.close();
					out.close();
					System.out.println("Saved");
				} catch (IOException e1) {
					System.out.println("Saving failed: "+ e1);
				}
				
			}
		});
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportFile.showOpenDialog(null);
			}
		});
		end.add(export);
		end.setLayout(new GridLayout(3,1));
		add(end, BorderLayout.SOUTH);
	}
}

package Map;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class Elements extends JPanel {
	public static Platform currentPlatform;
	public static AudioLog currentAudio;
	public Elements(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Map Elements"));
	
	//TOP
		JButton addPlatform = new JButton("Add Platform");
		JButton addAudio = new JButton("Add Audio Log");
		JPanel add = new JPanel();
	
	//MID
		String[] platforms = new String[mapBuilder.level.platforms.size()];
		int i = 0;
		for(Platform p: mapBuilder.level.platforms){
			platforms[i] = p.name;
			i++;
		}
		JComboBox<String> platformList = new JComboBox<String>(platforms);
		addPlatform.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.level.platforms.add(new Platform(0, 0, "null.jpg", "New Platform"));
				System.out.println("Added new platform");
				platformList.removeAllItems();
				for(Platform p: mapBuilder.level.platforms){
					platformList.addItem(p.name);
				}
				platformList.setSelectedIndex(mapBuilder.level.platforms.size() - 1);
			}
		});
		JLabel platformXLabel = new JLabel("Null");
		JSlider platformXPosition = new JSlider(0, mapBuilder.width);
		platformXPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(currentPlatform != null){
					currentPlatform.x = platformXPosition.getValue();
					platformXLabel.setText("X: " + Integer.toString(currentPlatform.x));
				}
			}
		});
		JLabel platformYLabel = new JLabel("Null");
		JSlider platformYPosition = new JSlider(0, mapBuilder.width);
		platformYPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(currentPlatform != null){
					currentPlatform.y = platformYPosition.getValue();
					platformYLabel.setText("Y: " + Integer.toString(currentPlatform.y));
				}
			}
		});
		JTextField platformName = new JTextField();
		platformName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = platformList.getSelectedIndex();
				currentPlatform.name = platformName.getText();
				platformList.removeAllItems();
				for(Platform p: mapBuilder.level.platforms){
					platformList.addItem(p.name);
				}
				platformList.setSelectedIndex(i);;
			}
		});
		JLabel currentPic = new JLabel("null");
		
		platformList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(platformList.getSelectedIndex() != -1){
					currentPlatform = mapBuilder.level.platforms.get(platformList.getSelectedIndex());
					platformXLabel.setText("X: " + Integer.toString(currentPlatform.x));
					platformXPosition.setValue(currentPlatform.x);
					platformYLabel.setText("Y: " + Integer.toString(currentPlatform.y));
					platformYPosition.setValue(currentPlatform.y);
					platformName.setText(currentPlatform.name);
					currentPic.setText(currentPlatform.pictureFile);
				}
			}
		});
		JFileChooser pic = new JFileChooser();
		pic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlatform.pictureFile = pic.getSelectedFile().getName();
				currentPic.setText(currentPlatform.pictureFile);
			}
		});
		
		JButton picButton = new JButton("Choose Texture File");
		picButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pic.showOpenDialog(null);
			}
		});
		JPanel platformEditor = new JPanel();
		platformEditor.setBorder(BorderFactory.createTitledBorder("Platform Editor"));
		platformEditor.setLayout(new GridLayout(20, 1));
		platformEditor.add(platformList);
		platformEditor.add(platformXLabel);
		platformEditor.add(platformXPosition);
		platformEditor.add(platformYLabel);
		platformEditor.add(platformYPosition);
		platformEditor.add(platformName);
		platformEditor.add(currentPic);
		platformEditor.add(picButton);
		
		
		
		//TODO: make an audio log editor too...
		
		
		JPanel editor = new JPanel();
		editor.add(platformEditor);
	//BOTTOM
		JButton export = new JButton("Export Level");
		
		addPlatform.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Write addition functions
				
			}
		});
		addAudio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Write addition functions
				
			}
		});
		
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					//TODO: Have a prompt for where to save maybe... depends if you integrate the map app into this app
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
		//Naming schemes got out of control
		add.add(addPlatform);
		add.add(addAudio);
		add(add, BorderLayout.NORTH);
		add(editor, BorderLayout.CENTER);
		add(export, BorderLayout.SOUTH);
	}
}

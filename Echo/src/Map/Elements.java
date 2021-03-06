package Map;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Map.mapBuilder.View;

@SuppressWarnings("serial")
public class Elements extends JPanel {
	public static Platform currentPlatform;
	public static AudioLog currentAudioLog;
	public Elements(){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Map Elements"));
	
	//TOP
		//backgroundChooser
		JFileChooser backgroundFile = new JFileChooser("../MapData/Textures");
		backgroundFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = backgroundFile.getSelectedFile();
				mapBuilder.level.background = f.getAbsolutePath();
				mapBuilder.render.repaint();
			}
		});
		
		JButton backgroundChooser = new JButton("Choose Background Image");
		backgroundChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundFile.showOpenDialog(null);
			}
		});
		
		JButton addPlatform = new JButton("Add Platform");
		JButton addAudio = new JButton("Add Audio Log");
		JPanel add = new JPanel();
	
	//MID
			//PLATFORM
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
				mapBuilder.render.repaint();
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
				mapBuilder.render.repaint();
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
				mapBuilder.render.repaint();
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
		
		ArrayList<JCheckBox> views = new ArrayList<JCheckBox>();
		for(View v: mapBuilder.View.values()){
			JCheckBox newbie = new JCheckBox(v.name());
			newbie.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(newbie.isSelected()){
						currentPlatform.visibleRange.add(mapBuilder.View.valueOf(newbie.getText()));
					} else {
						currentPlatform.visibleRange.remove(mapBuilder.View.valueOf(newbie.getText()));
					}
					mapBuilder.render.repaint();
				}
			});
			views.add(newbie);
		}
		
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
					for(JCheckBox c: views){
						mapBuilder.View v = mapBuilder.View.valueOf(c.getText());
						c.setSelected(currentPlatform.visibleRange.contains(v));
					}
				}
			}
		});
		JFileChooser pic = new JFileChooser("../MapData/Textures");
		pic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlatform.pictureFile = pic.getSelectedFile().getAbsolutePath();
				currentPic.setText(currentPlatform.pictureFile);
				mapBuilder.render.repaint();
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
		for(JCheckBox c: views){
			platformEditor.add(c);
		}
		
		
	
			//AUDIO
		String[] audioLogs = new String[mapBuilder.level.interactables.size()];
		i = 0;
		for(AudioLog p: mapBuilder.level.interactables){
			audioLogs[i] = p.name;
			i++;
		}
		JComboBox<String> audioLogList = new JComboBox<String>(audioLogs);
		addAudio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.level.interactables.add(new AudioLog(0, 0, "null.mp3", "null.jpg","New AudioLog"));
				System.out.println("Added new audio log");
				audioLogList.removeAllItems();
				for(AudioLog p: mapBuilder.level.interactables){
					audioLogList.addItem(p.name);
				}
				audioLogList.setSelectedIndex(mapBuilder.level.interactables.size() - 1);
				mapBuilder.render.repaint();
			}
		});
		JLabel audioLogXLabel = new JLabel("Null");
		JSlider audioLogXPosition = new JSlider(0, mapBuilder.width);
		audioLogXPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(currentAudioLog != null){
					currentAudioLog.x = audioLogXPosition.getValue();
					audioLogXLabel.setText("X: " + Integer.toString(currentAudioLog.x));
				}
				mapBuilder.render.repaint();
			}
		});
		JLabel audioLogYLabel = new JLabel("Null");
		JSlider audioLogYPosition = new JSlider(0, mapBuilder.width);
		audioLogYPosition.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(currentAudioLog != null){
					currentAudioLog.y = audioLogYPosition.getValue();
					audioLogYLabel.setText("Y: " + Integer.toString(currentAudioLog.y));
				}
				mapBuilder.render.repaint();
			}
		});
		JTextField audioLogName = new JTextField();
		audioLogName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = audioLogList.getSelectedIndex();
				currentAudioLog.name = audioLogName.getText();
				audioLogList.removeAllItems();
				for(AudioLog p: mapBuilder.level.interactables){
					audioLogList.addItem(p.name);
				}
				audioLogList.setSelectedIndex(i);;
			}
		});
		JLabel currentLogPic = new JLabel("null");
		JLabel currentLogFile = new JLabel("null");
		ArrayList<JCheckBox> audioViews = new ArrayList<JCheckBox>();
		for(View v: mapBuilder.View.values()){
			JCheckBox newbie = new JCheckBox(v.name());
			newbie.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(newbie.isSelected()){
						currentAudioLog.visibleRange.add(mapBuilder.View.valueOf(newbie.getText()));
					} else {
						currentAudioLog.visibleRange.remove(mapBuilder.View.valueOf(newbie.getText()));
					}
					mapBuilder.render.repaint();
				}
			});
			audioViews.add(newbie);
		}
		
		audioLogList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(audioLogList.getSelectedIndex() != -1){
					currentAudioLog = mapBuilder.level.interactables.get(audioLogList.getSelectedIndex());
					audioLogXLabel.setText("X: " + Integer.toString(currentAudioLog.x));
					audioLogXPosition.setValue(currentAudioLog.x);
					audioLogYLabel.setText("Y: " + Integer.toString(currentAudioLog.y));
					audioLogYPosition.setValue(currentAudioLog.y);
					audioLogName.setText(currentAudioLog.name);
					currentLogPic.setText(currentAudioLog.pictureFile);
					currentLogFile.setText(currentAudioLog.audioFile);
					for(JCheckBox c: audioViews){
						mapBuilder.View v = mapBuilder.View.valueOf(c.getText());
						c.setSelected(currentAudioLog.visibleRange.contains(v));
					}
				}
			}
		});
		
		JFileChooser audioFile = new JFileChooser("../MapData/Audio");
		audioFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAudioLog.audioFile = audioFile.getSelectedFile().getAbsolutePath();
				currentLogFile.setText(currentAudioLog.audioFile);
				mapBuilder.render.repaint();
			}
		});
		
		JButton audioFileButton = new JButton("Choose Audio File");
		audioFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				audioFile.showOpenDialog(null);
			}
		});
		
		JFileChooser audioPic = new JFileChooser("../MapData/Textures");
		audioPic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentAudioLog.pictureFile = audioPic.getSelectedFile().getAbsolutePath();
				currentLogPic.setText(currentAudioLog.pictureFile);
				mapBuilder.render.repaint();
			}
		});
		
		JButton audioPicButton = new JButton("Choose Texture File");
		audioPicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				audioPic.showOpenDialog(null);
			}
		});
		
		JPanel audioEditor = new JPanel();
		audioEditor.setBorder(BorderFactory.createTitledBorder("Audio Editor"));
		audioEditor.setLayout(new GridLayout(20, 1));
		audioEditor.add(audioLogList);
		audioEditor.add(audioLogXLabel);
		audioEditor.add(audioLogXPosition);
		audioEditor.add(audioLogYLabel);
		audioEditor.add(audioLogYPosition);
		audioEditor.add(audioLogName);
		audioEditor.add(currentLogPic);
		audioEditor.add(audioPicButton);
		audioEditor.add(currentLogFile);
		audioEditor.add(audioFileButton);
		for(JCheckBox c: audioViews){
			audioEditor.add(c);
		}
		
			//EDITOR PANEL
		JPanel editor = new JPanel();
		editor.setLayout(new BorderLayout());
		editor.add(platformEditor, BorderLayout.WEST);
		editor.add(audioEditor, BorderLayout.EAST);
	//BOTTOM
		JLabel error = new JLabel("");
		JButton importer = new JButton("Import Level");
		
		JFileChooser importFile = new JFileChooser("../MapData/Room");
		importFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//De-serialize, and setequal to mapBuilder.level
				FileInputStream f;
				try {
					f = new FileInputStream(importFile.getSelectedFile().getAbsolutePath());
					ObjectInputStream in = new ObjectInputStream(f);
					Room level = (Room) in.readObject();
					in.close();
					f.close();
					mapBuilder.level = level;
					platformList.removeAllItems();
					for(Platform p: mapBuilder.level.platforms){
						platformList.addItem(p.name);
					}
					audioLogList.removeAllItems();
					for(AudioLog p: mapBuilder.level.interactables){
						audioLogList.addItem(p.name);
					}
					mapBuilder.render.repaint();
					error.setText("");
				} catch (IOException | ClassNotFoundException e1) {
					System.out.println("Failed import");
					error.setText("Failed import");
				}
			}
		});
		
		importer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				importFile.showOpenDialog(null);
				
			}
		});
		
		JButton export = new JButton("Export Level");
		
		JFileChooser exportFile = new JFileChooser("../MapData/Room");
		exportFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String f = exportFile.getSelectedFile().getAbsolutePath();
				try {
					FileOutputStream out = new FileOutputStream(f);
					ObjectOutputStream outObj;
					outObj = new ObjectOutputStream(out);
					outObj.writeObject(mapBuilder.level);
					outObj.close();
					out.close();
					System.out.println("Saved");
					error.setText("Saved");
				} catch (IOException e1) {
					System.out.println("Saving failed: "+ e1);
					error.setText("Saving failed");
				}
				
			}
		});
		export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportFile.showOpenDialog(null);
			}
		});
		JPanel end = new JPanel();
		end.add(importer);
		end.add(export);
		end.add(error);
		JButton swap = new JButton("Map Editor");
		swap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mapBuilder.frame.setVisible(false);
				mapBuilder.frame.disable();
				mapBuilder.mapEdit();
			}
		});
		end.add(swap);
		//Naming schemes got out of control
		add.setLayout(new BorderLayout());
		add.add(backgroundChooser, BorderLayout.NORTH);
		add.add(addPlatform, BorderLayout.WEST);
		add.add(addAudio, BorderLayout.EAST);
		add(add, BorderLayout.NORTH);
		add(editor, BorderLayout.CENTER);
		add(end, BorderLayout.SOUTH);
	}
}

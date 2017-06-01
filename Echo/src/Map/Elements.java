package Map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Elements extends JPanel {
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
		JPanel platformEditor = new JPanel();
		//TODO: add editing options for all of the fields with an auto update
		platformEditor.add(platformList);
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

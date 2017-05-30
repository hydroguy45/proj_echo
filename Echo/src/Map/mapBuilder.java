package Map;

import java.awt.BorderLayout;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mapBuilder {

	public static void main(String[] args) {
		//Instantiate abstractions
		Room level = new Room();
		System.out.println(level.toString());
		try{
			FileOutputStream out = new FileOutputStream("test.ser");
			ObjectOutputStream outObj = new ObjectOutputStream(out);
			outObj.writeObject(level);
			outObj.close();
			out.close();
			System.out.println("Saved to test.ser...");
		} catch (IOException i){
			
		}
		
		//Load GUI
		JFrame frame = new JFrame("Loading");
		JPanel render = new Render();//Rendering
		JPanel elements = new Elements();//Elements
		JPanel visions = new Visions(); //Visions
		frame.setLayout(new BorderLayout());
		frame.add(visions, BorderLayout.WEST);
		frame.add(render, BorderLayout.CENTER);
		frame.add(elements, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}

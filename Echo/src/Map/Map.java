package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
	ArrayList<ArrayList<Room>> RoomLayout; //Goes row, column like a matrix
	public Map() {
		RoomLayout = new ArrayList<ArrayList<Room>>();
		RoomLayout.add(new ArrayList<Room>());
		System.out.println("X size:"+RoomLayout.size());
		System.out.println("Y size:"+RoomLayout.get(0).size());
	}
	void printRoomStruct(){
		if(RoomLayout.size()>0 && RoomLayout.get(0).size()>0){
			for(int x=0;x<RoomLayout.size(); x++){
				for(int y=0;y<RoomLayout.get(x).size(); y++){
					System.out.print("X ");
				}
				System.out.print("\n\n");
			}
		}
	}
	/** Used to draw the map to a certain context
	 * @param scale The amount of rooms that can fit on the height or width
	 * @param g The graphics region for display
	 * @param x The x room offset for the upper lefthand corner
	 * @param y The y room offset for the upper lefthand corner
	 * @param highlightedX The x coord of the highlighted room
	 * @param highlightedY The y coord of the highlighted room
	 * */
	void draw(Graphics g, int x, int y, int highlightedX, int highlightedY, int scale){
		//Write this to use the draw function of every room
		// 1 unit scale --> 1 room width by 1 room height
		// 2 unit scale --> 4 rooms on the screen
		// 3 unit scale --> 9 rooms on the screen
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, mapBuilder.width, mapBuilder.height);
		//Draw individual rooms
		for(int xCoord = x; xCoord < x + scale; xCoord++){
			for(int yCoord = y; yCoord < y + scale; yCoord++){
				try{
					if(RoomLayout.size()>xCoord && RoomLayout.get(xCoord).size()>yCoord){
						Room room = RoomLayout.get(xCoord).get(yCoord);
						Graphics roomG = g.create(((xCoord-x)*mapBuilder.width)/scale, ((yCoord-y)*mapBuilder.height)/scale, mapBuilder.width/scale, mapBuilder.height/scale);
						room.draw(roomG, scale);
					}
				} catch (java.lang.NullPointerException | java.lang.IndexOutOfBoundsException e){
					//Do nothing
				}
			}
		}
		//Draw grid lines
		g.setColor(new Color(193,210,255));
		for(int xCoord = 0; xCoord<=scale; xCoord++){
			g.drawLine((xCoord*mapBuilder.width)/scale, mapBuilder.height, (xCoord*mapBuilder.width)/scale, 0);
		}
		for(int yCoord = 0; yCoord<=scale; yCoord++){
			g.drawLine(0, (yCoord*mapBuilder.height)/scale, mapBuilder.width, (yCoord*mapBuilder.height)/scale);
		}
		//Draw selected portion
		g.setColor(Color.BLACK);
		g.drawRect(((highlightedX-x)*mapBuilder.width)/scale, ((highlightedY-y)*mapBuilder.height)/scale, mapBuilder.width/scale, mapBuilder.height/scale);
	}
}

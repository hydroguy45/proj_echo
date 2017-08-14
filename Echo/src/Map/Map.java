package Map;

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
		g.clearRect(0, 0, mapBuilder.width, mapBuilder.height);
		for(int xCoord = x; xCoord < x + scale; xCoord++){
			for(int yCoord = y; yCoord < y + scale; yCoord++){
				if(RoomLayout.size()>xCoord && RoomLayout.get(xCoord).size()>yCoord){
					Room room = RoomLayout.get(xCoord).get(yCoord);
					Graphics roomG = g.create((xCoord-x)/scale*mapBuilder.width, (yCoord-y)/scale*mapBuilder.height, mapBuilder.width/scale, mapBuilder.height/scale);
					room.draw(roomG, scale);
					if(xCoord == highlightedX && yCoord == highlightedY){
						g.drawRect((xCoord-x)/scale*mapBuilder.width, (yCoord-y)/scale*mapBuilder.height, mapBuilder.width/scale, mapBuilder.height/scale);
					}
				}
			}
		}

	}
}

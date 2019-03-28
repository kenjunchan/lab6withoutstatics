package lab4;
//Anna Bortle
public class Model {
	
	static int xloc = 0;
	static int yloc = 0;
	int xIncr = 8;
	int yIncr = 2;
	int frameWidth;
	int frameHeight;
	int imgWidth;
	int imgHeight;
	static Direction d;
	
	//constructor
	public Model(int width, int height, int imageWidth, int imageHeight) {
		frameWidth = width;
		frameHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
		d = Direction.SOUTHEAST;	//starting direction
	}

	public void updateLocationAndDirection() {
		
		//test for collisions
		if (xloc + imgWidth > frameWidth) { //hitting right boundary
			xIncr = -xIncr;
			switch (d){		
			case EAST:
				d = Direction.WEST;
				break;
			case NORTHEAST:
				d = Direction.NORTHWEST;
				break;
			case SOUTHEAST:
				d = Direction.SOUTHWEST;
				break;
			default:
				break;
			}
		}
		
		if (xloc < 0) {	//hitting left boundary
			xIncr = -xIncr;
			switch (d) {
			case WEST:
				d = Direction.EAST;
				break;
			case NORTHWEST:
				d = Direction.NORTHEAST;
				break;
			case SOUTHWEST:
				d = Direction.SOUTHEAST;
				break;
			default:
				break;
			}
		}
		
		if (yloc < 0 ) {	//hitting top boundary
			yIncr = -yIncr;
			switch (d) {
			case NORTH:
				d = Direction.SOUTH;
				break;
			case NORTHEAST:
				d = Direction.SOUTHEAST;
				break;
			case NORTHWEST:
				d = Direction.SOUTHWEST;
				break;
			default:
				break;
			}
		}
		
		if (yloc + imgHeight > frameHeight) {		//hitting bottom boundary
			yIncr = -yIncr;
			switch (d) {
			case SOUTH:
				d = Direction.NORTH;
				break;
			case SOUTHEAST:
				d = Direction.NORTHEAST;
				break;
			case SOUTHWEST:
				d = Direction.NORTHWEST;
				break;
			default:
				break;
			}
		}
		
		//update locations
		xloc = xloc + xIncr;
		yloc = yloc + yIncr;
	}

	public static int getX() {
		return xloc;
	}
	public static int getY() {
		return yloc;
	}

	public static Direction getDirect() {
		return d;
	}
}

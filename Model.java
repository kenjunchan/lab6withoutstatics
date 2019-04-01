package lab4;
//TEAM 11-12
//B.Azueta, A.Bortle, H.Bridge, K.Chan, C.Lemus

public class Model {
	
	int xloc = 0;
	int yloc = 0;
	int xIncr = 4;
	int yIncr = 1;
	int frameWidth;
	int frameHeight;
	int imgWidth;
	int imgHeight;
	public Direction d;
	public boolean movementFlag; //moving = true, idle = false

	
	//constructor
	public Model(int width, int height, int imageWidth, int imageHeight) {
		frameWidth = width;
		frameHeight = height;
		imgWidth = imageWidth;
		imgHeight = imageHeight;
		d = Direction.SOUTHEAST;	//starting direction
		movementFlag = true;
	}

	public void updateLocationAndDirection() {
		
		//test for collisions, change direction if needed
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

	public int getX() {
		if (this.movementFlag) {
			//only update location/direction if orc is moving
			updateLocationAndDirection(); 
		}
		return this.xloc;
	}
	public int getY() {
		if (this.movementFlag) {
			updateLocationAndDirection();
		}
		
		return this.yloc;
		
	}
	public Model getState() {
		return this;
	}
	public Direction getDirect() {
		return d;
	}
	public boolean getMovementFlag() {
		return this.movementFlag;
	}
	public boolean switchMovementFlag() {
		this.movementFlag = !this.movementFlag;
		return this.movementFlag;
	}
}

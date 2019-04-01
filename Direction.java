package lab4;
//TEAM 11-12
//B.Azueta, A.Bortle, H.Bridge, K.Chan, C.Lemus

public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest");
	
	private String name = null;
	
	private Direction(String s){
		name = s;
	}
	public String getName() {
		return name;
	}


}

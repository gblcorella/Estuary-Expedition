package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

public enum Direction {
	
	LEFT("left"),
	RIGHT("right"),
	UP("up"),
	DOWN("down");

	private String name;
	
	/** Private Constructor used to set the name of the Direction
	 * 
	 * @param s, the String being used to set the name
	 */
	private Direction(String s){
		name = s;
	}

	/**
	 * Public String getter method that returns the name. It takes no params 
	 * 
	 * @return name The direction's String name
	 */
	public String getName() {
		return name;
	}
	
}
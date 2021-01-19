package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class BlueCrab extends Animal{
	
	public Double length;
	public boolean isCaught;
	public String name = "Blue Crab";
	public Direction flow;
	public int x;
	public int y;
	
	/**
	 * Public stub constructor for BlueCrab
	 */
	public BlueCrab() {}
	
	/**
	 * Public Constructor for Blue Crab, the Constructor takes in an int x, int b and direction 
	 * The constructor sets all the params to its values along having a boolean value of false when asked if 
	 * it is already on the boat. The length of the crab is also generated as a Random Range between 5 and 7 inches
	 * 
	 * @param a The X value given in this case 
	 * @param b The Y value given in this case 
	 * @param d Direction of the way in which the blue crab is going 
	 * 
	 */
	public BlueCrab(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
		this.name = "Blue Crab";
		this.onBoat = false;
      
      length =randRng(5.00, 7.00);

	}

	/**
	 * Simple getter method to acquire the int X location that takes in no 
	 * parameters
	 * 
	 * @return x, the BlueCrab's int x location
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Simple getter method to acquire the int Y location that takes in no 
	 * parameters
	 * 
	 * @return y, the BlueCrab's int y location
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter for the x int value in BlueCrab
	 * 
	 * @param a Sets the BlueCrab's x int value
	 */
	public void setX(int a) {
		this.x = a;
	}
	/**
	 * Setter for the y int value in BlueCrab
	 * 
	 * @param b Sets the BlueCrab's y int value
	 */
	public void setY(int b) {
		this.y = b;
	}
	
	/**
	* Public Direction method to get a BlueCrab's flow
	* <p>
	* @return flow, the Direction in which the specific object is flowing
	*/
	public Direction getFlow() {
		return flow;
	}
	
	/**
	 * Sets the flow and the Direction d
	 * 
	 * @param d Direction in which the object is flowing
	 */
	public void setFlow(Direction d) {
		this.flow = d;
	}
	
	/**
	 * Public string method to acquire the name of the specific Entities
	 * <p>
	 * @return name, The String name of the BlueCrab
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Public double method to get the length of the specific Entities
	 * 
	 * @return length, The double length of the BlueCrab
	 */
	public double getLength() {
		return length;
	}
}
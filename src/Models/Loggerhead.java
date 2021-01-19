package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Loggerhead extends Animal{

	public Double length;
	public boolean isCaught;
	public String name = "Loggerhead";
	public int x;
	public int y;
	public Direction flow;


	
	/** 
	 * Simple Constructor for the Loggerhead that takes in x,y and the direction and 
	 * also makes the facts to be a length with a rand rang of 28,34. It also makes
	 * the onBoat boolean false and gives the Loggerhead a name.
	 * 
	 * @param a Loggerhead's location 
	 * @param b Loggerhead's y location
	 * @param d Direction of the flow
	 */
	public Loggerhead(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
		this.name = "Loggerhead";
		this.onBoat = false;
		this.length = randRng(28.00, 34.00);
	}
  
	/** Public Constructor used to create a Loggerhead with a random length between
	 * 28 and 34.
	 */
	public Loggerhead() {
		this.length = randRng(28.00, 34.00);
	}

	
	/**
	 * Public getter that returns the x cord
	 * 
	 * @return x Getter for the x int value in Loggerhead
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Public getter that returns the y cord
	 * 
	 * @return x Getter for the y int value in Loggerhead
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter for the x int value in Loggerhead
	 * 
	 * @param a, Sets Loggerhead's x value 
	 */
	public void setX(int a) {
		this.x = a;
	}
	
	/**
	 * Setter for the y int value in Loggerhead
	 * 
	 * @param b, Sets Loggerhead's y value 
	 */
	public void setY(int b) {
		this.y = b;
	}
	
	/**
	* Public Direction method to get a Loggerhead's flow
	* 
	* @return flow, The Direction in which the specific object is flowing
	*/
	public Direction getFlow() {
		return flow;
	}
	
	/**
	 * Sets the flow and the Direction d
	 * 
	 * @param d Direction in which the object is flowing.
	 * 
	 */
	public void setFlow(Direction d) {
		this.flow = d;
	}


	/**
	 * Public string method to acquire the name
	 * 
	 * @return name, The name of the Loggerhead
	 */

	public String getName() {
		return name;
	}
	/**
	 * Public double method to get the length of the Specific Entities
	 * 
	 * @return length, The double length of the Loggerhead
	 */
	public double getLength() {
		return length;
	}
}
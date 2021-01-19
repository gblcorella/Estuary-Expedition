package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Weakfish extends Animal{

	public Double length;
	public boolean isCaught;
	public Direction flow;
	public int x;
	public int y;
	public String name = "Weakfish";
	
	/** 
	 * Simple Constructor or the Weakfish takes in x,y and the direction and 
	 * also makes the facts to be a length with a rand rang of 12,32
	 * 
	 * @param a Weak Fish's location 
	 * @param b Weak Fish's y location
	 * @param d Direction of the way the fush is facing 
	 */

	public Weakfish(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
		this.name = "Weakfish";
		this.onBoat = false;
		length =randRng(12,32);
	}
	

	/** Public Constructor used to create a random length for the Weakfish.
	 */
	public Weakfish() {
		length =randRng(12,32);
	}
      
	/**
	 * Public getter that returns the x
	 * 
	 * @return x Getter for the x int value in Weakfish 
	 */

	public int getX() {
		return x;
	}
	
	/**
	 * Public getter that returns the y
	 * 
	 * @return x Getter for the y int value in Weakfish 
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter for the x int value in Weakfish
	 * 
	 * @param a Also sets Weakfish's x value 
	 */

	public void setX(int a) {
		this.x = a;
	}
	
	/**
	 * Setter for the y int value in Weakfish
	 * @param b Also sets Weakfish's y value to b
	 */
	public void setY(int b) {
		this.y = b;
	}
	
	/**
	* Public Direction method to get a Weakfish's flow
	* 
	* @return flow, The Direction in which the specific object is flowing
	*/

	public Direction getFlow() {
		return flow;
	}
	
	/**
	 * Sets the flow and the Direction d
	 * 
	 * @param d Dircetion in which the object is flowing.
	 */

	public void setFlow(Direction d) {
		this.flow = d;
	}

	/**
	 * Public string method to acquire the name
	 * 
	 * @return name, The name of the Weakfish
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Public double method to get the length of the Specific Entities
	 * 
	 * @return length, The double length of the WeakFish
	 */

	public double getLength() {
		return length;
	}
	
	
}
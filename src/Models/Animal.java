package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Animal extends Entities{
	
	public int x;
	public int y;
	public double weight;
	public double length;
	public boolean onBoat = false;
	public boolean isCaught;
	public Direction flow;
	
	/** Public Stub Constructor for Animal
	 */
	public Animal() {}
	
	/**
	 * Public Constructor for Animal that
	 * takes in int x, int y and Direction d

	 * @param x Sets the Animals X location
	 * @param y Sets the Animals Y location 
	 * @param d Direction of the fish for the fishing game 
	 */
	public Animal(int x, int y, Direction d) {
		this.x = x;
		this.y = y;
		this.flow = d;
		this.onBoat = false;
	}

	/**
	 * Simple getter method to acquire the Y location that takes in no 
	 * parameters

	 * @return y, The Animal's y int location
	 */
	public int getY() {
		return y;
	}
	/**
	 * Simple getter method to acquire the X location that takes in no 
	 * parameters

	 * @return x, The Animal's x int location
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Simple setter method to set the X int location that takes in no 
	 * parameters

	 * @param a, an integer that the x location will be set to
	 */
	public void setX(int a) {
		this.x = a;
	}
	
	/**
	 * Simple getter method of Direction to determine the direction of the flow. 
	 * It does not take in any param but returns the flow.
	 * 
	 * @return flow The flow of the water 
	 */
	public Direction getFlow() {
		return flow;
	}
	
	/**
	 * Simple setter method to set the direction.
	 * <p>
	 * @param d, Sets The Animal's flow to Direction d. 
	 */
	public void setFlow(Direction d) {
		this.flow = d;
	}

	/**
	 * Simple setter method to set the Y int location that takes in no 
	 * parameters
	 * 
	 * @param b, an integer that the y location will be set to 
	 */

	public void setY(int b) {
		this.y = b;
	}
	
}

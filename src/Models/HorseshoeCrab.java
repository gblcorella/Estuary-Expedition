package Models;


/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */


@SuppressWarnings("serial")
public class HorseshoeCrab extends Animal{

	public double length;
	public boolean isCaught;
	public int x;
	public int y;
	public Direction flow;
	public String name = "Horseshoe Crab";


	/** Public Constructor used to create a HorseshoeCrab with a name, isCaught boolean, 
	 * onBoat boolean, and random length. 
	 */
  
	public HorseshoeCrab() {
		name = "Horseshoe Crab";
		isCaught = false;
		onBoat = false;
		length = randRng(18.00,26.00);
	}


	
	/** 
	 * Simple Constructor or the HorseshoeCrab that takes in x,y and the direction and 
	 * also makes the facts to be a length with a rand rang of 18,26. It also makes
	 * the onBoat boolean false and gives a HorseshoeCrab a name.
	 * 
	 * @param a Weak Fish's location 
	 * @param b Weak Fish's y location
	 * @param d Direction of the flow 
	 */
	
	public HorseshoeCrab(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
		this.name = "Horseshoe Crab";
		this.onBoat = false;
		this.length =randRng(18.00,26.00);
	}

	/**
	 * Public getter that returns the x cord
	 * 
	 * @return x Getter for the x int value in HorseshoeCrab
	 */
  
	public int getX() {
		return x;
	}
	
	/**
	 * Public getter that returns the y cord
	 * 
	 * @return y Getter for the y int value in HorseshoeCrab
	 */
  
	public int getY() {
		return y;
	}


	/**
	 * Setter for the x int value in HorseshoeCrab
	 * 
	 * @param a, Sets HorseshoeCrab's x value 
	 */
  
	public void setX(int a) {
		this.x = a;
	}


	/**
	 * Setter for the y int value in HorseshoeCrab
	 * 
	 * @param b, Sets HorseshoeCrab's y value 
	 */
	public void setY(int b) {
		this.y = b;
	}

	
	/**
	* Public Direction method to get a HorseshoeCrab's flow
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
	 * @return name, The name of the HorseshoeCrab
	 */

	public String getName() {
		return name;
	}
  
	/**
	 * Public double method to get the length of the Specific Entities
	 * 
	 * @return length, The double length of the HorseshoeCrab
	 */
	public double getLength() {
		return length;
	}

	

	

}
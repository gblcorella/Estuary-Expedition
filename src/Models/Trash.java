package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Trash extends Entities{
	
	int x;
	int y;
	boolean isCaught;
	Direction flow;
	
	/** A Stub Trash Constructor. It sets the Trash's name to "Trash",
	 * the isCaught boolean to false, and the onBoat boolean to false. 
	 */
	public Trash() {
		name = "Trash";
		isCaught = false;
		onBoat = false;
	}
	
	/** 
	 * Simple Constructor or the Trash takes in x,y and the direction
	 * 
	 * @param a Trash location 
	 * @param b Trash y location
	 * @param d Direction of the way the fish is facing 
	 */
	
	public Trash(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
		name = "Trash";
		this.onBoat = false;
	}
	
	/**
	 *  Public getter that returns the x in Trash
	 *  
	 * @return x Getter for the x function 
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Public getter that returns the y in Trash
	 * <p>
	 * @return y Returns the y function
	 */	

	public int getY() {
		return y;
	}
	
	
 /**
 * Setter for the x in Trash
 * 
 * @param a Sets the x value to the int a
 */

	public void setX(int a) {
		this.x = a;
	}
	
	 /**
	 * Setter for the b in Trash
	 * 
	 * @param b Sets the y value to the int b
	 */
	public void setY(int b) {
		this.y = b;
	}
	

 /**
 * Public string method to acquire the name
 * 
 * @return name The name of the trash
 */
	public String getName() {
		return name;
	}

	
/** 
 * Public method to get the flow's direction.
 * @return flow The Direction that the trash is flowing
 */
	public Direction getFlow() {
		return flow;
	}
	
/**
 * Public method used to set the flow's direction.
 * 
 * @param d Sets the flow to Direction d
 */
	public void setFlow(Direction d) {
		this.flow = d;
	}
}
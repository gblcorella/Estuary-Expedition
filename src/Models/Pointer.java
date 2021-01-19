package Models;


/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Pointer implements java.io.Serializable{

	public double x;
	public double y;
	public double reading = 0.0;
	public Direction d = Direction.RIGHT;

	/**
	 * Pointer stub for constructor
	 */

	public Pointer() {}
	
	/**
	 * A method used to get the reading value, a double. 
	 * 
	 * @return reading The Pointer's reading value.
	 */
	public double getReading() {
		return reading;
	}
	
	/**
	 * A setter that sets the reading value in Pointer.
	 * 
	 * @param r The value used to set the reading value.
	 */
	public void setReading(double r) {
		this.reading = r;
	}
	
	/**
	 *  Public getter that returns the double x
	 *  
	 * @return x Getter for the x function 
	 */

	public double getX() {
		return x;
	}
	/**
	 * Set for the x
	 * @param d Also sets the x to double d
	 */

	public void setX(double d) {
		this.x = d;
	}

}
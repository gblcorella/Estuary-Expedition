package Models;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

import java.util.Random;
import java.text.DecimalFormat;


@SuppressWarnings("serial")
public class Entities implements java.io.Serializable {

	public int x;
	public int y;
	public Direction flow;
	protected Random random = new Random();
	public boolean isCaught = false;
	public boolean onBoat = false;
	public String name;
    DecimalFormat dec = new DecimalFormat("#0.00");
    public double length;
/**
	 * A Public stub constructor for Entities
	 */
	public Entities() {}

	
/** A public constructor that creates an entity with 3 parameters that
	 * represent an Entities x value, y value, and flow. 
	 * 
	 * @param a, An int that sets the Entities x value
	 * @param b, An int that sets the Entities y value
	 * @param d, An direction that sets the Entities flow direction
	 */
	public Entities(int a, int b, Direction d) {
		this.x = a;
		this.y = b;
		this.flow = d;
	}


   /** A method that will return a double within the range of min and max.
    *  This will be used to generate the length of each entity.
    *  <p>
    * @param min, a double representing the minimum length
    * @param max, a double representing the maximum length   *
    * @return ret, a double within the range of min and max that represents length.
    */
   public double randRng(double min, double max){
      double ret = min +(max - min) *random.nextDouble();
      ret = Double.parseDouble(dec.format(ret));
      return ret;
   }


   /** A method that gives the current y coor
    * <p>
    * @return y, the current y coor for Entities
    */

	public int getY() {
		return y;
	}


	/**
	 * A method that gives the curent x coor
	 * <p>
	 * @return x, the current x coor for Entities
	 */

	public int getX() {
		return x;
	}

	/** A method that sets the x coor to int a
	 * 
	 * @param a, An integer that will set the Entities x cord
	 */

	public void setX(int a) {
		this.x = a;
	}

	/** A method that sets the y coor to int b
	 * 
	 * @param b, An integer that will set the Entities y cord
	 */

	public void setY(int b) {

		this.y = b;

	}

	/** A method that returns the name of the Entities
	 * <p>
	 * @return name, The String name of the entities
	 */

	public String getName() {
		return name;
	}


	/** A method that returns true or false depending on if an Entity isCaught.
	 * <p>
	 * @return isCaught, a boolean.
	 */
  
	public boolean getIsCaught() {
		return isCaught;
	}

	/** A method that sets the objects isCaught status to true or false
	 * 
	 * @param a, a boolean value, either True or False.
	 */
  
	public void setIsCaught(boolean a) {
		isCaught = a;
	}


	
	/** A method that returns true or false depending on if the object is onBoat
	 * <p>
	 * @return onBoat, a boolean value, either True or False.
	 */
	public boolean getOnBoat() {
		return onBoat;
	}


	/** A method that sets the onBoat boolean to true or false.
	 * 
	 * @param a, a Boolean value that will change the onBoat Entities value
	 */

	public void setOnBoat(boolean a) {
		this.onBoat = a;
	}


	
	/** A method that will get the direction of an object
	 * <p>
	 * @return flow, the direction of the flow of the object
	 */
	public Direction getFlow() {
		return flow;
	}

	/** A method that will set the direction of an object
	 * 
	 * @param d, the direction that the flow will change to
	 */

	public void setFlow(Direction d) {

		this.flow = d;

	}
	
	/** A method that will get the double length of the Entities.
	 * <p>
	 * @return length, The length of the Entities.
	 */
	public double getLength() {
		return length;
	}

}
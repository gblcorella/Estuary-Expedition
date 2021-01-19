package Models;


/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class Net implements java.io.Serializable{
	
	public int x;
	public int y;
	public Direction flow;
	public int poleLength;
	public int polex;
	
	/**
	 * Public net method that sets the flow to the right direction
	 */
	public Net() {
		flow = Direction.RIGHT;
	}

}
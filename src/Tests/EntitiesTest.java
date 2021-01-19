package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Models.Direction;
import Models.Entities;
import Models.Trash;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

public class EntitiesTest {

	Entities Etester = new Entities();
	Entities Etester2 = new Entities(1, 2, Direction.RIGHT);
	Entities Etester3 = new Trash();
	
	/**
	 * A test method for the Entities class.
	 */
	@Test
	public final void entitiesTest() {
		assertEquals(Etester.onBoat, false);
		assertEquals(1, Etester2.getX());
		assertEquals(2, Etester2.getY());
		assertEquals(Direction.RIGHT, Etester2.getFlow());
		assertEquals(Etester.getName(), null);
		Etester.name = "Entity";
		assertEquals(Etester.getName(), "Entity");
		assertEquals(Etester.getIsCaught(), false);
		assertEquals(Etester.getOnBoat(), false);


		Etester.setX(4);
		assertEquals(Etester.x, 4);
		
		Etester.setY(5);
		assertEquals(Etester.y, 5);
		
		
		Etester.setFlow(Direction.RIGHT);
		assertEquals(Etester.flow, Direction.RIGHT);	
		
		Etester.setIsCaught(true);
		assertEquals(Etester.isCaught, true);
	
		Etester.setOnBoat(true);
		assertEquals(Etester.onBoat, true);
		
		Etester3.setX(5);
		Etester3.setY(3);
		Etester3.setFlow(Direction.LEFT);
		assertEquals(Etester3.getX(), 5);
		assertEquals(Etester3.getY(), 3);
		assertEquals(Etester3.getName(), "Trash");
		assertEquals(Etester3.getFlow(), Direction.LEFT);
		Etester3.length = 5.0;
		assertEquals(Etester3.getLength(), 5.0, .5);
		
		
	}
}
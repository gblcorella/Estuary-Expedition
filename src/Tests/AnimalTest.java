package Tests;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

import static org.junit.Assert.*;

import org.junit.Test;

import Models.Animal;
import Models.BlueCrab;
import Models.Direction;
import Models.HorseshoeCrab;
import Models.Loggerhead;
import Models.Weakfish;

public class AnimalTest {

	Animal testAnimal = new Animal();
	Animal testAnimal2 = new Animal(1, 2, Direction.LEFT);
	Loggerhead testLogger = new Loggerhead();
	Loggerhead testLogger2 = new Loggerhead(1, 2, Direction.LEFT);
	Weakfish testFish = new Weakfish();
	Weakfish testFish2 = new Weakfish(1, 2, Direction.LEFT);
	BlueCrab testCrab = new BlueCrab();
	BlueCrab testCrab2 = new BlueCrab(1, 2, Direction.LEFT);
	HorseshoeCrab testHorse = new HorseshoeCrab();
	HorseshoeCrab testHorse2 = new HorseshoeCrab(1, 2, Direction.LEFT);
	
	/**
	 * A test method for the animal class and its subclasses.
	 */
	@Test
	public void animalTest(){
		
		assertEquals(testAnimal.onBoat, false);
		assertEquals(1, testAnimal2.getX());
		assertEquals(2, testAnimal2.getY());
		assertEquals(Direction.LEFT, testAnimal2.getFlow());

		testAnimal.setX(4);
		assertEquals(testAnimal.x, 4);
		
		testAnimal.setY(5);
		assertEquals(testAnimal.y, 5);
		
		
		testAnimal.setFlow(Direction.RIGHT);
		assertEquals(testAnimal.flow, Direction.RIGHT);	
	}	
	
	@Test
	public void loggerheadTest() {
		
		assertEquals(testLogger.onBoat, false);
		assertEquals(1, testLogger2.getX());
		assertEquals(2, testLogger2.getY());
		assertEquals(Direction.LEFT, testLogger2.getFlow());
		assertEquals("Loggerhead", testLogger.getName());

		testLogger.setX(4);
		assertEquals(testLogger.x, 4);
		
		testLogger.setY(5);
		assertEquals(testLogger.y, 5);
		
		testLogger.setFlow(Direction.RIGHT);
		assertEquals(testLogger.flow, Direction.RIGHT);		
		
		testLogger.length = 5.0;
		assertEquals(testLogger.getLength(), 5.0, .5);
	}
	
	@Test
	public void weakfishTest() {
		
		assertEquals(testFish.onBoat, false);
		assertEquals(1, testFish2.getX());
		assertEquals(2, testFish2.getY());
		assertEquals(Direction.LEFT, testFish2.getFlow());
		assertEquals("Weakfish", testFish.getName());

		testFish.setX(4);
		assertEquals(testFish.x, 4);
		
		testFish.setY(5);
		assertEquals(testFish.y, 5);
		
		testFish.setFlow(Direction.RIGHT);
		assertEquals(testFish.flow, Direction.RIGHT);		
		
		testFish.length = 5.0;;
		assertEquals(testFish.getLength(), 5.0, .5);
	}
	
	@Test
	public void blueCrabTest() {
		
		assertEquals(testCrab.onBoat, false);
		assertEquals(1, testCrab2.getX());
		assertEquals(2, testCrab2.getY());
		assertEquals(Direction.LEFT, testCrab2.getFlow());
		assertEquals("Blue Crab", testCrab.getName());

		testCrab.setX(4);
		assertEquals(testCrab.x, 4);
		
		testCrab.setY(5);
		assertEquals(testCrab.y, 5);
		
		testCrab.setFlow(Direction.RIGHT);
		assertEquals(testCrab.flow, Direction.RIGHT);		
		
		testCrab.length = 5.0;
		assertEquals(testCrab.getLength(), 5, .5);
	}
	
	@Test
	public void HorseshoeCrabTest() {
		
		assertEquals(testHorse.onBoat, false);
		assertEquals(1, testHorse2.getX());
		assertEquals(2, testHorse2.getY());
		assertEquals(Direction.LEFT, testHorse2.getFlow());
		assertEquals("Horseshoe Crab", testHorse.getName());

		testHorse.setX(4);
		assertEquals(testHorse.x, 4);
		
		testHorse.setY(5);
		assertEquals(testHorse.y, 5);
		
		testHorse.setFlow(Direction.RIGHT);
		assertEquals(testHorse.flow, Direction.RIGHT);	
		
		testHorse.length = 5.0;
		assertEquals(testHorse.getLength(), 5.0, .5);
	}
}



	
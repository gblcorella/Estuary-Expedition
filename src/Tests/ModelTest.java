package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;
import Models.BlueCrab;
import Models.Direction;
import Models.Entities;
import Models.HorseshoeCrab;
import Models.Loggerhead;
import Models.ModelR;
import Models.Pointer;
import Models.Trash;
import Models.Weakfish;


/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */


public class ModelTest {

	ModelR tester = new ModelR(1000, 1000);
	ModelR tester2 = new ModelR();
	
	/**
	 * A test method for the model's constructor.
	 */
	@Test
	public void modelTest() {
		
		assertEquals(tester.getScreenWidth(), 1000);
		assertEquals(tester.getScreenHeight(), 1000);
		Direction d = Direction.LEFT;
		assertEquals(d.getName(), "left");
		
		assertEquals(tester.runTimer, false);
		tester.setRunTimer(true);
		assertEquals(tester.runTimer, true);
		
		assertEquals(tester.timer, 30);
		tester.setTimer(0);
		assertEquals(tester.timer, 0);
		
	
	}
	
	/**
	 * A test method for the genObj function inside of ModelR
	 */
	@Test
	public void genObjTest() {

		for (int j = 0; j < 50; j++) {
			tester.genObj();
		}
		
		assertEquals(tester.objs.size(), 50);
		
		for(int j = 0; j < 50; j++) {
			
			assertTrue(tester.objs.get(j) instanceof Entities);
			
			if (tester.objs.get(j).getFlow() == Direction.LEFT) {
				assertEquals(tester.objs.get(j).getX(), 1000);
			}
			
			if (tester.objs.get(j).getFlow() == Direction.RIGHT) {
				assertEquals(tester.objs.get(j).getX(), 0);
			}
		}
	}
	
	/**
	 * A test method for when changes in directions occur
	 */
	@Test
	public void arrowKeyTest() {
		tester.left();
		assertEquals(tester.getPlayer().flow, Direction.LEFT);
		assertEquals(tester.getNet().flow, Direction.LEFT);
		
		tester.right();
		assertEquals(tester.getPlayer().flow, Direction.RIGHT);
		assertEquals(tester.getNet().flow, Direction.RIGHT);
		
		int n = tester.getNet().y;
		int p = tester.getNet().poleLength;
		int s = tester.getScreenHeight();
		
		tester.down();
		assertEquals(tester.getNet().y, (int)(n + (s * .03)));
		assertEquals(tester.getNet().poleLength, (int)(p + (s * .03)));
		
		n = (int)(s * .4);
		tester.getNet().y = n;
		tester.up();
		assertEquals(tester.getNet().y, (int)(n - (s * .03)));
		
		n = (int)(s * .5);
		tester.getNet().y = n;
		tester.up();
		assertEquals(tester.getNet().y, (int)(n - (s * .03)));
		
		n = (int)(s * .3);
		tester.getNet().y = n;
		tester.up();
		assertEquals(tester.getNet().y, n);
	}
	
	/**
	 * A test method for when objects move in the model.
	 */
	@Test
	public void moveTests() {
		tester.getPlayer().flow = Direction.LEFT;
		int p = tester.getScreenWidth();
		tester.getPlayer().x = p;
		tester.getNet().x = p;
		tester.getNet().polex = p;
		
		tester.moveBoat();
		assertEquals(tester.getPlayer().x, p - tester.xinc);
		assertEquals(tester.getNet().x, p - tester.xinc);
		assertEquals(tester.getNet().polex, p - tester.xinc);
		
		tester.getPlayer().x = 0;
		tester.getNet().x = 0;
		tester.getNet().polex = 0;
		
		tester.moveBoat();
		assertEquals(tester.getPlayer().x, 0);
		assertEquals(tester.getNet().x, p = 0);
		assertEquals(tester.getNet().polex, 0);
		
		tester.getPlayer().flow = Direction.RIGHT;
		
		tester.moveBoat();
		assertEquals(tester.getPlayer().x, tester.xinc);
		assertEquals(tester.getNet().x, tester.xinc);
		assertEquals(tester.getNet().polex, tester.xinc);
		
		p = tester.getScreenWidth();
		tester.getPlayer().x = p;
		tester.getNet().x = p;
		tester.getNet().polex = p;
		
		tester.moveBoat();
		assertEquals(tester.getPlayer().x, p);
		assertEquals(tester.getNet().x, p);
		assertEquals(tester.getNet().polex, p);	
	}
	
	/** 
	 * A method that tests the catchEntities function in the model.
	 */
	@Test
	public void catchTests() {
		tester.objs.add(new Loggerhead(100, 100, Direction.LEFT));
		tester.objs.add(new BlueCrab(200, 200, Direction.RIGHT));
		tester.objs.add(new HorseshoeCrab(100, 200, Direction.LEFT));
		tester.objs.add(new Weakfish(200, 100, Direction.RIGHT));
		tester.objs.add(new Trash(100, 0, Direction.LEFT));
		tester.objs.add(new BlueCrab(0, 100, Direction.RIGHT));
		
		tester.getNet().y = 101;
		tester.getNet().x = 99;
		
		tester.catchEntities();
		assertEquals(tester.objs.get(0).isCaught, true);
		assertEquals(tester.objs.get(1).isCaught, false);
		assertEquals(tester.objs.get(2).isCaught, false);
		assertEquals(tester.objs.get(3).isCaught, false);
		assertEquals(tester.objs.get(5).isCaught, false);
	}
	
	/**
	 * A method that tests the moveEntities() function in the model
	 */
	@Test
	public void moveEntitiesTests() {
		tester.getNet().x = 100;
		tester.getNet().y = 1000;
		tester.getNet().flow = Direction.LEFT;
		tester.objs.add(new Loggerhead(0, 0, Direction.RIGHT));
		tester.objs.add(new BlueCrab(1000, 0, Direction.LEFT));
		tester.objs.add(new Loggerhead(0, 0, Direction.RIGHT));

		tester.objs.get(0).setIsCaught(true);
		assertEquals(tester.objs.get(0).getOnBoat(), false);
		
		tester.moveEntities();
		assertEquals(tester.objs.get(0).getX(), 100);
		assertEquals(tester.objs.get(0).getY(), 1000);
		assertEquals(tester.objs.get(0).getFlow(), Direction.LEFT);
		assertEquals(tester.objs.get(0).getOnBoat(), false);
		assertEquals(tester.objs.get(1).getX(), 1000 - tester.fishinc);
		assertEquals(tester.objs.get(2).getX(), tester.fishinc);
		
		tester.getNet().y = 0;
		tester.objs.add(new Weakfish(0, 0, Direction.RIGHT));
		tester.objs.get(3).setIsCaught(true);
		assertEquals(tester.objs.get(3).getOnBoat(), false);
		tester.moveEntities();
		assertEquals(tester.objs.get(3).getOnBoat(), true);
		assertEquals(tester.caught.get(1), tester.objs.get(3));
		
		tester.fishScore = 6;
		tester.moveEntities();
		tester.fishScore = 11;
		tester.moveEntities();
		tester.fishScore = 16;
		tester.moveEntities();
	}
	
	/**
	 * A test method for the function removeCaught() in the model.
	 */
	@Test
	public void removeCaughtTests() {
		Weakfish w = new Weakfish(1, 2, Direction.LEFT);
		Loggerhead l = new Loggerhead(0, 1, Direction.RIGHT);
		BlueCrab b = new BlueCrab(4, 3, Direction.RIGHT);
		HorseshoeCrab h = new HorseshoeCrab(3, 3, Direction.LEFT);
		
		tester.objs.add(w);
		tester.objs.add(l);
		tester.objs.add(b);
		tester.caught.add(w);
		tester.caught.add(l);
		tester.caught.add(h);
		assertEquals(tester.objs.get(0), w);
		
		tester.removeCaught();
		assertEquals(tester.objs.get(0), b);
	}
	
	/** 
	 * A test method for the removeEscaped() function in the model.
	 */
	@Test
	public void removeEscapedTest() {
		Weakfish w = new Weakfish(tester.getScreenWidth() - 5, 5, Direction.LEFT);
		Loggerhead l = new Loggerhead(tester.getScreenWidth() + 5, 5, Direction.LEFT);;
		BlueCrab b = new BlueCrab(-3, 3, Direction.RIGHT);
		HorseshoeCrab h = new HorseshoeCrab(3, 3, Direction.LEFT);
		
		tester.objs.add(w);
		tester.objs.add(l);
		tester.objs.add(b);
		tester.escaped.add(h);
		tester.removeEscaped();
		
		assertEquals(tester.escaped.get(1), l);
	}
	
	/** 
	 * A test method used to test the getFishingScore() method in the model
	 */
	@Test
	public void getFishingScoreTests() {
		Weakfish w = new Weakfish();
		Loggerhead l = new Loggerhead();
		BlueCrab b = new BlueCrab();
		Trash t = new Trash();
		
		tester.caught.add(w);
		tester.caught.add(l);
		tester.caught.add(b);
		tester.caught.add(t);
		
		assertEquals(2, tester.getFishingScore());
	}

	/** 
	 * A test method used to test the update() function in the model
	 */
	@Test
	public void updateTests() {
		tester.runTimer = false;
		tester.genObj();
		tester.genObj();
		tester.update();
		tester.runTimer = true;
		tester.update();
		tester.startedFishing = true;
		tester.inLab = false;
		tester.runTimer = true;
		tester.update();
		tester.inLab = true;
		tester.pointerMoving = true;
		tester.update();
		tester.labTutorial = true;
		tester.mapping = true;
		tester.caught.add(new HorseshoeCrab());
		tester.factLocations.add(new Point(0, 0));
		tester.update();
		tester.measured.add(new HorseshoeCrab());
		tester.measured.add(new BlueCrab());
		tester.update();
		tester.runTimer = false;
		tester.update();
		assertEquals(tester.objs.size(), 5);
		}
	

	/**
	 * A test function used to test the lastCaught() method in the model.
	 */
	@Test
	public void lastCaughtTests() {
		tester.lastCaught();
		assertEquals(tester.caught.isEmpty(), true);
		assertEquals(tester.lastCaught, null);
		
		Weakfish w = new Weakfish();
		Loggerhead l = new Loggerhead();
		BlueCrab b = new BlueCrab();
		Trash t = new Trash();
		HorseshoeCrab h = new HorseshoeCrab();
		
		tester.caught.add(w);
		tester.caught.add(l);
		tester.caught.add(b);
		assertEquals(tester.caught.isEmpty(), false);
		assertEquals(tester.caught.size(), 3);
		tester.lastCaught();
		assertEquals(tester.lastCaught, "Blue Crab");
		tester.caught.add(l);
		tester.lastCaught();
		assertEquals(tester.lastCaught, "Loggerhead");
		tester.caught.add(w);
		tester.lastCaught();
		assertEquals(tester.lastCaught, "Weakfish");
		tester.caught.add(t);
		tester.lastCaught();
		assertEquals(tester.lastCaught, "Trash");
		tester.caught.add(h);
		tester.lastCaught();
		assertEquals(tester.lastCaught, "Horseshoe Crab");

	}
	
	/**
	 * A test method used to test the timer methods in the model.
	 */
	@Test
	public void timer30Tests() {
		tester.timerStarted = false;
		assertEquals(tester.timer, 30);
		assertEquals(tester.time, (int)(java.lang.System.currentTimeMillis() % 30000 / 1000));
		
		tester.timer30();
		assertEquals(tester.timerStarted, true);

		tester.timer30();
		
		tester.timePassed = 29;
		tester.timer30();
		
		tester.time = 100000;
		tester.timer30();
		assertEquals(tester.timePassed, (30 - 100000 + (int)((java.lang.System.currentTimeMillis() % 30000) / 1000)));
		
		tester.time = -1;
		tester.timer30();
		assertEquals(tester.timePassed, ((int)((java.lang.System.currentTimeMillis() % 30000) / 1000) +1));
		
		tester.resetTimer();
		assertEquals(tester.timer, 30);
		assertEquals(tester.timeLeft, 30);
		assertEquals(tester.timePassed, 1);
		assertEquals(tester.runTimer, false);
		assertEquals(tester.timerStarted, false);
		
	}
	
	/**
	 * A test method used to test the pointer functions in the model.
	 */
	@Test
	public void pointerTests() {
		Pointer p = new Pointer();
		p.x = 0;
		tester.setPointer(p);
		assertEquals(tester.getPointer(), p);
		
		tester.shiftPointerL();
		assertEquals(tester.getPointer().x, 0, 10);
		
		tester.shiftPointerR();
		assertEquals(tester.getPointer().x, (int)(tester.getScreenWidth() * .01), 10);
		
		tester.getPointer().x = (int)(tester.getScreenWidth() * .9);
		tester.shiftPointerR();
		assertEquals(tester.getPointer().x, (int)(tester.getScreenWidth() * .9), 5);
		
		tester.getPointer().x = (int)(tester.getScreenWidth() * .91);
		tester.shiftPointerL();
		assertEquals(tester.getPointer().x, (int)(tester.getScreenWidth() * .9), 5);	
		
		p.x = tester.startPointer.getX() - (tester.getScreenWidth() * .05);
		tester.setPointer(p);
		tester.shiftPointerL();
		assertEquals(tester.getPointer().d, Direction.RIGHT);
		
		HorseshoeCrab h = new HorseshoeCrab();
		tester.caught.add(h);
		tester.resetPointer();
		
		Trash t = new Trash();
		tester.caught.add(t);
		tester.getPointer().x = 0;
		tester.resetPointer();
		p.d = Direction.LEFT;
		tester.movePointer();
	}
	
	/**
	 * A test method used to test the quntityCaught() function in the model.
	 */
	@Test
	public void quantityCaughtTests() {
		tester.caught.add(new Loggerhead());
		tester.caught.add(new Trash());
		tester.caught.add(new HorseshoeCrab());
		tester.caught.add(new BlueCrab());
		tester.caught.add(new Weakfish());
		tester.caught.add(new Loggerhead());
		tester.caught.add(new Entities());

		
		tester.quantityCaught();
		
		assertEquals(tester.quantityCaught[0], 2);
		assertEquals(tester.quantityCaught[1], 1);
		assertEquals(tester.quantityCaught[2], 1);
		assertEquals(tester.quantityCaught[3], 1);
		assertEquals(tester.quantityCaught[4], 2);
	}


	/**
	 * A test method for the compareMap() function in the model.
	 */
	@Test
	public void compareMapTests() {
	Point a = new Point();
	Point b = new Point();
	Point c = new Point();
	Point d = new Point();
	a.setLocation(tester.mapAnimals.get(0).getX(), tester.mapAnimals.get(0).getY());
	b.setLocation(tester.mapAnimals.get(1).getX(), tester.mapAnimals.get(1).getY());
	c.setLocation(tester.mapAnimals.get(2).getX(), tester.mapAnimals.get(2).getY());
	d.setLocation(tester.mapAnimals.get(3).getX(), tester.mapAnimals.get(3).getY());
	tester.factLocations.add(a);
	tester.factLocations.add(b);
	tester.factLocations.add(c);
	tester.factLocations.add(d);
	tester.compareMap();
	
	assertEquals(tester.mappedAnimals[0], true);
	assertEquals(tester.mappedAnimals[1], true);
	assertEquals(tester.mappedAnimals[2], true);
	assertEquals(tester.mappedAnimals[3], true);
	}
	
	/**
	 * A test function for the moveAnimals() function in the model.
	 */
	@Test
	public void moveAnimalsTest() {
		tester.clickx = tester.mapAnimals.get(0).getX() + (int)(tester.getScreenWidth() * .05);
		tester.clicky = tester.mapAnimals.get(0).getY() + (int)(tester.getScreenHeight() * .05);
		tester.mapSetUp();
		tester.releasex = (int)tester.factLocations.get(0).getX() + (int)(tester.getScreenWidth() * .05);
		tester.releasey = (int)tester.factLocations.get(0).getY() + (int)(tester.getScreenHeight() * .05);
		tester.moveAnimalsMap();
		tester.clickx = tester.mapAnimals.get(1).getX() + (int)(tester.getScreenWidth() * .05);
		tester.clicky = tester.mapAnimals.get(1).getY() + (int)(tester.getScreenHeight() * .05);
		tester.releasex = (int)tester.factLocations.get(1).getX() + (int)(tester.getScreenWidth() * .05);
		tester.releasey = (int)tester.factLocations.get(1).getY() + (int)(tester.getScreenHeight() * .05);
		tester.moveAnimalsMap();
		tester.clickx = tester.mapAnimals.get(2).getX() + (int)(tester.getScreenWidth() * .05);
		tester.clicky = tester.mapAnimals.get(2).getY() + (int)(tester.getScreenHeight() * .05);
		tester.releasex = (int)tester.factLocations.get(2).getX() + (int)(tester.getScreenWidth() * .05);
		tester.releasey = (int)tester.factLocations.get(2).getY() + (int)(tester.getScreenHeight() * .05);
		tester.moveAnimalsMap();
		tester.clickx = tester.mapAnimals.get(3).getX() + (int)(tester.getScreenWidth() * .05);
		tester.clicky = tester.mapAnimals.get(3).getY() + (int)(tester.getScreenHeight() * .05);
		tester.releasex = (int)tester.factLocations.get(3).getX() + (int)(tester.getScreenWidth() * .05);
		tester.releasey = (int)tester.factLocations.get(3).getY() + (int)(tester.getScreenHeight() * .05);
		tester.moveAnimalsMap();
		
	}
	
	/**
	 * A test function for the mappingScore() function in the model
	 */
	@Test
	public void mappingScoreTest() {
		tester.mappedAnimals[0] = true;
		tester.mappingScore();
		assertEquals(tester.mappingScore, 1);
		tester.mappedAnimals[1] = true;
		tester.mappingScore();
		assertEquals(tester.mappingScore, 2);
		tester.mappedAnimals[2] = true;
		tester.mappedAnimals[3] = true;
		tester.mappingScore();
		assertEquals(tester.mappingScore, 4);
	}
	
	/**
	 * A test function for the getter functions in ModelR
	 */
	@Test
	public void getterTests() {
		assertEquals(tester.getObjs(), tester.objs);
		assertEquals(tester.getScore(), tester.score);
		assertEquals(tester.getCaught(), tester.caught);
		assertEquals(tester.getFactDisplayed(), tester.factDisplayed);
		assertEquals(tester.getFactLocations(), tester.factLocations);
		assertEquals(tester.getLastCaught(), tester.lastCaught);
		assertEquals(tester.getQuantityCaught(), tester.quantityCaught);
		assertEquals(tester.getMeasured(), tester.measured);
		assertEquals(tester.getMapAnimals(), tester.mapAnimals);
		assertEquals(tester.getMappedAnimals(), tester.mappedAnimals);
		assertEquals(tester.getTimer(), tester.timer);
	}
}
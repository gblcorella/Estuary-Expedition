package Models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class ModelR implements java.io.Serializable{
	Player player; 
	Net net;
	Map map;
	static int screenHeight; // height of screen in pixels
	static int screenWidth; // width of screen in pixels
	public int fishinc;  // x-value that objects move on each tick, + or - depending on direction
	public int fishScore; // score based on objects caught
	public int measuringScore = 0;
	public int score;
	public int mappingScore = 0;
	public String lastCaught; // name of the last object caught
	public double lastCaughtLength; // length of the last object caught
	public ArrayList<Entities> objs = new ArrayList<Entities>(); // list of objects in the water
	public ArrayList<Entities> caught = new ArrayList<Entities>(); // list of objects brought on to boat
	public ArrayList<Entities> mapAnimals = new ArrayList<Entities>();
	public int[] quantityCaught = new int[5]; // list of integers representing number of each object caught
	public int[] factDisplayed = new int[4]; // which fact number will be used in the mapping game
	public boolean[] mappedAnimals = new boolean[4];
	public ArrayList<Point> factLocations = new ArrayList<Point>();
	public ArrayList<Entities> escaped = new ArrayList<Entities>(); // list of objects that are not caught and move off screen
	public boolean startedFishing = false;
	public boolean mapping = false;
	public boolean loggerfact = false;
	public boolean horsefact = false;
	public boolean crabfact = false;
	public boolean fishfact = false;
	public int xinc; // x-value that boat and net will move each tick
 
	// mouse 
	public boolean dragging = false;
	public int clickx = -1;
	public int clicky = -1;
	public int dragx = -1;
	public int dragy = -1;
	public int releasex = -1;
	public int releasey = -1;
	
	// timer constants
	public int timer = 30;  // initialized timer at 30 seconds
	public int timeLeft = 30; // timeLeft initialized at 30 seconds
	public int time = (int)(java.lang.System.currentTimeMillis() % 30000 / 1000); // initialized current time
	public int timePassed = 1; 
	public boolean runTimer = false;
	public boolean timerStarted = false;

	// lab and pointer constants
	Pointer pointer;  // Pointer that player will control to measure Animal
	public Pointer startPointer; // Pointer with x-value coordinating to correct Animal length
	double pointerstart; // x-location that pointer will move back and forth around
	double pointerzero; // x-location where pointer will be at the 0 mark on the ruler
	public double pointerinc; // x-value that pointer will move on each tick, coordinating with .4 inches on ruler
	public double readinginc = .4;  // value that reading changes on each tick, + or - depending on direction
	double scaleLength; // value to multiply animal's length by to properly scale animal with ruler
	public boolean pointerMoving = false;  // pointer is not moving
	public boolean inLab = false;
	public ArrayList<Entities> measured = new ArrayList<Entities>(); // list of all successfully measured Animal's
	public boolean labTutorial = false;

	// fishing level
	public boolean easy;
	public boolean medium;
	public boolean hard;
	public boolean insane;
	
	// game state booleans
	   public boolean displayStartScreen = true;
		public boolean displayLetsGoScreen = false;
		public boolean displayFishingScreen = false;
		public boolean clickedContinue = false;
		public boolean continued = false;
		public boolean startedMeasuring = false;
		public boolean continuedToMap = false;
		public boolean displaymapnext = false;
		public boolean displayendscreen = false;
		public boolean rachelgoodbye = false;
		public boolean stewgoodbye = false;
		public boolean eddiegoodbye = false;
		public boolean allgoodbye = false;
	   
	   public boolean tutorialstarted = true;
		public boolean tutleftright = false;
		public boolean tutdown = false;
		public boolean tutup = false;
		public boolean tutcaughtfirst = false;
		
	/** 
	 * Model stub for the constructor.
	 */
	public ModelR() {}
	
	/** Public constructor for the model that takes in int width, "w",
	 * and int height, "h". These values are later taken in the view. It
	 * uses those 2 int's in order to create objects, including 
	 * the object locations, for the model to use.
	 * 
	 * @param w Sets the Model's screenWidth
	 * @param h Sets the Model's screenHeight
	 */
	public ModelR(int w, int h) {
		// screen dimensions
		screenWidth = w;
		screenHeight = h;
		
		// new Player
		player = new Player();
		player.x = (int)(w * .1);
		player.y = (int)(h * .3);
		xinc = (int)(w * .01);
		
		// new Net
		net = new Net();
		net.x = (int)(w * .15);
		net.poleLength = (int)(h *.02);
		net.y = (int)(h * .35);
		
		// new Pointer
		pointer = new Pointer();
		pointerzero = w * .328;
		pointer.x = pointerzero;
		pointerinc = w * 0.0059027;
		startPointer = new Pointer();
		
		// new Map
		map = new Map();
		map.location1 = new Point((int)(screenWidth * .7), (int)(screenHeight * .75));
		map.location2 = new Point((int)(screenWidth * .25), (int)(screenHeight * .38));
		map.location3 = new Point((int)(screenWidth * .5), (int)(screenHeight * .8));
		map.location4 = new Point((int)(screenWidth * .5), (int)(screenHeight * .42));
		mapAnimals.add(new Loggerhead((int)(screenWidth * .12), (int)(screenHeight * .82), Direction.LEFT));
		mapAnimals.add(new HorseshoeCrab((int)(screenWidth * .25), (int)(screenHeight * .7), Direction.LEFT));
		mapAnimals.add(new BlueCrab((int)(screenWidth * .12), (int)(screenHeight * .7), Direction.LEFT));
		mapAnimals.add(new Weakfish((int)(screenWidth * .25), (int)(screenHeight * .82), Direction.LEFT));
		mappedAnimals[0] = false;
		mappedAnimals[1] = false;
		mappedAnimals[2] = false;
		mappedAnimals[3] = false;
		
		// other constants 
		fishinc = (int)(w * .008);
		scaleLength = w * .0149;
		}


	/** A method that generates a random number between 0 and 5 and makes the
	 *  object that coordinates to that number. Each object is assigned a
	 *  random height and direction. Each object is added to the model's objects
	 *  array  
	 */
	
	public void genObj() {
		Entities temp;
		Random ran = new Random();
		int x;
		Direction d = randomDirection();
		int y = randomHeight();
		if (d == Direction.LEFT) {
			x = screenWidth;
		}
		else {
			x = 0;
		}
		if (ran.nextInt(5) == 0) {
			temp = new Trash(x, y, d);
			}
		else if (ran.nextInt(5) == 1) {
			temp = new BlueCrab(x, y, d);
			} 
		else if (ran.nextInt(5)== 2) {
			temp = new Weakfish(x, y, d);
			} 
		else if (ran.nextInt(5) == 3) {
			temp = new HorseshoeCrab(x, y, d);
			} 
		else {
			temp = new Loggerhead(x, y, d);
		}
		objs.add(temp);
	}

	
	/** A method that is called by KeyListener when the left arrow key 
	 * is pressed. It changes the direction of the net and boat to LEFT 
	 */
	
	public void left() {
		player.flow = Direction.LEFT;
		net.flow = Direction.LEFT;
	}

	/** A method that is called by KeyListener when the right arrow key 
	 * is pressed. It changes the direction of the net and boat to RIGHT 
	 */
	public void right() {
		player.flow = Direction.RIGHT;
		net.flow = Direction.RIGHT;
	}
	
	/** A method that is called by KeyListener when the down arrow key 
	 * is pressed. It lowers the net and lengthens the net's pole. 
	 */
    public void down() {
    	net.y += (screenHeight * .03);
    	net.poleLength += (screenHeight * .03);
    }

    /** A method that is called by KeyListener when the up arrow key 
	 * is pressed. It lowers the net and lengthens the net's pole. 
	 */
    public void up() {
    	if (net.y >= screenHeight * .4) {
    		net.y -= (screenHeight * .03);
    		net.poleLength -= (screenHeight * .03);
    	}
    }

    /** A method that decreases the x position of the boat, net, and pole.
     * It does not allow the player to go off of the screen.
     */
    public void moveLeft() {
    	if (player.x > 0) {
    		this.player.x -= xinc;
    		this.net.x -= xinc;
    		this.net.polex -= xinc;
    	}
    }

    /** A method that increases the x position of the boat, net, and 
     * pole. It does not allow the player to go off of the screen.
     */
   
    public void moveRight() {
    if (player.x < screenWidth * .85) {
    		this.player.x += xinc;
    		this.net.x += xinc;
    		this.net.polex += xinc;	
    	}
    }

    /** A method that calls moveRight() if the boat's direction is RIGHT.
     * Alternatively, it calls moveLeft() if the boat's direction is LEFT.   
     * 
     */
    
    public void moveBoat() {
    	if (player.flow == Direction.RIGHT) {
			moveRight();
		}
		if(player.flow == Direction.LEFT) {
			moveLeft();
		}
    }

  /** A method that checks if the objects on the screen are within 
   * a certain distance of the net. If the object is close enough to the
   * net, the object's isCaught boolean is set to true. 
   */ 
    public void catchEntities() {
    	double paddingy = screenHeight * .03;
		double paddingx = screenWidth * .03;

		for (Entities e : objs) {
    		if (net.y > (e.getY() - paddingy) && net.y < (e.getY() + paddingy) && net.x > (e.getX() - paddingx) && net.x < (e.getX() + paddingx)) {
    			e.setIsCaught(true);
    		}
		}
    }
   /** A method that checks if the user's score in the fishing game is above 
    * a certain score. Depending on the score, the game will adjust the catchable 
    * object's speed in order to increase difficulty. 
    */ 
    
	public void checkDifficulty() {
		if (fishScore > 15) {
			insane = true;
		}
		else if (fishScore > 10) {
			hard = true;
		}
		else if (fishScore > 5) {
			medium = true;
		}
		else {
			easy = true;
			medium = false;
			hard = false;
			insane = false;
		}

	}

    /** A method that checks if the entities on the screen are caught.  if an object is caught,
     * it's x, y, and flow are set to the same values as the net.  if the caught object is
     * close enough to the boat, it's onBoat boolean is set to true and it is added to the 
     * caught arrayList. If the entities are not caught, it's x value is changed based on its 
     * direction. The x value, indicating object speed, will move by a greater amount if the player
     * is performing well, based on their current score.
     */
	public void moveEntities() {
		checkDifficulty();
		for (Entities e : objs) {
			if (e.getIsCaught() == true) {
				e.setX(net.x);
				e.setY(net.y);
				e.setFlow(net.flow);	
				if (e.getY() <= (player.y + (screenHeight * .1))){
					e.setOnBoat(true);
					caught.add(e);
				}
			}
			else {
				if (easy == true && medium == false && hard == false && insane == false) {
					if (e.getFlow() == Direction.LEFT) {
						e.setX(e.getX() - (int)(fishinc));
					}
					else {
						e.setX(e.getX() + (int)(fishinc));
					}
				}
				else if (easy == true && medium == true && hard == false && insane == false) {
					if (e.getFlow() == Direction.LEFT) {
						e.setX(e.getX() - (int)(fishinc * 2));
					}
					else {
						e.setX(e.getX() + (int)(fishinc * 2));
					}
				}
				else if (easy == true && medium == true && hard == true && insane == false) {
					if (e.getFlow() == Direction.LEFT) {
						e.setX(e.getX() - (int)(fishinc * 3));
					}
					else {
						e.setX(e.getX() + (int)(fishinc * 3));
					}
				}
				else {
					if (e.getFlow() == Direction.LEFT) {
						e.setX(e.getX() - (int)(fishinc * 5));
					}
					else {
						e.setX(e.getX() + (int)(fishinc * 5));
					}
				}
			}
		}
	}
    
    /** A method that sets the lastCaught variable to the name of the entity
     * last added to the caught ArrayList. This is used for determining which
     * fact for Eddie to share.
     */
	public void lastCaught() {
		if (caught.isEmpty() == false) {
			int s = caught.size();
			if (caught.get(s - 1) instanceof BlueCrab) {
				lastCaught = "Blue Crab"; 
			}
			else if (caught.get(s - 1) instanceof Loggerhead) {
				lastCaught = "Loggerhead"; 
			}
			else if (caught.get(s-1) instanceof HorseshoeCrab) {
				lastCaught = "Horseshoe Crab";
			}
			else if (caught.get(s-1) instanceof Weakfish) {
				lastCaught = "Weakfish";
			}
			else lastCaught = "Trash";

		}

	}


    /** A method that removes all objects that are on the boat from the objs 
     * ArrayList. 
     */
    public void removeCaught() {
    	for (Entities e : caught) {
    		if(objs.contains(e)) {
    			objs.remove(e);
    		}
    	}
    }
    
    /** A method that checks if an object goes off of the screen. If it does,
     * the object is added to the escaped ArrayList and removed from the objs 
     * ArrayList.
     */
    public void removeEscaped() {
    	for (Entities e : objs) {
    		if ((e.getX() < 0) || (e.getX() > screenWidth)) {
    			escaped.add(e);
    		}
    	}
    	for (Entities e : escaped) {
    		if (objs.contains(e)) {
    			objs.remove(e);
    		}
    	}
    }

    /** A method that adds 1 to the score for each Animal in the caught ArrayList and
     * subtracts 1 from the score for each caught Trash object. It returns the score
     * for the fishing game.
     * 
     * @return s An indicator that is used to either increase or decrease the player's score.
     */
    public int getFishingScore() {
    	int s = 0;
    	for (Entities e : caught) {
    		if (e instanceof Animal) {
    			s++;
    		}
    		if (e instanceof Trash) {
    			s--;
    		}
    	}
    	return s;
    }

    /** A method that will continue to call genObjs() so there are always 5 entities on the screen
     * during the fishing game. Once the player starts the game, runTimer is set to true.  The time, positions, score,
     * and ArrayLists are continually updated. 
     * In the lab, the pointer moves during the tutorial, and the timer starts after the tutorial.  The pointer stops
     * moving when the 30 second timer runs out.  The timer will also stop if the player measures all of the caught objects.
     * In the mapping game, the map is set up with 4 fact locations.  The location of the animals is compared to the 
     * fact locations.  The scores from the fishing game, the measuring game, and the mapping game form the final score.
     */
	public void update() {
		while(objs.size() < 5) {
			genObj();
		}
		if ((startedFishing == true) && (inLab == false)){
			if (runTimer == true) {
				timer30();
			}
		moveBoat();
		catchEntities();
		moveEntities();
		removeCaught();
		fishScore = getFishingScore();
		removeEscaped();
		quantityCaught();
		lastCaught();
		}
		
		
		if (inLab) {
			if(labTutorial) {
				if ((pointerMoving) && (caught.isEmpty() == false)) {
					resetPointer();
					movePointer();
				}
				if (measured.size() >= 2) {
					if (runTimer == true) {
					timer30();
				}
					if (runTimer == false) {
						pointerMoving = false;
					}
				}
			}	
			if (caught.isEmpty() == true) {
				time = (int)(java.lang.System.currentTimeMillis() % 30000 / 1000);
				timePassed = 29;
				timer = 0;
			}
		}
		if (mapping) {
			while(factLocations.size() < 4) {
				mapSetUp();
			}
			compareMap();
		}
		measuringScore = measured.size();
		mappingScore = mappingScore();
		score = fishScore + measuringScore + mappingScore;
	}	

	/** A method that returns the model's player.
	 * 
	 * @return player The player in the model
	 */
	
	public Player getPlayer() {
		return player;
	}

	/** A method that returns the model's net
	 * 
	 * @return net The net in the model
	 */
	public Net getNet() {
		return net;
	}

	/** A method that generates a random integer that correlates to a y-value beneath 
	 * the water in the view
	 * 
	 * @return a random integer that correlates to a y-value beneath the water in the view
	 */
	public static int randomHeight() {
		Random random = new Random();
		int bound = (int)(screenHeight *.45);
		int a = random.nextInt(bound) + (int)(screenHeight * .4);
		return a;
	}
	
	/** A method that randomly returns either LEFT or RIGHT
	 * 
	 * @return d Either the direction Left or direction Right
	 */
	public Direction randomDirection() {
		Random r = new Random();
		int y =  r.nextInt();
		if(y % 2 == 0) {
			Direction d = Direction.LEFT;
	         return d;
	      }
	      else  {
	    	  Direction d = Direction.RIGHT;
	         return d;
	      }
	}

	/** 
	 * A method that returns the screen height.
	 * 
	 * @return screenHeight The screenHeight in the model
	 */
	public int getScreenHeight() {
		return screenHeight;
	}
	
	/** A method that  returns the screen width.
	 * 
	 * @return screenWidth The screenWidth in the model
	 */
	public int getScreenWidth() {
		return screenWidth;
	}
	
	/** A method that changes the time value to the system time when timer30() is first called
	 * timerStarted is changed to true, so time will only be changed once
	 * each additional time timer30() is called, the current time and initial time 
	 * are used to calculate how much time has passed.  The timer value is how much time 
	 * is left of the 30 seconds. 
	 */	
	public void timer30() {
		if (timerStarted == false) {
			time = (int)(java.lang.System.currentTimeMillis() % 30000 / 1000);
			timerStarted = true;
		}
		int curr = (int)((java.lang.System.currentTimeMillis() % 30000) / 1000);
		int temp = timePassed;
		if (curr > time) {
			timePassed = curr - time;
			}
		else if (curr < time){	
			timePassed = (int)(30 - time + curr);
			}
		else {		
			timePassed = 0;
			}	
		if((timePassed == 0) && (temp == 29)) {
			runTimer = false;
			timer = 0;		
		}
		else {
			timer = 30 - timePassed;
		}
	}
	
	/** A method that sets the boolean runTimer to either true or false. 
	 * 
	 * @param b Sets the runTimer to True or False
	 */
	public void setRunTimer(boolean b) {
		runTimer = b;
	}
	
	/** A method that sets the timer to an integer. 
	 * 
	 * @param t Sets the timer 
	 */
	public void setTimer(int t) {
		timer = t;
	}

	/** A method that resets the timer back to 30. 
	 */
	public void resetTimer() {
		this.timer = 30;
		this.timeLeft = 30;	 
		this.timePassed = 1; 
		this.runTimer = false;
		this.timerStarted = false;
	}

	/** A method that returns the models pointer. 
	 * 
	 * @return pointer the model's Pointer
	 */
	public Pointer getPointer() {
		return pointer;
	}
	
	/** A method that sets the pointer. 
	 * 
	 * @param p Sets the Pointer
	 */
	public void setPointer(Pointer p) {
		this.pointer = p;
	}
	
	/** A method that checks the pointer's direction and decides which way to shift
	 * the pointer for the measurement minigame. If the direction is equal to right,
	 * the method will call shiftPointerR(). If the direction is equal to left, the
	 * method will call shiftPointerL(). 
	 */
	public void movePointer() {
		if (pointer.d == Direction.RIGHT) {
			shiftPointerR();
		}
		if (pointer.d == Direction.LEFT) {
			shiftPointerL();
		}
	}
	
	/** A method that shifts the pointer to the Right based on a certain range.
	 */
	public void shiftPointerR() {
		if (pointer.x <= (startPointer.getX() + (screenWidth * .05))) {
			this.pointer.x += pointerinc;
			this.pointer.reading += readinginc;
		}
		else {
			pointer.d = Direction.LEFT;
		}
	}
	
	/** A method that shifts the pointer to the Left based on a certain range.
	 */
	public void shiftPointerL() {
		if (pointer.x > (startPointer.getX() - (screenWidth * .05))) {
		this.pointer.x -= pointerinc;
		this.pointer.reading -= readinginc;
		}
		else {
			pointer.d = Direction.RIGHT;
			}
		}
	
	/** A method that resets the pointer to the new object's length during the
	 * measurement game. If the new object is trash, that object is removed 
	 * from the caught list. 
	 */
	public void resetPointer(){
		if (caught.isEmpty() == false) {
		Entities last = caught.get(caught.size() - 1);
		
		while ((last instanceof Trash) && (caught.isEmpty() == false)) {
			caught.remove(caught.size() - 1);
			if (caught.isEmpty() == false) {
				last = caught.get(caught.size() - 1);
			}
		}
			startPointer.setX((last.getLength() * scaleLength) + pointerzero);
			startPointer.setReading(last.getLength());
			if (pointer.x == 0) {
				pointer.reading = startPointer.getReading();
				pointer.x = startPointer.getX();
			}
		}
	}
	
	/** A method that updates the quantityCaught array with the amounts of each of the Entities
	 * in the model's caught ArrayList. Loggerhead count is at position 0, horseshoe crab is at 1,
	 * blue crab is at 2, weakfish is at 3, and trash is at 4. 
	 */
	public void quantityCaught() {
		int logger = 0;
		int trash = 0;
		int horse = 0;
		int crab = 0;
		int fish = 0;

		if (caught != null) {
			for (Entities e : caught) {
				if (e instanceof Loggerhead) {
					logger++;

				}
				else if (e instanceof BlueCrab) {
					crab++;

				}
				else if (e instanceof HorseshoeCrab) {
					horse++;

				}
				else if (e instanceof Weakfish) {
					fish++;

				}
				else 
					trash++; }}

		quantityCaught[0] = logger;
		quantityCaught[1] = horse;
		quantityCaught[2] = crab;
		quantityCaught[3] = fish;
		quantityCaught[4] = trash;
	}

	/** A method that sets up the mapping game by choosing which fact will be displayed, 
	 * based on the amount of specific objects caught during the game. It then randomly decides 
	 * which fact will be set to which spot in the factLocation array of points.
	 */
	public void mapSetUp() {
		factDisplayed[0] = quantityCaught[0] % 2;
		factDisplayed[1] = quantityCaught[1] % 2;
		factDisplayed[2] = quantityCaught[2] % 2;
		factDisplayed[3] = quantityCaught[3] % 2;
		
		while(factLocations.size() < 4) {
			factLocations.add(new Point());
		}
		
		Random r = new Random();
		int x = r.nextInt(4);
		for(int i = 0; i < 4; i++) {
			if (x < 3) {
				x++;
			}
			else {
				x = 0;
			}
			if (x == 0) {
				factLocations.set(i, map.location1.getLocation());
			}
			if (x == 1) {
				factLocations.set(i, map.location2.getLocation());
			} 
			if (x == 2) {
				factLocations.set(i, map.location3.getLocation());
			}
			if  (x == 3){
				factLocations.set(i, map.location4.getLocation());
			}
		}
	}
	
	/** A method that compares the animals dragged position location to that of their fact location. If the position is in the
	 * correct range, a boolean will be switched true for that animal, telling the controller that the player got the answer
	 * correct. 
	 */
	
	public void compareMap() {
		int xpadding = (int)(screenWidth * .2);
		int ypadding = (int)(screenHeight * .15);
		if ((factLocations.get(0).x > (mapAnimals.get(0).getX() - xpadding)) && (factLocations.get(0).x < (mapAnimals.get(0).getX() + xpadding))
				&& (factLocations.get(0).y > (mapAnimals.get(0).getY() - ypadding)) && (factLocations.get(0).y < (mapAnimals.get(0).getY() + ypadding))){
			mappedAnimals[0] = true;
		}
		if ((factLocations.get(1).x > (mapAnimals.get(1).getX() - xpadding)) && (factLocations.get(1).x < (mapAnimals.get(1).getX() + xpadding))
				&& (factLocations.get(1).y > (mapAnimals.get(1).getY() - ypadding)) && (factLocations.get(1).y < (mapAnimals.get(1).getY() + ypadding))){
			mappedAnimals[1] = true;
		}
		if ((factLocations.get(2).x > (mapAnimals.get(2).getX() - xpadding)) && (factLocations.get(2).x < (mapAnimals.get(2).getX() + xpadding))
				&& (factLocations.get(2).y > (mapAnimals.get(2).getY() - ypadding)) && (factLocations.get(2).y < (mapAnimals.get(2).getY() + ypadding))){
			mappedAnimals[2]= true;
		}
		if ((factLocations.get(3).x > (mapAnimals.get(3).getX() - xpadding)) && (factLocations.get(3).x < (mapAnimals.get(3).getX() + xpadding))
				&& (factLocations.get(3).y > (mapAnimals.get(3).getY() - ypadding)) && (factLocations.get(3).y < (mapAnimals.get(3).getY() + ypadding))){
			mappedAnimals[3] = true;
		}
	}
	
	/** A method that is used to adjust the users score based on the mapping minigame.
	 * The score adjustment is based on the boolean updated by compareMap().
	 * 
	 * @return mappingScore, an int that is the score in the mapping game
	 */
	public int mappingScore() {
		mappingScore = 0;
		for(int i = 0; i < 4; i++) {
			if (mappedAnimals[i] == true) {
				mappingScore++;
			}
		}
		return mappingScore;
	}
		
	
	/** A method used to move the animals to their correct location. If the animal is clicked and dragged to
	 * the correct location, the animal's location will be moved.  Or else, it will return to its original location.
	 */
	public void moveAnimalsMap() {
		int xpadding = (int)(screenWidth * .1);
		int ypadding = (int)(screenHeight * .1);
		
		if ((clickx > (mapAnimals.get(0).getX())) && (clickx < (mapAnimals.get(0).getX() + xpadding))
				&& (clicky > (mapAnimals.get(0).getY())) && (clicky < (mapAnimals.get(0).getY() + ypadding))){
			
			if ((releasex > (factLocations.get(0).x - (xpadding / 3))) && (releasex < (factLocations.get(0).x  + (xpadding * 2)))
					&& (releasey > (factLocations.get(0).y - (ypadding / 3))) && (releasey < (factLocations.get(0).y  + (ypadding * 2)))){
				mapAnimals.get(0).setX(factLocations.get(0).x );
				mapAnimals.get(0).setY(factLocations.get(0).y );
			}
		}
		 
		if ((clickx > (mapAnimals.get(1).getX())) && (clickx < (mapAnimals.get(1).getX() + xpadding))
				&& (clicky > (mapAnimals.get(1).getY())) && (clicky < (mapAnimals.get(1).getY() + ypadding))){
			
			if ((releasex > (factLocations.get(1).x - (xpadding / 3))) && (releasex < (factLocations.get(1).x  + (xpadding * 2)))
					&& (releasey > (factLocations.get(1).y - (ypadding / 3))) && (releasey < (factLocations.get(1).y  + (ypadding * 2)))){
				mapAnimals.get(1).setX(factLocations.get(1).x );
				mapAnimals.get(1).setY(factLocations.get(1).y );
			}
		}
		
		if ((clickx > (mapAnimals.get(2).getX())) && (clickx < (mapAnimals.get(2).getX() + xpadding))
				&& (clicky > (mapAnimals.get(2).getY())) && (clicky < (mapAnimals.get(2).getY() + ypadding))){
			
			if ((releasex > (factLocations.get(2).x -(xpadding / 3))) && (releasex < (factLocations.get(2).x  + (xpadding * 2)))
					&& (releasey > (factLocations.get(2).y - (ypadding / 3))) && (releasey < (factLocations.get(2).y  + (ypadding * 2)))){
				mapAnimals.get(2).setX(factLocations.get(2).x );
				mapAnimals.get(2).setY(factLocations.get(2).y );
			}
		}
		
		if ((clickx > (mapAnimals.get(3).getX())) && (clickx < (mapAnimals.get(3).getX() + xpadding))
				&& (clicky > (mapAnimals.get(3).getY())) && (clicky < (mapAnimals.get(3).getY() + ypadding))){
			
			if ((releasex > (factLocations.get(3).x - (xpadding / 3))) && (releasex < (factLocations.get(3).x  + (xpadding * 2)))
					&& (releasey > (factLocations.get(3).y - (ypadding / 3))) && (releasey < (factLocations.get(3).y  + (ypadding * 2)))){
				mapAnimals.get(3).setX(factLocations.get(3).x );
				mapAnimals.get(3).setY(factLocations.get(3).y );
			}
		}
	}
	
	/**
	 * Simple getter method to acquire the ArrayList of Entities displayed during fishing game

	 * @return objs, the ArrayList of Entities on the screen
	 */
	public ArrayList<Entities> getObjs(){
		return objs;
	}
	
	/**
	 * Simple getter method to acquire the total score

	 * @return score, an int representing the total score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Simple getter method to acquire the time on the timer

	 * @return timer, an int representing the time on the timer
	 */
	public int getTimer() {
		return timer;
	}
	
	/**
	 * Simple getter method to acquire the quantity of each entity caught

	 * @return quantityCaught, an int[] with each int representing the quantity of each entity caught
	 */
	public int[] getQuantityCaught() {
		return quantityCaught;
	}
	
	/**
	 * Simple getter method to acquire the last caught entity

	 * @return lastCaught, a String of the name of the last caught entity in the fishing game
	 */
	public String getLastCaught() {
		return lastCaught;
	}
	
	/**
	 * Simple getter method to acquire all caught Entities

	 * @return caught, the ArrayList of all caught Entities
	 */
	public ArrayList<Entities> getCaught(){
		return caught;
	}
	
	/**
	 * Simple getter method to acquire all measured Entities

	 * @return measured, the ArrayList of all Entities that have been successfully measured
	 */
	public ArrayList<Entities> getMeasured(){
		return measured;
	}
	
	/**
	 * Simple getter method to acquire the array that determines which fact to display

	 * @return factDisplayed, an int[] that correlates with the fact image to display
	 */
	public int[] getFactDisplayed() {
		return factDisplayed;
	}
	
	/**
	 * Simple getter method to acquire the points where facts will be displayed on the map

	 * @return factLocations, an ArrayList of Points where facts will be displayed on the map
	 */
	public ArrayList<Point> getFactLocations(){
		return factLocations;
	}
	
	/**
	 * Simple getter method to acquire the ArrayList of Entities that will be displayed on the map

	 * @return mapAnimals, the ArrayList of Entities that are displayed on the map
	 */
	public ArrayList<Entities> getMapAnimals(){
		return mapAnimals;
	}
	
	/**
	 * Simple getter method to acquire whether an Animal has been successfully mapped

	 * @return mappedAnimals, an boolean[] that is true if an Animal has been successfully mapped
	 */
	public boolean[] getMappedAnimals() {
		return mappedAnimals;
	}
	
}
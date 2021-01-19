package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Models.Direction;
import Models.Entities;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;


/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */

@SuppressWarnings("serial")
public class ViewR extends JPanel implements java.io.Serializable{
	
	// screen dimensions
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int screenWidth = screenSize.width;
	public int screenHeight = screenSize.height;
	
	//fonts
	Font f = new Font("Dialog", Font.PLAIN, (int)(screenHeight * .033));
	Font f2 = new Font("Dialog", Font.BOLD, (int)(screenHeight * .075));
	Font f3 = new Font("Dialog", Font.PLAIN, (int)(screenHeight * .02));
	
	int score = 0;
	public int timer = 30;
	ArrayList<Entities> objs;
	int[] caughtNums;

	// display booleans
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
	
	String lastCaught; // name of last caught object
	
	// fact images
	int loggerfact = 0;
	int crabfact = 2;
	int fishfact = 4;
	int horsefact = 6;


	// images
    BufferedImage[] waterpics;
    BufferedImage[] boatpics;
    BufferedImage[] crabpics;
    BufferedImage[] rpics;
    BufferedImage[] loggerpics;
    BufferedImage[] netpics;
    BufferedImage[] polepics;
    BufferedImage[] fishpics;
    BufferedImage[] horseshoepics;
    BufferedImage[] factpics;
    BufferedImage[] mappingpics;
    BufferedImage[] otherpics;
    BufferedImage startpic;
    BufferedImage startbutton;
    BufferedImage letsgopic;
    BufferedImage rachelpic;
    BufferedImage rachelboatpic;
    BufferedImage rachelwavepic; 
    BufferedImage fact;
    BufferedImage estuarypic;
    BufferedImage labpic;
    BufferedImage rulerpic;
    BufferedImage pointerpic;
	BufferedImage continuepic;
	BufferedImage continuepic2;
	BufferedImage sealettuce;
	BufferedImage labHorsePic;
	BufferedImage labFishPic;
	BufferedImage labLoggerPic;
	BufferedImage labCrabPic;
	BufferedImage rachelBubble1;
	BufferedImage paperpic;
	BufferedImage eddielabpic;
	BufferedImage stewpic;
	BufferedImage sandpic;
	BufferedImage skypic;
	BufferedImage tutBubble1;
	BufferedImage tutBubble2;
	BufferedImage tutBubble3;
	BufferedImage tutBubble4;
	BufferedImage tutBubble5;
	BufferedImage loggericon;
	BufferedImage fishicon;
	BufferedImage crabicon;
	BufferedImage horseicon;
	BufferedImage mtutBubble1;
	BufferedImage mtutBubble2;
	BufferedImage mtutBubble3;
	BufferedImage MapExplain;
	BufferedImage MapNext;
	BufferedImage LastBackground;
	BufferedImage Rbye;
	BufferedImage Sbye;
	BufferedImage Ebye;
	BufferedImage oopspic;
	
    int waterPicNum = 0;
    int crabPicNum = 0;
    int loggerPicNum = 0;
    int fishPicNum = 0;
    int horsePicNum = 0;
    int rPicNum = 1;
    int netPicNum = 1;

    
    // boat
	int boatx = (int)(screenWidth * .1);
	int boaty = (int)(screenHeight * .3);
    int boatxinc;
    int boatPicNum = 1;
    Direction boatDirection;
 
    // net
    int netx = (int)(screenWidth * .1);
    int nety = (int)(screenHeight * .35);
    
    // pole
    int poleLength = (int)(screenHeight*.02);
    int poley = (int)(screenHeight * .35);
     
    //rachel
    int rx = boatx + (int)(screenWidth * .05);
    int ry = boaty - (int)(screenHeight * .08);
    
	//ruler
	int rux = (int)(screenWidth * .35);
	int ruy = (int)(screenHeight * .53);
	int animalx = rux + (int)(screenWidth * .002);

	// pointer
	double pox;
	int poy = (int)(screenHeight * .5);
	double reading = 0.0;
	
	// mouse 
	public boolean dragging = false;
	public int clickx = -1;
	public int clicky = -1;
	public int dragx = -1;
	public int dragy = -1;
	public int releasex = -1;
	public int releasey = -1;
	public boolean released = false;
	
	// mapping constants
	int horsex = (int)(screenWidth * .25);
	int horsey = (int)(screenHeight * .7);
	int crabx = (int)(screenWidth * .12);
	int craby = (int)(screenHeight * .7);
	int fishx = (int)(screenWidth * .25);
	int fishy = (int)(screenHeight * .82);
	int loggerx = (int)(screenWidth * .12);
	int loggery = (int)(screenHeight * .82);
	int[] factDisplayed;
	ArrayList<Point> factLocations;
	ArrayList<Entities> mapAnimals;
	boolean[] mappedAnimals;
	
	// tutorial booleans
	public boolean tutorialstarted = true;
	public boolean tutleftright = false;
	public boolean tutdown = false;
	public boolean tutup = false;
	public boolean tutcaughtfirst = false;
	
	ArrayList<Entities> caught;
	ArrayList<String> notepad;
	public ArrayList<Entities> measured;
 
	public JFrame frame;
	
	/**
	 * A public constructor for ViewR that creates multiple BufferedImages for our
	 * game, as well as scaling those BufferedImages. It also sets setFocusable to true
	 * and it sets setFocusTraversalKeysEnabled to false. Lastly, it creates a new Jframe().
	 */
	public ViewR() {		

    	this.setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        frame = new JFrame();
      
        //loads water images
		waterpics = new BufferedImage[24];
    	for(int i = 1; i < 25; i++) {
    		BufferedImage img = createImage("images/waterImages/" + i + ".png");
    		img = getScaledImage(img, screenWidth, screenHeight);
    		waterpics[i-1] = img;
    	}
    	//loads boat images
    	boatpics = new BufferedImage[2];
    	for (int j = 1; j < 3; j++) {
    		BufferedImage boatimg = createImage("images/boatImages/" + j + ".png");
    		boatimg = getScaledImage(boatimg, (int)(screenWidth*.2), (int)(screenHeight*.2));
    		boatpics[j-1] = boatimg;
    	}
    	
    	//loads blue crab images
    	crabpics = new BufferedImage[6];
    	for (int i = 1; i < 7; i++) {
    		BufferedImage crabimg = createImage("images/crabImages/" + i + ".png");
    		crabimg = getScaledImage(crabimg, (int)(screenWidth*.1), (int)(screenHeight*.1));
    		crabpics[i-1] = crabimg;
    	}
    	
    	//loads weakfish images
    	fishpics = new BufferedImage[6];
    	for (int i = 1; i < 7; i++) {
    		BufferedImage fishimg = createImage("images/weakfishImages/" + i + ".png");
    		fishimg = getScaledImage(fishimg, (int)(screenWidth*.1), (int)(screenHeight*.1));
    		fishpics[i-1] = fishimg;
    	}
    	
    	//loads loggerhead images
    	loggerpics = new BufferedImage[6];
    	for (int i = 1; i < 7; i++) {
    		BufferedImage loggerimg = createImage("images/loggerheadImages/" + i + ".png");
    		loggerimg = getScaledImage(loggerimg, (int)(screenWidth*.1), (int)(screenHeight*.1));
    		loggerpics[i-1] = loggerimg;
    	}
    	//loads horseshoe crab images
    	horseshoepics = new BufferedImage[6];
    	for (int i = 1; i < 7; i++) {
    		BufferedImage horseimg = createImage("images/horseshoeImages/" + i + ".png");
    		horseimg = getScaledImage(horseimg, (int)(screenWidth*.1), (int)(screenHeight*.1));
    		horseshoepics[i-1] = horseimg;
    	}
    	
    	//loads net images
    	netpics = new BufferedImage[2];
    	for (int i = 1; i < 3; i++) {
    		BufferedImage netpic = createImage("images/netImages/" + i + ".png");
    		netpic = getScaledImage(netpic, (int)(screenWidth*.1), (int)(screenHeight*.1));
    		netpics[i-1] = netpic;
    	}
    	//loads pole images
    	polepics = new BufferedImage[1];
    	for (int i = 1; i < 2; i++) {
    		BufferedImage polepic = createImage("images/poleImages/" + i + ".png");
    		polepic = getScaledImage(polepic, (int)(screenWidth*.1), poleLength);
    		polepics[i-1] = polepic;
    	}

    	//loads fact bubble images
    	factpics = new BufferedImage[8];
    	for (int i = 1; i < 3; i++) {
    		BufferedImage factpic = createImage("images/facts/logger" + i + ".png");
    		factpic = getScaledImage(factpic, (int)(screenWidth * .25), (int)(screenHeight * .3));
    		factpics[i - 1] = factpic;
    		factpic = createImage("images/facts/crab" + i + ".png");
    		factpic = getScaledImage(factpic, (int)(screenWidth * .25), (int)(screenHeight * .3));
    		factpics[i + 1] = factpic;
    		factpic = createImage("images/facts/fish" + i + ".png");
    		factpic = getScaledImage(factpic, (int)(screenWidth * .25), (int)(screenHeight * .3));
    		factpics[i + 3] = factpic;
    		factpic = createImage("images/facts/horse" + i + ".png");
    		factpic = getScaledImage(factpic, (int)(screenWidth * .25), (int)(screenHeight * .3));
    		factpics[i + 5] = factpic;
    	}
    	
    	// loads fact images for mapping
    	mappingpics = new BufferedImage[8];
    	for (int i = 1; i < 3; i++) {
    		BufferedImage mappingpic = createImage("images/mappingImages/logger" + i + ".png");
    		mappingpic = getScaledImage(mappingpic, (int)(screenWidth * .125), (int)(screenHeight * .15));
    		mappingpics[i - 1] = mappingpic;
    		mappingpic = createImage("images/mappingImages/horse" + i + ".png");
    		mappingpic = getScaledImage(mappingpic, (int)(screenWidth * .125), (int)(screenHeight * .15));
    		mappingpics[i + 1] = mappingpic;
    		mappingpic = createImage("images/mappingImages/crab" + i + ".png");
    		mappingpic = getScaledImage(mappingpic, (int)(screenWidth * .125), (int)(screenHeight * .15));
    		mappingpics[i + 3] = mappingpic;
    		mappingpic = createImage("images/mappingImages/fish" + i + ".png");
    		mappingpic = getScaledImage(mappingpic, (int)(screenWidth * .125), (int)(screenHeight * .15));
    		mappingpics[i + 5] = mappingpic;
    	}
    	
    	// loads other miscellaneous images
		otherpics = new BufferedImage[35];
		for (int i = 0; i < 35; i++) {
			BufferedImage otherpic = createImage("images/otherImages/" + i + ".png");
			otherpics[i] = otherpic;
		}
    	
    	
    	// people images
    	stewpic = getScaledImage(otherpics[12], (int)(screenWidth * .15), (int)(screenHeight * .5));
		rachelpic = otherpics[14];
    	rachelboatpic = getScaledImage(rachelpic, (int)(screenWidth*.1), (int)(screenHeight*.25));
    	rachelwavepic = otherpics[15];
    	eddielabpic = getScaledImage(otherpics[17], (int)(screenWidth * .4), (int)(screenHeight * .8));
    	
    	//fishing game images
    	sealettuce = getScaledImage(otherpics[7], (int)(screenWidth * .1), (int)(screenHeight * .1));
    	sandpic = getScaledImage(otherpics[23], screenWidth, (int)(screenHeight * .15));
    	skypic = getScaledImage(otherpics[24], screenWidth, (int)(screenHeight * .6));
    	
    	// images for fishing tutorial
		tutBubble1 = getScaledImage(otherpics[18], (int) (screenWidth * .4), (int) (screenHeight * .3));
		tutBubble2 = getScaledImage(otherpics[19], (int) (screenWidth * .4), (int) (screenHeight * .3));
		tutBubble3 = getScaledImage(otherpics[20], (int) (screenWidth * .4), (int) (screenHeight * .3));
		tutBubble4 = getScaledImage(otherpics[21], (int) (screenWidth * .4), (int) (screenHeight * .3));
		tutBubble5 = getScaledImage(otherpics[22], (int) (screenWidth * .4), (int) (screenHeight * .3));
    	
		// images for measuring tutorial;
    	mtutBubble1 = getScaledImage(otherpics[25], (int)(screenWidth * .27), (int)(screenHeight * .27));
    	mtutBubble2 = getScaledImage(otherpics[26], (int)(screenWidth * .27), (int)(screenHeight * .27));
    	mtutBubble3 = getScaledImage(otherpics[27], (int)(screenWidth * .27), (int)(screenHeight * .27));
    	
    	// lab images
		labpic = createImage("images/LabImages/labbackground.png");
		labpic = getScaledImage(labpic, screenWidth, screenHeight);
		rulerpic = createImage("images/LabImages/RulerShaft.png");
		rulerpic = getScaledImage(rulerpic, (int)(screenWidth*.55), (int)(screenHeight*.25));
		pointerpic = createImage("images/LabImages/arrow.png");
		pointerpic = getScaledImage(pointerpic, (int)(screenWidth *.05), (int)(screenHeight * .2));
		paperpic = getScaledImage(otherpics[13], (int)(screenWidth * .2), (int)(screenHeight*.5));
    	labCrabPic = otherpics[0];
    	labHorsePic = otherpics[1];
    	labLoggerPic = otherpics[2];
    	labFishPic = otherpics[3];
		
    	// mapping images
		loggericon = getScaledImage(loggerpics[2], (int)(loggerpics[2].getWidth() / 4), (int)(loggerpics[2].getHeight() / 3)); 
		horseicon = getScaledImage(horseshoepics[2], (int)(horseshoepics[2].getWidth() / 4), (int)(horseshoepics[2].getHeight() / 3)); 
		crabicon = getScaledImage(crabpics[2], (int)(crabpics[2].getWidth() / 4), (int)(crabpics[2].getHeight() / 3)); 
		fishicon = getScaledImage(fishpics[2], (int)(fishpics[2].getWidth() / 4), (int)(fishpics[2].getHeight() / 3));
		
		// other images
       	startpic = getScaledImage(otherpics[8], screenWidth, screenHeight);
    	letsgopic = getScaledImage(otherpics[4], screenWidth, screenHeight);
    	continuepic = getScaledImage(otherpics[5], (int)(screenWidth * .2), (int)(screenHeight* .2));
    	continuepic2 = getScaledImage(otherpics[6], (int)(screenWidth * .2), (int)(screenHeight * .1));
    	estuarypic = getScaledImage(otherpics[10], screenWidth, screenHeight);
    	startbutton = getScaledImage(otherpics[11], (int)(screenWidth * .2), (int)(screenHeight * .3));
    	rachelBubble1 = getScaledImage(otherpics[16], (int)(screenWidth * .27), (int)(screenHeight * .27));
    	MapExplain = getScaledImage(otherpics[28], (int) (screenWidth * .27), (int) (screenHeight * .27));
		MapNext = getScaledImage(otherpics[29], (int) (screenWidth * .27), (int) (screenHeight * .27));
		LastBackground = getScaledImage(otherpics[30], screenWidth, screenHeight);
		Sbye = getScaledImage(otherpics[31], (int) (screenWidth * .27), (int) (screenHeight * .27));
		Ebye = getScaledImage(otherpics[32], (int) (screenWidth * .27), (int) (screenHeight * .27));
		Rbye = getScaledImage(otherpics[33], (int) (screenWidth * .27), (int) (screenHeight * .27));
		oopspic = getScaledImage(otherpics[34], (int) (screenWidth * .27), (int) (screenHeight * .27));
	
	}

	   /** A method that is used to create an image from a filename
	    * 
	    * @param file, a String file used to create an image
	    * @return bufferedImage, if the file is readable. Otherwise it will return null.
	    */
    private BufferedImage createImage(String file){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(file));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
  
    /** 
     * A function that is used to find out the scale for the images based 
     * off of screen size.
     * 
     * @param srcImg, the Image being scaled
     * @param w, the width being used
     * @param h, the height being used
     * @return resizedImg that is better scaled.
     */
	private BufferedImage getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}




	  /**
	    * A function that draws all objects on the screen. We used booleans in this function
	    * in order to determine which part of the game a user was approaching or currently on.
	    *
	    * @param g, The graphics that allow us to draw
	    * 
	    */
	public void paint(Graphics g) {

    	waterPicNum = (waterPicNum + 1) % 24;
    	crabPicNum = (crabPicNum + 1) % 3;
    	loggerPicNum = (loggerPicNum + 1) % 3;
    	fishPicNum = (fishPicNum + 1) % 3;
    	horsePicNum = (horsePicNum + 1) % 3;
    	polepics[0] = getScaledImage(polepics[0], (int)(screenWidth*.1), poleLength);
    	

    	// draws start screen
    	if (displayStartScreen) {
    		g.drawImage(startpic, 0, 0, Color.yellow, this);
    	}
    	
    	
    	// after player presses start, draws "let's go" screen
    	if (displayLetsGoScreen) {
    		g.drawImage(letsgopic, 0, 0, this);
    	}
    	
    	
    	// after player clicks "Let's go", fishing game starts
    	if((timer > 0) && (displayFishingScreen == true)) {

    	g.drawImage(skypic, 0, 0, this);
    	g.drawImage(waterpics[waterPicNum], 0, 0, this);
    	g.drawImage(sandpic, 0, (int)(screenHeight * .85), this);
    	g.drawImage(rachelboatpic, rx,  ry, this);
    	g.drawImage(boatpics[boatPicNum], boatx, boaty, this);
    	g.drawImage(netpics[netPicNum], netx, nety, this);
    	g.drawImage(polepics[0], netx, poley, this);
    	
    	
    	g.setFont(f);
    	g.drawString("ESTUARY EXPEDITION", (int)(screenWidth * .01), (int)(screenHeight * .04));
    	g.drawString("Score: " + score + "", (int)(screenWidth * .01), (int)(screenHeight * .08));
    	g.drawString("Time: " + timer + "", (int)(screenWidth * .01), (int)(screenHeight * .12));

    	if ((tutorialstarted == true) && (tutleftright  == false) && (tutdown == false) && (tutup == false) && (tutcaughtfirst == false)){
			g.drawImage(tutBubble1, (rx+ (int)(screenWidth * .026)), (ry-(int)(screenHeight * .185)) , this);
			
		}
    	// if (tutleftright  == true){// && (tutorialstarted == false)) {
    	if ((tutorialstarted == false) && (tutleftright  == true) && (tutdown == false) && (tutup == false) && (tutcaughtfirst == false)) {
    		g.drawImage(tutBubble2, (rx+ (int)(screenWidth * .026)), (ry-(int)(screenHeight * .185)) , this);
    	}


    	//if  (tutdown == true) {
    	if ((tutorialstarted == false) && (tutleftright  == false) && (tutdown == true) && (tutup == false) && (tutcaughtfirst == false)) {
    		g.drawImage(tutBubble3, (rx+ (int)(screenWidth * .026)), (ry-(int)(screenHeight * .185)) , this);
    	}

    	// if (tutup == true){
    	if ((tutorialstarted == false) && (tutleftright  == false) && (tutdown == false) && (tutup == true) && (tutcaughtfirst == false)) {
    		g.drawImage(tutBubble4, (rx+ (int)(screenWidth * .026)), (ry-(int)(screenHeight * .185)) , this);
}
    	//if (tutcaughtfirst == true) {
    	if ((tutorialstarted == false) && (tutleftright  == false) && (tutdown == false) && (tutup == false) && (tutcaughtfirst == true)) {
    		g.drawImage(tutBubble5, (rx+ (int)(screenWidth * .026)), (ry-(int)(screenHeight * .185)) , this);
    	}
    	

    	//displays the correct image depending on which entities are on the field
    	for (Entities e : objs) {
    		if ((e.getName() == "Blue Crab") && (e.getFlow() == Direction.RIGHT)) {
    			g.drawImage(crabpics[crabPicNum], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Blue Crab") && (e.getFlow() == Direction.LEFT)) {

    			g.drawImage(crabpics[crabPicNum + 3], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Horseshoe Crab") && (e.getFlow() == Direction.RIGHT)) {
    			g.drawImage(horseshoepics[horsePicNum], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Horseshoe Crab") && (e.getFlow() == Direction.LEFT)) {
    			g.drawImage(horseshoepics[horsePicNum + 3], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Loggerhead") && (e.getFlow() == Direction.RIGHT)) {
    			g.drawImage(loggerpics[loggerPicNum], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Loggerhead") && (e.getFlow() == Direction.LEFT)) {
    			g.drawImage(loggerpics[loggerPicNum + 3], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Weakfish") && (e.getFlow() == Direction.RIGHT)) {
    			g.drawImage(fishpics[fishPicNum], e.getX(), e.getY(), this);  
    			}
    		if ((e.getName() == "Weakfish") && (e.getFlow() == Direction.LEFT)) {
    			g.drawImage(fishpics[fishPicNum + 3], e.getX(), e.getY(), this);  
    			}
    		if (e.getName() == "Trash"){
    			g.drawImage(sealettuce, e.getX(), e.getY(), this);
    		}
    		else {
    		}
    	}
    	
    	// displays a fact about the Animal that was caught most recently
    	if (caught.isEmpty() == false) {
    		switch(lastCaught) {
    		case "Loggerhead" : int loggerfact = caughtNums[0] % 2;
    		g.drawImage(factpics[loggerfact], (int)(screenWidth - factpics[loggerfact].getWidth()), (int)(screenHeight *.65), this);
    		break;
    		
    		case "Blue Crab" : int crabfact = (caughtNums[2] % 2) + 2;
			g.drawImage(factpics[crabfact], (int)(screenWidth - factpics[crabfact].getWidth()), (int)(screenHeight *.65), this);
			break;
			
    		case "Weakfish" : int fishfact = (caughtNums[3] % 2) + 4;
    		g.drawImage(factpics[fishfact], (int)(screenWidth - factpics[fishfact].getWidth()), (int)(screenHeight *.65), this);
    		break;
    		
    		case "Horseshoe Crab" : int horsefact = (caughtNums[1] % 2) + 6;
    		g.drawImage(factpics[horsefact], (int)(screenWidth - factpics[horsefact].getWidth()), (int)(screenHeight *.65), this);
    		break;
    		
    		default:
    			break;
    		}
    	}
 	}
    	
    	// displays number of each object caught
    	if ((timer <= 0) && (displayFishingScreen == true)){
    		g.drawImage(estuarypic, 0, 0, this);
    		g.setFont(f2);
    		FontMetrics fm = g.getFontMetrics();
    	    int w = fm.stringWidth("Good Job!  You Caught:");
    	    g.drawString("Good Job!  You Caught:", ((screenWidth / 2) - (w/2)), (int)(screenHeight * .15));
    	    g.drawString(caughtNums[0] + " Loggerheads", (int)(screenWidth * .3), (int)(screenHeight * .3));
    	    g.drawString(caughtNums[1] + " Horseshoe Crabs", (int)(screenWidth * .3), (int)(screenHeight * .4));
    	    g.drawString(caughtNums[2] + " Blue Crabs", (int)(screenWidth * .3), (int)(screenHeight * .5));
    	    g.drawString(caughtNums[3] + " Weakfish", (int)(screenWidth * .3), (int)(screenHeight * .6));
    	    g.drawString(caughtNums[4] + " Seaweed", (int)(screenWidth * .3), (int)(screenHeight * .7));
    	    g.drawString("Your score is " + score +"!", (int)(screenWidth * .25), (int)(screenHeight *.85));
    	    g.drawImage(loggerpics[1], (int)(screenWidth * .2), (int)(screenHeight * .2), this);
    	    g.drawImage(horseshoepics[1], (int)(screenWidth * .2), (int)(screenHeight * .3), this);
    	    g.drawImage(crabpics[2], (int)(screenWidth * .2), (int)(screenHeight * .4), this);
    	    g.drawImage(fishpics[1], (int)(screenWidth * .2), (int)(screenHeight * .5), this);
    	    g.drawImage(sealettuce, (int)(screenWidth * .2), (int)(screenHeight * .6), this);
    	    g.drawImage(continuepic, (int)(screenWidth * .73), (int)(screenHeight * .6), this);
    	}
    	
    	// displays Lab scene
		if (clickedContinue == true){
			g.drawImage(labpic, 0, 0, this);
			g.drawImage(rulerpic, rux, ruy, this);
			g.drawImage(pointerpic, (int)pox, poy, this);
			rachelpic = getScaledImage(rachelpic, (int)(screenWidth * .2), (int)(screenHeight * .8));
			g.drawImage(rachelpic, (int)(screenWidth * .05), (int)(screenHeight * .3), this);
	
			// displays instructions from Rachel and start button
			if (startedMeasuring == false) {
				g.drawImage(rachelBubble1, (int)(screenWidth * .21), (int)(screenHeight * .15), this);
				g.drawImage(startbutton, (int)(screenWidth * .6), (int)(screenHeight * .15), this);
			}
			
			// after player clicks start, displays measuring game
			if (startedMeasuring == true) {
				g.setFont(f2);
				g.drawString("Time: " + timer + "", (int)(screenWidth * .008), (int)(screenHeight * .07));
				g.setFont(f);
				g.drawString("Animals Measured: " + measured.size(), (int)(screenWidth * .008), (int)(screenHeight * .12));
				g.drawString("Score: " + score, (int)(screenWidth * .008), (int)(screenHeight * .17));
				int noteX = (int)(screenWidth *.27);
				g.setFont(f3);
				g.drawImage(paperpic, (int)(screenWidth *.24), (int)(screenHeight * .02), this);
				g.drawString("Blackbird Creek Survey Data", noteX, (int)(screenHeight * .045));
				if (measured.isEmpty() == false) {
				for(Entities e : measured) {
					int i = measured.indexOf(e);
					int noteY = (int)((i * screenHeight * .0219) + (screenHeight * .082));
					g.drawString(e.getName() + ": " + e.getLength() + " inches", noteX, noteY);
				}
			}
			if (caught.isEmpty() == false) {
				int i = caught.size() - 1;
			if (caught.get(i).getName() == "Trash") {
				while((i > 0) &&(caught.get(i).getName() == "Trash")){
					i--;
				}	
			}
			if (caught.get(i).getName() == "Blue Crab") {
				labCrabPic = getScaledImage(labCrabPic, (int)(caught.get(i).getLength() * screenWidth * .0152), (int)(screenHeight * .1));
						g.drawImage(labCrabPic, animalx, (int)(screenHeight * .61), this);
					}
			else if (caught.get(i).getName() == "Horseshoe Crab") {
					labHorsePic = getScaledImage(labHorsePic, (int)(caught.get(i).getLength() * screenWidth * .0152), (int)(screenHeight*.1));
						g.drawImage(labHorsePic, animalx, (int)(screenHeight * .63), this);
					}
			else if (caught.get(i).getName() == "Loggerhead") {
				labLoggerPic = getScaledImage(labLoggerPic, (int)(caught.get(i).getLength() * screenWidth * .0152), (int)(screenHeight*.17));
						g.drawImage(labLoggerPic, animalx, (int)(screenHeight * .64), this);
						}
			else if (caught.get(i).getName() == "Weakfish") {
				labFishPic = getScaledImage(labFishPic, (int)(caught.get(i).getLength() * screenWidth * .015), (int)(screenHeight*.18));
						g.drawImage(labFishPic, animalx, (int)(screenHeight * .63), this);
						}
			}
			

			//Measurement Game Tutorial
							if (measured.size() == 0){
									g.drawImage(mtutBubble1, (int) (screenWidth * .21), (int) (screenHeight * .15), this);}
							if (measured.size() == 1){
									g.drawImage(mtutBubble2, (int) (screenWidth * .21), (int) (screenHeight * .15), this);}
							if (measured.size() == 2){

									g.drawImage(mtutBubble3, (int) (screenWidth * .21), (int) (screenHeight * .15), this);}
			
			// when time runs out or player measures all caught animals, displays Eddie and continue button
			if (timer == 0) {
				g.drawImage(eddielabpic, (int)(screenWidth * .6), (int)(screenHeight * .2), this);
				g.drawImage(continuepic2, (int)(screenWidth * .5), (int)(screenHeight*.65), this);
				}
			}
		}
		
		
		if (continuedToMap) {
	
			g.drawImage(estuarypic, 0, 0, this);
			g.drawImage(stewpic, (int)(screenWidth * .01), (int)(screenHeight * .4), this);
			g.drawImage(MapExplain, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
			g.setFont(f2);
			g.drawString("Score: " + score, (int)(screenWidth * .008), (int)(screenHeight * .08));
			
			// draws 4 facts on the map
			if(factLocations.isEmpty() == false) {
			if (mappedAnimals[0] == false) {
				g.drawImage(mappingpics[factDisplayed[0]], factLocations.get(0).x, factLocations.get(0).y, this);
			}
			if (mappedAnimals[1] == false) {
				g.drawImage(mappingpics[factDisplayed[1] + 2],factLocations.get(1).x, factLocations.get(1).y, this);
			}
			if (mappedAnimals[2] == false) {
				g.drawImage(mappingpics[factDisplayed[2] + 4],factLocations.get(2).x, factLocations.get(2).y, this);
			}
			if (mappedAnimals[3] == false) {
			g.drawImage(mappingpics[factDisplayed[3] + 6],factLocations.get(3).x, factLocations.get(3).y, this);
			}
			
			// draws each animal on the map
			if (mappedAnimals[0] == false) {
				g.drawImage(loggerpics[2], loggerx, loggery, this);
			}
			if (mappedAnimals[1] == false) {
				g.drawImage(horseshoepics[2], horsex, horsey, this);
			}
			if (mappedAnimals[2] == false) {
				g.drawImage(crabpics[2], crabx, craby, this);
			}
			if (mappedAnimals[3] == false) {
			g.drawImage(fishpics[2], fishx, fishy, this);
			}
			
			
			// drags clicked image with the mouse
			if ((clickx > (screenWidth * .12)) && (clickx < (screenWidth * .2)) && (clicky > (screenHeight * .7)) && (clicky < (screenHeight * .8))){
				crabx = dragx - (crabpics[2].getWidth() / 2);
				craby= dragy - (crabpics[2].getHeight() / 2);
				
				//if the mouse is released and the animal is not at the right fact location, the image returns to its original position
				if (released) {
					if (mappedAnimals[2] == false) {
							crabx = (int)(screenWidth * .12);
							craby = (int)(screenHeight * .7);
							g.drawImage(oopspic, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
					}
				}
			}
			
			// drags clicked image with the mouse
			if ((clickx > (screenWidth * .12)) && (clickx < (screenWidth * .2)) && (clicky > (screenHeight * .82)) && (clicky < (screenHeight * .92))){
				loggerx = dragx - (loggerpics[2].getWidth() / 2);
				loggery= dragy - (loggerpics[2].getWidth() / 2);
				//if the mouse is released and the animal is not at the right fact location, the image returns to its original position
				if (released) {
					if (mappedAnimals[0] == false) {
							loggerx = (int)(screenWidth * .12);
							loggery = (int)(screenHeight * .82);
							g.drawImage(oopspic, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
					}
				}
			}
			// drags clicked image with the mouse
			if ((clickx > (screenWidth * .25)) && (clickx < (screenWidth * .33)) && (clicky > (screenHeight * .82)) && (clicky < (screenHeight * .92))){
				fishx = dragx - (fishpics[2].getWidth() / 2);
				fishy= dragy - (fishpics[2].getWidth() / 2);
				//if the mouse is released and the animal is not at the right fact location, the image returns to its original position
				if (released) {
					if (mappedAnimals[3] == false) {
							fishx = (int)(screenWidth * .25);
							fishy = (int)(screenHeight * .82);
							g.drawImage(oopspic, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
					}
				}
			}
			// drags clicked image with the mouse
			if ((clickx > (screenWidth * .25)) && (clickx < (screenWidth * .33)) && (clicky > (screenHeight * .7)) && (clicky < (screenHeight * .8))){
				horsex = dragx - (horseshoepics[2].getWidth() / 2);
				horsey= dragy - (horseshoepics[2].getWidth() / 2);
				//if the mouse is released and the animal is not at the right fact location, the image returns to its original position
				if (released) {
					if (mappedAnimals[1] == false) {
							horsex = (int)(screenWidth * .25);
							horsey = (int)(screenHeight * .7);
							g.drawImage(oopspic, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
					}
				}
			}
			
			// if the loggerhead is mapped to the correct location, displays icons of number of loggerheads caught
			if (mappedAnimals[0]) {
				if (caughtNums[0] >= 1) {
					int j = caughtNums[0];
					int l = (int)(Math.floor(caughtNums[0] / 5));
					int k = j - (l * 5);
					
					while (j > 0) {
						while (l > 0) {
							int c = 0;
							int i = 0;
							while(c < 5) {
								g.drawImage(loggericon, factLocations.get(0).x + i, factLocations.get(0).y + ((l - 1) * loggericon.getHeight()), this);
								i += loggericon.getWidth();
								c++;
								j--;
							}
							l--;
						}
						while (k > 0) {
							l = (int)(Math.floor(caughtNums[0] / 5));
							g.drawImage(loggericon, (factLocations.get(0).x + ((k - 1) * loggericon.getWidth())), (factLocations.get(0).y + (l * loggericon.getHeight())), this);
							k--;
							j--;
						}	
					}
				}
				// if none caught, draws one icon
				else {
					g.drawImage(loggericon, factLocations.get(0).x, factLocations.get(0).y, this);
				}
			}
			// if the horseshoe crab is mapped to the correct location, displays icons of number of horseshoe crabs caught
			if (mappedAnimals[1]) {
				if (caughtNums[1] >= 1) {
					int j = caughtNums[1];
					int l = (int)(Math.floor(caughtNums[1] / 5));
					int k = j - (l * 5);
					
					while (j > 0) {
						while (l > 0) {
							int c = 0;
							int i = 0;
							while(c < 5) {
								g.drawImage(horseicon, factLocations.get(1).x + i, factLocations.get(1).y + ((l - 1) * horseicon.getHeight()), this);
								i += horseicon.getWidth();
								c++;
								j--;
							}
							l--;
						}
						while (k > 0) {
							l = (int)(Math.floor(caughtNums[1] / 5));
							g.drawImage(horseicon, (factLocations.get(1).x + ((k - 1) * horseicon.getWidth())), (factLocations.get(1).y + (l * horseicon.getHeight())), this);
							k--;
							j--;
						}	
					}
				}
				// if none caught, draws one icon
				else {
					g.drawImage(horseicon, factLocations.get(1).x, factLocations.get(1).y, this);
				}
			}
			// if the blue crab is mapped to the correct location, displays icons of number of blue crabs caught
			if (mappedAnimals[2]) {
				if (caughtNums[2] >= 1) {
					int j = caughtNums[2];
					int l = (int)(Math.floor(caughtNums[2] / 5));
					int k = j - (l * 5);
					
					while (j > 0) {
						while (l > 0) {
							int c = 0;
							int i = 0;
							while(c < 5) {
								g.drawImage(crabicon, factLocations.get(2).x + i, factLocations.get(2).y + ((l - 1) * crabicon.getHeight()), this);
								i += crabicon.getWidth();
								c++;
								j--;
							}
							l--;
						}
						while (k > 0) {
							l = (int)(Math.floor(caughtNums[2] / 5));
							g.drawImage(crabicon, (factLocations.get(2).x + ((k - 1) * crabicon.getWidth())), (factLocations.get(2).y + (l * crabicon.getHeight())), this);
							k--;
							j--;
						}	
					}
				}
				// if none caught, draws one icon
				else {
					g.drawImage(crabicon, factLocations.get(2).x, factLocations.get(2).y, this);
				}
			}
			// if the weakfish is mapped to the correct location, displays icons of number of weakfish caught
			if (mappedAnimals[3]) {
				if (caughtNums[3] >= 1) {
					int j = caughtNums[3];
					int l = (int)(Math.floor(caughtNums[3] / 5));
					int k = j - (l * 5);
					
					while (j > 0) {
						while (l > 0) {
							int c = 0;
							int i = 0;
							while(c < 5) {
								g.drawImage(fishicon, factLocations.get(3).x + i, factLocations.get(3).y + ((l - 1) * fishicon.getHeight()), this);
								i += fishicon.getWidth();
								c++;
								j--;
							}
							l--;
						}
						while (k > 0) {
							l = (int)(Math.floor(caughtNums[3] / 5));
							g.drawImage(fishicon, (factLocations.get(3).x + ((k - 1) * fishicon.getWidth())), (factLocations.get(3).y + (l * fishicon.getHeight())), this);
							k--;
							j--;
						}	
					}
				}
				else {
					// if none caught, draws one icon
					g.drawImage(fishicon, factLocations.get(3).x, factLocations.get(3).y, this);
				}
			}
			
		}
	
	// when all animals are succesffuly mapped, displays continue button
	if ((mappedAnimals[0]) && (mappedAnimals[1]) && (mappedAnimals[2]) && (mappedAnimals[3])) {
		displaymapnext = true;
		g.drawImage(MapNext, (int) (screenWidth * .13), (int) (screenHeight * .15), this);
		g.drawImage(continuepic, (int) (screenWidth * .7), (int) (screenHeight * .05), this);
	}
	}
	
		// when player presses continue, displays end screen
		if (displayendscreen== true) {
			g.drawImage(LastBackground, 0, 0, this);
			g.drawString("Score: " + score, (int)(screenWidth * .008), (int)(screenHeight * .08));
		}
		
		// displays goodbyes
		if (displayendscreen) {
			if ((rachelgoodbye == true) && (stewgoodbye == false)&& (eddiegoodbye == false)&& (allgoodbye == false)){
				g.drawImage(Rbye, (int) (screenWidth * .30), (int) (screenHeight * .19), this);
				g.setFont(f3);
				g.drawString("(Hit Enter to Continue)", (int) (screenWidth * .36), (int) (screenHeight * .43));
			}
			if ((rachelgoodbye == false) && (stewgoodbye == true)&& (eddiegoodbye == false)&& (allgoodbye == false)) {
				g.drawImage(Sbye, (int) (screenWidth * .58), (int) (screenHeight * .19), this);
			}
		if ((rachelgoodbye == false) && (stewgoodbye == false)&& (eddiegoodbye == true)&& (allgoodbye == false)) {
				g.drawImage(Ebye, (int) (screenWidth * .70), (int) (screenHeight * .19), this);
			}
		if ((rachelgoodbye == false) && (stewgoodbye == false)&& (eddiegoodbye == false)&& (allgoodbye == true)) {
			g.setFont(f2);		
			g.drawString("Goodbye! Enjoy your visit!", (int) (screenWidth * .2), (int) (screenHeight * .2));

			}
		}
	}

	/**
	 * A method that is used to get the screenWidth as an int
	 * 
	 * @return int screenWidth
	 */
	public int getWidth() {
		return screenWidth;
	}

	/**
	 * A method that is used to get the screenHeight as an int
	 * 
	 * @return int screenHeight
	 */
	public int getHeight() {
		return screenHeight;
	}

	/**
	 * A method that is used to update the view.
	 * 
	 * @param playerx, The x cord of the player is set equal to the boat's x cord
	 * @param netx, The x cord of the net is set equal to the net's x cord
	 * @param nety, The y cord of the net is set equal to the net's y cord
	 * @param polelength, the int poleLength is set equal to the poleLength
	 * @param o, An arrayList of Entities that is used by objs in the view. Represents all objects.
	 * @param score, An int that is used by the score in the view
	 * @param timer, An int that is used by the timer in the view
	 * @param caughtNums, An array of int's that is used by caughtNums in the view
	 * @param lastCaught, A string that is used by lastCaught in the view
	 * @param caught, An arrayList of Entities that is used by caught. Represents the caught animals.
	 * @param pointerx, A double that is used by pox in the View.
	 * @param reading, A double that is used by reading in the View.
	 * @param measured, An arrayList of Entities that is used by measured. Represents the measured animals.
	 * @param factDisplayed, An array of int's that is used by factsDisplayed in the view.
	 * @param factLocations, An arrayList of Points that is used by factLocations in the view.
	 * @param mapAnimals, An arrayList of Entities that is used by mapAnimals in the view.
	 * @param mappedAnimals, An array of boolean's that is used by mappedAnimals in the view.
	 */
	public void update(int playerx, int netx, int nety, int polelength, ArrayList<Entities> o,
			int score, int timer, int[] caughtNums, String lastCaught, ArrayList<Entities> caught, 
			double pointerx, double reading, ArrayList<Entities> measured, int[] factDisplayed, ArrayList<Point> factLocations,
			ArrayList<Entities> mapAnimals, boolean[] mappedAnimals) {

		this.boatx = playerx;
		this.rx = playerx + (int)(screenWidth * .05);
		this.netx = netx;
		this.nety = nety;
		this.poleLength = polelength;
		this.objs = o;
		this.score = score;
		this.timer = timer;
		this.caughtNums = caughtNums;
		this.lastCaught = lastCaught;
		this.caught = caught;
		this.pox = pointerx;
		this.reading = reading;
		this.measured = measured;
		this.factDisplayed = factDisplayed;
		this.factLocations = factLocations;
		this.mapAnimals = mapAnimals;
		this.mappedAnimals = mappedAnimals;
		

    	frame.setFocusable(true);
		frame.add(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		frame.setVisible(true);
		frame.repaint();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * A method that is used to read view from file
	 * @param c, an array list of all caught entities
	 * @param dss, boolean that displays start screen
	 * @param dlgs, boolean that displays let's go screen
	 * @param dfs, boolean that displays fishing screen
	 * @param cc, boolean that's true when player presses continue
	 * @param cont, boolean that's true when game is continued
	 * @param sm, boolean that's true when measuring game starts
	 * @param ctm, boolean that displays map
	 * @param dmn, boolean that displays new map image
	 * @param des, boolean that displays end screen
	 * @param rg, boolean displaying rachel's goodbye
	 * @param sg, boolean displaying stew's goodbye
	 * @param eg, boolean displaying eddie's goodbye
	 * @param ag, boolean displaying everyone saying goodbye
	 * @param ts, boolean starting tutorial
	 * @param tlr, boolean showing tutorial with arrow keys
	 * @param td, boolean showing tutorial for down arrow
	 * @param tu, boolean showing tutorial for up arrow
	 * @param tcf, boolean that's true when player catches first animal
	 */
	   public void updateC(ArrayList<Entities> c, boolean dss, boolean dlgs,
			      boolean dfs, boolean cc, boolean cont, boolean sm, boolean ctm,
			      boolean dmn, boolean des, boolean rg, boolean sg, boolean eg, boolean ag,
			      boolean ts, boolean tlr,boolean td, boolean tu, boolean tcf) {
			         caught = c;
			         displayStartScreen = dss;
			      	displayLetsGoScreen = dlgs;
			      	displayFishingScreen = dfs;
			      	clickedContinue = cc;
			      	continued = cont;
			      	startedMeasuring = sm;
			      	continuedToMap = ctm;
			      	displaymapnext = dmn;
			      	displayendscreen = des;
			      	rachelgoodbye = rg;
			      	stewgoodbye = sg;
			      	eddiegoodbye = eg;
			      	allgoodbye = ag;
			         tutorialstarted = ts;
				      tutleftright = tlr;
			      	tutdown = td;
			      	tutup = tu;
			      	tutcaughtfirst = tcf;
			   }

	   /** A method that changes the pictures for the boat and net to left facing
	    */
    public void left() {
    	boatPicNum = 0;
    	netPicNum = 0;
    	}

    /**
     * A method that changes the pictures for the boat and net to right facing
     */
    public void right() {
    	boatPicNum = 1;
    	netPicNum = 1;
    }
    
    
    /** A method used for detection of arrow key presses
     *
     * @param keyListener, the key that is pressed
     */
	public void addKeyListener(KeyListener keyListener) {
		frame.addKeyListener(keyListener);
	}

	   /** A method that adds detection for mouse clicks
	    *
	    * @param mouseListener, the clicked point
	    */
	public void addMouseListener(MouseAdapter mouseListener) {
		frame.addMouseListener(mouseListener);
	}
	
	/** A method that adds detection for mouse movement
	 *
	 * @param mouseMotionListener, the motion points
	 */
	public void addMouseMotionListener(MouseAdapter mouseMotionListener) {
		frame.addMouseMotionListener(mouseMotionListener);
	}
	
	/** A method that is used to get the Score
	 * 
	 * @return score, an int
	 */
	public int getScore() {
		return this.score;
	}
		
}


package Controllers;

import java.io.*;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import Models.ModelR;
import Views.ViewR;

/**
 * @author  Cara Eppes, Jenny Cox, Gabe Corella, Alessio Iccari, Dane Malanoski
 */


@SuppressWarnings("serial")
public class ControllerR implements java.io.Serializable{

	private ModelR model;
	private ViewR view;
	
	/**
	 * ControllerR constructor method that implements an instance of View and Model. 
	 * Within the view, we are adding a KeyListener, a MouseListenener, and a MouseMotionListener
	 */
	public ControllerR() {
		view = new ViewR();
		model = new ModelR(view.screenWidth, view.screenHeight);
		view.addKeyListener(new Key());
		view.addMouseListener(new Mouse());
		view.addMouseMotionListener(new Mouse());
	}

	/**
	* Main method for ControllerR class that creates an instance of 
	* ControllerR and calls the start() method within the controller
	* 
	* @param args The arguments within the the main method
	*/
	public static void main(String[] args) {
		ControllerR c = new ControllerR();
		c.start();
	}

	/**
	* Iterates through the for-loop and updates the model and view. The 
	* model update method takes in no parameters while the view updates the Players
	* x/y, net's x/y, score, timer, quantity caught, last caught, caught, pointers, measure,
	* fact and mapping variables
	*/
	public void start() {
		for (int i = 0; i < 10000; i++) {
			model.update();
			view.update(model.getPlayer().x, model.getNet().x, model.getNet().y, model.getNet().poleLength, model.getObjs(),
					model.getScore(), model.getTimer(), model.getQuantityCaught(), model.getLastCaught(), model.getCaught(),
					model.getPointer().x, model.getPointer().getReading(), model.getMeasured(), model.getFactDisplayed(),
					model.getFactLocations(), model.getMapAnimals(), model.getMappedAnimals());
		}
	}

	class Key implements KeyListener {


		private ObjectInputStream oisM;

		/**
		 * Represents stub for overridden method keyTyped
		 * @param e Simple argument taken in order to see if key is typed
		 */		
		public void keyTyped(KeyEvent e) {}


		/**
		 * Represents and checks if any of the arrow keys are pressed:
		 * If the left/right arrow keys are pressed then update the view & model for left/right for Boat Direction.
		 * If the up/down arrow keys are pressed update the model to bring the net down or up.
		 * If the letter 's' is pressed, skip to the scoreboard and stop the timer
		 * During measuring game, space bar starts and stops pointer movement.
		 * if pointer is within .4 of correct value, animal is added to measured and
		 * removed from caught.
		 * the 'p' key is used to write the state of the game to a file
		 * the 'c' key is used to read the state of the game out of the file
		 * the Enter key is used to toggle through tutorials and speech bubbles
		 * 
		 * @param e Sets keyCode assigned to e and checks the keyCode with the ArrowKeys
		 *
		 */

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();

			if (code == KeyEvent.VK_LEFT) {
				view.left();
				model.left();
			}
			if (code == KeyEvent.VK_RIGHT) {
				view.right();
				model.right();
			}
			if (code == KeyEvent.VK_DOWN) {
				model.down();
			}
			if (code == KeyEvent.VK_UP) {
				model.up();
			}

			// stops timer to skip game
			if (e.getKeyChar() == 's') {
				model.setRunTimer(false);
				model.setTimer(-1);
			}
			
			//writes mode to file
         if ( e.getKeyChar() == 'p') {
            try {
               ObjectOutputStream oosM = new ObjectOutputStream(new FileOutputStream(new File("model.data")));
               oosM.writeObject(model);
               oosM.close();
            } catch (IOException ioe) {
               System.out.println("Error writing model to file");
            }
         }
         
         //reads model from file
         if ( e.getKeyChar() == 'c') {
            try {
               oisM = new ObjectInputStream(new FileInputStream("model.data"));
               model = (ModelR) oisM.readObject();
               view.updateC(model.caught, model.displayStartScreen, model.displayLetsGoScreen, model.displayFishingScreen,
	                  model.clickedContinue, model.continued, model.startedMeasuring, model.continuedToMap,
                     model.displaymapnext, model.displayendscreen, model.rachelgoodbye, model.stewgoodbye,
                     model.eddiegoodbye, model.allgoodbye, model.tutorialstarted, model.tutleftright, model.tutdown,
                     model.tutup, model.tutcaughtfirst);             
            } catch (Exception ioe) {
               System.out.println("Model not found");
            }
         }

			// ****Tutorial controller***
			// triggers first tutorial picture, changes when you hit ENTER
			if ((view.tutorialstarted == true) && (view.tutleftright == false) && (view.tutdown == false)
					&& (view.tutup == false) && (view.tutcaughtfirst == false)) {
				if (code == KeyEvent.VK_ENTER) {
					view.tutorialstarted = false;
					view.tutleftright = true;
               model.tutorialstarted = false;
					model.tutleftright = true;
				}

			}
			// triggers the 2nd tutorial picture, changes when you hit LEFT ARROW
			if ((view.tutorialstarted == false) && (view.tutleftright == true) && (view.tutdown == false)
					&& (view.tutup == false) && (view.tutcaughtfirst == false)) {
				view.tutdown = false;
				if (code == KeyEvent.VK_LEFT) {
					view.tutleftright = false;
					view.tutdown = true;
               model.tutleftright = false;
					model.tutdown = true;
				}

			}
			// triggers the 3rd tutorial picture, changes when you hit the DOWN ARROW
			if ((view.tutorialstarted == false) && (view.tutleftright == false) && (view.tutdown == true)
					&& (view.tutup == false) && (view.tutcaughtfirst == false)) {
				if (code == KeyEvent.VK_DOWN) {
					view.tutdown = false;
					view.tutup = true;
               model.tutdown = false;
					model.tutup = true;
				}

			}
			// triggers the 4th tutorial picture, changes when you catch a fish
			if ((view.tutorialstarted == false) && (view.tutleftright == false) && (view.tutdown == false)
					&& (view.tutup == true) && (view.tutcaughtfirst == false)) {
				if (code == KeyEvent.VK_UP) {
					if (view.getScore() >= 1) {
						view.tutup = false;
						view.tutcaughtfirst = true;
                  model.tutup = false;
						model.tutcaughtfirst = true;
					}
				}

			}

			// triggers the 4th tutorial picture, changes when you catch a fish
			if ((view.tutorialstarted == false) && (view.tutleftright == false) && (view.tutdown == false)
					&& (view.tutup == false) && (view.tutcaughtfirst == true)) {
				if (code == KeyEvent.VK_DOWN) {
					view.tutcaughtfirst = false;
					model.resetTimer();
					model.runTimer = true;
               model.tutcaughtfirst = false;
				}
			}

			/*
			 * during measuring game, space bar starts and stops pointer movement. if
			 * pointer is within .4 of correct value, animal is added to measured and
			 * removed from caught
			 */
			if ((view.startedMeasuring == true) && (model.caught.isEmpty() == false)) {
				if (code == KeyEvent.VK_SPACE) {
					if (model.pointerMoving == true) {
						model.pointerMoving = false;
						if ((model.getPointer().reading <= model.startPointer.reading + .5)
								&& (model.getPointer().reading > model.startPointer.reading - .5)) {
							model.measured.add(model.caught.get(model.caught.size() - 1));
							model.caught.remove(model.caught.size() - 1);
						}
					} else {
						model.pointerMoving = true;
					}
				}
			}
			if ((view.rachelgoodbye == false) && (view.stewgoodbye == false) && (view.eddiegoodbye == true)
					&& (view.allgoodbye == false) && (view.displayendscreen == true)) {
				if (code == KeyEvent.VK_ENTER) {
					view.rachelgoodbye = false;
					view.stewgoodbye = false;
					view.eddiegoodbye = false;
					view.allgoodbye = true;
               model.rachelgoodbye = false;
					model.stewgoodbye = false;
					model.eddiegoodbye = false;
					model.allgoodbye = true;

				}
			}

			if ((view.rachelgoodbye == false) && (view.stewgoodbye == true) && (view.eddiegoodbye == false)
					&& (view.allgoodbye == false) && (view.displayendscreen == true)) {
				if (code == KeyEvent.VK_ENTER) {
					view.rachelgoodbye = false;
					view.stewgoodbye = false;
					view.eddiegoodbye = true;
					view.allgoodbye = false;
               model.rachelgoodbye = false;
					model.stewgoodbye = false;
					model.eddiegoodbye = true;
					model.allgoodbye = false;

				}
			}

			if ((view.rachelgoodbye == true) && (view.stewgoodbye == false) && (view.eddiegoodbye == false)
					&& (view.allgoodbye == false) && (view.displayendscreen == true)) {
				if (code == KeyEvent.VK_ENTER) {
					view.rachelgoodbye = false;
					view.stewgoodbye = true;
					view.eddiegoodbye = false;
					view.allgoodbye = false;
               model.rachelgoodbye = false;
					model.stewgoodbye = true;
					model.eddiegoodbye = false;
					model.allgoodbye = false;
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
		}
	}

	class Mouse extends MouseAdapter implements MouseMotionListener, java.io.Serializable{
		/**
		 * This is an overridden method used to represent when the mouse
		 * is initially pressed. When it is initially pressed, the released and dragging
		 * booleans in the view get changed.
		 * @param e, MouseEvent represents an event that gets done by the mouse.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			view.released = false;
			Point p = e.getPoint();
			view.clickx = p.x;
			view.clicky = p.y;
			view.dragging = true;
			model.clickx = p.x;
			model.clicky = p.y;
			model.dragging = true;
		}

		/** This is an overridden method used to represent when the mouse
		 * is initially released. When it is initially released, the released boolean
		 * is switched to true and the releasex and releasey are both updated in the model and view.
		 * Also, the moveAnimalsMap() function is called and the view and model dragging boolean is set to false.
		 * @param e, MouseEvent represents an event that gets done by the mouse.
		*/
		@Override
		public void mouseReleased(MouseEvent e) {
			view.released = true;
			Point p = e.getPoint();
			model.releasex = p.x;
			model.releasey = p.y;
			view.releasex = p.x;
			view.releasey = p.y;
			model.moveAnimalsMap();
			view.dragging = false;
			model.dragging = false;

		}


		/**
		 * This is an overridden method that is used in our last minigame 
		 * to drag different animals to different facts
		 * @param e, MouseEvent represents an event done by the mouse
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = e.getPoint();
			view.dragx = p.x;
			view.dragy = p.y;
			if (view.dragging) {
				view.repaint();
			}
		}

		/**
		 * This is the stub for the overridden method mouseMoved
		 * @param e, MouseEvent represents an event done by the mouse
		 */
		public void mouseMoved(MouseEvent e) {}
    
		/**
		 * The overridden method mouseClicked takes in a MouseEvent e and if a particular
		 * button is pressed then the view is updated along with the models timer and measuring game
		 * 
		 * @param e MouseEvent that represents an event done by the mouse.
		 */

		@Override
		public void mouseClicked(MouseEvent e) {

			// if "Start" is clicked, takes player to next screen
			if (view.displayStartScreen) {
				if ((e.getX() > (int) (.364 * view.screenWidth)) && (e.getX() < (int) (.7 * view.screenWidth))
						&& (e.getY() > (int) (.43 * view.screenHeight))
						&& (e.getY() < (int) (.695 * view.screenHeight))) {
					view.displayLetsGoScreen = true;
					view.displayStartScreen = false;
               model.displayLetsGoScreen = true;
					model.displayStartScreen = false;
				}
			}

			// if the "Let's go!" button is clicked, starts the fishing researcher game
			if (view.displayLetsGoScreen) {
				if ((e.getX() > (int) (.49 * view.screenWidth)) && (e.getX() < (int) (.763 * view.screenWidth))
						&& (e.getY() > (int) (.763 * view.screenHeight))
						&& (e.getY() < (int) (.92 * view.screenHeight))) {
					view.displayFishingScreen = true;
					view.displayLetsGoScreen = false;
					model.startedFishing = true;
               model.displayFishingScreen = true;
					model.displayLetsGoScreen = false;
				}
			}

			// if player clicks "Continue", takes player to lab
			if ((view.clickedContinue == false) && (view.displayFishingScreen == true) && (view.timer <= 0)) {
				if ((e.getX() > (int) (.73 * view.screenWidth)) && (e.getX() < (int) (.95 * view.screenWidth))
						&& (e.getY() > (int) (view.screenHeight * .64))
						&& (e.getY() < (int) (.75 * view.screenHeight))) {
					view.clickedContinue = true;
               model.clickedContinue = true;
					model.resetTimer();
					model.runTimer = false;
					model.inLab = true;
					model.startedFishing = false;
					view.displayFishingScreen = false;
               model.displayFishingScreen = false;
				}
			}

			// if player clicks "Start" in lab, starts measuring game
			if ((view.clickedContinue == true) && (model.pointerMoving == false)) {
				if ((e.getX() > (int) (.6 * view.screenWidth)) && (e.getX() < (int) (.8 * view.screenWidth))
						&& (e.getY() > (int) (view.screenHeight * .15))
						&& (e.getY() < (int) (.45 * view.screenHeight))) {
					model.labTutorial = true;
					model.pointerMoving = true;
					view.startedMeasuring = true;
					model.startedMeasuring = true;
					model.runTimer = true;
				} 
			}

			if ((view.continuedToMap == false) && (model.timer == 0) && (model.inLab == true)) {
				if ((e.getX() > (int) (.5 * view.screenWidth)) && (e.getX() < (int) (.7 * view.screenWidth))
						&& (e.getY() > (int) (view.screenHeight * .65))
						&& (e.getY() < (int) (.75 * view.screenHeight))) {
					view.continuedToMap = true;
               model.continuedToMap = true;
					model.inLab = false;
					model.mapping = true;
					view.startedMeasuring = false;
					view.clickedContinue = false;
               model.startedMeasuring = false;
					model.clickedContinue = false;
				}
			}
			if ((view.displaymapnext == true) && (view.rachelgoodbye == false)) {
				if ((e.getX() > (int) (.7 * view.screenWidth)) && (e.getX() < (int) (.9 * view.screenWidth))
						&& (e.getY() > (int) (view.screenHeight * .05))
						&& (e.getY() < (int) (.2 * view.screenHeight))) {
					view.displaymapnext = false;
					view.rachelgoodbye = true;
					view.displayendscreen = true;
               model.displaymapnext = false;
					model.rachelgoodbye = true;
					model.displayendscreen = true;

				}
			}

		}
	}
}
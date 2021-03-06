import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;
/**
 * This class will create a dice on the screen.
 * Click on the dice to roll it and then you will be able to perform
 * your move. THE DICE IS TOTALLY RANDOM.
 */
public class Dice{

	private Random r;
	private final int DICE_LENGTH = 6;
	private Canvas canvas;
	private GraphicsContext gc;
	private AnimatedImage aniImage;
	private int diceNumber; //This number is between 1-6
	private double canvasPosX, canvasPosY;
	private double screenWidth, screenHeight;
	private boolean AI;
	private boolean wait;
	private boolean diceThrown;
	private boolean stopDice;

	public Dice(Group root, double w, double h, boolean AI){
		diceThrown = false;
		wait = false;
		screenWidth = w;
		screenHeight = h;

		canvasPosX = screenWidth * 0.016; //rita från denna position
		canvasPosY = screenHeight * 0.2;
		r = new Random();

		canvas = new Canvas(canvasPosX + 102, canvasPosY + 112); //canvas storlek i parametrarna (till denna position)
		root.getChildren().add(canvas);

		canvas.setTranslateX(canvasPosX);
		canvas.setTranslateY(canvasPosY);

		gc = canvas.getGraphicsContext2D();

		this.AI = AI;
		aniImage = createDice();
	}


	public AnimatedImage createDice() {
		AnimatedImage dice = new AnimatedImage();
		Image[] imageArray = new Image[DICE_LENGTH];
		for (int i = 1; i < 7; i++) {
		    imageArray[i-1] = new Image("images/dice/dice" + i + ".png");
		}
		dice.frames = imageArray;
		dice.duration = 0.1; //speed of diceAnimation
		return dice;
	}

	/**
	 * Resets the dice speed and puts it back to
	 * normal. Use this when turn is done.
	 */
	public void resetDice() {
		diceThrown = false;
	}

	/**
	 * Used as helper in diceEvent method.
     */
	private void animateDice(double time) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	    	gc.drawImage(aniImage.getFrame(time), canvasPosX, canvasPosY);
			wait = false;
	}

	/**
	 * Put this method in the loop to both animate the dice
	 * and make it interactive. Clicking on the dice will slow
	 * it down and make it eventually stop on a number.
	 * That number will then be stored in a variable and shall
	 * be returned (by another method) if you wanna use it to attack.
	 * @param time program runtime
     */
	//FAILED TO SLOW DICE DOWN..
	public void diceEvent(double time) {

		if(!AI) {
			canvas.toFront(); //viktigt att din canvas hamnar längst fram.
			//only when the diceAnimation variable is true can you press on the dice
			canvas.setOnMousePressed(e -> {
				if (!diceThrown) {
					diceThrown = true;
					gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
					gc.drawImage(aniImage.getRandomFrame(), canvasPosX, canvasPosY);
					diceNumber = aniImage.getDiceNumber();
				}
			});
		}

		if(AI && stopDice == true && !diceThrown) {
			diceThrown = true;
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			gc.drawImage(aniImage.getRandomFrame(), canvasPosX, canvasPosY);
			diceNumber = aniImage.getDiceNumber();
		}

		//stop animation of dice when diceAnimation is false
		if(!wait) {
			//wait = true;
			if (!diceThrown)
				animateDice(time);
		}
	}

	public void stopDice() {
		stopDice = true;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public void removeDice() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public boolean getDiceThrown() {
		return diceThrown;
	}

	public void setDiceThrown(boolean v) {
		diceThrown = v;
	}

	public void setDiceNumber(int nr) {
		diceNumber = nr;
	}
}
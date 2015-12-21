// Name: Edward Camp
// Project: EventHandling
// File: HelloFrame.java
// Description: Testing out how the frame works, as well as putting together the frame.

import javax.swing.*;

public class HelloFrame extends JFrame{
	public static final int HEIGHT = 500;
	public static final int WIDTH = 500;
	
	/**
	 * BASIC CONSTRUCTOR
	 */
	public HelloFrame(){
		/** Setting Up Settings for Frame **/
		super("Hello World!");								// Call to the super class's constructor
		setSize(WIDTH, HEIGHT);								// Sets the size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Terminates program when frame is closed
		setLocationRelativeTo(null);						// Centers the frame onscreen
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * CONSTRUCTOR FOR EVENT-HANDLING
	 */
	public HelloFrame(String title){
		/** Setting Up Settings for Frame **/	
		super(title);										// Call to the super class's constructor
		setSize(WIDTH, HEIGHT);								// Sets the size of the frame			
		setLocationRelativeTo(null);						// Centers the frame onscreen
	}
	
	/**
	 * CONSTRUCTOR FOR EVENT-HANDLING AND WINDOW-ADAPTERS
	 */
	public HelloFrame(int action, String title){
		/** Setting Up Settings for Frame **/	
		super(title);												// Call to the super class's constructor
		setSize(WIDTH, HEIGHT);										// Sets the size of the frame	
		if(action == 1)
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	// Does nothing when the close button on the window is pressed (This is later handled by a WindowListener object)
		else
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// Terminates program when frame is closed

		setLocationRelativeTo(null);								// Centers the frame onscreen
	}
	
	/**
	 * CONSTRUCTOR FOR EVENT-HANDLING, WINDOW-ADAPTERS, and DIMENSIONS
	 */
	public HelloFrame(int action, String title, int width, int height){
		/** Setting UP Settings for Frame **/
		super(title);												// Call to the super class's constructor
		setSize(width, height);										// Sets the size of the frame	
		if(action == 1)
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	// Does nothing when the close button on the window is pressed (This is later handled by a WindowListener object)
		else
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			// Terminates program when frame is closed

		setLocationRelativeTo(null);								// Centers the frame onscreen
	}
	
	public static void main(String[] args){
		JFrame frame = new HelloFrame();					// Create Frame
	}
}

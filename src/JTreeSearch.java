// Name: Edward Camp
// Project: ChoosingFromAList
// File: JTreeSearch.java
// Description: A quick basic test of Swing's Tree component through a game of Hide n' Seek. This determines the
//				difficulty of the game.

import javax.swing.*;
import javax.swing.tree.*;

import java.util.*;

import java.awt.*;

public class JTreeSearch extends HelloFrame{
	
	// Instance Variables
	private JSpinner difficulty;
	private JButton diffOK;
	private String[] modes = {"EASY", "INTERMEDIATE", "HARD"};
	private JTreeSearchGame gameFrame;
	
	public JTreeSearch(){
		/** Frame Set Up **/
		super(0,"Choose Difficulty", 300, 150);
		
		/** Panel Set Up **/
		JPanel diffPanel = new JPanel();
		
		/** JSpinner Set Up **/
		difficulty = new JSpinner(new SpinnerListModel(modes));					// Spinner will have the choices EASY, INTERMEDIATE, and HARD

		// OBTAINED ONLINE IN ORDER TO ADJUST SPINNER WIDTH: http://stackoverflow.com/questions/7374659/jspinner-increase-length-of-editor-box
		JComponent field = ((JSpinner.DefaultEditor) difficulty.getEditor());
	    Dimension prefSize = field.getPreferredSize();
	    prefSize = new Dimension(100, prefSize.height);
	    field.setPreferredSize(prefSize);
		
		diffPanel.add(difficulty);
		
		/** JButton Set Up **/
		diffOK = new JButton("OK");												// JButton will have OK on it.
		diffOK.addActionListener(e -> diffOKClick());
		diffPanel.add(diffOK);
		
		/** Frame Tweaking **/
		this.add(diffPanel);
		this.setVisible(true);
	}
	
	public void diffOKClick(){
		String modeSelected = (String) difficulty.getValue();					// Get value selected from the JSpinner
		
		/** Selecting a Mode **/
		if(modeSelected.equals("EASY")){
			this.setVisible(false);
			gameFrame = new JTreeSearchGame(2, this);
		}
		else if(modeSelected.equals("INTERMEDIATE")){
			this.setVisible(false);
			gameFrame = new JTreeSearchGame(3, this);
		}
		else if(modeSelected.equals("HARD")){
			this.setVisible(false);
			gameFrame = new JTreeSearchGame(4, this);
		}
		else{
			JOptionPane.showMessageDialog(diffOK, "Please choose appropriate mode", "INVALID DIFFICULTY", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	// MAIN METHOD ==============================================================================================
	public static void main(String[] args){
		new JTreeSearch();
	}
}

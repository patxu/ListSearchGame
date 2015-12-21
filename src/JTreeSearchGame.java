// Name: Edward Camp
// Project: ChoosingFromAList
// File: JTreeSearch.java
// Description: A quick basic test of Swing's Tree component through a game of Hide n' Seek. This will create the game itself
//				based on difficulty chosen.

import javax.swing.*;
import javax.swing.tree.*;

import java.util.*;
import java.awt.*;

public class JTreeSearchGame extends HelloFrame{
	
	// Instance Variables
	private JLabel selection, message;
	private JTree treeMaze;
	private JScrollPane scrollBar;
	private DefaultMutableTreeNode root;
	private JButton gameOK;
	
	private ArrayList<Integer> cases;
	
	private int denomProbabilty;						 // This will keep track of the denominator of the probability that a case will be in a leaf.
	private int winningNum;								 // This is the winning case number. Find this number to win.
	
	public JTreeSearchGame(int casePerLevel, JTreeSearch diffFrame){
		/** Setting Up Frame **/
		super(0,"Hide n' Seek", 300, 600);
		
		/** Link HomeFrame to this Frame **/
		//homeScreen = diffFrame;
		
		/** Setting Up Panel **/
		JPanel gamePanel = new JPanel();
		
		/** Setting Up Number of Cases **/
		int numCases = (int) Math.pow(10, casePerLevel - 1);				// Determine how many cases will be in the game.
		winningNum = (int) Math.round((Math.random() * numCases) + .5);		// Determine winning number
		cases = new ArrayList<Integer>(numCases);							// Initialize ArrayList
		for(;numCases > 0; numCases--)										// Add all cases from numCases to 1 to ArrayList
			cases.add(numCases);
		
		/** Objective Message **/
		message = new JLabel("Find the number " + winningNum);
		gamePanel.add(message);
		
		/** Creating root of tree **/
		root = new DefaultMutableTreeNode("TOP FOLDER");
		
		/** Using recursive method to build Tree **/
		gameTreeCreation(casePerLevel);
		
		/** Add newly created tree to JTree **/
		treeMaze = new JTree(root);
		treeMaze.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);		// Set the tree where you can only select one node at a time
		treeMaze.addTreeSelectionListener(e -> nodeSelected());
		
		/** Add JTree to ScrollPane **/
		scrollBar = new JScrollPane(treeMaze, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setPreferredSize(new Dimension(270,495));
		gamePanel.add(scrollBar);
		
		/** JLabel **/
		selection = new JLabel();
		gamePanel.add(selection);
		
		/** Setting Up Button **/
		gameOK = new JButton("SELECT");
		gameOK.addActionListener(e -> selectClicked());
		gamePanel.add(gameOK);
		
		/** Final Tweaking **/
		this.add(gamePanel);
		this.setVisible(true);
		
	}
	
	private void nodeSelected(){
		DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) treeMaze.getLastSelectedPathComponent();		// Getting the Tree Node Selected.
		if(nodeSelected != null){
			String selectedString = (String) nodeSelected.getUserObject();											// Getting String of selected node.
			selection.setText(selectedString);
		}
	}
	
	private void selectClicked(){
		DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) treeMaze.getLastSelectedPathComponent();		// Getting the Tree Node Selected.
		
		if(nodeSelected != null){
			String selectedString = (String) nodeSelected.getUserObject();											// Getting String of selected node.
			
			if(selectedString.equals(Integer.toString(winningNum))){
				JOptionPane.showMessageDialog(gameOK, "YOU WIN!", "WINNER!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			else{
				JOptionPane.showMessageDialog(gameOK, "Try again...", "INCORRECT NUMBER", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private void gameTreeCreation(int casePerLevel){
		denomProbabilty = (int) Math.pow(casePerLevel, casePerLevel - 1);
		gameTreeCreationHelper(root, casePerLevel, casePerLevel - 1);
	}
	
	private void gameTreeCreationHelper(DefaultMutableTreeNode parent, int casePerLevel, int levelOfRecursion){
		// RECURSION CASE - If we are not at leaf level, then recursively branch out by the number of case per Level
		if(levelOfRecursion > 0){
			for(int i = 0 ;i < casePerLevel; i++){
				DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(Character.toString((char) (65 + i)));		// Create node folders starting from "A"
				parent.add(newChild);																					// Add child to parent
				gameTreeCreationHelper(newChild, casePerLevel, levelOfRecursion - 1);									// Recurse
			}
		}
		
		// BASE CASE - When we reach leaf level, determine whether a case should be placed there.
		else{
			ArrayList<Integer> removeList = new ArrayList<Integer>();
			for(int indivCase: cases){
				if(Math.random() < ((double)1/denomProbabilty)){
					removeList.add(indivCase);														// Remove the case if it will be added to leaf
					parent.add(new DefaultMutableTreeNode(Integer.toString(indivCase)));			// Add case to the leaf
				}
			}
			for(Integer removedInt: removeList)
				cases.remove(removedInt);
			denomProbabilty--;																		// Increase Probability for next leaf
		}
	}	
}

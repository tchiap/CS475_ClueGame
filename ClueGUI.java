/* ClueGUI.java
 *
 * Author: Martin Aber
 * Partners: Rick Gearheart and Thomas Chiapete
 *
 * This class holds the GUI methods for the Solution.java
 * class.
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

public class ClueGUI 
{
	public static void main(String[] args) 
	{

	}//end main

	public ClueGUI()
	{
	}//end ClueGUI

	//produces a popup window that promps for a game number to randomly
     //generate a solution
     public static void initGUI()
     {
 		int number = 0;
 		Scanner numberScan;	
 		do
 		{
 	     	numberScan = new Scanner(JOptionPane.showInputDialog(null, 
 				 "Enter the game number greater than zero:  "));
 	     	if(numberScan.hasNextInt())
 			   number = numberScan.nextInt();
	     
 		}while(number<1);
	
 		Solution.init(number);

     }//end initGUI


    /* creates a series of popup windows that prompts the user for a 
     * murderer, a weapon, and a room.  The data entry is case sensitive
     * currently because the popup windows are temporary place holders 
     * for buttons that will be implemented once this method is linked
     * to the user interface
     */
    public static int accuseGUI()
    {
		String murderer;  //holds the entered value of the murderer
        	String weapon;    //holds the entered value of the weapon
        	String room;      //holds the entered value of the room

		//prompts the user for a murderer
        	 murderer = JOptionPane.showInputDialog(null, "Enter the name " +
		"of the murderer:  \nChoices:  MRS_PEACOCK, MR_GREEN, " +
		"MISS_SCARLET, \nPROFESSOR_PLUM, COLONEL_MUSTARD, MRS_WHITE \n"+
		"(Please enter the data as displayed)");
		murderer.trim();
        
		//prompts the user for a weapon
	    	weapon = JOptionPane.showInputDialog(null, "Enter the murder weapon:"+
		"\nChoices:  REVOLVER, LEAD_PIPE, CANDLESTICK_HOLDER, \n" +
                "ROPE, KNIFE, WRENCH \n (Please enter the data as displayed)");
	    	weapon.trim();
        
		//prompts the user for a room
	    	room = JOptionPane.showInputDialog(null, "Enter the room name:\n" +
                "Choices:  LIBRARY, STUDY, BILLIARD_ROOM, BALLROOM, \n" +
                "HALL, DINING_ROOM, KITCHEN, CONSERVATORY \n" +
		"(Please enter the data as displayed)");
	    	room.trim();

		//sends the entered answers to the accuse method to count correct answers
		return Solution.accuse(murderer, weapon, room);
        
    }//end accurseGUI

    //prints the correct answer to an output box
    public static void printGUI()
    {
        JOptionPane.showMessageDialog(null, "The current solution is: \n" +
		"Killer = " + Solution.solution_suspect + "\n" + "Weapon = " + 
                Solution.solution_weapon + "\n" + "Room = " + Solution.solution_room);
        
    }//end printGUI

}//end ClueGUI

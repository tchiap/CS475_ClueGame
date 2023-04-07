/*
 * Solution.java
 *
 * Author: Martin Aber
 * Partners: Rick Gearheart and Thomas Chiapete
 *
 * This class takes input from the user to randomly select a murderer, a
 * weapon, and room. From there it takes in answers and compares it to
 * a solution object created with the answer.  The GUI methods are 
 * currently place holders for buttons on the user interface that will
 * be added at a later date.
 */

import java.util.*;
import javax.swing.*;

//Creates a solution to the game of Clue
public class Solution 
{
    //Creates an enumeration of all possible suspects
    public enum Suspect {MRS_PEACOCK, MR_GREEN, MISS_SCARLET, PROFESSOR_PLUM,
            COLONEL_MUSTARD, MRS_WHITE, MAX_SUSPECT};    
            
    //Creates an enumeration of all possible weapons
    public enum Weapon {REVOLVER, LEAD_PIPE, KNIFE, CANDLESTICK_HOLDER,
            ROPE, WRENCH, MAX_WEAPON};
    
    //Creates an enumeration of all possible locations
    public enum Room {LIBRARY, STUDY, BILLIARD_ROOM, BALLROOM, HALL,
            DINING_ROOM, KITCHEN, CONSERVATORY, MAX_ROOM};
    
    static Suspect solution_suspect; //holds the murderer
    static Weapon solution_weapon;  //holds the weapon
    static Room solution_room;  //holds the location
    private static int randMurderMod = 42;
    private static int randWeaponMod = 17;
    private static int randRoomMod = 36;
    
    public static void main(String[] args) { }//end main
    
    //creates a Solution object that contains the suspect that commited the
    //murder, the weapon that was used, and the room it happend in.
    public Solution()
    {
       ClueGUI.initGUI();
    }//end Solution
    
    //Asks for a game number and assigns a corrasponding suspect, weapon,
    //and room to the solution.
    public static void init(int game_number)
    {
         //searches through the Suspects to find a matching value
         solution_suspect = Suspect.values() 
         [ (int)(Math.sqrt(game_number*randMurderMod)%Suspect.MAX_SUSPECT.ordinal()) ];

         //searches through the Weapons to find a matching value
         solution_weapon = Weapon.values() 
         [ (int)(Math.sqrt(game_number*randMurderMod)%Weapon.MAX_WEAPON.ordinal()) ];
         
         //searches through the Room to find a matching value
         solution_room = Room.values() 
         [ (int)(Math.sqrt(game_number*randMurderMod)%Room.MAX_ROOM.ordinal()) ];

     }//end init
 
     //This code is executed when the player attempst to solve the game
     public static int accuse(String userInput_murderer, String userInput_weapon, String userInput_room)
     {
        int correctAnswers = 0; //a running count of the correct answers


        //compares the the entered killer to the stored killer, if correct
	   //imcraments count
        if(userInput_murderer.compareTo(solution_suspect.toString()) == 0)
            correctAnswers++;
        
        //compares the entered weapon to the stored weapon, if correct
        //incraments count
        if(userInput_weapon.compareTo(solution_weapon.toString()) == 0)
            correctAnswers++;
        
        //compares the entered room to the stored room, if correct
        //incraments count
        if(userInput_room.compareTo(solution_room.toString()) == 0)
            correctAnswers++;
        
	return correctAnswers;
    }//end accuse
   
    //prints the correct answer to the screen.
    public static void print()
    {
        System.out.println("\nThe current solution is: ");
        System.out.println("Killer = " + solution_suspect + "\n" + "Weapon = "+ 
                solution_weapon + "\n" + "Room = " + solution_room);
        
    }//end print
  
}//end Solution

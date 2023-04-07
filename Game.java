/**
 * Author: Tom Chiapete
 * Partners: Rick Gearheart and Martin Aber
 * Game Class
 * 15-Sept-2006
 * 
 * This class is our current setup to allow players to enter commands.
 * This allows players to create and initialize a game, switch rooms, 
 * accuse - using the Solution class method, and ask for help by listing 
 * a list of commands, suspects, weapons, and rooms.
 */


// Import libraries

import java.io.*;
import java.util.*;
import javax.swing.*;


public class Game
{
    
    // Data members    
    protected static int nCurrentRoom;
    protected static String sCurrentRoom;

	// Solution.Room searches Solution for the variable type of Room to typecast
	// it to the same value as the enum.
    public static Solution.Room sCurrentRoom2;
    protected static int totalAccuse;
    protected static int totalMove;
    protected static int totalQuestion;
    protected static int totalSearch;
    
    // Constants
    protected static final int NUM_ROOMS = 9;
    protected static final int NUM_SUSPECTS = 6;
    protected static final int NUM_WEAPONS = 6;
    protected static final int MAX_ROOM_INDEX = 8;
    
    // Array of Rooms and Array of Suspects Declaration
    protected static Room [] roomsArr;
    protected static Suspect [] suspectsArr;
    
   /**
    * main method()
    * Main execution of program.
    * 
    * Calls to initialize a game and its solution.
    * If from the command line, the user enters "test"
    * as a string argument for the main, this will 
    * execute the debug interface instead.
    * 
    * For normal execution, bring up command boxes.
    */
	
    public static void main(String [] args)
    {            
               
        // Initialize Game variables
        Game.init();
        
        // Intialize and create the solution.
        GUI.initGUI();
        
		// Set up the mansion
        Game.roomInit();

		// Display the 6 commands box
        GUI.chooseCommands();
            
        // Create a random number between 1 and the number of rooms
        nCurrentRoom = randomInt(0,NUM_ROOMS-1);
        
        // Update the room name.  Show the room name.
        updateRoomName((Solution.Room.values()[nCurrentRoom]));
	   sCurrentRoom2 = ((Solution.Room.values()[nCurrentRoom]));
        GUI.showCurrentRoom(sCurrentRoom);

    }//end main


    /**
     * init() method
     * Sets or resets data members to their initial values.
     */
    private static void init()
    {
        sCurrentRoom = "";
        nCurrentRoom = 0;
        totalAccuse = 0;
        totalMove = 0;
        totalQuestion = 0;
        totalSearch = 0;
    }
    
	/** 
	 * roomInit() method
	 * Sets up the mansion and set up the alibis.  
	 * If they're not the murderer, stick them into a random room.
	 */
    private static void roomInit()
    {
        // Initialize Arrays
        roomsArr = new Room [NUM_ROOMS];
        suspectsArr = new Suspect [NUM_SUSPECTS];

		// Set up the suspect names
        for (int i =0; i < NUM_SUSPECTS; i++)
        {
            suspectsArr[i] = new Suspect(Solution.Suspect.values()[i]);
            
        }

		// Set up the room and room names
        for (int i =0; i < NUM_ROOMS; i++)
        {
            roomsArr[i] = new Room();
            roomsArr[i].setRoom(Solution.Room.values()[i]);
        }

		// Add the suspects to the rooms.  
		// Stick the murderer in the murder room.
		// Stick everyone else in a random room.
        for (int i =0; i < NUM_SUSPECTS; i++)
        {
            int randomRoomNum;
            // HE DUNIT!!! Stick the murderer in the murder room, and also in the bogus alibi room
            if (suspectsArr[i].myself == (Solution.solution_suspect))
            {
                roomsArr[Solution.solution_room.ordinal()].addSuspect(Solution.Suspect.values()[i]);
                roomsArr[MAX_ROOM_INDEX].addSuspect(Solution.Suspect.values()[i]);
		suspectsArr[i].suspectRoom = roomsArr[MAX_ROOM_INDEX];
		
                
            }
			// If they're not the murderer stick them in a random room
            else
            {
                randomRoomNum = (int)((Math.random() *1000)% (NUM_ROOMS-1));

				// While the random number equals the solution room ordinal...
                while (randomRoomNum == Solution.solution_room.ordinal())
                    randomRoomNum = (int)((Math.random() * 1000) % (NUM_ROOMS-1));

				// Finally, add suspect to the random room
                roomsArr[randomRoomNum].addSuspect(Solution.Suspect.values()[i]);
		suspectsArr[i].suspectRoom = roomsArr[randomRoomNum];
            
            }   
        }

		// Add weapons to the rooms
        for(int i =0; i < NUM_WEAPONS; i++)
        {
            int randomRoomNum;
            // Stick the murder weapon in the murder room
            if (Solution.solution_weapon.ordinal() == Solution.Weapon.values()[i].ordinal())
            {
            	roomsArr[Solution.solution_room.ordinal()].addWeapon(Solution.Weapon.values()[i]);
            } 
			// If its not the murder weapon, place it in a random room
            else
            {
                randomRoomNum = (int)((Math.random() * 1000)% (NUM_ROOMS-1));
                while (randomRoomNum == Solution.solution_room.ordinal())
                    randomRoomNum = (int)((Math.random() * 1000)% (NUM_ROOMS-1));
                roomsArr[randomRoomNum].addWeapon(Solution.Weapon.values()[i]);
            }
        }
        
		// Stick some random stuff in the alibi room for the murderer to lie about
        int randomNum;

		// Add the weapon at the random number to the alibi room (MAX_ROOM)
        randomNum = (int)((Math.random() *1000)% NUM_SUSPECTS);
        while (randomNum == Solution.solution_weapon.ordinal())
            randomNum = (int)((Math.random() *1000)% NUM_SUSPECTS);
        roomsArr[MAX_ROOM_INDEX].addWeapon(Solution.Weapon.values()[randomNum]);

		// Do the ewapon at the random number generated to the alibi room
        randomNum = (int)((Math.random() * 1000)% NUM_SUSPECTS);
        while(randomNum == Solution.solution_suspect.ordinal())
            randomNum = (int)((Math.random() *1000)% NUM_SUSPECTS);
        roomsArr[MAX_ROOM_INDEX].addSuspect(Solution.Suspect.values()[randomNum]);
                
        //for (int i =0; i < 9; i++)
            //roomsArr[i].print();        
    }   

	/**
	 * search() method
	 * This method will search for weapon or weapons in the current room the user is in.
	 * This returns a String of what it finds, if anything.
	 */
	public static String search()
    {

		// Search for the weapons and store them off in a String array
        String weaponArr[] = roomsArr[nCurrentRoom].searchWeapons();
            
        String writeIn = ""; // The text to be returned to be written into GUI later
        int counter = 0; // Counter - if 0 weapons found, let the user know later

		// Get weapons and their alibis, and add them to the writeIn variable
        for (int x = 0; x < weaponArr.length; x++)
        {
            if (weaponArr[x] != null)
            {
                //writeIn += weaponArr[x]+ Game.roomsArr[Game.nCurrentRoom].weapon_alibis.getEntry(counter+1)+"\n";   
                writeIn += roomsArr[nCurrentRoom].weapon_alibis.getEntry(counter+1) + "\n";
                counter++;
            }
        } 

		// There were no weapons found in the current room.  Send back a friendly message.
        if (counter == 0)
            writeIn = "There were no weapons found in this room.";

		// Return the search results.
		return sCurrentRoom+" Search:\n\n"+writeIn;        
    }

	/**
	 * solve() method
	 * Solve is more of a debugging method.  Allows to see what suspects and weapons are in the rooms.
	 */
    public static String[] solve()
    {
	   String a[] = new String[Solution.Room.MAX_ROOM.ordinal()+1];
		// Print everything found in the rooms
        for (int x = 0; x < roomsArr.length; x++)
        {
	   	a[x] = roomsArr[x].print();     
        }  
	   return a;       
    }
    
    /**
     * randomInt() public method
     * This generates a random integer using the lower and upper bound
     * parameters.
     * 
     */
    public static int randomInt(int lowerBound, int upperBound)
    {
        // If the lowerBound argument is actually greater than the upperBound,
        // swap the numbers so we have something to work with.
        if (lowerBound > upperBound)
        {
            int temp = upperBound;
            upperBound = lowerBound;
            lowerBound = temp;
        }
        
        // Save some work if the bounds are equal.  Return if equal
        else if (lowerBound == upperBound)
            return lowerBound;
            
        // Compute a random number
        return (int)Math.floor(Math.random()*upperBound+lowerBound);
    }

    /**
     * move() method
     * Moves to another room.
     * Calls GUI to alert the user as to his or her move.
     */
    public static void move(Solution.Room newRoom)
    {
        // Increment move counter -- for scoring
        totalMove++;

        // Find and attach the current room name.  Output room name.
        updateRoomName(newRoom);
        GUI.showMovingToRoom(newRoom.toString());
		nCurrentRoom = newRoom.ordinal();
		sCurrentRoom2 = newRoom;
    }

    /** 
     * accuse() method
     * This will execute the Solution class accuse method and tell the user 
     * how many correct answers he/she has.
     */
    public static void accuse()
    {
        String murderer=null;
	   String weapon=null;
	   String room=null;
        // Increment the accuse counter.  Used later for scoring.
        totalAccuse++;
        
        // The total number of correct answers
        int totalCorrect;
        totalCorrect = Solution.accuse(murderer, weapon, room); //ClueGUI.accuseGUI();
        
        // If the player has 3 correct answers, then yay, the player wins.
		// Otherwise, tell them how many correct answers they have.
        GUI.showAccuseResult(totalCorrect);
    }

    /**
       Suspect() method
       This method will be called from the main menu, and it will read in 
       a few values and then ask the character a couple of questions

    */
    public static String suspect(Solution.Suspect questionee,
				 Solution.Suspect personOfInterest,
				 Solution.Weapon weaponOfInterest)
    {
	int index = -1;
	String alibi;
	for(int i =0; i < NUM_SUSPECTS; i++)
	    {
		if(questionee.equals(Solution.Suspect.values()[i]))
	   		   index = i;
	
	
	    }
	
	alibi = suspectsArr[index].check_suspect(personOfInterest);
	alibi = alibi +"\n" + suspectsArr[index].check_weapon(weaponOfInterest);
	
	alibi = alibi +"\n"+ suspectsArr[index].check_room(Solution.Room.valueOf(sCurrentRoom));
	return alibi;
					

    }
    
    /**
     * updateRoomName() method
     * This method takes in a room, and updates the room name String data member.
     */
    private static void updateRoomName(Solution.Room tempRoom)
    {      
        sCurrentRoom = tempRoom.toString();
	   sCurrentRoom2 = tempRoom;
    }

}

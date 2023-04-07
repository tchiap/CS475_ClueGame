/*
 * Room.java
 *
 * Author: Martin Aber
 * Partners: Rick Gearheart and Thomas Chiapete
 *
 * This class holds linked lists of alibis for the weapons and suspects
 * in the room object.  Most of the methods deal with internal actions by
 * the system; however, the user will print out the suspects and weapons 
 * or just the weapons for each room.
 */

import java.util.*;
import javax.swing.*;

//creates room objects to hold suspect and weapon alibis
public class Room
{
     RoomLinkedList suspect_alibis = new RoomLinkedList();
     RoomLinkedList weapon_alibis = new RoomLinkedList();
     int roomID;
	static String contains;

    public Room()
    {
        init();  //clears the linked lists
    }//end Room constructor

    public static void main(String[] args) { }//end main

    public void init()
    {
        suspect_alibis.clear();
        weapon_alibis.clear();
		print();
    }//end init

    //adds a suspect alibi to the linked list
    public void addSuspect(Solution.Suspect suspectName)
    {
        Alibi alibiList = new Alibi("suspectalibis.txt");
        Suspect_Alibi suspectAlibi = new Suspect_Alibi(alibiList, 
                                suspectName.ordinal(), roomID);

        suspect_alibis.add(suspectAlibi.getAlibi(), suspectName.ordinal());
    }//end addSuspect

    //adds a weapon alibi to the linked list
    public void addWeapon(Solution.Weapon weaponName)
    {
        Alibi alibiList = new Alibi("weaponalibis.txt");
        Weapon_Alibi weaponAlibi = new Weapon_Alibi(alibiList, 
                                    weaponName.ordinal(), roomID);

        weapon_alibis.add(weaponAlibi.getAlibi(), weaponName.ordinal());
    }//end addWeapon

    //sets the roomID to that of the enums value
    public void setRoom(Solution.Room roomName)
    {
        roomID = roomName.ordinal();
    }//end setRoomId

    //checks for an alibi of a given person
    public String searchPerson(Solution.Suspect suspect_looked_for)
    {
        //holds the location of the suspects alibi in the linked list
        int location = suspect_alibis.containsSuspect(suspect_looked_for);

        if(location > -1)
            return(suspect_alibis.getEntry(location));
        else
            return("I don't know where " + suspect_looked_for + 
                                            " was that night.");
    }//end searchPerson

    //checks for an alibi of a given weapon
    public String searchWeapon(Solution.Weapon weapon_looked_for)
    {
        //holds the location of the weapons alibi in the linked list
        int location = weapon_alibis.containsWeapon(weapon_looked_for);

        if(location > -1)
            return (weapon_alibis.getEntry(location));
        else
            return ("I did not see the " + 
                                weapon_looked_for + " that night.");
    }//end searchWeapon

    //matches the entered roomName enum value to the room id
    public String checkRoom(Solution.Room roomName)
    {
        if(roomName.ordinal() == roomID)
            return("I was in the " + roomName + " that night.");
        else
            return("I was not in the " + 
                                        roomName + " that night.");
    }//end checkRoom

    //searches for all the weapons in the room
    public void search()
    {
        int arrLocation = 0; //counter variable
        String weaponArr [] = weapon_alibis.gatherWeapons(weapon_alibis);

        System.out.println("The weapons found in the " + 
                            Solution.Room.values()[roomID] + " are: ");
        while(weaponArr[arrLocation] != null)
        {
            System.out.println(weaponArr[arrLocation]);
            arrLocation++;
        }
    }//end serach
    
    public String [] searchWeapons()
    {
        String weaponArr[] = weapon_alibis.gatherWeapons(weapon_alibis);
        return weaponArr;        
    }
    
    public String [] searchSuspects()
    {
        String suspectArr[] = suspect_alibis.gatherSuspects(suspect_alibis);
        return suspectArr;
    }


	public String print()
	{
	     int arrLocation = 0; //counter variable
		String suspectArr [] = suspect_alibis.gatherSuspects(suspect_alibis);
		String weaponArr [] = weapon_alibis.gatherWeapons(weapon_alibis);

		contains = "The suspects found in the " + Solution.Room.values()[roomID] + " are: ";
		while(suspectArr[arrLocation] != null)
		{
			contains = contains + "< " + suspectArr[arrLocation] + " > " ;
			arrLocation++;
		}

		arrLocation=0; //re-zero the counter

		contains = contains + "\n" + "The weapons found in the " + Solution.Room.values()[roomID] + " are: ";
		while(weaponArr[arrLocation] != null)
		{
			contains = contains + "< " + weaponArr[arrLocation] + " > " ;
			arrLocation++;
		}


		return (contains + "\n");
	}

}//end Room

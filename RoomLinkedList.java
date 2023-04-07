/*
 * RoomLinkedList.java
 *
 * Author: Martin Aber
 * Partners: Rick Gearheart and Thomas Chiapete
 * 
 * This class holds the nodes for linked lists in the Room.java class.
 * It also processes searches due to the private status of the nodes.
*/


import java.util.*;
import javax.swing.*;

public class RoomLinkedList
{
	Node firstNode; // reference to first node
    	int length; // number of entries in list

	public final void clear()
	{
		firstNode = null;
      	length = 0;
	}//end clear


	//Node class and methods to set weapons and suspects into the room.

	private class Node
    	{
		private String data; // data portion
        	private Node next; // link to next node
		private int location; //keeps the ordinal of the enum value

        	private Node(String dataPortion, int ordinal)
        	{
            	data = dataPortion;
            	location = ordinal;
        	} // end constructor

    	} // end Node

	//adds a new node to the linked list
	public boolean add(String newEntry, int enumOrdinal)
    	{
      	Node newNode = new Node(newEntry, enumOrdinal);

      	if (isEmpty())
            firstNode = newNode;
        	else // add to end of nonempty list
        	{
            	Node lastNode = getNodeAt(length);
            	lastNode.next = newNode; // make last node reference new node
        	} // end if
        	length++;
        	return true;
    	} // end add

	//checks if the list is empty
	public boolean isEmpty()
    	{
    	  return length == 0;
    	} // end isEmpty

	//returns the data of a node at a given location
	public String getEntry(int givenPosition)
	{
		String result = null;
		 
		if(!isEmpty() && (givenPosition>=1) && (givenPosition <= length))
			result = getNodeAt(givenPosition).data;

	    return result;
	}//end getEntry

	//returns a node at a given postion
	private Node getNodeAt(int givenPosition)
	{
	    Node currentNode = firstNode;
	    // traverse the list to locate the desired node
	    for (int counter = 1; counter < givenPosition; counter++)
		    currentNode = currentNode.next;

		return currentNode;
	}//end getNodeAt

	//seraches for a suspect
	public int containsSuspect(Solution.Suspect mySuspect)
	{
		boolean found = false;
		Node currentNode = firstNode;
	     int locCount = 1; //location value

	     //walks through the linked list looking for mySuspect as a substring
		while(!found && (currentNode != null))
		{
		    	if(mySuspect.ordinal() == currentNode.location)
				found = true;
			else
			{
				currentNode = currentNode.next;
				locCount++;
			}
		}
		
		if(found) return locCount; //returns the location of mySuspect in the list
		else return -1; //returns a known invaild number if it is not found
	}//end containsSuspect

	//seraches for a weapon
	public int containsWeapon(Solution.Weapon myWeapon)
	{
	    boolean found = false;
	    Node currentNode = firstNode;
	    int locCount = 1; //location value
	    
		//walks through the linked list looking for myObject as a substring
		while(!found && (currentNode != null))
		{
		    	if(myWeapon.ordinal() == currentNode.location)
				found = true;
			else
			{
				currentNode = currentNode.next;
				locCount++;
			}
			
		}

		if(found) return locCount; //returns the location of myObject in the list
		else return -1;  //returns a known invalid number if it is not found
	}//end containsWeapon

	//returns an array of all the weapons in the list
	public String[] gatherWeapons(RoomLinkedList weapon_alibis)
	{
		int count = 0;
		String weaponArr [] = new String[Solution.Weapon.MAX_WEAPON.ordinal()];
		Node currentNode = firstNode;

		//walks through the list and stores the string value of the enum location
		while(currentNode != null)
		{
			weaponArr[count] = (Solution.Weapon.values()[currentNode.location]).toString();
			currentNode = currentNode.next;
			count++;
		}
		return weaponArr;
	}//end gatherWeapons

	//returns and array of all suspects in the list
	public String[] gatherSuspects(RoomLinkedList suspect_alibis)
	{
		int count = 0;
		String suspectArr [] = new String[Solution.Suspect.MAX_SUSPECT.ordinal()];
		Node currentNode = firstNode;

		//walks through the list and stores the string value of the enum location
		while(currentNode != null)
		{
			suspectArr[count] = (Solution.Suspect.values()[currentNode.location]).toString();
			currentNode = currentNode.next;
			count++;
		}
		return suspectArr;
	}//end gatherSuspects

}//end RoomLinkedList.java

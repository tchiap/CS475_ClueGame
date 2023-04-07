/*
 Alibi class
 Author: Rick Gearheart
 Partners: Martin Aber and Thomas Chiapete

 This class will read in a list of alibis from a text file
 and then store them all in a nifty little array for your 
 alibi making pleasure

 To run the test method run the program from command line
 and use the argument 'test'. The program will then generate
 an alibi for every suspect, and every weapon.
*/

import java.util.*;
import javax.swing.*;
import java.io.*;



public class Alibi
{
    private String[] possibleAlibis;
    private static int randomModifier = 1337;
    private LinkedList chosenAlibis = new LinkedList();
    private static final int totalSuspects = 6;
    private static final int totalWeapons = 6;
    

    public static void main(String [] args)
    {
	if(args[0] != null)
	    if (args[0].equals("test"))
		test();
    }


    // The test method will select all of the alibis
    // in a random order, with no duplicates
    public static void test()
    {
	String testAlibi;

	// New up a suspect alibi list and grab an alibi for each suspect
       	Alibi suspectAlibis = new Alibi("suspectalibis.txt");
	for (int i = 0; i < totalSuspects; i++)
	{
	    Suspect_Alibi testSuspect = new Suspect_Alibi(suspectAlibis,i, i);
	    testAlibi = testSuspect.getAlibi();
	    System.out.println(testAlibi+ "\n");
	}
	// New up a weapon alibi list and grab an alibi for each weapon
	// for the purposes of the test they are stuck in rooms sequentially
	Alibi weaponAlibis = new Alibi("weaponalibis.txt");
	for (int i = 0; i < totalWeapons; i++)
	{
	    Weapon_Alibi testWeapon = new Weapon_Alibi(weaponAlibis,i,i);
	    testAlibi = testWeapon.getAlibi();
	    System.out.println(testAlibi+ "\n");
	}
	
	
    }
    

    // Constructor that reads in our alibis
    public Alibi(String filename)
    {
	possibleAlibis = populateArrayFromFile(filename);

    }
    
    // This method will take a string representing a filename
    // and then read in each line from that text file as an 
    // array item and return the array
    public String[] populateArrayFromFile(String filename)
    {
	LinkedList possibleAlibiReadList = new LinkedList();
	try
	{
	    Scanner alibiReader = new Scanner(new File(filename));

	    while ( alibiReader.hasNextLine())
	    {
		possibleAlibiReadList.add(alibiReader.nextLine());
	    }

	    int size = possibleAlibiReadList.size();
	    String[] tempAlibiArray = new String[size];

	    for (int i =0; i< tempAlibiArray.length; i++)
	    {
		tempAlibiArray[i] = (String)possibleAlibiReadList.poll();
	    }
	    return tempAlibiArray;
	}

	catch (FileNotFoundException e)
	{
	    System.out.println(filename + " does not exist.");
	    System.exit(1);
        }
	return null;

    }
   
    // this method will randomly grab an alibi and make sure
    // it hasn't previously been selected
    public String getAlibi()
    {
	
	String alibiHolder = possibleAlibis[(int)(Math.random()*
			       randomModifier)% possibleAlibis.length];
	if (chosenAlibis.contains(alibiHolder))
	    alibiHolder = getAlibi();
	chosenAlibis.add(alibiHolder);
	return alibiHolder;   
    }

}

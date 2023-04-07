/*
  Suspect.java
  Author: Rick Gearheart
  Partners: Martin Aber and Tom Chiapete
  Suspect Class
  
  To run the test method of the class, enter 'test' as an
  argument to the java command the class will create a few
  suspects and stick them in a room with some weapons
  it will then ask them questions

*/

public class Suspect
{
    //member variables
    Room suspectRoom;
    static Room[] testroom; // Array of Rooms used solely for testing
    Solution.Suspect myself;  

    public static void main(String [] args)
    { }
    
    public Suspect(Solution.Suspect identity)
    {
	myself = identity;
    }

    // Initializes a Suspect and puts him or her in a room
    // (passes an index.)
    public Room in_room(Room room)
    {
	suspectRoom = room;
	suspectRoom.addSuspect(myself);
	return suspectRoom;

    }

    // Uses Suspect's room index to check for 
    // an alibi for a Suspect passed as a parameter
    public String check_suspect(Solution.Suspect personOfInterest)
    {
	
	return suspectRoom.searchPerson(personOfInterest);

    }
 
    // Uses room index to check for an alibi for a WEAPON
    // passed as a parameter
    public String check_weapon(Solution.Weapon weaponOfInterest)
    {
	return suspectRoom.searchWeapon(weaponOfInterest);

    }

    // Uses room index to check if this Suspect was in the ROOM
    // passed as a parameter
    public String check_room(Solution.Room roomOfInterest)
    {

	return suspectRoom.checkRoom(roomOfInterest);

    }

    // Uses room pointer to print all information about the room
    // the Suspect was in. Used for Debug Only
    public void print()
    {

	suspectRoom.print();
    }
    
    // Test logic to ensure proper operation of coded logic above
    public static void test()
    {
	testroom = new Room[2];
	testroom[0] = new Room();
	testroom[1] = new Room();
	testroom[0].setRoom(Solution.Room.values()[1]);
	testroom[1].setRoom(Solution.Room.values()[2]);


	// add the Revolver lead pipe and knife to our room
	testroom[0].addWeapon(Solution.Weapon.values()[0]);
	testroom[0].addWeapon(Solution.Weapon.values()[1]);
	testroom[0].addWeapon(Solution.Weapon.values()[2]);
	testroom[1].addWeapon(Solution.Weapon.values()[4]);
	
	// new up a few suspects to test with
	Suspect mrGreen = new Suspect(Solution.Suspect.values()[1]);
	Suspect profPlum = new Suspect(Solution.Suspect.values()[3]);
	Suspect msScarlet = new Suspect(Solution.Suspect.values()[2]);
	testroom[1] = msScarlet.in_room(testroom[1]);
	testroom[0] = mrGreen.in_room(testroom[0]);
	testroom[0] = profPlum.in_room(testroom[0]);

	// Ask Mr. Green about the Lead Pipe
	System.out.println("Asking Mr. Green about the Lead Pipe");
	mrGreen.check_weapon(Solution.Weapon.values()[1]);
	System.out.println("");
	
	// Ask Miss Scarlet about the Wrench
	System.out.println("Asking Miss Scarlet about the Wrench");
	msScarlet.check_weapon(Solution.Weapon.values()[5]);
	System.out.println("");

	// Ask Professor Plum about Mr. Green
	System.out.println("Asking Professor Plum about Mr. Green");
	profPlum.check_suspect(Solution.Suspect.values()[1]);
	System.out.println("");

	// Ask Professor Plum about Miss Scarlet
	System.out.println("Asking Professor Plum about Miss Scarlet");
	profPlum.check_suspect(Solution.Suspect.values()[2]);
	System.out.println("");
	testroom[0].print();
	System.out.println("");
	testroom[1].print();
	
    }

}

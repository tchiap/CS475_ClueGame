/*
 Weapon_Alibi class
 Author: Rick Gearheart
 Partners: Martin Aber and Thomas Chiapete

 This class will be used to hold a weapon's alibi, 
 the alibi itself is grabbed from the alibi set that
 it is created with

*/


public class Weapon_Alibi
{
    private String alibiText = "not set";
    private int myRoomValue;
    private int myWeaponValue;


    public static void main (String[] args)
    {

    }

    public Weapon_Alibi(Alibi possibleAlibiSet,int weaponValue, int roomValue)
    {
	alibiText = possibleAlibiSet.getAlibi();
	myRoomValue = roomValue;
	myWeaponValue = weaponValue;
    }

    public String getAlibi()
    {
	return ("The " + Solution.Weapon.values()[myWeaponValue].toString() +
		" " + alibiText + "." );
    }

}

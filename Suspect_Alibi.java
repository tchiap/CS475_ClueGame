/*
 Suspect_Alibi class
 Author: Rick Gearheart
 Partners: Martin Aber and Thomas Chiapete

 This class will be used to hold a suspect for each suspect
 the alibi itself will be grabbed from the alibi set that
 is used to create the Suspect_Alibi

*/

public class Suspect_Alibi
{
    private String alibiText= "not set";
    private int mySuspectValue;

    public static void main(String[] args)
    {

    }
   
    public Suspect_Alibi(Alibi possibleAlibiSet, int suspectValue, int roomValue)
    {
	alibiText = possibleAlibiSet.getAlibi();
	mySuspectValue = suspectValue;

    }


    public String getAlibi()
    {
	return (Solution.Suspect.values()[mySuspectValue].toString()
		+" "+ alibiText );
    }
}

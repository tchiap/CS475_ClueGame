/**
* Author: Tom Chiapete
* Partners: Rick Gearheart and Martin Aber
* GUI Class
* 9-Oct-2006
*
* This class is our current setup for the graphical user interface.
* Other classes can access the GUI methods in this class, while they run
* the logic.
*
*/
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI
{

    public final static boolean RIGHT_TO_LEFT = false;

    protected static JFrame roomFrame;
    protected static JFrame questionFrame;
    protected static JFrame accuseFrame;
    protected static JFrame suspectFrame;

    protected static String suspectChosen;
    protected static String weaponChosen;
    protected static String roomChosen;

    protected static Solution.Suspect witnessChosen;
    protected static Solution.Suspect suspectChosen2;
    protected static Solution.Weapon weaponChosen2;
    protected static Solution.Room selectedRoom;
    protected static String myCurrentRoom;
    private static int totalPoints;

    //constructor
    public GUI()
    { } //end constructor

    //main method
    public static void main(String[] args)
    { } //end main

    //initializes the game
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

    //sets behavior for the main window
    public static void chooseCommands()
    {
        JFrame.setDefaultLookAndFeelDecorated(false);

        JFrame roomFrame = new JFrame("CLUE");

        roomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane and components in GridLayout
        addComponentsToCommandPane(roomFrame.getContentPane());

        roomFrame.pack();
        roomFrame.setVisible(true);
    }// end chooseCommands

    //sets behavior for the suspect window
    public static void suspect()
    {
        JFrame.setDefaultLookAndFeelDecorated(false);

        JFrame questionFrame = new JFrame("SUSPECT");

        questionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



        //Set up the content pane and components in GridLayout
        addComponentsToQuestionPane(questionFrame.getContentPane());

        questionFrame.pack();
        questionFrame.setVisible(true);
    }// end suspect

    //sets behavior for the accuse window
    public static void accuse()
    {

        JFrame.setDefaultLookAndFeelDecorated(false);

        JFrame accuseFrame = new JFrame("Accuse...");

        accuseFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



        //Set up the content pane and components in GridLayout
        addComponentsToAccusePane(accuseFrame.getContentPane());

        accuseFrame.pack();
        accuseFrame.setVisible(true);

    }//end accuse

    //sets behavior for the move window
    public static void move()
    {
        JFrame.setDefaultLookAndFeelDecorated(false);

        JFrame roomFrame = new JFrame("Choose Room:");

        roomFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);



        //Set up the content pane and components in GridLayout
        addComponentsToMovePane(roomFrame.getContentPane());

        roomFrame.pack();
        roomFrame.setVisible(true);

    }//end move

    //Create the buttons for the "Accuse" button
    public static void addComponentsToAccusePane(Container contentPane)
    {
        if (RIGHT_TO_LEFT)
            
		contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

	   // Any number of rows and 2 columns
        contentPane.setLayout(new GridLayout(0,2));

        //Create labels for each section
        JLabel lblSuspect = new JLabel("Suspect:");
        JLabel lblWeapon = new JLabel("Using Weapon:");
        JLabel lblInRoom = new JLabel("In Room:");

        //Create a blank space
        JLabel lblBlank = new JLabel("  ");

        //********** Accuse Suspects **********
        //Create buttons for the Suspects
        JButton btnPeacock = new JButton("Mrs. Peacock");
        JButton btnGreen = new JButton("Mr. Green");
        JButton btnScarlet = new JButton("Miss Scarlet");
        JButton btnPlum = new JButton("Professor Plum");
        JButton btnMustard = new JButton("Colonel Mustard");
        JButton btnWhite = new JButton("Miss White");

        //Add a Suspect label
        contentPane.add(lblSuspect);
        contentPane.add(new JLabel("..."));

        //Add the Suspect buttons to the window
        contentPane.add(btnPeacock);
        contentPane.add(btnGreen);
        contentPane.add(btnScarlet);
        contentPane.add(btnPlum);
        contentPane.add(btnMustard);
        contentPane.add(btnWhite);

        //Create listener for Mrs. Peacock
        btnPeacock.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[0].toString(); }
            } );

        //Create listener for Mr. Green
        btnGreen.addActionListener(
             new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[1].toString(); }
            } );

        //Create listener for Miss. Scarlet
        btnScarlet.addActionListener(
             new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[2].toString(); }
            } );

        //Create listener for Prof. Plum
        btnPlum.addActionListener(
             new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[3].toString(); }
            } );

        //Create listener for Col. Mustard
        btnMustard.addActionListener(
             new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[4].toString(); }
            } );

        //Create listener for Mrs. White
        btnWhite.addActionListener(
             new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen = Solution.Suspect.values()[5].toString(); }
            } );

        //********** Accuse Weapons **********
        //Create buttons for the weapons
        JButton btnRevolver = new JButton("Revolver");
        JButton btnLeadPipe = new JButton("Lead Pipe");
        JButton btnKnife = new JButton("Knife");
        JButton btnCandlestickHolder = new JButton("Candlestick Holder");
        JButton btnRope = new JButton("Rope");
        JButton btnWrench = new JButton("Wrench");

        //Add a label for the weapons
        contentPane.add(lblWeapon);
        contentPane.add(new JLabel("..."));

        //Add the buttons to the window
        contentPane.add(btnRevolver);
        contentPane.add(btnLeadPipe);
        contentPane.add(btnKnife);
        contentPane.add(btnCandlestickHolder);
        contentPane.add(btnRope);
        contentPane.add(btnWrench);

        //Add a listener for the Revolver
        btnRevolver.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[0].toString(); }
            } );

        //Add a listener for the Lead Pipe
        btnLeadPipe.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[1].toString(); }
            } );

        //Add a listener for the Knife
        btnKnife.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[2].toString(); }
            } );

        //Add a listener for the Candlestick Holder
        btnCandlestickHolder.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[3].toString(); }
            } );

        //Add a listener for the Rope
        btnRope.addActionListener(
          new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[4].toString(); }
            } );

        //Add a listener for the Wrench
        btnWrench.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen = Solution.Weapon.values()[5].toString(); }
            } );

        //********** Accuse Roms **********
        //Create buttons for the rooms
        JButton btnLibrary = new JButton("Library");
        JButton btnStudy = new JButton("Study");
        JButton btnBilliardRoom = new JButton("Billiard Room");
        JButton btnBallroom = new JButton("Ballroom");
        JButton btnHall = new JButton("Hall");
        JButton btnDiningRoom = new JButton("Dining Room");
        JButton btnKitchen = new JButton("Kitchen");
        JButton btnConservatory = new JButton("Conservatory");

        //Add label for the rooms
        contentPane.add(lblInRoom);
        contentPane.add(new JLabel("..."));

        //Add the buttons for the rooms
        contentPane.add(btnLibrary);
        contentPane.add(btnStudy);
        contentPane.add(btnBilliardRoom);
        contentPane.add(btnBallroom);
        contentPane.add(btnHall);
        contentPane.add(btnDiningRoom);
        contentPane.add(btnKitchen);
        contentPane.add(btnConservatory);

        //Add a listener for the Library
        btnLibrary.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[0].toString(); }
            } );

        //Add a listener for the Study
        btnStudy.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[1].toString(); }
            } );

        //Add a listener for the Billiard Room
        btnBilliardRoom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[2].toString(); }
            } );

        //Add a listener for the Ballroom
        btnBallroom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[3].toString(); }
            } );

        //Add a listener for the Hall
        btnHall.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[4].toString(); }
            } );

        //Add a listener for the Dining room
        btnDiningRoom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[5].toString(); }
            } );

        //Add a listener for the Kitchen
        btnKitchen.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[6].toString(); }
            } );

         //Add a listener for the Conservatory
         btnConservatory.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { roomChosen = Solution.Room.values()[7].toString(); }
            } );

        //********** Accuse Button **********
        //Create a 'Submit' Button for the accuse
        JButton btnAccuse = new JButton("Accuse!");
        contentPane.add(new JLabel("."));
        contentPane.add(new JLabel("."));
        contentPane.add(new JLabel("."));
        contentPane.add(btnAccuse);


        btnAccuse.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
			 Game.totalAccuse++;
                int correctAnswers = Solution.accuse(suspectChosen,weaponChosen,roomChosen);
                GUI.showAccuseResult(correctAnswers);
            }
        } );

    }//end addComponentsToAccusePane

    //Creates the buttons for "Main" window
    public static void addComponentsToCommandPane(Container contentPane)
    {
        if (RIGHT_TO_LEFT)
            contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //Any number of rows and 2 columns
        contentPane.setLayout(new GridLayout(0,3));

        //Creates the buttons for the main window
        JButton btnAccuse = new JButton("Accuse");
        JButton btnMove = new JButton("Move");
        JButton btnHelp = new JButton("Help");
        JButton btnQuit = new JButton("Quit");
        JButton btnSuspect = new JButton("Suspect");
        JButton btnSearch = new JButton("Search");
        JButton btnSolve = new JButton("Solve");

        //Adds the buttons to the window
        contentPane.add(btnAccuse);
        contentPane.add(btnMove);
        contentPane.add(btnSuspect);
        contentPane.add(btnSearch);
        contentPane.add(btnHelp);
        contentPane.add(btnQuit);
        contentPane.add(btnSolve);

        //Adds a listener for the Accuse button
        btnAccuse.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { GUI.accuse(); }
            } );

        //Adds a listener for the Move button
        btnMove.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { GUI.move(); }
            } );

        //Adds a listener for the Help button
        btnHelp.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { GUI.help(); }
            } );

        //Adds a listener for the Quit button
        btnQuit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { GUI.exitHandler(); }
            } );

        //Adds a listener for the Search button
        btnSearch.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { JOptionPane.showMessageDialog(null,Game.search()); Game.totalSearch++;}
            } );

	   //Adds a listener for the Suspect button
        btnSuspect.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { GUI.suspect(); }
            } );

        //Adds a listener for the Solve button
        btnSolve.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { answer(); }
            } );

    }//end addComponentsToCommandPane

    //Displays the number of answers correct or "congratulations"
    public static void showAccuseResult(int correctAnswers)
    {
	   totalPoints = (Game.totalAccuse*50) + (Game.totalMove*5) +(Game.totalSearch*5) + Game.totalQuestion;
        // If the player has 3 correct answers, then yay, the player wins.
        if(correctAnswers == 3)
        {
            JOptionPane.showMessageDialog(null, "Congratulations! You've won!\n" +
				"You won in: \n" +Game.totalQuestion+ " questions, \n" + 
				Game.totalSearch + " searches, \n" + 
				Game.totalAccuse + " accuses, \nand " +
				Game.totalMove + " moves.  For a total of " +totalPoints+ " points." );
        }

        // Otherwise, play on.  Tell how close or far away they are.
        else
            JOptionPane.showMessageDialog(null, "You have " + correctAnswers + " correct answers.");
    }//end showAccuseResult

    //Creates a error message
    public static void showUnknownCommandDialog()
    {
        JOptionPane.showMessageDialog(null,"Unrecognized command.  Please try again.");
    }//end showUnknownCommandDialog

    //Creates a small window that lists command help.
    public static void help()
    {
        JOptionPane.showMessageDialog(null,
            "You currently in the " + myCurrentRoom + ".\n\n" +
            "Commands include:\n"+
            "help, suspect, move, search, accuse, quit, solve\n\n"+
            "Suspects include:\n"+
            "MRS_PEACOCK, MR_GREEN, MISS_SCARLET, PROFESSOR_PLUM, COLONEL_MUSTARD, MRS_WHITE\n\n"+
            "Weapons include:\n"+
            "ROPE, REVOLVER, KNIFE, CANDLESTICK_HOLDER, WRENCH, LEAD_PIPE\n\n"+
            "Rooms include:\n"+
            "LIBARY, STUDY, BILLIARD_ROOM, BALLROOM, HALL, DINING_ROOM, KITCHEN, CONSERVATORY");
    }//end help

    //Conferms if the player wants to exit
    public static void exitHandler()
    {
        // Receives the response.
        int response = JOptionPane.showConfirmDialog(null,
            "Do you wish to exit this game?");

        // Switch the response.  Execute appropriate actions.
        switch(response)
        {
            case JOptionPane.YES_OPTION: System.exit(0); break;
            case JOptionPane.NO_OPTION: break;
            case JOptionPane.CANCEL_OPTION:  break;
            case JOptionPane.CLOSED_OPTION:  break;
        }
    }//end exitHandler

    //Shows the current room the user is in
    public static void showCurrentRoom(String room)
    {
            myCurrentRoom=room;
            JOptionPane.showMessageDialog(null,"You are currently in the "+room+".");

    }//end showCurrentRoom

    //Shows the new room the user is moving to.
    public static void showMovingToRoom(String room)
    {
        myCurrentRoom=room;
        JOptionPane.showMessageDialog(null,"You have now moved to the "+room+".");
    }// end showMovingToRoom

    //This method sets the function "MOVE"
    public static void addComponentsToMovePane(Container contentPane)
    {
	   if (RIGHT_TO_LEFT)
            contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        //Creates a button for each room.
	   contentPane.setLayout(new GridLayout(0,2));
	   JButton btnLibrary = new JButton("Library");
   	   JButton btnStudy = new JButton("Study");
	   JButton btnBilliardRoom = new JButton("Billiard Room");
	   JButton btnBallRoom = new JButton("Ball Room");
	   JButton btnHall = new JButton("Hall");
	   JButton btnDiningRoom = new JButton("Dining Room");
	   JButton btnKitchen = new JButton("Kitchen");
	   JButton btnConservatory = new JButton("Conservatory");

        //adds the buttons to the move menu
	   contentPane.add(btnLibrary);
	   contentPane.add(btnStudy);
	   contentPane.add(btnBilliardRoom);
	   contentPane.add(btnBallRoom);
	   contentPane.add(btnHall);
	   contentPane.add(btnDiningRoom);
	   contentPane.add(btnKitchen);
	   contentPane.add(btnConservatory);

        //Calls the Game.move function for the Library
	   btnLibrary.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    selectedRoom = Solution.Room.values()[0];
                            Game.move(selectedRoom); }
                } );

            //Calls the Game.move function for the Study
            btnStudy.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[1];
                            Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the Billiard Room
            btnBilliardRoom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[2];
                            Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the Ball Room
            btnBallRoom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[3];
                            Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the Hall
            btnHall.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[4];
                            Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the DiningRoom
            btnDiningRoom.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[5];
                            Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the Kitchen
            btnKitchen.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[6];
			   Game.move(selectedRoom); }
		} );

            //Calls the Game.move function for the Conservatory
            btnConservatory.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { selectedRoom = Solution.Room.values()[7];
			   Game.totalMove++;
			   Game.move(selectedRoom); }

		} );

    }//end addComponentsToMovePane

    //Creates the 'SUSPECT' button for the main game window.
    public static void addComponentsToQuestionPane(Container contentPane)
    {
        if (RIGHT_TO_LEFT)
            contentPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

	// Any number of rows and 2 columns
        contentPane.setLayout(new GridLayout(0,2));

        // Label the catagories
        JLabel lblWitness = new JLabel("Question this Witness:");
        JLabel lblSuspect = new JLabel("About this Suspect:");
        JLabel lblWeapon = new JLabel("Using this Weapon:");

        JLabel lblBlank = new JLabel("  ");

        //Create a 'SUBMIT' button to submit answers
	   JButton btnQuestion = new JButton("Question!");

        //Create buttons to store the Suspects questioned
        JButton btnPeacock = new JButton("Mrs. Peacock");
        JButton btnGreen = new JButton("Mr. Green");
        JButton btnScarlet = new JButton("Miss Scarlet");
        JButton btnPlum = new JButton("Professor Plum");
        JButton btnMustard = new JButton("Colonel Mustard");
        JButton btnWhite = new JButton("Miss White");

        //Create buttons to store the Suspects questioned about
	   JButton btnPeacock2 = new JButton("Mrs. Peacock");
        JButton btnGreen2 = new JButton("Mr. Green");
        JButton btnScarlet2 = new JButton("Miss Scarlet");
        JButton btnPlum2 = new JButton("Professor Plum");
        JButton btnMustard2 = new JButton("Colonel Mustard");
        JButton btnWhite2 = new JButton("Miss White");

        //Create buttons to store the weapon values
        JButton btnRevolver = new JButton("Revolver");
        JButton btnLeadPipe = new JButton("Lead Pipe");
        JButton btnKnife = new JButton("Knife");
        JButton btnCandlestickHolder = new JButton("Candlestick Holder");
        JButton btnRope = new JButton("Rope");
        JButton btnWrench = new JButton("Wrench");

        //Add a label to the catagory
        contentPane.add(lblWitness);
        contentPane.add(new JLabel("..."));

        //Add buttons for the witness questioned
        contentPane.add(btnPeacock);
        contentPane.add(btnGreen);
        contentPane.add(btnScarlet);
        contentPane.add(btnPlum);
        contentPane.add(btnMustard);
        contentPane.add(btnWhite);

        //Add a lable for the Suspect questioned about.
        contentPane.add(lblSuspect);
        contentPane.add(new JLabel("..."));

        //Add buttons for the suspects
	   contentPane.add(btnPeacock2);
        contentPane.add(btnGreen2);
        contentPane.add(btnScarlet2);
        contentPane.add(btnPlum2);
        contentPane.add(btnMustard2);
        contentPane.add(btnWhite2);

        //Add label for the weapons
        contentPane.add(lblWeapon);
        contentPane.add(new JLabel("..."));

        //Add buttons for the weaopns
        contentPane.add(btnRevolver);
        contentPane.add(btnLeadPipe);
        contentPane.add(btnKnife);
        contentPane.add(btnCandlestickHolder);
        contentPane.add(btnRope);
        contentPane.add(btnWrench);

        //Add spaces and a submit button
        contentPane.add(new JLabel("."));
        contentPane.add(new JLabel("."));
        contentPane.add(new JLabel("."));
        contentPane.add(btnQuestion);

        //************** Witness Questioned **************
        //Create listeners for the Suspects that are questioned
        //Create a listener for Mrs. Peacock
        btnPeacock.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[0]; }
            } );

        //Create a listener for Mr. Green
        btnGreen.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[1]; }
            } );

        //Create a listener for Miss. Scarlet
        btnScarlet.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[2]; }
            } );

        //Create a listener for Prof. Plum
        btnPlum.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[3]; }
            } );

        //Create a listener for Col. Mustard
        btnMustard.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[4]; }
            } );

        //Create a listener for Mrs. White
        btnWhite.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { witnessChosen = Solution.Suspect.values()[5]; }
            } );

        //************** Suspects Questioned About **************
        //Create listeners for the Suspects that are questioned about
        //Create a listener for Mrs. Peacock
        btnPeacock2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[0]; }
            } );

        //Create a listener for Mr. Green
        btnGreen2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[1]; }
            } );

        //Create a listener for Miss. Scarlet
        btnScarlet2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[2]; }
            } );

        //Create a listener for Prof. Plum
        btnPlum2.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[3]; }
            } );

        //Create a listener for Col. Mustard
        btnMustard2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[4]; }
            } );

        //Create a listener for Mrs. White
        btnWhite2.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { suspectChosen2 = Solution.Suspect.values()[5]; }
            } );

        //************** Weapons Questioned About **************
        //Create listeners for the weapons questioned about
        //Create a listener for the Revolver
        btnRevolver.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[0]; }
            } );

        //Create a listener for the Lead Pipe
        btnLeadPipe.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[1]; }
            } );

        //Create a listener for the Knife
        btnKnife.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[2]; }
            } );

        //Create a listener for the Candlestick Holder
        btnCandlestickHolder.addActionListener(
           new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[3]; }
            } );

        //Create a listener for the Rope
        btnRope.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[4]; }
            } );

        //Create a listener for the Wrench
        btnWrench.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                { weaponChosen2 = Solution.Weapon.values()[5]; }
            } );

        //************** Submit Button **************
        btnQuestion.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    // Executed when question is clicked
				Game.totalQuestion++;
                    String alibi = Game.suspect(witnessChosen, suspectChosen2, weaponChosen2);
                    JOptionPane.showMessageDialog(null, alibi);
                }
            } );

    }// end addComponentsToQuestionPane

    //Displays the answer
    public static void answer()
    {
	   String answer = "";
        JOptionPane.showMessageDialog(null,
            Solution.solution_suspect+ " was the murderer, using the "+Solution.solution_weapon+" in the "+ Solution.solution_room+".");
	
		String a [] = Game.solve();
		for (int i=0; i<a.length-1; i++)
			answer = answer + a[i] + "\n";

		JOptionPane.showMessageDialog(null, answer);

    } //end answer

}//end GUI class


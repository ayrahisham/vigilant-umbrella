// Assignment 3
// Nur Suhaira Bte Badrul Hisham
// UOW ID: 5841549
// This is my own work, and 
// I have not passed my program to my friends
// and willing to accept whatever 

// Assume that a company wishes to conduct a lucky draw.
// We will hide some numbers at the back of the buttons

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.ImageIcon;
//import javax.swing.JOptionPane;


class PigeonHolesFrame extends JFrame implements ActionListener 
{
    private final JButton [] jbArray;
    private final JButton [] jbSummary;
    private final JButton [] jbLuckyNo;
    private final GridLayout gl;
    private final List <Integer> drawBox;

    public PigeonHolesFrame ()
    {
        //Window title
    	super ("Good Luck To You");

        //A window with 63 buttons including summary title and summary of lucky no chosen
    	gl = new GridLayout (9, 7);
    	setLayout (gl);

        //Created buttons
    	jbArray = new JButton [49];
        jbSummary = new JButton [7];
        jbLuckyNo = new JButton [7];

        //Buttons to store 49 numbers
    	for (int i = 0; i < 49; i++)
    	{
    		jbArray [i] = new JButton ("SP");
    		jbArray [i].setBackground (Color.DARK_GRAY);
                jbArray [i].setForeground (Color.WHITE);
    		add (jbArray [i]);
    		jbArray [i].addActionListener (this);
    	}
        
        //Buttons to state "SUMMARY" title
        jbSummary[0] = new JButton ("S");
        jbSummary[1] = new JButton ("U");
        jbSummary[2] = new JButton ("M");
        jbSummary[3] = new JButton ("M");
        jbSummary[4] = new JButton ("A");
        jbSummary[5] = new JButton ("R");
        jbSummary[6] = new JButton ("Y");
        
        for (int i = 0; i < 7; i++)
    	{
    		jbSummary [i].setBackground (Color.CYAN);
    		add (jbSummary[i]);
    		jbSummary [i].addActionListener (this);
    	}
        
        //Buttons to store chosen lucky no
        for (int i = 0; i < 7; i++)
    	{
    		jbLuckyNo [i] = new JButton ("SP");
                jbLuckyNo [i].setBackground (Color.CYAN);
    		add (jbLuckyNo [i]);
    		jbLuckyNo [i].addActionListener (this);
    	}

    	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    	setSize (600, 500);
    	setVisible (true);
        
        //Randomly shuffled number 1 to 49
        drawBox = IntStream.rangeClosed (1, 49)
                     .boxed()
                     .collect (Collectors.toCollection (ArrayList::new));
        Collections.shuffle (drawBox);
        drawBox.subList (0, 49);
    }
    
	@Override
	public void actionPerformed (ActionEvent e)
	{
            final List <Integer> luckyNo;
            int [] storedLucky = new int [7] ;
            //To randomly choose only 7 numbers of 49 numbers
            luckyNo = IntStream.rangeClosed (1, 49)
                                              .boxed()
                                              .collect (Collectors.toCollection (ArrayList::new));
            Collections.shuffle(luckyNo);
            luckyNo.subList(0, 7);
            
            int n = 0;
            
            //To display the numbers and where they are stored
            for (JButton k : jbArray)
            {
                k.setText (String.valueOf (drawBox.get(n)));
                k.setBackground (Color.LIGHT_GRAY);
                k.setForeground (Color.BLACK);
                ++n;
            }
            
            /*
            //Prompt a window to await the contestants before revealing the lucky no
            ImageIcon luckyIcon = new ImageIcon (getClass().getResource("luckyIcon.jpg"));
            JOptionPane.showMessageDialog(null, "Get ready! The number in GREEN wins the TOP prize!", 
                    "And the winner is...", JOptionPane.INFORMATION_MESSAGE,
                    luckyIcon); 
            */
            
            //Once user press OK button, first 6 buttons chosen turn yellow
            // Last button chosen turn green (top prize)
            int index = 0;
            while (index < 6)
            {
                jbArray[luckyNo.get(index)].setBackground (Color.YELLOW);
                jbArray[luckyNo.get(index)].setForeground (Color.BLACK);
                storedLucky[index] = luckyNo.get(index);
                /*
                ImageIcon prizeIcon = new ImageIcon (getClass().getResource("prizes.jpg"));
                String string = "No " + (index+1) + " winner goes to lucky number...";
                JOptionPane.showMessageDialog(null, string, 
                    "Here are our winners...", JOptionPane.INFORMATION_MESSAGE,
                    prizeIcon); 
                */
                ++index;
            }
            
            int pos = index;
            jbArray[luckyNo.get(pos)].setBackground (Color.GREEN);
            storedLucky[pos] = luckyNo.get(pos);
            /*
            ImageIcon topPrizeIcon = new ImageIcon (getClass().getResource("topPrize.jpg"));
            String string = "Congrats! Our TOP prize goes to...";
            JOptionPane.showMessageDialog(null, string, 
                    "Here are our winners...", JOptionPane.INFORMATION_MESSAGE,
                    topPrizeIcon);
            */
            //Turn font red once lucky no are revealed
            for (JButton k : jbSummary)
            {
                k.setForeground (Color.WHITE);
                k.setBackground (Color.DARK_GRAY);
            }
            
            int a = 0;
            
            //To display the lucky numbers in summary
            for (JButton k : jbLuckyNo)
            {
                if (a < 6)
                {
                    k.setText (String.valueOf (drawBox.get(storedLucky[a])));
                    k.setBackground (Color.YELLOW);
                    k.setForeground (Color.BLUE);
                }
                else
                {
                    k.setText (String.valueOf (drawBox.get(storedLucky[a])));
                    k.setBackground (Color.GREEN);
                    k.setForeground (Color.BLUE);
                }
                
                ++a;
            }
	}

    public static void main (String [] args)
    {
    	PigeonHolesFrame pg = new PigeonHolesFrame ();
    }
}

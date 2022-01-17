/*
 * This program takes a tm input file and determines what the alphabet is for
 * that tm and uses the given transitions to see if a user's input string 
 * would be accepted or not.
 */
package cs475assign3;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Coby Zimmerman
 */
public class CS475Assign3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String startState = null;
        String fromState;
        Character read;
        Character write;
        Character direction;
        String toState;
        
        ArrayList<String> acceptStates = new ArrayList<>();
        ArrayList<String> rejectStates = new ArrayList<>();
        
        ArrayList<Character> inputAlphabet = new ArrayList<>();
        
        ArrayList<Transition> transitions = new ArrayList<>();
        
        String userInputString;
        String userConfirm;

        File textFile;
        
        //Sets up JFileChooser for user to choose a file
        JFileChooser chooseFile = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TEXT FILES", "txt", "text");
        chooseFile.setFileFilter(filter);
        int returnVal = chooseFile.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            textFile = chooseFile.getSelectedFile();
            
            //Try to read what is in the selected file
            try {
                //Determine the start state from first line of file
                try (Scanner scanner = new Scanner(textFile)) {
                    //Determine the start state from first line of file
                    startState = scanner.nextLine();
                    
                    //Take second line of file and put each accept state into
                    //arrayList as separate members (as long as one space between them)
                    String acceptedStatesLine = scanner.nextLine();
                    acceptStates = new ArrayList<>
                            (Arrays.asList(acceptedStatesLine.split(" ")));
                    
                    //Take third line and put each reject state into arraylist
                    String rejectStatesLine = scanner.nextLine();
                    rejectStates = new ArrayList<>
                            (Arrays.asList(rejectStatesLine.split(" ")));
                    
                    //Take fourth line and put each element in arrayList,
                    //getting rid of commas first so you have the inputAlphabet
                    String inputAlphabetLine = scanner.nextLine();
                    inputAlphabetLine = inputAlphabetLine.replaceAll(",", "");
                    for (char c : inputAlphabetLine.toCharArray()) {
                        inputAlphabet.add(c);
                    }
                    
                    System.out.println("This is the input alphabet associated "
                            + "with the input TM file: " + inputAlphabet);
                    
                    //While loop to help figure out the alphabets and total states.
                    //Also makes new transition objects from file and adds them
                    //to the transitions arrayList
                    while (scanner.hasNextLine()) {
                        String[] splitTrans = scanner.nextLine().split("[(,)]+");                       
                        fromState = splitTrans[0].replaceAll("\\s", "");
                        read = splitTrans[1].charAt(0);
                        write = splitTrans[2].charAt(0);
                        direction = splitTrans[3].charAt(0);
                        toState = splitTrans[4].replaceAll("\\s", "");
                        
                        Transition transition = new Transition(fromState, 
                                read, write, direction, toState);
                        transitions.add(transition);  
                                
                    }  
                }
            } catch (FileNotFoundException ex) {
                ex.toString();
            }
            userConfirm = JOptionPane.showInputDialog(null, "Would you like to "
                    + "input a string (Y/N)?");
            
            //if for the cancel button to avoid null pointer
            if (userConfirm != null) {
                while(userConfirm.equals("Y")) {
                    userInputString = JOptionPane.showInputDialog("Please enter an "
                        + "input string with no spaces");
                    
                    //Initialize tape and headPos
                    ArrayList<Character> tapeArrayList = new ArrayList<>();
                    int headPosition = 0;
                    Tape tape = new Tape(tapeArrayList, headPosition);
                    tape.initialize(userInputString);
            
                    String current = startState;
                    ArrayList<String> fin;
                    fin = acceptStates;
                    
                    //outerloop used to check for if the input will be accepted or not
                    outerloop:
                    while(!acceptStates.contains(current) && !rejectStates.contains(current)) {
                        
                        //for loop checking that the user string follows the alphabet
                        for (int i = 0; i < userInputString.length(); i++) {
                            char c = userInputString.charAt(i);
                        
                            if (!inputAlphabet.contains(c)) {
                                break outerloop;
                            }
                        }
                    
                        for (Transition j : transitions) {
                            /*
                            If there is a transition with current as the from state
                            that reads the same value as where the head position is
                            pointing on the tape, set this element of the tape 
                            equal to the write part of the transition.
                        
                            If the direction of the transition says 'R', increment
                            the head position. If it says 'L', decrement the head
                            position.
                        
                            Update current to equal the transition's toState
                            */
                            if (j.getFromState().equals(current) && 
                                    j.getRead().equals(tape.cells.get(tape.headPosition))) {
                                tape.cells.set(tape.headPosition, j.getWrite());
                                if (j.getDirection().equals('R')) {
                                    tape.headPosition++;
                                    if (tape.headPosition >= tape.cells.size()) {
                                        tape.cells.add(' ');
                                    }
                                }
                                else {
                                    tape.headPosition--;
                                }
                                current = j.toState;
                                break;
                            }
                        }
                    }
                    
                    if (fin.contains(current)) {
                        JOptionPane.showMessageDialog(null, "The TM ACCEPTED"
                                + " your string");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The TM DID NOT ACCEPT"
                                + " your string");
                    }

                    userConfirm = JOptionPane.showInputDialog(null, "Would you like"
                            + " to input another string (Y/N)?");
                }
            }
        } 
    }
}


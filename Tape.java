/*
 * Tape class with Tape object constructor and initialize method to put the 
 * user input string into the tape.
 */
package cs475assign3;

import java.util.ArrayList;

/**
 *
 * @author Coby Zimmerman
 */
public class Tape {
    public ArrayList<Character> cells;
    public int headPosition;

    public Tape(ArrayList<Character> cells, int headPosition) {
        this.cells = cells;
        this.headPosition = headPosition;
    }

    public ArrayList<Character> getCells() {
        return cells;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public void setCells(ArrayList<Character> cells) {
        this.cells = cells;
    }

    public void setHeadPosition(int headPosition) {
        this.headPosition = headPosition;
    }

    public void initialize(String input) {
        //Go through each input char and place them into the tape
        for (char c : input.toCharArray()) {
            cells.add(c);
        }
    }
}




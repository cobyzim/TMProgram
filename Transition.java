/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475assign3;

/**
 *
 * Transition class with constructor for transition objects, setters and getters
 * for the object data fields, and a toString method for debugging.
 * 
 * @author Coby Zimmerman
 */
public class Transition {
    public String fromState;
    public Character read;
    public Character write;
    public Character direction;
    public String toState;
    
    public Transition(String fromState, Character read, 
            Character write, Character direction, String toState) {
        this.fromState= fromState;
        this.read = read;
        this.write = write;
        this.direction = direction;
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "Transition{" + "fromState=" + fromState + ", read=" + read + ", write=" + write + ", direction=" + direction + ", toState=" + toState + '}';
    }

    public String getFromState() {
        return fromState;
    }

    public Character getRead() {
        return read;
    }

    public Character getWrite() {
        return write;
    }

    public Character getDirection() {
        return direction;
    }

    public String getToState() {
        return toState;
    }

    public void setFromState(String fromState) {
        this.fromState = fromState;
    }

    public void setRead(Character read) {
        this.read = read;
    }

    public void setWrite(Character write) {
        this.write = write;
    }

    public void setDirection(Character direction) {
        this.direction = direction;
    }

    public void setToState(String toState) {
        this.toState = toState;
    }

    
    
    
}


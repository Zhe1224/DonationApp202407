package com.tarumtrsw2407.donationapp202407.boundary;

import java.util.Scanner;

/**
 *
 * @author Wong Xiao Zhe
 * @param <T>
 */
public abstract class BasePrompt<T> {
    protected String message = "";
    protected String blankMessage = "No input entered.";
    protected T blankValue = null;
    protected String noMatchMessage = "Invalid input. Please try again.";
    protected T noMatchValue = null;
    
    public T getBlankValue() {
        return blankValue;
    }
    
    public T getNoMatchValue() {
        return noMatchValue;
    }

    public void setMessage(String message) {
        this.message = (message==null||message.isEmpty())?null:message;
    }
    
    public void setBlankMessage(String message) {
        this.noMatchMessage = (message==null||message.isEmpty())?null:message;
    }
    
    public void setBlankValue(T value) {
        this.blankValue = value;
    }

    public void setNoMatchMessage(String message) {
        this.noMatchMessage = (message==null||message.isEmpty())?null:message;
    }

    public void setNoMatchValue(T value) {
        this.noMatchValue = value;
    }
    
    public void continueOnBlankInput(){
        setBlankValue(null);
    }
    
    public void continueOnNoMatch(){
        setNoMatchValue(null);
    }

    protected abstract T convertInput(String input);

    public abstract T prompt();

    protected String askUser() {
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }
}


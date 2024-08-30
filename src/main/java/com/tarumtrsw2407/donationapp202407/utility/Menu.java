package com.tarumtrsw2407.donationapp202407.utility;

import java.util.Scanner;

public final class Menu<T>{
    private class Config{
        public String prompt;
        public int choices;
        public boolean continueOnBlank;
        public boolean continueOnNoInput;
    }
    public String message="";
    public Entry<String,T>[] options;
    public Entry<String,T> blank=null;
    public Entry<String,T> nil=null;
    public Menu() {
    }
    public void setOptions(String[] labels,T[] items){
        this.options=new Entry<String,T>("",null).build(labels,items);
    }
    /*
    public void includeOptions(String[] labels,T[] items){
        this.options.append()=new Entry<String,T>("",null).build(labels,items);
    }
    */
    public void excludeOptions(String[] labels){
    }
    public String getBlankMessage(){
        return blank.key;
    }
    public String getNoMatchMessage(){
        return nil.key;
    }
    public T getBlankValue(){
        return blank.value;
    }
    public T getNoMatchValue(){
        return nil.value;
    }
    public void setBlankValue(T item){
        this.blank.value=item;
    }
    public void setBlankMessage(String message){
        if (message==null||message.isEmpty()) this.blank.key=null;
        this.blank.key=message;
    }
    public void continueOnBlankInput(){
        setBlankValue(null);
    }
    public void setNoMatchValue(T item){
        this.nil.value=item;
    }
    public void continueOnNoMatch(){
        setNoMatchValue(null);
    }
    public void setNoMatchMessage(String message){
        if (message==null||message.isEmpty()) this.nil.key=null;
        this.nil.key=message;
    }
    public T prompt(){
        for (Entry<String, T> option : options) System.out.println(option.key);
        String input;
        for(;;){
            input=askUser();
            if (input.isEmpty()){
                if (blank.key!=null) System.out.println(blank.key);
                if (blank.value!=null) return blank.value;
                continue;
            }
            for (Entry<String, T> option : options) if (option.key.startsWith(input)) return option.value;
            if (nil.key!=null) System.out.println(nil.key);
            if (nil.value!=null) return nil.value;
        }
    }
    private String askUser(){
        System.out.println(message);
        return new Scanner(System.in).nextLine();
    }
}
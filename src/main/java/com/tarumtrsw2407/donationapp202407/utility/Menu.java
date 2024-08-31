package com.tarumtrsw2407.donationapp202407.utility;

import com.tarumtrsw2407.donationapp202407.adt.Entry;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import java.util.Scanner;

public final class Menu<T>{
    public String message="";
    public ListInterface<Entry<String,T>> options=new ArrayList<>();
    public boolean showOptions=true;
    private Entry<String,T> blank=new Entry(null,null);
    private Entry<String,T> nil=new Entry(null,null);
    public Menu() {}
    public boolean equals(Menu<T> menu){
        return options.getItems().equals(menu.options.getItems());
    }
    public String toString(){
        StringBuilder s=new StringBuilder("Menu \n");
        s.append("Message: ").append(message).append('\n');
        s.append(blank.value==null?"Denies":"Accepts").append(" blank input\n");
        s.append(nil.value==null?"Reprompts":"No reprompt").append(" on no match\n");
        s.append(options.size()).append(" option").append(options.size()!=1&&"s");
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
    public void setNoMatchMessage(String message){
        this.nil.key=(message==null||message.isEmpty())?null:message;
    }
    public void continueOnNoMatch(){
        setNoMatchValue(null);
    }
    public T prompt(){
        if (showOptions) for (Entry<String, T> option : options) System.out.println(option.key);
        for(;;){
            String input=askUser();
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
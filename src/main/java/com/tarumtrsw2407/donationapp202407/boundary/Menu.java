package com.tarumtrsw2407.donationapp202407.boundary;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.Entry;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import java.util.function.Function;

/**
 *
 * @author Wong Xiao Zhe
 */
public final class Menu<T> extends InputPrompt<T>{
    public ListInterface<Entry<String,T>> options=new ArrayList<>();
    public boolean showOptions=true;
    public Menu(){this(null);}
    private Menu(Function<String, T> converter) {
        super(converter);
    }
    public boolean equals(Menu<T> menu){
        return options.getItems().equals(menu.options.getItems());
    }
    @Override
    public String toString(){
        StringBuilder s=new StringBuilder("Menu \n");
        s.append("Message: ").append(message).append('\n');
        s.append(getBlankValue()==null?"Denies":"Accepts").append(" blank input\n");
        s.append(getNoMatchValue()==null?"Reprompts":"No reprompt").append(" on no match\n");
        s.append(options.size()).append(" option").append((options.size()!=1)?"s":"");
        return s.toString();
    }
    public ListInterface<Entry<String, T>> getOptions() { return new ArrayList<>(options); }
    public void setOptions(ListInterface<Entry<String, T>> options) { this.options = new ArrayList<>(options); }
    public boolean isShowOptions() { return showOptions; }
    public void setShowOptions(boolean showOptions) { this.showOptions = showOptions; }
    @Override
    protected T convertInput(String input) {
        ListInterface<Entry<String,T>> r = options.filter(o->o.getKey().contains(input));
        if (r.size()<1) return null;
        return r.at(0).getValue();
    }
    @Override
    public T prompt() {
        if (showOptions) {
            for (Entry<String, T> option : options) {
                System.out.println(option.getKey());
            }
        }
        return super.prompt();
    }
}
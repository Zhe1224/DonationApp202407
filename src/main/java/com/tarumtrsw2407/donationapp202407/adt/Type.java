package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author Wong Xiao Zhe
 */
public enum Type {
    Government,Private,Public;
    @Override
    public String toString(){
        switch(this){
            case Government: return "Government";
            case Private: return "Private";
            case Public: return "Public";
            default: return null;
        }
    }
}

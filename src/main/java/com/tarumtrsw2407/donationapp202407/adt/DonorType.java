package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author Wong Xiao Zhe
 */
public enum DonorType {
    Government,Private,Public,Null;
    @Override
    public String toString(){
        String s="";
        switch(this){
            case Government: s= "Government";break;
            case Private: s= "Private";break;
            case Public: s= "Public";break;
            case Null: s= "?";break;
            default:
        }
        return s;
    }
}

package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author Wong Xiao Zhe
 */
public enum DonorField {
    ID,Type,Name,Date,Region,Null;
    @Override
    public final String toString(){
    switch (this) {
        case ID:return "ID";
        case Type:return "Category";
        case Name:return "Name";
        case Date:return "Birth/Found Date";
        case Region:return "Home Region";
        case Null:return "?";
        default:return null;
    }
    }
}

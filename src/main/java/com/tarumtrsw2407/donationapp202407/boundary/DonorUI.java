package com.tarumtrsw2407.donationapp202407.boundary;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;
import com.tarumtrsw2407.donationapp202407.control.DonorManager;

/**
 *
 * @author Wong Xiao Zhe
 */


public class DonorUI {
    private enum CollOps{
    append, search, filter, update, delete, print, summary, leave;
    @Override
        public final String toString(){
            switch (this) {
            case append:
                return "Add donor";
            case search:
                return "Search donor";
            case filter:
                return "Filter donors";
            case update:
                return "Update donor";
            case delete:
                return "Remove donor";
            case print:
                return "List donors";
            case summary:
                return "Get summary";
            case leave:
                return "Leave";
            default:
                return null;
            }
    }
}
    private DonorManager donors;
    public DonorUI(){
        this(new DonorManager());
    }
    public DonorUI(DonorManager donors){
        this.donors=donors;
    }
    public DonorManager main(DonationsManager donations){
        do{
            Menu<CollOps> IO = new Menu<>();
            IO.include(CollOps.append.toString(),CollOps.append);
            if (donors.size()>0) {
                IO.include(CollOps.search.toString(),CollOps.search);
                IO.include(CollOps.filter.toString(),CollOps.filter);
                IO.include(CollOps.update.toString(),CollOps.update);
                IO.include(CollOps.delete.toString(),CollOps.delete);
                IO.include(CollOps.print.toString(),CollOps.print);
            }
            IO.include(CollOps.summary.toString(),CollOps.summary);
            IO.include(CollOps.leave.toString(),CollOps.leave);
            IO.setBlankValue(CollOps.leave);
            switch(IO.prompt()){
                case append:donors=appendDonor();
                /*case search:searchDonor(donations);
                case filter:donors=filterDonor();
                case update:donors=updateDonor();
                case delete:donors=deleteDonor();*/
                case print:
                /*case append:donors=appendDonor();break;
                case filter:donors=filterDonor(donations);break;
                case update:donors=updateDonor();break;
                case delete:donors=deleteDonor();break;*/
                case leave:return donors;
                default:
            }
        }while(true);
    }
    public DonorManager appendDonor(){
        InputPrompt<String> pName = new InputPrompt<>(string->string);
        
        return null;
    }
}

package com.tarumtrsw2407.donationapp202407.boundary;

import com.tarumtrsw2407.donationapp202407.adt.ArrayList;
import com.tarumtrsw2407.donationapp202407.adt.CollOps;
import com.tarumtrsw2407.donationapp202407.adt.ListInterface;
import com.tarumtrsw2407.donationapp202407.control.DonorList;

/**
 *
 * @author Wong Xiao Zhe
 */


public class DonorManager {
    private DonorList donors;
    public DonorManager(){
        this(new DonorList());
    }
    public DonorManager(DonorList donors){
        this.donors=donors;
    }
    public DonorManager main(){
        CollOps ops[]={CollOps.append,CollOps.filter,CollOps.update,CollOps.delete};
        do{
            Menu<CollOps> IO = new Menu<>();
            ListInterface<CollOps> oops=new ArrayList<>(ops).getItems(0,donors.size()>0?3:0);
            for (CollOps collOps:oops) IO.include(collOps.toString().concat(" Donors"),collOps);
            IO.include(CollOps.leave.toString(),CollOps.leave);
            IO.setBlankValue(CollOps.leave);
            switch(IO.prompt()){
                /*case append:donors=appendDonor();break;
                case filter:donors=filterDonor();break;
                case update:donors=updateDonor();break;
                case delete:donors=deleteDonor();break;*/
                case leave:return this;
                default:
            }
        }while(true);
    }
    /*public DonorList appendDonor(){
        InputPrompt<String> name = new InputPrompt<>(string->string);
        
    }*/
}

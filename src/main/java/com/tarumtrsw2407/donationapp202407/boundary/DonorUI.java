package com.tarumtrsw2407.donationapp202407.boundary;

import com.tarumtrsw2407.donationapp202407.adt.DonorBody;
import com.tarumtrsw2407.donationapp202407.adt.DonorField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import com.tarumtrsw2407.donationapp202407.adt.DonorType;
import com.tarumtrsw2407.donationapp202407.control.DonorManager;
import com.tarumtrsw2407.donationapp202407.control.DonationsManager;

/**
 *
 * @author Wong Xiao Zhe
 */


public class DonorUI {
    private enum CollOps{
    append, list, filter, update, delete, summary, leave;
    @Override
        public final String toString(){
            switch (this) {
            case append:
                return "Add donor";
            case list:
                return "View donors";
            case filter:
                return "Search/Filter donors";
            case update:
                return "Update donor";
            case delete:
                return "Remove donor";
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
    public DonorManager main(){
        return main(null);
    }
    public DonorManager main(DonationsManager donations){
        do{
            switch(askForChoice(
                "Select an operation:",
                "Exiting donor subsystem",
                CollOps.leave,
                "Please enter a valid operation!",
                CollOps.append,CollOps.list,CollOps.filter,CollOps.summary,CollOps.leave
            )){
                case append:donors=appendDonor();break;
                case list:donors=viewDonors(donors);break;
                case filter:filterDonors();break;
                case summary:summary(donations);break;
                case leave:return donors;
                default:
            }
        }while(true);
    }
    private DonorManager appendDonor(){
        do{
        String abortedMessage="Aborted adding donor.";
        String blank="";
        DonorBody kind=askForChoice(
                "Is the donor a person or an organisation?",abortedMessage,DonorBody.Null,"Please select a valid donor type!",
                DonorBody.Person,
                DonorBody.Org);
        if (kind==DonorBody.Null) break;
        String id=askForInput("Enter donor ID: (Blank to abort)",abortedMessage,blank);
        if (id.equals(blank)) break;
        DonorType cat=askForChoice(
                "Select donor category: (Blank to abort)",abortedMessage,DonorType.Null,"Please select a valid category!",
                DonorType.Government,
                DonorType.Private,
                DonorType.Public);
        if (cat==DonorType.Null) break;
        String fName=askForInput("Enter donor ".concat(kind==DonorBody.Person?"first ":"").concat("name: (Blank to abort)"),abortedMessage,blank);
        if (fName.equals(blank)) break;
        String lName="";
        if (kind==DonorBody.Person) lName=askForInput("Enter donor last name: (optional)",abortedMessage,blank);
        Date date=askForDate(new Date(Long.MAX_VALUE),abortedMessage,kind);
        if (date==new Date(Long.MAX_VALUE)) break;
        String region=askForInput("Enter donor home region: (Blank to abort)",abortedMessage,blank);
        if (region.equals(blank)) break;
        donors.add(kind,id,cat,fName,lName,date,region);
        }while(false);
        return donors;
    }
    private DonorManager filterDonors(){
        String abortedMessage="Filter operation aborted.";
        DonorField f=askForChoice(
            "Filter donors by",abortedMessage,DonorField.Null,"Please select a valid field!",
            DonorField.ID,DonorField.Type,DonorField.Name,DonorField.Date,DonorField.Region
        );
        switch (f){case Null:return donors;}
        switch (f){
            case ID:case Name:case Region:
                String query=askForInput("Filtering donors by ".concat(f.toString()),"...",abortedMessage);
                if ("".equals(query)) return donors;
                switch (f){
                    case Name:return viewDonors(donors.filter(donor->donor.getName().contains(query)));
                    case ID:return viewDonors(donors.filter(donor->donor.getId().equals(query)));
                    case Region:return viewDonors(donors.filter(donor->donor.getHomeRegion().equals(query)));
                }
            case Date:
                Date date=askForDate(new Date(Long.MAX_VALUE),abortedMessage,DonorBody.Null);
                if (date.equals(new Date(Long.MAX_VALUE))) return donors;
                return viewDonors(donors.filter(donor->donor.getExistDate().equals(date)));
            case Type:
                DonorType cat=askForChoice(
                "Select donor category: (Blank to abort)",abortedMessage,DonorType.Null,"Please select a valid category!",
                DonorType.Government,
                DonorType.Private,
                DonorType.Public);
                if (cat==DonorType.Null) return donors;
                return viewDonors(donors.filter(donor->donor.getType().equals(cat)));
        }
        return null;
    }
    private DonorManager viewDonors(DonorManager donors){
        return viewDonors(donors,null);
    }
    private DonorManager viewDonors(DonorManager donors,DonationsManager donations){
        while(true){
           System.out.println(donors.printDonors());
           String query=askForInput("Select a donor using ID:","Exited donor view","");
           if ("".equals(query)) return donors;
           DonorManager r=donors.filter(donor->donor.getId().equals(query));
           if (r.size()<1) continue;
            switch(askForChoice(
                "Select an operation:",
                "Exiting single donor view",
                CollOps.leave,
                "Please enter a valid operation!",
                CollOps.update,CollOps.delete,CollOps.leave
            )){
                case update:
                    String abortedMessage="";
                    String id=askForInput("Enter donor ID: (Blank to retain)",abortedMessage,r.getDonors().at(0).getId());
                    DonorType cat=askForChoice(
                            "Select donor category: (Blank to retain)",abortedMessage,r.getDonors().at(0).getType(),"Please select a valid category!",
                            DonorType.Government,
                            DonorType.Private,
                            DonorType.Public);
                    if (cat==DonorType.Null) return donors;
                    String fName=askForInput("Enter donor first name: (Blank to retain)",abortedMessage,"");
                    String lName=askForInput("Enter donor last name: (Blank to retain)",abortedMessage,"");
                    Date date=askForDate(r.getDonors().at(0).getExistDate(),abortedMessage,DonorBody.Null);
                    String region=askForInput("Enter donor home region: (Blank to retain)",abortedMessage,r.getDonors().at(0).getHomeRegion());
                    donors.update(r.getDonors().at(0),id,cat,fName,lName,date,region);
            return donors;
                case delete:donors.remove(r.getDonors().at(0));
                case leave:return donors;
                default:
            }
        }
    }
    private void summary(DonationsManager donations){
        System.out.println(donors.summary(donations));
    }
    private String askForInput(String message,String blankMessage,String nullText){
        InputPrompt<String> p = new InputPrompt<>(string->string);
        p.setBlankMessage(blankMessage);
        p.setBlankValue(nullText);
        p.setMessage(message);
        return p.prompt();
    }
    private Date askForDate(Date nullDate,String blankMessage,DonorBody kind){
        Function<String, Date> parseFunction = createDateParser("yyyy-MM-dd");
        InputPrompt<Date> p = new InputPrompt<>(parseFunction);
        String dateNoun;
        switch (kind){
            case Person:dateNoun="birthday";break;
            case Org:dateNoun="found date";break;
            case Null:default:dateNoun="birth/found date";
        }
        p.setMessage("Enter donor ".concat(dateNoun).concat(": (Blank to abort)"));
        p.setBlankMessage(blankMessage);
        p.setBlankValue(nullDate);
        p.setNoMatchMessage("Please enter a valid date in yyyy-MM-dd format!");
        return p.prompt();
    }
    private <T> T askForChoice(String message,String blankMessage,T blankValue,String noMatchMessage,T... options) {
        Menu<T> menu = new Menu<>();
        for (T option:options) menu.include(option.toString(),option);
        menu.setMessage(message);
        menu.setBlankMessage(blankMessage);
        menu.setBlankValue(blankValue);
        menu.setNoMatchMessage(noMatchMessage);
        return menu.prompt();
    }
    private static Function<String, Date> createDateParser(String format) {
        final DateFormat dateFormat = new SimpleDateFormat(format);
        return str -> {
            try {
                return dateFormat.parse(str);
            } catch (ParseException e) {
                return null;
            }
        };
    }
}
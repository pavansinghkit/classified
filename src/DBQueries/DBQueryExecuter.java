package DBQueries;

import adminPackage.Classifieds;
import adminPackage.User;
import adminPackage.ValidateLogin;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DBQueryExecuter {

    public static void updateClassifiedsData(int classified_id, String status){
        DBQueryClassified d = new DBQueryClassified();
        int result = d.updateStatus(classified_id, status);
        if(result == 1) {
            System.out.println("User: " + classified_id +" status updated to: "+ status);
        } else {
            System.out.println();
        }
    }
    public static void updateUsersData(String username, String status){
        DBQueryUser d = new DBQueryUser();
        int result = d.updateStatus(username, status);
        if(result == 1) {
            System.out.println("User: " + username +" status updated to: "+ status);
        } else {
            System.out.println();
        }
    }

    public static void getUsersData(String status) {
        DBQueryUser d = new DBQueryUser();
        HashMap<Integer,User> hm = d.find(status);
        for(Integer i : hm.keySet()) {
            String key = i.toString();
            String value1 = hm.get(i).getName();
            String value2 = hm.get(i).getPassword();
            long value3 = hm.get(i).getPhone_num();
            String value4 = hm.get(i).getStatus();
            System.out.println(key + ")" + " " + "Username: " + value1 + " " + "Password: " + value2 + " " + "Phone Number: " + value3 + " " + "Status: " +value4);
            System.out.println(" ");
        }
    }

    public static void getClassifiedsData(String status) {
        DBQueryClassified d = new DBQueryClassified();
        HashMap<Integer, Classifieds> hm = d.find(status);
        for(Integer i : hm.keySet()) {
            String key = i.toString();
            int value1 = hm.get(i).getClassified_id();
            String value2 = hm.get(i).getTitle();
            int value3 = hm.get(i).getPrice();
            String value4 = hm.get(i).getDescription();
            String value5 = hm.get(i).getCategory();
            String value6 = hm.get(i).getStatus();
            Date value7 = hm.get(i).getDate_Published();
            String value8 = hm.get(i).getPublisher();
            Date value9 = hm.get(i).getDate_Modified();
            String value10 = hm.get(i).getModifier();
            System.out.println(key +")" + "  " + "Classified Id:" +  value1 + " " + "Title:" + value2 + " " + "Price:" + value3+ " "  + "Description:" + value4+ " "  + "Category:" + value5+ " "  + "Status:" + value6+ " "  + "Date Published:" + value7+ " "  + "Publisher:" + value8+ " "  + "Date Modified:" + value9+ " "  + "Modified Date:" + value10);
        }
    }
}

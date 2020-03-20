package DBQueries;

import adminPackage.Classifieds;
import adminPackage.User;
import adminPackage.ValidateLogin;

import java.util.List;

public class DBQueryExecuter {
    public static void getUsersData() {
        DBQueryUser d = new DBQueryUser();
        List<User> list = d.find();
        for(User u : list) {
            System.out.println(u.toString());
        }
    }
    public static void getClassifiedsData() {
        DBQueryClassified d = new DBQueryClassified();
        List<Classifieds> list = d.find();
        for(Classifieds u : list) {
            System.out.println(u.toString());
        }
    }
}

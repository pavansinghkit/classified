package DBQueries;

import adminPackage.Classifieds;
import adminPackage.User;

import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class DBQueryClassified {
    private static Connection con;

    DBQueryClassified() {
        if (con == null) {
            MysqlCon obj1 = new MysqlCon();
            con = obj1.connect();
        }
    }

    public void insert() {

    }

    public int updateStatus(int classified_id, String status) {
        int result = 0;
        try {
            Statement statement = con.createStatement();
            String sql = "UPDATE classified_info SET status = '"+status+"' WHERE classified_id = '"+classified_id+"'";
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public HashMap<Integer, Classifieds> find(String classifiedStatus) {
        HashMap<Integer, Classifieds> hm = new HashMap<Integer, Classifieds>();
        try {
            String sql = "select * from classified_info where status = '" + classifiedStatus + "'";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 1;
            while (result.next()) {
                Classifieds classifieds = new Classifieds();
                classifieds.setClassified_id(result.getInt(1));
                classifieds.setTitle(result.getString(2));
                classifieds.setPrice(result.getInt(3));
                classifieds.setDescription(result.getString(4));
                classifieds.setCategory(result.getString(5));
                classifieds.setStatus(result.getString(6));
                classifieds.setDate_Published(result.getDate(7));
                classifieds.setPublisher(result.getString(8));
                classifieds.setDate_Modified(result.getDate(9));
                classifieds.setModifier(result.getString(10));
                hm.put(counter, classifieds);
                counter++;
            }
            counter = counter - 1;
            System.out.println(" ");
            System.out.println(" ******** There are " + "(" + counter + ")" + " users to be approved ********");
            System.out.println(" ");
            return hm;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hm;
    }


//    public List<Classifieds> find() {
//        List<Classifieds> list = new LinkedList<>();
//        try {
//            String sql = "select * from Classifieds where Status = 'Pending'";
//            Statement statement = con.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            while (result.next()){
//                Classifieds classified = new Classifieds();
//                classified.setClassified_id(result.getInt(1));
//                classified.setTitle(result.getString(2));
//                classified.setPrice(result.getInt(3));
//                classified.setDescription(result.getString(4));
//                classified.setCategory(result.getString(5));
//                classified.setDate_Published(result.getDate(6));
//                classified.setPublisher(result.getString(7));
//                classified.setStatus(result.getString(8));
//                list.add(classified);
//            }
//            //System.out.println("Result="+result.getRow());
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public void delete() {
    }
}
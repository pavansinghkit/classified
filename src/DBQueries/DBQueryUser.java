package DBQueries;

import adminPackage.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class DBQueryUser {
        private static Connection con;
    DBQueryUser() {
        if(con == null) {
            MysqlCon obj1 = new MysqlCon();
            con = obj1.connect();
        }
    }
    public void insert() {

    }
    public int updateStatus(String username, String status) {
        int result = 0;
        try {
            Statement statement = con.createStatement();
            String sql = "UPDATE tbl_users SET status = '"+status+"' WHERE username = '"+username+"'";
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
//    public int updateStatus(String username) {
//        int result = 0;
//        try{
//            Statement statement = con.createStatement();
//            String sql = "UPDATE tbl_users SET status = 'A' WHERE username = '"+username+"'";
//             result = statement.executeUpdate(sql);
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    public HashMap<Integer, User> find(String userStatus) {
        HashMap<Integer, User> hm = new HashMap<Integer, User>();
        try {
            String sql = "select * from tbl_users where status = '"+userStatus+"'";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int counter = 1;
            while(result.next()) {
                String status = result.getString(4);
                    User user = new User();
                    user.setName(result.getString(1));
                    user.setPassword(result.getString(2));
                    user.setPhone_num(result.getLong(3));
                    user.setStatus(status);
                    hm.put(counter, user);
                    counter++;

            }
            counter = counter - 1;
            Thread.sleep(400);
            System.out.println(" ");
            System.out.println(" ******** There are " + "(" + counter +")" + " users to be approved ********");
            System.out.println(" ");
            Thread.sleep(400);
            return hm;
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        return hm;
    }
//    public List<User> find() {
//        List<User> list = new LinkedList<>();
//        try {
//            String sql = "select * from tbl_users where Status = 'UA'";
//            Statement statement = con.createStatement();
//            ResultSet result = statement.executeQuery(sql);
//            while (result.next()){
//                User user1 = new User();
//                user1.setName(result.getString(1));
//                user1.setPassword(result.getString(2));
//                user1.setPhone_num(result.getLong(3));
//                user1.setStatus(result.getString(4));
//                list.add(user1);
//            }
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public void delete() {
    }
}

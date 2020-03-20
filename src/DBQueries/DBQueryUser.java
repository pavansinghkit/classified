package DBQueries;

import adminPackage.User;
import adminPackage.ValidateLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void update() {


    }
    public List<User> find() {
        List<User> list = new LinkedList<>();
        try {
            String sql = "select * from User where Status = 'Pending'";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                User user1 = new User();
                user1.setName(result.getString(1));
                user1.setPassword(result.getString(2));
                user1.setPhone_num(result.getInt(3));
                user1.setStatus(result.getString(4));
                list.add(user1);
//                System.out.print(result.getString(1));
//                System.out.print(result.getString(2));
//                System.out.print(result.getInt(3));
//                System.out.println();
            }
            //System.out.println("Result="+result.getRow());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void delete() {
    }
}

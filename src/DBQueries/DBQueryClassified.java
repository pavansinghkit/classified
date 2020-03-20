package DBQueries;

import adminPackage.Classifieds;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

class DBQueryClassified {
    private static Connection con;
    DBQueryClassified() {
        if(con == null) {
            MysqlCon obj1 = new MysqlCon();
            con = obj1.connect();
        }
    }
    public void insert() {

    }
    public void update() {

    }
    public List<Classifieds> find() {
        List<Classifieds> list = new LinkedList<>();
        try {
            String sql = "select * from Classifieds where Status = 'Pending'";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                Classifieds classified = new Classifieds();
                classified.setClassified_id(result.getInt(1));
                classified.setTitle(result.getString(2));
                classified.setPrice(result.getInt(3));
                classified.setDescription(result.getString(4));
                classified.setCategory(result.getString(5));
                classified.setDate_Published(result.getDate(6));
                classified.setPublisher(result.getString(7));
                classified.setStatus(result.getString(8));

                list.add(classified);
//                System.out.print(result.getStri.lng(1));
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
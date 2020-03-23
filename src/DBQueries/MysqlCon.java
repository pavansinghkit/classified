package DBQueries;


import java.sql.*;

public class MysqlCon {

    public Connection connect(){
        int count=0;
        Log log=new Log();
        while(count<2) {
            try{
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://database-1.cda7tdexq29d.us-east-2.rds.amazonaws.com:3306/classified","root","root12345");
                //here Classfield is database name, root is username and password is bordoloa
                count++;
                String msg="Success";
                log.logging(count, msg);
                return con; //returning the connection instance : FOR SUCCESS

            }catch(Exception e){
                count++;
                System.out.println("Error in connection. Reconecting... ");
                log.logging(count, e.toString());
            }
        }
        return null; //Returning NULL: for fail
    }

}
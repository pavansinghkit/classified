package Driver;

import DBQueries.MysqlCon;
import adminPackage.ValidateLogin;

import java.sql.*;


public class DriverClass {

    public static void main(String[] args) throws InterruptedException {
        MysqlCon mysqlcon = new MysqlCon();
        Connection new_connection = mysqlcon.connect();
        System.out.println("Welcome to Amazon IntraClassifieds! Please Login to Continue");
        ValidateLogin obj1 = new ValidateLogin();
        obj1.login();
    }
}

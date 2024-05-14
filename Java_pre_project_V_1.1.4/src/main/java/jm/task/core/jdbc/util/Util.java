package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static Util instance;
    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String USER = "root";
    private static final String PASSWORD = "C5dKaZa8";
    private  Connection conn;


    private Util() {

        try {

            conn = DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e){

            e.printStackTrace();
        }

    }

    public static Util getInstance() {

        if (instance == null) {

            instance = new Util();
        }

        return instance;
    }

    public Connection getConn() {

        return conn;
    }

}

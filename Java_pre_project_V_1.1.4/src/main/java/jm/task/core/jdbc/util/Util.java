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

        Util localInstance = instance;

        if (localInstance == null) {

            synchronized (Util.class) {

                localInstance = instance;

                if (localInstance == null) {

                    instance= localInstance = new Util();
                }
            }
        }

        return localInstance;
    }

    public Connection getConn() {

        return conn;
    }

}

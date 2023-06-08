package xyz.lhweb.furns.utils;

import java.sql.Connection;

public class DBtest {
    public static void main(String[] args) {
        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}

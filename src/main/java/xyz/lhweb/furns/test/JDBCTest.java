package xyz.lhweb.furns.test;

import xyz.lhweb.furns.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * jdbctest
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = JDBCUtilsByDruid.getConnection();
        System.out.println(conn);
        JDBCUtilsByDruid.close(null,null,conn);
    }
}

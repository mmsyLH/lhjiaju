package xyz.lhweb.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于druid数据库连接池的工具类
 *
 * @author 罗汉
 * @date 2023/04/01
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;

    //创建一个ThreadLocalhost 里面放的是连接
    private  static ThreadLocal<Connection> threadLocalConn=new ThreadLocal<>();;

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //因为我们是web项目，他的工作目录在out, 文件的加载，需要使用类加载器
            //找到我们的工作目录
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("druid.properties"));
            //properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //编写getConnection方法
    // public static Connection getConnection() throws SQLException {
    //     return ds.getConnection();
    // }
    public static Connection getConnection()  {
        Connection connection = threadLocalConn.get();
        if (connection==null){//说明当前的threadLocal里没有连接
            //就从数据库连接池中获得一个连接并且放进去
            try {
                connection= ds.getConnection();
                //设置事务不自动提交
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocalConn.set(connection);
        }
        return connection;
    }

    /**
     * 提交事务
     */
    public static void commit(){
        Connection connection = threadLocalConn.get();
        if(connection!=null){//确保该连接不是空的才能提交事务
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
            }
        }
        //1 当提交后需要吧这个连接从threadlocal中清除掉
        //2 不然，会造成threadloacl 长时间持有该连接，会影响效率
        //3 tomcat底层使用的是线程池技术
        threadLocalConn.remove();
    }

    /**
     * 回滚
     * 回滚,撤销和连接关联的操作，例如dml操作  删除、修改、添加
     */
    public  static  void  rollBack(){
        Connection connection = threadLocalConn.get();
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        threadLocalConn.remove();
    }
    //关闭连接, 再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

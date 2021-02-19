import java.sql.*;

import org.springframework.stereotype.Component;

/**
 * @author hongzhengwei
 * @create 2021/2/18 5:36 下午
 * @desc
 **/

public class DateSourceC {
    public static String url = "jdbc:mysql://localhost:3306/test";
    public static String username = "root";
    public static String password = "root";
    boolean transactionFlag = false;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public DateSourceC() throws ClassNotFoundException {
        //加载MySql的驱动类
        Class.forName("com.mysql.jdbc.Driver");
    }
    void rollback() throws SQLException {
        conn.rollback();
    }
    /**
     * @author hongzhengwei
     * @create 2021/2/19 9:02 上午
     * @desc 连接MySql数据库，用户名和密码都是root
     **/
    Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(transactionFlag);
        //MySQL的默认隔离级别是REPEATABLE READ。
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return conn;
    }

    Statement getStatement() throws SQLException {
        stmt = conn.createStatement();
//        PreparedStatement pstmt = con.prepareStatement(sql);
//        CallableStatement cstmt = con.prepareCall("{CALL demoSp(? , ?)}");
        return stmt;
    }

    boolean executeOperate(String sql) throws SQLException {
        int rows = stmt.executeUpdate(sql);
        boolean flag = stmt.execute(sql);
        return flag;
    }

    boolean executeQuery(String sql) throws SQLException {
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("name");
            String pass = rs.getString(1); // 此方法比较高效
        }
        boolean flag = stmt.execute(sql);
        return flag;
    }

    void closeResource() {
        if (rs != null) {   // 关闭记录集
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {   // 关闭声明
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {  // 关闭连接对象
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

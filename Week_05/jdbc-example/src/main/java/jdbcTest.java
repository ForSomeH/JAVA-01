import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongzhengwei
 * @create 2021/2/19 9:37 上午
 * @desc 作业1，原生的jdbc
 **/
public class jdbcTest {
    public static void main(String[] args) throws SQLException {
        List<User> result = new ArrayList<>(10);
        User user = new User("exception", 12, 18);
        User user2 = new User("exception", 11, 19);
        UserService userService = new UserServiceImpl();
        String savaSql = userService.save(user);
        String savaSql2 = userService.save(user2);
        String querySql = userService.query(user);
        user.setAge(28);
        String updateSql = userService.update(user);
        String deleteSql = userService.delete(user);
        DateSource dateSource = null;
        try {
            dateSource = new DateSource();
            dateSource.getConnection();
            //创建一个preparedStatement
            Statement stmt = dateSource.getStatement();
            int flag1 = stmt.executeUpdate(savaSql);
            int flag2 = stmt.executeUpdate(savaSql2);
            int flag3 = stmt.executeUpdate(updateSql);
            //执行
            ResultSet rs = stmt.executeQuery(querySql);
            //结果集
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id"); // 此方法比较高效
                int age = rs.getInt("age"); // 此方法比较高效
                User resultUser = new User(name, id, age);
                result.add(resultUser);
            }
            stmt.executeLargeUpdate(deleteSql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            dateSource.rollback();
            e.printStackTrace();
        } finally {
            //关闭资源
            dateSource.closeResource();
        }
    }
}
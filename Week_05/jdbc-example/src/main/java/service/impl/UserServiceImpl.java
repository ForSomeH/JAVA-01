package service.impl;

import bean.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public String save(User user) {
        String sql = "insert into user (id,name, age) value (" + user.getId() + ",'" + user.getName() + "'," + user.getAge() + ")";
        return sql;
    }

    @Override
    public String update(User user) {
        String sql = "update user set age=" + user.getAge() + " where id=" + user.getId();
        return sql;
    }

    @Override
    public String delete(User user) {
        String sql = "delete from user  where id=" + user.getId();
        return sql;
    }

    @Override
    public String query(User user) {
        String sql = "select * from user";
        return sql;
    }
}

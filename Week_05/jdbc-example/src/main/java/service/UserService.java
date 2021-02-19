package service;

import bean.User;

import java.util.List;

public interface UserService {
    String save(User user);

    String update(User user);

    String delete(User user);

    String query(User user);
}

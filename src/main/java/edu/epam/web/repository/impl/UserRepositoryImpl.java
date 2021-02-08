package edu.epam.web.repository.impl;

import edu.epam.web.entity.User;
import edu.epam.web.entity.UserRole;
import edu.epam.web.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final List<User> userList = new ArrayList<>();

    {
        userList.add(new User("user1", "user1", UserRole.ADMIN));
    }

    private static UserRepositoryImpl instance = new UserRepositoryImpl();

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance(){
        return instance;
    }

    @Override
    public boolean create(User user) {
        boolean result = false;
        if(!userList.contains(user)) {
            result = userList.add(user);
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        Optional<User> user = findById(id);
        if(user.isPresent()){
            result = userList.remove(user.get());
        }
        return result;
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> result = userList.stream().filter(x -> x.getId() == id).findFirst();
        return result;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> result = userList.stream().filter(x -> x.getLogin().equals(login)).findFirst();
        return result;
    }
}

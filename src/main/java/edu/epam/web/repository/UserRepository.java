package edu.epam.web.repository;

import edu.epam.web.entity.User;

import java.util.Optional;

public interface UserRepository {

    boolean create(User user);

    boolean delete(long id);

    Optional<User> findById(long id);

    Optional<User> findByLogin(String login);
}

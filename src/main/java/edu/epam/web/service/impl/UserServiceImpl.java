package edu.epam.web.service.impl;

import edu.epam.web.dao.UserDao;
import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.exceptoin.DaoException;
import edu.epam.web.validator.CheckUserInfo;
import edu.epam.web.entity.User;
import edu.epam.web.repository.UserRepository;
import edu.epam.web.repository.impl.UserRepositoryImpl;
import edu.epam.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl  implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final UserServiceImpl instance = new UserServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();
    private final CheckUserInfo validator = CheckUserInfo.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean result = false;
        if(validator.isValid(user)) {
            Optional<User> userInRepository = userDao.findByLogin(user.getLogin());
            if (userInRepository.isPresent()) {
                logger.info("User {} in repository already exist.", user);
            } else{
                result = userDao.register(user);
                logger.info("Add user {} in repository.", user);
            }
        }
        else {
            logger.info("User {} is invalid.", user);
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        Optional<User> userInRepository = userDao.findById(id);
        if(userInRepository.isPresent()){
            result = userDao.delete(id);
            logger.info("Delete user {} from repository.", userInRepository);
        }
        else {
            logger.info("There's not user with id {} in the repository.", id);
        }
        return result;
    }

    @Override
    public Optional<User> login(String login, String password) {
        Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent()) {
            if(user.get().getPassword().equals(password)){
                logger.info("{} User's password does mismatch.", user);
            }
            else {
                user = Optional.empty();
                logger.info("{} login.", user);
            }
        }
        else {
            logger.info("There's not user with login {} in the repository.", login);
        }
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> result = userDao.findById(id);
        return result;
    }
}

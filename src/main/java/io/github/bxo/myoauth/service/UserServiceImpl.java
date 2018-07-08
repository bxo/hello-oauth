package io.github.bxo.myoauth.service;

import io.github.bxo.myoauth.dao.UserDao;
import io.github.bxo.myoauth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserBy(int id) {
        return userDao.findById(id).get();
    }

    @Override
    public User findByname(String username) {
        return userDao.findByname(username);
    }
}

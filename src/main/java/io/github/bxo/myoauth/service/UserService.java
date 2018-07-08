package io.github.bxo.myoauth.service;

import io.github.bxo.myoauth.entity.User;


public interface UserService {
    User findUserBy(int id);

    User findByname(String username);
}

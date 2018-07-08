package io.github.bxo.myoauth.dao;

import io.github.bxo.myoauth.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDao extends JpaRepository<User,Integer> {
    User findByname(String username);

}

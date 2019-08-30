package com.shimh.repository;

import java.util.List;

import com.shimh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //Long findOneByAccount(String account);
    User findByAccount(String account);
    List<User> findAllByTitleLike(String pattern);
}

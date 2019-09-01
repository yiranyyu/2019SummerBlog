package com.mydg.repository;

import com.mydg.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //Long findOneByAccount(String account);
    User findByAccount(String account);
    List<User> findAllByNicknameLike(String pattern);
}

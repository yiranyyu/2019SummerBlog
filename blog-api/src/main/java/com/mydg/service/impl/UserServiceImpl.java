package com.mydg.service.impl;

import com.mydg.common.util.PasswordHelper;
import com.mydg.entity.User;
import com.mydg.repository.UserRepository;
import com.mydg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public Long saveUser(User user) {
        PasswordHelper.encryptPassword(user);
        return userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public Long updateUser(User user) {
        User oldUser = userRepository.findOne(user.getId());
        oldUser.setNickname(user.getNickname());
        oldUser.setAvatar(user.getAvatar());
        oldUser.setPassword(user.getPassword());
        PasswordHelper.encryptPassword(oldUser);
        return oldUser.getId();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> listUsersByNicknameLike(String pattern) {
        return userRepository.findAllByNicknameLike(pattern);
    }
}

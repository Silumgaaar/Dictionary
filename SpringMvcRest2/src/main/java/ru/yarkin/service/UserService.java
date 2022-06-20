package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.securitydao.RoleDao;
import ru.yarkin.dao.securitydao.UserDao;
import ru.yarkin.models.database.Role;
import ru.yarkin.models.database.User;


import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void add(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne(2L));
        user.setRoles(roles);
        userDao.create(user);
    }


    public User getOne(String userName) {
        return userDao.findByName(userName);
    }

}

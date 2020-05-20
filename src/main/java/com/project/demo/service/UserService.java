package com.project.demo.service;

import com.project.demo.controller.UserController;
import com.project.demo.dao.Database;
import com.project.demo.entity.EventListReq;
import com.project.demo.entity.EventListRes;
import com.project.demo.entity.LoginUser;
import com.project.demo.entity.RegisterUser;
import com.project.demo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to handel all the user service. It acts as bridge between controller and database.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    Database database;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User newUser = null;
        try {
            newUser = new User(userName,database.getPassword(userName), new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return newUser;
    }

    public boolean registerUser(RegisterUser registerUser) throws Exception {
        return database.registerUser(registerUser);
    }

    public boolean checkCredentials(LoginUser loginUser) throws Exception {
        return database.loginUser(loginUser);
    }

    public boolean createEvent(EventListReq eventList) throws Exception {
        String token = UserController.token;
        return database.createEvent(eventList,jwtUtil.extractUserName(token));
    }

    public boolean deleteEvent(int eventID) throws Exception {
        return database.deleteEvent(eventID);
    }

    public boolean updateEvent(EventListReq list, int id) throws Exception {
        return database.updateEvent(id,list);
    }

    public List<EventListRes> eventLists() throws Exception {
        String token = UserController.token;
        return database.getAllEvent(jwtUtil.extractUserName(token));
    }
}

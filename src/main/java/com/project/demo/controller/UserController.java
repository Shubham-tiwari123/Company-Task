package com.project.demo.controller;

import com.project.demo.entity.*;
import com.project.demo.service.UserService;
import com.project.demo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil  jwtUtil;

    public static String token;

    /**
     * This API is used to register a new user and store the user in db.
     * @param registerUser
     * @return
     */
    @PostMapping("/register_user")
    public Map<String,Boolean> registerUser(@RequestBody RegisterUser registerUser){
        Map<String,Boolean> res = new HashMap<>();
        try {
            if (userService.registerUser(registerUser)) {
                res.put("status", true);
                return res;
            }
            res.put("status", false);
            return res;
        }catch (Exception e){
            System.out.println("Exception:"+e);
            res.put("status", false);
            return res;
        }
    }


    /**
     * This API is used to check the user credentials for login. If the credentials are valid then a JWT token is
     * returned is the response. This token is used in further api calls.
     * @param loginUser
     * @return
     */
    @PostMapping("/login_user")
    public Map<String,String> loginUser(@RequestBody LoginUser loginUser){
        System.out.println("Called");
        Map<String,String> res = new HashMap<>();
        try {
            if (userService.checkCredentials(loginUser)) {
                String jwt = jwtUtil.generateToken(loginUser.getUserEmail());
                res.put("jwt", jwt);
                res.put("status", "true");
                return res;
            }
            res.put("status", "false");
            return res;
        }catch (Exception e){
            System.out.println("Exception:"+e);
            res.put("status", "false");
            return res;
        }
    }


    /**
     * This API is used to create an event for the login users.
     * First the JWT token is extracted from header and it is verified. If token is correct then only this api is called
     * else user is not allowed to create an event
     * @param eventList
     * @return
     */
    @PostMapping("/create_event")
    public Map<String,String> createNewEvent(@RequestBody EventListReq eventList){
        System.out.println("Called create event");
        Map<String,String> res = new HashMap<>();
        try {
            if (userService.createEvent(eventList)) {
                res.put("eventStatus", "true");
                return res;
            }
            res.put("eventStatus", "false");
            return res;
        }catch (Exception e){
            System.out.println("Exception:"+e);
            res.put("eventStatus", "false");
            return res;
        }
    }

    /**
     * This API is used to delete an event for the login users.
     * First the JWT token is extracted from header and it is verified. If token is correct then only this api is called
     * else user is not allowed to delete an event
     * @param id
     * @return
     */
    @DeleteMapping("/delete_event/{id}")
    public Map<String,String> deleteEvent(@PathVariable String id){
        System.out.println("Called delete event");
        int eventId = Integer.parseInt(id);
        Map<String,String> res = new HashMap<>();
        try {
            if (userService.deleteEvent(eventId)) {
                res.put("deleteStatus", "true");
                return res;
            }
            res.put("deleteStatus", "false");
            return res;
        }catch (Exception e){
            System.out.println("Exception:"+e);
            res.put("status", "false");
            return res;
        }
    }

    /**
     * This API is used to update an event for the login users.
     * First the JWT token is extracted from header and it is verified. If token is correct then only this api is called
     * else user is not allowed to update an event
     * @param eventList
     * @param id
     * @return
     */
    @PutMapping("/update_event/{id}")
    public Map<String,String> updateEvent(@RequestBody EventListReq eventList, @PathVariable String id){
        System.out.println("Called delete event");
        int eventId = Integer.parseInt(id);
        Map<String,String> res = new HashMap<>();
        try {
            if (userService.updateEvent(eventList, eventId)) {
                res.put("updateStatus", "true");
                return res;
            }
            res.put("updateStatus", "false");
            return res;
        }catch (Exception e){
            System.out.println("Exception:"+e);
            res.put("updateStatus", "false");
            return res;
        }
    }

    /**
     * This API is used to get all the events for the login users.
     * First the JWT token is extracted from header and it is verified. If token is correct then only this api is called
     * else user is not allowed to access this api
     * @return
     */
    @GetMapping("/event_list")
    public List<EventListRes> getEventList(){
        try {
            return userService.eventLists();
        }catch (Exception e){
            System.out.println("exception:"+e);
            return null;
        }
    }
}

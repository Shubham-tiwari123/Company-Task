package com.project.demo.dao;

import com.project.demo.entity.EventListReq;
import com.project.demo.entity.EventListRes;
import com.project.demo.entity.LoginUser;
import com.project.demo.entity.RegisterUser;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class is used to handle all the database related operations
 */
@Service
public class Database {

    private Connection connect = null;
    private Statement statement, statement1 = null;
    private ResultSet resultSet, rs1 = null;

    public boolean createDbConnection() throws Exception {
        String dbClassName = "com.mysql.cj.jdbc.Driver";
        Class.forName(dbClassName);
        Properties property = new Properties();
        property.put("user", "root");
        property.put("password", "");
        String CONNECTION = "jdbc:mysql://127.0.0.1/company?serverTimezone=UTC";
        connect = DriverManager.getConnection(CONNECTION, property);
        statement = connect.createStatement();
        statement1 = connect.createStatement();
        return true;
    }

    public boolean registerUser(RegisterUser registerUser) throws Exception{
        if (createDbConnection()){
            String sql = "insert into register_user(userEmail,userName, password) " +
                    "values ('"+registerUser.getUserEmail()+"','"+registerUser.getName()+"'," +
                    "'"+registerUser.getPassword()+"')";
            statement.execute(sql);
            return true;
        }
        return false;
    }

    public boolean loginUser(LoginUser loginUser) throws Exception{
        if (createDbConnection()){
            String sql ="select * from register_user where userEmail='"+loginUser.getUserEmail()+"'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String password = resultSet.getString("password");
                if (password.equals(loginUser.getPassword()))
                    return true;
            }
            return false;
        }
        return false;
    }

    public boolean createEvent(EventListReq eventList,String userName) throws Exception{
        if (createDbConnection()){
            String sql = "insert into event_list(eventName,eventDate, eventPlace,userName) " +
                    "values ('"+eventList.getEventName()+"','"+eventList.getEventDate()+"'," +
                    "'"+eventList.getPlace()+"','"+userName+"')";
            statement.execute(sql);
            return true;
        }
        return false;
    }

    public boolean deleteEvent(int eventID) throws Exception{
        if (createDbConnection()){
            String sql = "delete from event_list where eventID="+eventID;
            statement.execute(sql);
            return true;
        }
        return false;
    }

    public boolean updateEvent(int eventID, EventListReq list) throws Exception{
        if (createDbConnection()){
            String sql = "update event_list set eventName='"+list.getEventName()+"',eventPlace='"+list.getPlace()+"'," +
                    "eventDate='"+list.getEventDate()+"' where eventID="+eventID;
            statement.execute(sql);
            return true;
        }
        return false;
    }

    public List<EventListRes> getAllEvent(String userName) throws Exception{
        List<EventListRes> list = new ArrayList<>();
        if (createDbConnection()){
            String sql ="select * from event_list where userName='"+userName+"'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                EventListRes eventList = new EventListRes();
                eventList.setEventID(resultSet.getInt("eventID"));
                eventList.setEventName(resultSet.getString("eventName"));
                eventList.setEventDate(resultSet.getString("eventDate"));
                eventList.setPlace(resultSet.getString("eventPlace"));
                list.add(eventList);
            }
            return list;
        }
        return null;
    }

    public String getPassword(String userName) throws Exception {
        if (createDbConnection()){
            String sql ="select * from register_user where userEmail='"+userName+"'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                return resultSet.getString("password");
            }
            return "";
        }
        return null;
    }
}

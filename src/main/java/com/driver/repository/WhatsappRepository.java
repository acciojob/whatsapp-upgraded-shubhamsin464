package com.driver.Repository;

import com.driver.Group;
import com.driver.Message;
import com.driver.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Repository
public class WhatsappRepository {
    HashMap<String, User> userList;
    HashMap<String,List<User>>groupList;
    //List<Group>groupList;
    HashMap<Integer, Message>messageList;
    public WhatsappRepository(){
        userList=new HashMap<>();
        groupList=new HashMap<>();
        messageList=new HashMap<>();
    }

    public String addUser(String name, String mobile) throws Exception {
        for(User user : userList.values()){
            if(user.getMobile().equals(mobile))
                throw new Exception("User already exists");
        }
        User user =new User(name,mobile);
        userList.put(mobile,user);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
        int size=users.size();
        Group group=null;
        if(size==2){
            User user=users.get(1);
            group=new Group(user.getName(),2);

        }
        else if(size>2){
            int size1=groupList.size()+1;
            group=new Group("Group "+size1,size);
            groupList.put(group.getName(),users);

        }
        return group;
    }

    public int createMessage(String content) {
        Message m=new Message(messageList.size()+1,content,new Date());
        messageList.put(m.getId(),m);
        return m.getId();
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if(!groupList.containsKey(group.getName()))
            throw new Exception("Group does not exist");
        List<User>user1=groupList.get(group.getName());
        if(user1.get(0).getName().equals(approver))
            throw new Exception("Approver does not have rights");
        if(!user1.contains(user))
            throw new Exception("User is not a participant");
        int n=user1.indexOf(user);
        user1.set(n,user1.get(0));
        user1.set(0,user);
        return "SUCCESS";

    }

    public int removeUser(User user) throws Exception {
        for(String name : groupList.keySet()){
            List<User>users=groupList.get(name);
            if(users.contains(user) && users.get(0).equals(user))
                throw new Exception("Cannot remove admin");
            if(users.contains(user)){
                users.remove(user);

            }

        }
        return  0;
    }
}

package com.driver.Services;

import com.driver.Group;
import com.driver.Message;
import com.driver.Repository.WhatsappRepository;
import com.driver.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhatsappService {
    // @Autowired
    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
        return whatsappRepository.addUser(name,mobile);

    }

    public Group createGroup(List<User> users) {
        return  whatsappRepository.createGroup(users);

    }

    public int createMessage(String content) {
        return whatsappRepository.createMessage(content);

    }

    public int sendMessage(Message message, User sender, Group group) {
        return  0;
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        return whatsappRepository.changeAdmin(approver, user, group);
    }

    public int removeUser(User user) {
        return 0;
        //whatsappRepository.removeUser(user);
    }
}

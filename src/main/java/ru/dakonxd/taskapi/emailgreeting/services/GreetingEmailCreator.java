package ru.dakonxd.taskapi.emailgreeting.services;

import org.springframework.stereotype.Service;
import ru.dakonxd.taskapi.emailgreeting.interfaces.EmailCreator;
import ru.dakonxd.taskapi.security.entities.User;

@Service
public class GreetingEmailCreator implements EmailCreator {

    @Override
    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    @Override
    public String createEmailTitle(User user) {
        return "Спасибо за регистрацию в сервисе <TaskTracker>!";
    }

    @Override
    public String createEmailText(User user) {
        return "Welcome to our service for task tracking!\n" +
                "Your login for authorization: " + user.getUsername();
    }
}

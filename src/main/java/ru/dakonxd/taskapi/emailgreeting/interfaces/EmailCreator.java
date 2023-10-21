package ru.dakonxd.taskapi.emailgreeting.interfaces;


import ru.dakonxd.taskapi.security.entities.User;

public interface EmailCreator {

    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}

package ru.dakonxd.taskapi.emailgreeting.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dakonxd.taskapi.emailgreeting.model.dto.EmailDTO;
import ru.dakonxd.taskapi.emailgreeting.services.rabbitmq.RabbitProducer;
import ru.dakonxd.taskapi.security.entities.User;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailCreatorService emailCreatorService;
    private final RabbitProducer rabbitProducer;


    public void sendEmail(User user) {
        List<User> users = new ArrayList<>(List.of(user));
        List<EmailDTO> emailMessages = emailCreatorService.getEmailMessages(users);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(emailMessages);
            rabbitProducer.sendMessage(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

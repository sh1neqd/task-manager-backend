package ru.dakonxd.taskapi.emailgreeting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private String recipientAddress;
    private String title;
    private String text;
}

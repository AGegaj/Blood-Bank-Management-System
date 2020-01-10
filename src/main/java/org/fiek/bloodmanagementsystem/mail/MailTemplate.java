package org.fiek.bloodmanagementsystem.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailTemplate {

    private String from;

    private String to;

    private String subject;

    private String text;
}

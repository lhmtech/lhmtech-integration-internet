package com.lhmtech.integration.internet.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

import javax.mail.internet.MimeMessage

/**
 * Created by lihe on 16-12-7.
 */
@Component
class EmailSender {
    @Autowired
    JavaMailSender javaMailSender

    void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage()
        MimeMessageHelper helper = new MimeMessageHelper(message, true)
        helper.setSubject(subject)
        helper.setTo(to)
        helper.setText(body, true)
        javaMailSender.send(message)
    }
}

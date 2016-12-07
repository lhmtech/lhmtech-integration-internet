package com.lhmtech.integration.internet.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import spock.lang.Specification

import javax.mail.internet.MimeMessage

/**
 * Created by lihe on 16-12-7.
 */
class EmailSenderTest extends Specification {
    def 'send email'() {
        given:
        JavaMailSender mockSender = Mock(JavaMailSender)
        EmailSender emailSender = new EmailSender()
        emailSender.javaMailSender = mockSender
        MimeMessage mockMessage = Mock(MimeMessage)
        MimeMessageHelper mockHelper = GroovyMock(MimeMessageHelper, global: true)

        when:
        emailSender.sendEmail('to', 'subject', 'body')

        then:
        1 * mockSender.createMimeMessage() >> mockMessage
        1 * new MimeMessageHelper(mockMessage, true) >> mockHelper
        1 * mockHelper.setSubject('subject')
        1 * mockHelper.setTo('to')
        1 * mockHelper.setText('body', true)
        1 * mockSender.send(mockMessage)

    }
}

package com.lhmtech.integration.internet.email

import com.lhmtech.common.system.FileUtility
import com.lhmtech.integration.internet.http.HttpClient
import groovy.util.slurpersupport.NodeChild
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by lihe on 16-12-6.
 */
@Ignore
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailSenderBlackboxTest extends Specification {
    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    EmailSender emailSender

    def "send test email"() {
        expect:
        emailSender.sendEmail('lihe.chen@gmail.com',
                'This is a test',
                '<h1>Hello World!</h1>'
        )
    }
}

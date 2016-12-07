package com.lhmtech.integration.internet.http

import com.lhmtech.common.system.FileUtility
import com.lhmtech.integration.internet.http.HttpClient
import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.HTTPBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by lihe on 16-12-6.
 */
class HttpClientBlackboxTest extends Specification {

    @Shared
    String location='./build/fileDownloader/'
    def setup() {
        new File(location).deleteDir()
    }

    def cleanup() {
        new File(location).deleteDir()
    }

    def 'download file' () {
        given:
        String filename = 'google'

        when:
        new HttpClient().downloadFile('https://www.google.com/', location, filename, true)

        then:
        new File(FileUtility.combinePath(location, filename)).exists()
    }

    def "HttpGet"() {
        given:
        String url = 'http://www.google.com'

        when:
        NodeChild html = new HttpClient().httpGet(url)

        then:
        html
    }
}

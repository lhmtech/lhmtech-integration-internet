package com.lhmtech.integration.internet.http

import com.lhmtech.common.system.FileUtility
import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.HTTPBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by lihe on 16-12-6.
 */
class HttpClientTest extends Specification {

    @Shared
    String location='./build/fileDownloader/'
    def setup() {
        new File(location).deleteDir()
    }

    def cleanup() {
        new File(location).deleteDir()
    }

    @Unroll
    def 'download file when file does not exists with overwrite flag = #overwrite' () {
        given:
        String filename = 'the-filename'
        String url = 'the-url'
        String binaryData = 'the-data'
        URL mockURL = GroovyMock(URL, global: true)
        InputStream mockInputStream = new ByteArrayInputStream(binaryData.getBytes());

        when:
        new HttpClient().downloadFile(url, location, filename, overwrite)

        then:
        1 * new URL(url) >> mockURL
        1 * mockURL.openStream() >> mockInputStream
        new File(FileUtility.combinePath(location, filename)).text == binaryData

        where:
        overwrite << [true, false]
    }

    def 'do not download file when file exists and overwrite flag = false' () {
        given:
        //create an exisiting file
        String filename = 'the-filename'
        String oldData = 'old-data'
        FileUtility.ensureFolder(location)
        new File(FileUtility.combinePath(location, filename)) << oldData

        String url = 'the-url'
        String binaryData = 'the-data'
        URL mockURL = GroovyMock(URL, global: true)
        InputStream mockInputStream = new ByteArrayInputStream(binaryData.getBytes());

        when:
        new HttpClient().downloadFile(url, location, filename, false)

        then:
        0 * new URL(url) >> mockURL
        0 * mockURL.openStream() >> mockInputStream
        new File(FileUtility.combinePath(location, filename)).text == oldData
    }

    def 'download file when file exists and overwrite flag = true' () {
        given:
        //create an exisiting file
        String filename = 'the-filename'
        String oldData = 'old-data'
        FileUtility.ensureFolder(location)
        new File(FileUtility.combinePath(location, filename)) << oldData

        String url = 'the-url'
        String binaryData = 'the-data'
        URL mockURL = GroovyMock(URL, global: true)
        InputStream mockInputStream = new ByteArrayInputStream(binaryData.getBytes());

        when:
        new HttpClient().downloadFile(url, location, filename, true)

        then:
        1 * new URL(url) >> mockURL
        1 * mockURL.openStream() >> mockInputStream
        new File(FileUtility.combinePath(location, filename)).text == binaryData
    }

    def "HttpGet"() {
        given:
        HTTPBuilder mockBuider = GroovyMock(HTTPBuilder, global: true)
        NodeChild mockNode = Mock(NodeChild)
        String url = 'http://www.google.com'

        when:
        NodeChild html = new HttpClient().httpGet(url)

        then:
        1 * new HTTPBuilder(url) >> mockBuider
        1 * mockBuider.get([:]) >> mockNode
        html == mockNode
    }
}

package com.lhmtech.integration.internet.http

import com.lhmtech.common.system.FileUtility
import groovy.util.slurpersupport.NodeChild
import groovyx.net.http.HTTPBuilder

/**
 * Created by lihe on 16-12-6.
 */
class HttpClient {
    void downloadFile(String url, String location, String filename, boolean overwrite) {
        FileUtility.ensureFolder(location)
        String fullFilePath = FileUtility.combinePath(location, filename)
        File imageFile = new File(fullFilePath)
        if (imageFile.exists() && !overwrite) {
            return
        }
        BufferedOutputStream stream = imageFile.newOutputStream()
        stream << new URL(url).openStream()
        stream.close()
    }

    NodeChild httpGet(String url) {
        HTTPBuilder httpBuilder = new HTTPBuilder(url)
        NodeChild html = httpBuilder.get([:])
        html
    }
}

package com.hallocasa.view.fileserver;

import com.hallocasa.commons.constants.SystemConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
@WebServlet(value = "/images/wcm/*")
public class FileServer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private class ImageInfo {

        private String extension;
        private String fullName;
        private String type;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // gets the requested file code
        ImageInfo imageInfo = getImageInfo(request);
        response.setContentType("image/" + imageInfo.extension);

        // forces download
        // String headerKey = "Content-Disposition";
        // String headerValue = String.format("attachment; filename=\"%s\"",
        // downloadFile.getName());
        // response.setHeader(headerKey, headerValue);
        // obtains response's output stream
        InputStream is;
        try (
                OutputStream outStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            int size = 0;
            is = new FileInputStream(new File(SystemConstants.WEBCONTENT_IMAGES_PATH
                    + imageInfo.fullName));
            while ((bytesRead = is.read(buffer)) != -1) {
                size = size + bytesRead;
                outStream.write(buffer, 0, bytesRead);
            }
            response.setContentLength(size);
        }
        is.close();
    }

    /**
     *
     * @param request
     * @return
     */
    private ImageInfo getImageInfo(HttpServletRequest request) {
        String baseUrl;
        if ((request.getServerPort() == 80) || (request.getServerPort() == 443)) {
            baseUrl = request.getScheme() + "://" + request.getServerName()
                    + request.getContextPath() + "/images/wcm/";
        } else {
            baseUrl = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath()
                    + "/images/wcm/";
        }

        StringBuffer buf = request.getRequestURL();

        if (request.getQueryString() != null) {
            buf.append("?");
            buf.append(request.getQueryString());
        }

        String imageName = buf.substring(baseUrl.length());
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.fullName = imageName;
        String[] subparts = imageName.split("\\.");
        imageInfo.extension = subparts[subparts.length - 1];
        imageInfo.type = "wcm";
        return imageInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}

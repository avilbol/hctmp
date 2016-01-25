/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.servlet;

import com.hallocasa.services.FileServices;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander Villamil
 */
public class ImageServlet extends HttpServlet {
   

    private String imagePath;
    
    /**
     * @inheritDoc
     * @throws ServletException 
     */
    //@Override
    public void init() throws ServletException {
        // Define base path somehow. You can define it as init-param of the servlet.
        this.imagePath = FileServices.USER_IMAGES_PATH;

        // In a Windows environment with the Applicationserver running on the
        // c: volume, the above path is exactly the same as "c:\var\webapp\images".
        // In Linux/Mac/UNIX, it is just straightforward "/var/webapp/images".
    }
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ServletContext cntx= getServletContext();

        String filename = request.getPathInfo();
        
        File file = new File(this.imagePath, URLDecoder.decode(filename, "UTF-8"));
        
        response.setContentType(cntx.getMimeType(file.getName()));
        response.setContentLength((int)file.length());
        
        try (FileInputStream in = new FileInputStream(file); 
                OutputStream out = response.getOutputStream()) {
            
            byte[] buf = new byte[1024];
            int count;
            while((count = in.read(buf)) >= 0){
                out.write(buf, 0, count);
            }
        }
    }
}
    
    


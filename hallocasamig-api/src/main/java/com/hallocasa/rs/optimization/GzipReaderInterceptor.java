package com.hallocasa.rs.optimization;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class GzipReaderInterceptor implements ReaderInterceptor {

	/**
	 * Log de la clase.
	 */
	private static final Logger LOG = LogManager.getLogger(GzipReaderInterceptor.class);
	
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context)  throws IOException, WebApplicationException {
        LOG.info("aroundReadFrom");
    	if ("gzip".equals(context.getHeaders().get("Content-Encoding"))) {
    		LOG.info("is gzip");
            InputStream originalInputStream = context.getInputStream();
            context.setInputStream(new GZIPInputStream(originalInputStream));
        }
    	LOG.info("proceed");
        return context.proceed();
    }

}
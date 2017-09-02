package com.avsoft.commons;

import java.util.HashMap;
import java.util.Map;

public class MimeTypeMetadata {

	public static Map<String, String> mimeTypeExt;
	
	static{
		mimeTypeExt = new HashMap<String, String>();
		mimeTypeExt.put("image/jpeg", "jpg");
		mimeTypeExt.put("image/pjpeg", "jpg");
		mimeTypeExt.put("image/jpg", "jpg");
		mimeTypeExt.put("image/png", "png");
		mimeTypeExt.put("image/gif", "gif");
		mimeTypeExt.put("image/bmp", "bmp");
		mimeTypeExt.put("text/plain", "txt");
		mimeTypeExt.put("application/pdf", "pdf");
	}
	
	public static String get(String mimeType){
		return mimeTypeExt.get(mimeType);
	}
	
}

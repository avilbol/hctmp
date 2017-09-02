package com.avsoft.commons;

import static com.avsoft.commons.RandomUtils.alphanumericRandom;
import static java.lang.String.format;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.detect.Detector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.imgscalr.Scalr;

import com.avsoft.commons.exceptions.FatalException;

public class AvsFileManager {
	
	private static final Logger LOG = LogManager.getLogger(AvsFileManager.class);

	public static void cleanFilesStartingWithPrefix(String dir, String prefix){
		if(prefix == null){
			return;
		}
		File folder = new File(dir + "/");
		final File[] files = folder.listFiles( new FilenameFilter() {
		    @Override
		    public boolean accept( final File dir,
		                           final String name ) {
		        return name.startsWith(prefix);
		    }
		} );
		for ( final File file : files ) {
		    if ( !file.delete() ) {
		       throw new FatalException("Can't remove " + file.getAbsolutePath() );
		    }
		}
	}
	
	public static String encodeToBase64(String dir, String filename){
		try{
			File file = new File(dir + "/" + filename);
			return Base64.encodeBase64String(FileUtils.readFileToByteArray(file));
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
	}
	
	public static String createFileFromBase64(String dir, String base64Name, String prefix){
		byte[] data = decodeBase64(base64Name);
		String filename = null;
		try{
			String str = "%s/%s-%s.%s";
			filename = format(str, dir, prefix, alphanumericRandom(20), getExtension(data));
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
		try (OutputStream stream = new FileOutputStream(filename)) {
		    stream.write(data);
		    return filename;
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
	}
	
	public static String createMinifiedImage(String dir, String fileLocation, Integer width, Integer height){
		try {
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
		    ImageReader reader = null;
		    while(readers.hasNext()) {
		        reader = readers.next();
		        LOG.info(reader);
		    }
			BufferedImage in = ImageIO.read(new File(fileLocation));
			double imgHeight = in.getHeight();
			double imgWidth = in.getWidth();
			double scaleHeight = imgHeight / height;
			double scaleWidth = imgWidth / width;
			double scaleCutHeight = 1;
			double scaleCutWidth = 1;
			if (scaleHeight > 1 || scaleWidth > 1){
				double scale = scaleHeight > scaleWidth ? scaleHeight : scaleWidth;
				scaleCutHeight = scale;
				scaleCutWidth = scale;
			}
			int newHeight = (int)(imgHeight / scaleCutHeight);
			int newWidth = (int)(imgWidth / scaleCutWidth);
			BufferedImage scaledImage = Scalr.resize(in, newWidth, newHeight);
			
			String fileName = FilenameUtils.getName(fileLocation);
			String fileExt = FilenameUtils.getExtension(fileName);
			
			String minifiedFileLoc = dir + "//" + fileName;
			File outputfile = new File(minifiedFileLoc);
			LOG.debug(minifiedFileLoc);
			ImageIO.write(scaledImage, fileExt, outputfile);
			return minifiedFileLoc;
		} catch(IOException e){
			throw new FatalException("Unexpected error", e);
		}
	}


	public static String getExtension(byte[] data) throws IOException{
		try (InputStream is = new ByteArrayInputStream(data);
		        BufferedInputStream bis = new BufferedInputStream(is);) {
		    AutoDetectParser parser = new AutoDetectParser();
		    Detector detector = parser.getDetector();
		    MediaType mediaType = detector.detect(bis, new Metadata());
		    String mimeType = mediaType.toString();
		    String ext = MimeTypeMetadata.get(mimeType);
		    if(ext == null){
		    	throw new FatalException("Extension not allowed");
		    }
		    return ext;
		}
	}
	
	public static void replaceMassive(String dir, String toReplace, String replace){
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : directoryStream) {
            	String pathname = path.getFileName().toString();
            	if(pathname.startsWith(toReplace)){
            		Files.move(path, path.resolveSibling(pathname.replaceAll(toReplace, replace)));
            	}
            }
        } catch (IOException ex) {
        	throw new FatalException("Unexpected error", ex);
        }
	}
	
	public static void main(String[] args) throws IOException{
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
	    ImageReader reader = null;
	    while(readers.hasNext()) {
	        reader = readers.next();
	        System.out.println(reader);
	    }
		/*List<File> filesInFolder = Files.walk(Paths.get("C:\\Users\\avilbol\\Pictures\\mine"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
		for(File file : filesInFolder){
			String folderForMinifiedImages = "C:\\Users\\avilbol\\Pictures\\mine-minified";
			createMinifiedImage(folderForMinifiedImages, file.getAbsolutePath(), 388, 259);
		}*/
	}
	
	public static List<String> fileList(String directory) {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                fileNames.add(path.toString());
            }
        } catch (IOException ex) {}
        return fileNames;
    }
	
	public static byte[] decodeBase64(String base64Str){
		if(base64Str == null || base64Str.isEmpty()){
			throw new FatalException("Invalid base64 file");
		}
		String[] base64ElementList = base64Str.split(",");
		return Base64.decodeBase64(base64ElementList[base64ElementList.length - 1]);
	}
}

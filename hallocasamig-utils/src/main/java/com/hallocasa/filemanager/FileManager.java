package com.hallocasa.filemanager;

import static com.hallocasa.randomutils.RandomUtils.alphanumericRandom;

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
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.tika.detect.Detector;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;

import com.hallocasa.utils.constants.exceptions.FatalException;
import static java.lang.String.format;

public class FileManager {

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
		String folder = "D:\\development\\hallocasa-portal-utils\\test2";
		replaceMassive(folder, "new-accght", "accght");
		/*String fromFolder = "D:\\development\\hallocasa-portal-utils\\test/from";
		String toFolder = "D:\\development\\hallocasa-portal-utils\\test/to";
		String prefix = "adfght";
		String file1B64 = encodeToBase64(fromFolder, "1.jpg");
		String file2B64 = encodeToBase64(fromFolder, "2.jpg");
		String file3B64 = encodeToBase64(fromFolder, "3.jpg");
		cleanFilesStartingWithPrefix(toFolder, prefix);
		System.out.println(createFileFromBase64(toFolder, file1B64, prefix));
		createFileFromBase64(toFolder, file2B64, prefix);
		createFileFromBase64(toFolder, file3B64, prefix);*/
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

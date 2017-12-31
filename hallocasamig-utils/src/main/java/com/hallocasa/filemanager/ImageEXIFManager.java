package com.hallocasa.filemanager;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

/**
 * This class will manage the image EXIF autoritation tags handling
 * @author avillamil
 */
public class ImageEXIFManager {

	private static final Logger LOG = LogManager.getLogger(ImageEXIFManager.class);
	
	/**
	 * Review if an image has EXIF rotation tags and apply then if so
	 * @param file
	 * @return
	 * @throws ImageProcessingException
	 * @throws IOException
	 * @throws MetadataException
	 */
	public static void tryAutoRotate(File file, String ext) throws IOException {
		try{
			BufferedImage image = correctOrientation(file);
			ImageIO.write(image, ext, file);
		} catch(Exception e){
			LOG.error("Error when trying to do EXIF autorotate" + e);
		}
	}
	
	/**
	 * Checks the orientation of the image and corrects it if necessary.
	 * <p>If the orientation of the image does not need to be corrected, no operation will be performed.</p>
	 * @param inputStream
	 * @return
	 * @throws ImageProcessingException
	 * @throws IOException
	 * @throws MetadataException
	 */
	private static BufferedImage correctOrientation(File file) throws ImageProcessingException, IOException, MetadataException {
		FileInputStream fis1 = new FileInputStream(file);
		FileInputStream fis2 = new FileInputStream(file);
		BufferedImage bimg = ImageIO.read(fis1);
		Metadata metadata = ImageMetadataReader.readMetadata(fis2);
	    if(metadata != null) {
	        if(metadata.containsDirectoryOfType(ExifIFD0Directory.class)) {
	            // Get the current orientation of the image
	            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
	            int orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
	            // Get the current width and height of the image
	            int width = bimg.getWidth();
	            int height = bimg.getHeight();
	            // Determine which correction is needed
	            AffineTransform t = new AffineTransform();
	            switch(orientation) {
	            case 1:
	                // no correction necessary skip and return the image
	                return bimg;
	            case 2: // Flip X
	                t.scale(-1.0, 1.0);
	                t.translate(-width, 0);
	                return transform(bimg, t);
	            case 3: // PI rotation 
	                t.translate(width, height);
	                t.rotate(Math.PI);
	                return transform(bimg, t);
	            case 4: // Flip Y
	                t.scale(1.0, -1.0);
	                t.translate(0, -height);
	                return transform(bimg, t);
	            case 5: // - PI/2 and Flip X
	                t.rotate(-Math.PI / 2);
	                t.scale(-1.0, 1.0);
	                return transform(bimg, t);
	            case 6: // -PI/2 and -width
	                t.translate(height, 0);
	                t.rotate(Math.PI / 2);
	                return transform(bimg, t);
	            case 7: // PI/2 and Flip
	                t.scale(-1.0, 1.0);
	                t.translate(height, 0);
	                t.translate(0, width);
	                t.rotate(  3 * Math.PI / 2);
	                return transform(bimg, t);
	            case 8: // PI / 2
	                t.translate(0, width);
	                t.rotate(  3 * Math.PI / 2);
	                return transform(bimg, t);
	            }
	        }
	    }
	    return null;
	}

	/**
	 * Performs the transformation
	 * @param bimage
	 * @param transform
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage transform(BufferedImage bimage, AffineTransform transform) throws IOException {
	    // Create an transformation operation
	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
	    // Create an instance of the resulting image, with the same width, height and image type than the referenced one
	    BufferedImage destinationImage = new BufferedImage( bimage.getHeight(), bimage.getWidth(), bimage.getType() );
	    op.filter(bimage, destinationImage);
	    return destinationImage;
	}
}

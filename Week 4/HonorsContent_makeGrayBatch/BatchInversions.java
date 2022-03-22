
/**
 *  Create new images that are photographic negatives (or inverted images) of selected images and save these new images 
 *  with filenames that are related to the original images
 * 
 * @author Joyce Lin
 * @version 1.0
 */
import edu.duke.*;
import java.io.*;

public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            // look for the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource inversion = makeInversion(inImage);
            inversion.setFileName("images/inverted-" + inImage.getFileName());
            inversion.draw();
            inversion.save();
        }
    }
    public void testInversion() {
	//ImageResource ir = new ImageResource();
	//ImageResource inversion = makeInversion(ir);
	//inversion.draw();
	selectAndConvert();
    }
}

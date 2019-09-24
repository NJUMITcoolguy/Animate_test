package edu.nyu.cs9053.homework3;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * User: blangel
 */

public class AnimatedAsciiArt {
	
	private static final String[] folder = {"/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/0.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/1.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/2.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/3.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/4.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/5.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/6.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/7.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/8.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/9.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/10.gif","/c/Users/njuli/Desktop/MS/CSGY_9053/hw3/fall-2019-i-homework-3-NJUMITcoolguy/src/main/resources/got/11.gif"};

    public static void main(String[] args) {
        AnimatedAsciiArt animatedAsciiArt = new AnimatedAsciiArt(args);
        animatedAsciiArt.play();
    }

    /**
     * @implNote input must not be null or empty if so {@code throw new IllegalArgumentException();}
     * @implNote use the {@linkplain #convert(String)}
     * @param files to convert into {@linkplain ImageInfoProvider[]}
     * @return converted {@linkplain ImageInfoProvider[]}
     */
    protected static ImageInfoProvider[] convert(String[] files) {
	    if (files == null || files.length == 0) {
	    	throw new IllegalArgumentException();
	    }
	    ImageInfoProvider[] converted_Imageinfoprivoder = new ImageInfoProvider[files.length];
	    for (int i = 0; i < files.length; i++) {
	    	converted_Imageinfoprivoder[i] = convert(files[i]);
	    }
	    return converted_Imageinfoprivoder;
    }

    protected static ImageInfoProvider convert(String file) {
        try {
            return new ImageInfoProvider(ImageIO.read(new File(file)));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private final ImageInfoProvider[] providers;

    private final AsciiArtConverter converter;

    private final AsciiArtPrinter printer;

    public AnimatedAsciiArt(String[] files) {
        this(convert(files), new AsciiArtConverter(), new AsciiArtPrinter());
    }

    public AnimatedAsciiArt(ImageInfoProvider[] providers, AsciiArtConverter converter, AsciiArtPrinter printer) {
        if ((providers == null) || (converter == null) || (printer == null)) {
            throw new IllegalArgumentException();
        }
        this.providers = providers;
        this.converter = converter;
        this.printer = printer;
    }

    /**
     * Converts the image data from {@linkplain #providers} into {@literal ASCII} art and prints.
     * @implNote before each print of art ensure the screen is cleared
     * @implNote after each print invoke {@linkplain #sleep()}
     */
    public void play() {
    	for (int i = 0; i < this.providers.length; i++) {
    		char[][] converted_Asciitable = converter.convert(providers[i]);
		    printer.clearScreen();
	        printer.print(converted_Asciitable);
	        sleep();
    	}
    }

    protected void sleep() {
        try {
            Thread.sleep(400L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }

}

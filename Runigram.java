import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = scaled(tinypic, 5, 5);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		 // read rgb values of each pixel and put them in the array
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int r = in.readInt();
                int g = in.readInt();
                int b = in.readInt();
                image[i][j] = new Color(r, g, b);
            }
        }
        return image;
	}

    // Prints the RGB values of a color the function get
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   
		System.out.printf("%3s,", c.getGreen()); 
        System.out.printf("%3s",  c.getBlue());  
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		// go over all pixels and print each one
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                print(image[i][j]);
            }
            System.out.println();
        }
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
	  // get number of rows
        int row = image.length;

        // get number of columns
        int col = image[0].length;

        // create a new image with the same size
        Color[][] flipped = new Color[row][col];

        // copy pixels from right to left
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                flipped[i][j] = image[i][col - 1 - j];
            }
        }
        return flipped;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		 // get number of rows
        int row = image.length;

        // get number of columns
        int col = image[0].length;

        // create a new image with the same size
        Color[][] flipped = new Color[row][col];
        
        // copy pixels from bottom to top
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                flipped[i][j] = image[row - 1 - i][j];
            }
        }
        return flipped;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		// compute brightness from red green blue of the pixel
        int lum = (int) (0.299 * pixel.getRed()
                       + 0.587 * pixel.getGreen()
                       + 0.114 * pixel.getBlue());

        // create gray color with this brightness
        Color gray = new Color(lum, lum, lum);

        return gray;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		// get number of rows
        int row = image.length;

        // get number of columns
        int col = image[0].length;

        // create a new image with the same size
        Color[][] grayscaled = new Color[row][col];

        // convert each pixel to gray using luminance
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grayscaled[i][j] = luminance(image[i][j]);
            }
        }
        return grayscaled;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		  // get number of rows in the original image
        int row = image.length;

        // get number of columns in the original image
        int col = image[0].length;

        // create a new image with the new size
        Color[][] scaledImg = new Color[height][width];
        
        // for each pixel in the new image choose a pixel from the original image
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int oldrow = i * row / height;
                int oldcol = j * col / width;
                scaledImg[i][j] = image[oldrow][oldcol];
            }
			      }

        return scaledImg;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		// mix red values of the two colors
        int newRed = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed());

        // mix green values of the two colors
        int newGreen = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());

        // mix blue values of the two colors
        int newBlue = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue());
        
        // create new color from the mixed values
        Color newColor = new Color(newRed, newGreen, newBlue);

        return newColor;
    }
	
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
	// create a new image with the same size as the input images
        Color[][] blendedColors = new Color[image2.length][image2[0].length];

        // blend each pair of pixels from the two images
        for (int i = 0; i < image1.length; i++) {
            for (int j = 0; j < image1[0].length; j++) {
                blendedColors[i][j] = blend(image1[i][j], image2[i][j], alpha);
            }
        }
        return blendedColors;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		// scale target image to the same size as the source image
        Color[][] targetScaled = scaled(target, source[0].length, source.length);
        setCanvas(targetScaled);
        StdDraw.show();

        // for each step blend the two images and show the result
        for (int i = 0; i <= n; i++) {
            double alpha = (double) i / n;
            Color[][] frame = blend(targetScaled, source, alpha);
            display(frame);
            StdDraw.pause(20);
        }	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
			
		}
	
	}
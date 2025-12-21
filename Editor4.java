import java.awt.Color;

public class Editor4 {

	public static void main (String[] args){
		//// Replace this comment with your code.
		//// This function is similar to the main function of Editor1.java			
	
	  // check that the user gave enough arguments
        if (args.length < 2) {
            System.out.println("Usage java Editor4 <image file> <steps>");
            return;
        }

        // get the file name and number of steps
        String fileName = args[0];
        int n = Integer.parseInt(args[1]);

        // read the original image
        Color[][] imageIn = Runigram.read(fileName);

        // make a gray image from the original image
        Color[][] imageOut = Runigram.grayScaled(imageIn);

        // show morph from the original image to the gray image
        Runigram.morph(imageIn, imageOut, n);
	
	}
}

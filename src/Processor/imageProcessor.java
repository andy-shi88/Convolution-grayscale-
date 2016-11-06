package Processor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class imageProcessor {
    public static final int bias = 128;
    public static final int stride = 1;
    
    public imageProcessor() {
        
    }
    
    public BufferedImage convertMatToBM(ArrayList<ArrayList<Double>> input){
        BufferedImage result = new BufferedImage(input.size(), input.get(0).size(), BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i< input.size(); i++){
            for (int j = 0; j<input.get(i).size(); j++) {
                int gray = (int)Math.round(input.get(i).get(j));
                Color c = new Color(gray, gray, gray);
                result.setRGB(i, j, c.getRGB());
            } 
        }
        return result;
    }
    public ArrayList<ArrayList<Double>> Convolve(ArrayList<ArrayList<Double>> input, double[] kernel)
    {
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
	
	int kernel_size = (int)Math.sqrt(kernel.length)- 1;
	for (int i = kernel_size; i < (input.size() - (this.stride - 1)); i += this.stride) { //loop vertically through image
            ArrayList<Double> temp_row = new ArrayList<Double>(); //storing temporary value of each convolved row
            for (int j = kernel_size; j < (input.get(i).size() - (this.stride - 1)); j += this.stride) { // loop horizontally through image
		//matrix multiplication for convolutional [dot product]
		int kernel_index = 0;
        	double temp_result = 0.0; //storing sum of kernel dot product			
		for (int k = (i - kernel_size); k <= (i); k++) { 
                    for (int l = (j - kernel_size); l <= (j); l++) {
			double mult_result = kernel[kernel_index] * input.get(k).get(l);
			temp_result += mult_result;
			kernel_index += 1;
                    }
		}
                temp_result += this.bias;
                //checking value bound
                if(temp_result <0)
                   temp_row.add(0.0);
                else if(temp_result > 255)
                   temp_row.add(255.0);
                else
                   temp_row.add(temp_result);
                //end of checking
            }
            result.add(temp_row);
        }
        int padd_num = (int)Math.floor(Math.sqrt(kernel.length)) - 1;
    	return this.Padd(result, padd_num);
    }
    public ArrayList<ArrayList<Double>> Padd(ArrayList<ArrayList<Double>> input, int n_pad) {
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
        int size = (n_pad) + input.size();
        ArrayList<Double> zero_row = new ArrayList<Double>();
        //initialize row of zero's
        for (int i = 0; i<size; i++){
            zero_row.add(0.0);
        }
        //end of initializing
        for (int i = 0; i< size ; i++) {
            if(i < (n_pad/2) || i >= input.size() + (n_pad/2)) {
                //add zero pad row on top and bottom row (condition based on n_pad value)
                result.add(zero_row);
            }else {
                ArrayList<Double> temp_row = new ArrayList<Double>(); //row for storing temporary values
                for (int j = 0; j < size; j++) { //considering image are scaled to square shape
                    if(j < (n_pad/2) || j >= input.size() + (n_pad/2)){
                        temp_row.add(0.0);
                    }else {
                        temp_row.add(input.get(i-(n_pad/2)).get(j - (n_pad/2)));
                    }
                }
                result.add(temp_row);
            }
            
        }
        return result;
    }
    public static ArrayList<ArrayList<Double>> getPixelArrayList(BufferedImage input)
    {
	ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
	for(int i = 0; i< input.getHeight(); i++) {
            ArrayList<Double> temp_row = new ArrayList<Double>();
            for (int j = 0; j < input.getWidth(); j++) {
                Color c = new Color(input.getRGB(i, j));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                int gray = red + green + blue;
                temp_row.add((double)gray); //retrieve gray scale pixel value of the image
            }
            result.add(temp_row);
	}
	return result;
    }
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
	  BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	  int x, y;
	  int ww = src.getWidth();
	  int hh = src.getHeight();
	  for (x = 0; x < w; x++) {
	    for (y = 0; y < h; y++) {
	      int col = src.getRGB(x * ww / w, y * hh / h);
	      img.setRGB(x, y, col);
	    }
	  }
	  return img;
    }
}

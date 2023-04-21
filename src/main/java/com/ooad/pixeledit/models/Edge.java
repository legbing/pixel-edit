package com.ooad.pixeledit.models;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Edge extends Filter {
    private String name;
    // Sobel filter

    private static Edge s = null;
    private static double[][] sobel = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
    

    HashMap<String, double[][]> filterMap = new HashMap<>();
    /*
    public Edge() {
        
        filterMap.put("sobel", );
    }
    */
    private Edge(String name, double[][] filter) {
        this.name = name;
        filterMap.put("sobel", filter);
    }

    public static Edge getInstance(String name, double[][] filter) {
        if (s == null) {
            s = new Edge(name, filter);
        }
        return s;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //filterMap.put("sobel", sobel);

    
    private int fixOutOfRangeRGBValues(double value) {
        if (value < 0.0) {
            value = -value;
        }
        if (value > 255) {
            return 255;
        } else {
            return (int) value;
        }
    }

    private double[][][] transformImageToArray(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        double[][][] image = new double[3][height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                image[0][i][j] = color.getRed();
                image[1][i][j] = color.getGreen();
                image[2][i][j] = color.getBlue();
                }
            }
        return image;
    }
    /*
    private double[][] applyConvolution(int width, int height, double[][][] image, double[][] filter) 
    {
        //Convolution convolution = new Convolution();

        int smallWidth = width - 3 + 1;
        int smallHeight = height - 3 + 1; 
        double [][] redConv = new double [smallWidth][smallHeight];
        double [][] greenConv = new double [smallWidth][smallHeight];
        double [][] blueConv = new double [smallWidth][smallHeight];
        for(int i=0;i<smallWidth;++i){
            for(int j=0;j<smallHeight;++j){
                redConv[i][j]=0;
                greenConv[i][j]=0;
                blueConv[i][j]=0;
            }
        }
        for(int i=0;i<smallWidth;++i){
            for(int j=0;j<smallHeight;++j){
                redConv[i][j] = singlePixelConvolution(image[0],i,j,filter,3, 3);
                greenConv[i][j] = singlePixelConvolution(image[1],i,j,filter,3, 3);
                blueConv[i][j] = singlePixelConvolution(image[2],i,j,filter,3, 3);
            }
        }
        //double[][] redConv = convolution.convolutionType2(image[0], height, width, filter, 3, 3, 1);
        //double[][] greenConv = convolution.convolutionType2(image[1], height, width, filter, 3, 3, 1);
        //double[][] blueConv = convolution.convolutionType2(image[2], height, width, filter, 3, 3, 1);
        double[][] finalConv = new double[redConv.length][redConv[0].length];
        for (int i = 0; i < redConv.length; i++) {
            for (int j = 0; j < redConv[i].length; j++) {
                finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
                }
            }
        return finalConv;
    }
    */
    private BufferedImage createImageFromConvolutionMatrix(BufferedImage originalImage, double[][] imageRGB)
    {
        BufferedImage writeBackImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageRGB.length; i++) {
            for (int j = 0; j < imageRGB[i].length; j++) {
                Color color = new Color(fixOutOfRangeRGBValues(imageRGB[i][j]),fixOutOfRangeRGBValues(imageRGB[i][j]),fixOutOfRangeRGBValues(imageRGB[i][j]));
                writeBackImage.setRGB(j, i, color.getRGB());
            }
        }
        //File outputFile = new File("EdgeDetection/edgesTmp.png");
        //ImageIO.write(writeBackImage, "png", outputFile);
        return writeBackImage;
    }

    @Override
    public BufferedImage applyFilter(BufferedImage bufferedImage) {
        double[][][] image = transformImageToArray(bufferedImage);
        double[][] filter = filterMap.get(this.name);
        double[][] convolvedPixels = applyConvolution(bufferedImage.getWidth(),bufferedImage.getHeight(), image, filter);
        return createImageFromConvolutionMatrix(bufferedImage, convolvedPixels);
    }

    private double[][] applyConvolution(int width, int height, double[][][] image, double[][] filter) {
		Convolution convolution = new Convolution();
		double[][] redConv = convolution.convolutionType2(image[0], height, width, filter, 3, 3, 1);
		double[][] greenConv = convolution.convolutionType2(image[1], height, width, filter, 3, 3, 1);
		double[][] blueConv = convolution.convolutionType2(image[2], height, width, filter, 3, 3, 1);
		double[][] finalConv = new double[redConv.length][redConv[0].length];
		for (int i = 0; i < redConv.length; i++) {
			for (int j = 0; j < redConv[i].length; j++) {
				finalConv[i][j] = redConv[i][j] + greenConv[i][j] + blueConv[i][j];
			}
		}
		return finalConv;
	}

   
}


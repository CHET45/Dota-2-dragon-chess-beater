/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gabenscam;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author pc
 */
public class readFromImage {
     public static void main(String[] args) {
        try {
            // Load the image
            BufferedImage image = ImageIO.read(new File("D:\\Документы\\JavaProjects\\gabenScam\\screens\\Shot.jpg"));
            
            // Define the color mappings
            Color orange = new Color(255, 128, 0);
            Color red = new Color(255, 0, 0);
            Color blue = new Color(0, 0, 255);
            Color cyan = new Color(0, 255, 255);
            Color pink = new Color(255, 0, 255);
            Color yellow = new Color(255, 255, 0);

            int[][] array = new int[image.getHeight()][image.getWidth()];

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);

                    if (isColorMatch(color, orange)) {
                        array[y][x] = 1;
                    } else if (isColorMatch(color, red)) {
                        array[y][x] = 2;
                    } else if (isColorMatch(color, blue)) {
                        array[y][x] = 3;
                    } else if (isColorMatch(color, cyan)) {
                        array[y][x] = 4;
                    } else if (isColorMatch(color, pink)) {
                        array[y][x] = 5;
                    } else if (isColorMatch(color, yellow)) {
                        array[y][x] = 6;
                    } else {
                        array[y][x] = 0; // Default value for non-matching colors
                    }
                }
            }

            // Print the array
            for (int y = 0; y < array.length; y++) {
                for (int x = 0; x < array[y].length; x++) {
                    System.out.print(array[y][x] + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if two colors are approximately the same
    private static boolean isColorMatch(Color c1, Color c2) {
        int threshold = 50;
        return Math.abs(c1.getRed() - c2.getRed()) < threshold &&
               Math.abs(c1.getGreen() - c2.getGreen()) < threshold &&
               Math.abs(c1.getBlue() - c2.getBlue()) < threshold;
    }
}

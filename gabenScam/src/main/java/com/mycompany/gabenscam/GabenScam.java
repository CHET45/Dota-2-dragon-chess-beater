/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gabenscam;

import java.awt.*; 
import java.awt.image.BufferedImage; 
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO; 
/**
 *I made it in 4 days, so it has a lot of bugs
 * and to exit you need to open task manager 
 * @author pc
 */
public class GabenScam {
 public static final long serialVersionUID = 1L; 
    public static void main(String[] args) throws AWTException, InterruptedException {
        /*All colors*/
            Color orange = new Color(230, 120, 90);
            Color red = new Color(80, 20, 30);
            Color blue = new Color(90, 100, 200);
            Color blue2 = new Color(150, 190, 240);
            Color cyan = new Color(70, 160, 180);
            Color pink = new Color(200, 120, 220);
            Color yellow = new Color(250, 220, 170);
            Color yellow1 = new Color(190, 180, 190);
            Color[] colorArray={orange,red,blue,blue2,cyan,pink,yellow,yellow1};
        /* Array of cubes*/
            int[][] array = new int[8][8];
        try {
            Thread.sleep(1000); 
            Robot r = new Robot(); 
            /* It saves screenshot to desired path */
            String arrayPath = "D:\\Документы\\JavaProjects\\gabenScam\\screens\\array"; 
            String imagePath = "D:\\Документы\\JavaProjects\\gabenScam\\screens\\image"; 
            String oneShotPath = "D:\\Документы\\JavaProjects\\gabenScam\\screens"; 
            String oneShotDirectory;            
            /* Used to get ScreenSize and capture image */
            Rectangle capture =  
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            /*size is size of one cube*/
            int size=90;
            int rgb;
            
            for(int c=0;c<500;c++){
                BufferedImage Image = r.createScreenCapture(capture); 
                
                /*slotImage is image only of cubes*/
                /*BufferedImage slotImage=Image.getSubimage(230,140,710,710);     */          
                
                /*
                Foloder for cubes:
                If exist then delet everything inside
                */
                
                /*oneShotDirectory =oneShotPath+File.separator+"oneShots_"+Integer.toString(c);
                File directory=new File(oneShotDirectory);
                boolean directoryCreated=directory.mkdir();
                if (!directoryCreated)
                    deleteDirectory(directory);*/
                
                /*Total is 64 cubes*/
                for(int y=0;y<8;y++){
                    for(int x=0;x<8;x++){   
                        /*subImage is image of cube*/
                        BufferedImage subImage=Image.getSubimage(225+(x*size)+20,135+(y*size)+20,size-40,size-40);
                        /*Calculates color of cube*/
                        rgb=averageRGB(subImage,0,0);
                        Color color = new Color(rgb);

                        /*System.out.println("("+Integer.toString(x)+";"+Integer.toString(y)+")"+color.toString());*/
                        /*Data in array:*/
                        color=colorMatch(color,colorArray);
                        if(color==orange)
                            array[y][x] = 1;
                        else if(color==red)
                            array[y][x] = 2;
                        else if(color==blue || color==blue2)
                            array[y][x] = 3;
                        else if(color==cyan)
                            array[y][x] = 4;
                        else if(color==pink)
                            array[y][x] = 5;
                        else if(color==yellow||color==yellow1)
                            array[y][x] = 6;
                        
                        /*ImageIO.write(subImage, "png", (new File(oneShotDirectory+"\\oneShot("+Integer.toString(x)+";"+Integer.toString(y)+").png"))); */
                        subImage=null;
                        color=null;
                    }
                }
                
                
                
                /*screenshot resave*/
                /*(new File(imagePath+"_"+Integer.toString(c)+"_.jpg")).delete(); 
                ImageIO.write(Image, "jpg",new File(imagePath+"_"+Integer.toString(c)+"_.jpg")); 
                //array with data
                BufferedWriter writer = new BufferedWriter(new FileWriter(arrayPath+"_"+Integer.toString(c)+"_.txt", false));
                    for (int[] array1 : array) {
                        for (int x = 0; x < array1.length; x++) {
                            writer.write(Integer.toString(array1[x]) + " ");
                        }
                        writer.newLine();
                    }
                writer.flush();*/

                int color;
                int[] startPos = {-1,-1},endPos={-1,-1};
                outerloop:
                for(int y=0;y<8;y++){
                    for(int x=0;x<8;x++){
                        color=array[y][x];
                        
                        
                        if(x<6&&array[y][x+1]==color){
                            /*
                                0010
                                1101
                                0010
                            */
                            if(y>0&&array[y-1][x+2]==color){
                                /*
                                    0010
                                    1100
                                */
                                startPos[0]=y-1;startPos[1]=x+2;
                            }
                            else if(x<5&&array[y][x+3]==color){
                                /*
                                    1101
                                */
                                startPos[0]=y;startPos[1]=x+3;
                            }
                            else if(y<7&&array[y+1][x+2]==color){
                                /*
                                    1100
                                    0010
                                */
                                startPos[0]=y+1;startPos[1]=x+2;
                            }
                            if(startPos[0]!=-1){                                
                                endPos[0]=y;endPos[1]=x+2;
                            }
                        }
                        else if(x>1&&array[y][x-1]==color){
                            /*
                                0100
                                1011
                                0100
                            */
                            if(y>0&&array[y-1][x-2]==color){
                                /*
                                    100
                                    011
                                */
                                startPos[0]=y-1;startPos[1]=x-2;
                            }
                            else if(y<7&&array[y+1][x-2]==color){
                                /*
                                    011
                                    100
                                */
                                startPos[0]=y+1;startPos[1]=x-2;
                            }
                            else if(x>2&&array[y][x-3]==color){
                                /*
                                    1011
                                */
                                startPos[0]=y;startPos[1]=x-3;
                            }
                            if(startPos[0]!=-1){                                
                                endPos[0]=y;endPos[1]=x-2;
                            }
                        }
                        else if(y<6&&array[y+1][x]==color){
                            /*
                                010
                                010
                                101
                                010
                            */             
                            if(x<7&&array[y+2][x+1]==color)
                            {
                                /*
                                    10
                                    10
                                    01
                                */
                                startPos[0]=y+2;startPos[1]=x+1;
                            }
                            else if(y<5&&array[y+3][x]==color){
                                /*
                                    1
                                    1
                                    0
                                    1
                                */
                                startPos[0]=y+3;startPos[1]=x;
                            }
                            else if(x>0&&array[y+2][x-1]==color){
                                /*
                                    01
                                    01
                                    10
                                */
                                startPos[0]=y+2;startPos[1]=x-1;
                            }
                            if(startPos[0]!=-1){                                
                                endPos[0]=y+2;endPos[1]=x;
                            }
                        }                        
                        else if(y>1&&array[y-1][x]==color){
                            /*
                                010
                                101
                                010
                                010
                            */
                            if(x<7&&array[y-2][x+1]==color){
                                /*
                                    01
                                    10
                                    10
                                */
                                startPos[0]=y-2;startPos[1]=x+1;
                            }
                            else if(x>0&&array[y-2][x-1]==color){
                                /*
                                    10
                                    01
                                    01
                                */
                                startPos[0]=y-2;startPos[1]=x-1;
                            }
                            else if(y>2&&array[y-3][x]==color){
                                /*
                                    1
                                    0
                                    1
                                    1
                                */
                               startPos[0]=y-3;startPos[1]=x;
                            }
                            if(startPos[0]!=-1){                                
                                endPos[0]=y-2;endPos[1]=x;
                            }
                        }
                        if(endPos[0]!=-1){
                            moveMouse(r,startPos,endPos,size);
                            endPos[0]=-1;
                            startPos[0]=-1;
                            break outerloop;
                        }
                    }
                }
                /*Image=null;*/
                /*directory=null;*/
                Thread.sleep(50);
            }
        } 
        catch (/*IOException|*/
                AWTException ex) { 
            System.out.println(ex); 
        } 
    }
    private static int averageRGB(BufferedImage image,int x,int y){
        /*Takes 25 points of image
        and count arifmetic average r, g and b,
        so average color
        */        
        int avgR=0;
        int avgG=0;
        int avgB=0;
        int rgb;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++)
            {
                rgb=image.getRGB(x+(5*i), y+(5*j));
                Color color = new Color(rgb);
                avgR+=color.getRed();
                avgG+=color.getGreen();
                avgB+=color.getBlue();
                color=null;
            }
        }
        
        Color color=new Color(avgR/25,avgG/25,avgB/25);
        return color.getRGB();
    }
    public static void deleteDirectory(File file)
    {
        /* store all the paths of files and folders present
         inside directory*/
        for (File subfile : file.listFiles()) {
 
            /*  if it is a subfolder,e.g Rohan and Ritik,
                recursively call function to empty subfolder*/
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
 
            /* delete files and empty subfolders*/
            subfile.delete();
        }
    }
    private static Color colorMatch(Color c1, Color[] colorArray) {
        /*
        Compares color c1 with all colors in colorArray
        and returns most similar color 
        */
        int diference=999;
        Color color=new Color(0,0,0);
        for(Color c2:colorArray){
            if(diference>   Math.abs(c1.getRed() - c2.getRed())+
                            Math.abs(c1.getGreen() - c2.getGreen()) +
                            Math.abs(c1.getBlue() - c2.getBlue())){
                diference=Math.abs(c1.getRed() - c2.getRed())+
                            Math.abs(c1.getGreen() - c2.getGreen()) +
                            Math.abs(c1.getBlue() - c2.getBlue());
                color=c2;
            }
        }
        return color;
    }
    private static void moveMouse(Robot r,int[] cor1, int[] cor2,int size) throws InterruptedException{  
        /*
            Moves mouse to cube what need to move, 
            press mose button, 
            slowly moves mouse with cube to final position,
            releases mouse button
        */
        /*System.out.println("Start("+cor1[1]+";"+cor1[0]+") Finish("+cor2[1]+";"+cor2[0]+")");*/
        r.mouseMove(225+(cor1[1]*size)+size/2, 135+(cor1[0]*size)+size/2);
        Thread.sleep(10);
        r.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(10);
        for(int i=size;i>0;i-=2){            
            r.mouseMove(225+cor2[1]*size-i*(cor2[1]-cor1[1])+size/2, 135+cor2[0]*size-i*(cor2[0]-cor1[0])+size/2);
            Thread.sleep(1);
        }
        Thread.sleep(10);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

package gui.Animator;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class PathPoints {

    private static PathPoints instance;
    private HashMap<Point,Point> locToPoint = new HashMap<>();
    private int width;
    private int height;

    private PathPoints(int width,int height){
        this.width = width;
        this.height = height;
        install();
    }

    public static PathPoints getInstance(int width,int height) {
        if(instance == null) {
            instance = new PathPoints(width,height);
        }
        return instance;
    }

    private void install(){
        int height1 = 74*height/100;
        int height2 = height/2;
        int width1 = 74*width/100;
        int width2 = width/2;

        for(int i=0 ; i<=55 ; i++){
            if(i>=0 && i<= 13){
                locToPoint.put(new Point(0,i), new Point(width - 2*width/17 - width/100,(i+2)*height/17)); //nice
            }
            else if(i == 14){
                locToPoint.put(new Point(0,i), new Point(width - 2*width/17 - width/100,height - height/100)); //nice
            }
            else if(i>=15 && i<=27){
                locToPoint.put(new Point(0,i), new Point(width - (i-12)*width/17,height - height/100));
            }
            else if(i == 28){
                locToPoint.put(new Point(0,i), new Point(width/100,16*height/17 + height/100));
            }
            else if(i>=29 && i<= 41) {
                locToPoint.put(new Point(0,i), new Point(width / 100, height - (i - 27) * height / 17 - height / 100));
            }
            else if(i == 42){
                locToPoint.put(new Point(0,i), new Point(width / 100, height - 15* height / 17 - height / 100));
            }
            else{
                locToPoint.put(new Point(0,i), new Point((i - 41)*width/17 + width/100,height-15*height/17 - height/100));
            }
        }
        for(int i=0; i<=39 ; i++) {
            if(i>=0 && i<= 9){
                locToPoint.put(new Point(1,i), new Point(width - 2*(width1/13 + width/17) - width/100,2*height/17 + (i+2)*height1/13));
            }
            else if(i == 10){
                locToPoint.put(new Point(1,i), new Point(width - 2*(width1/13 + width/17) - width/100,2*height/17 + height1));
            }
            else if(i>=11 && i<=19){
                locToPoint.put(new Point(1,i), new Point(width - (i - 8)*width1/13 - 2*width/17,2*height/17 + height1));
            }
            else if(i == 20){
                locToPoint.put(new Point(1,i), new Point(width - width1 - 2*width/17,2*height/17 + height1));
            }
            else if(i>=21 && i<= 29) {
                locToPoint.put(new Point(1, i), new Point(width - width1 - 2*width/17, height - (i - 19) * height1/ 13 - 2*height/17 - width/100));
            }
            else if(i == 30){
                locToPoint.put(new Point(1, i), new Point(width - width1 - 2*width/17, height - 11*height1/13 - 2*height/17 - width/100));
            }
            else if(i>=31 && i<=39){
                locToPoint.put(new Point(1,i), new Point(2*width/17 + (i-29)*width1/13 + width/100,height - 11*height1/13 - 2*height/17 - width/100));
            }
        }
        for(int i=0; i<=23 ; i++) {
            if(i>=0 && i<= 5){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width1/13 + width/17 + width2/9) - width/100,2*(height/17 + height1/13) + (i + 2)*height2/9));
            }
            else if(i == 6){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width1/13 + width/17 + width2/9) - width/100,2*(height/17 + height1/13) + height2));
            }
            else if(i>=7 && i<=11){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width/17 + width1/13) - (i-4)*width2/9 - width/100,2*(height/17 + height1/13) + height2));
            }
            else if(i == 12){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width/17 + width1/13) - width2 - width/100,2*(height/17 + height1/13) + height2));
            }
            else if(i>=13 && i<= 17){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width/17 + width1/13) - width2 - width/100,height - 2*(height/17 + height1/13) - (i-11)*height2/9 - width/100));
            }
            else if(i == 18){
                locToPoint.put(new Point(2,i), new Point(width - 2*(width/17 + width1/13) - width2 - width/100,height - 2*(height/17 + height1/13) - height2));
            }
            else if(i>=19 && i<=23){
                locToPoint.put(new Point(2,i), new Point(2*(width/17 + width1/13) + (i - 16)*height2/9 + width/100,height - 2*(height/17 + height1/13) - height2));
            }

        }
    }

    public Point pointFind(int [] loc){
        System.out.println("Array: " + Arrays.toString(loc));
        Point newP = new Point(loc[0],loc[1]);
        System.out.println("Pointx: " + locToPoint.get(newP).getX() + " Pointy: " + locToPoint.get(newP).getY());
        return locToPoint.get(newP);
    }
}

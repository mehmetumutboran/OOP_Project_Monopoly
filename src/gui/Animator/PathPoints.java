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
        for(int i=0 ; i<=55 ; i++){
            if(i>=0 && i<= 13){
                locToPoint.put(new Point(0,i), new Point(width - 2*width/17 - width/100,(i+2)*height/17)); //nice
            }
            else if(i == 14){
                locToPoint.put(new Point(0,i), new Point(width - 2*width/17 - width/100,height - height/100)); //nice
            }
            else if(i>=15 && i<=27){
                locToPoint.put(new Point(0,i), new Point(width - (i-12)*width/17 - width/100,height - height/100));
            }
            else if(i == 28){
                locToPoint.put(new Point(0,i), new Point(width - 17*width/17 + width/100,16*height/17 + height/100));
            }
            else if(i>=29 && i<= 41) {
                locToPoint.put(new Point(0, i), new Point(width - 17 * width / 17 + width / 100, height - (i - 27) * height / 17 - height / 100));
            }
            else if(i == 42){
                locToPoint.put(new Point(0, i), new Point(width - 17 * width / 17 + width / 100, height - 15* height / 17 - height / 100));
            }
            else{
                locToPoint.put(new Point(0,i), new Point((i - 41)*width/17 - width/100,height-15*height/17 - height/100));
            }
        }
        for(int i=0; i<=39 ; i++) {
            if(i>=0 && i<= 9){
                locToPoint.put(new Point(1,i), new Point(width - 4*width/17 - width/100,(i+4)*height/17));
            }
            else if(i == 10){
                locToPoint.put(new Point(1,i), new Point(width - 4*width/17 - 7*width/400,15*height/17 + 7*height/400));
            }
            else if(i>=11 && i<=19){
                locToPoint.put(new Point(1,i), new Point(width - (i - 6)*width/17 - width/100,15*height/17 + height/100));
            }
            else if(i == 20){
                locToPoint.put(new Point(1,i), new Point(width - 15*width/17 - width/100,15*height/17 + height/100));
            }
            else if(i>=21 && i<= 29) {
                locToPoint.put(new Point(1, i), new Point(width - 15 * width / 17 - width / 100, height - (i - 18) * height / 17 - 7 * height / 400));
            }
            else if(i == 30){
                locToPoint.put(new Point(1, i), new Point(width - 15 * width / 17 - width / 100, height - 12* height / 17 - 7 * height / 400));
            }
            else if(i>=31 && i<=39){
                locToPoint.put(new Point(1,i), new Point((i -28)*width/17  + 7*width/400,height - 12* height / 17 - 7 * height / 400));
            }
        }
        for(int i=0; i<=23 ; i++) {
            if(i>=0 && i<= 5){
                locToPoint.put(new Point(2,i), new Point(width - 6*width/17 - width/40,(i+6)*height/17 + height/40));
            }
            else if(i == 6){
                locToPoint.put(new Point(2,i), new Point(width - 6*width/17 - width/40,13*height/17 + height/40));
            }
            else if(i>=7 && i<=11){
                locToPoint.put(new Point(2,i), new Point(width - i*width/17 - width/40,13*height/17 + height/40));
            }
            else if(i == 12){
                locToPoint.put(new Point(2,i), new Point(width - 13*width/17 - width/40,13*height/17 + height/40));
            }
            else if(i>=13 && i<= 17){
                locToPoint.put(new Point(2,i), new Point(width - 13*width/17 - width/40,height - (i-6)*height/17 - height/40));
            }
            else if(i == 18){
                locToPoint.put(new Point(2,i), new Point(width - 13*width/17 - width/40,height - 11*height/17 - height/40));
            }
            else if(i>=19 && i<=23){
                locToPoint.put(new Point(2,i), new Point((i-13)*width/17 + width/40,height - 11*height/17 - height/40));
            }

        }

//        locToPoint.put(new Point(0,0), new Point(width - 2*width/17 - width/100,2*height/14 + width/100));
//        locToPoint.put(new Point(0,1), new Point(width - 2*width/17 - width/100,3*height/14 + width/100));
//        locToPoint.put(new Point(0,2), new Point(width - 2*width/17 - width/100,4*height/14 + width/100));
//        locToPoint.put(new Point(0,3), new Point(width - 2*width/17 - width/100,5*height/14 + width/100));
//        locToPoint.put(new Point(0,4), new Point(width - 2*width/17 - width/100,6*height/14 + width/100));
//        locToPoint.put(new Point(0,5), new Point(width - 2*width/17 - width/100,7*height/14 + width/100));
//        locToPoint.put(new Point(0,6), new Point(width - 2*width/17 - width/100,8*height/14 + width/100));
//        locToPoint.put(new Point(0,7), new Point(width - 2*width/17 - width/100,9*height/14 + width/100));
//        locToPoint.put(new Point(0,8), new Point(width - 2*width/17 - width/100,10*height/14 + width/100));
//        locToPoint.put(new Point(0,9), new Point(width - 2*width/17 - width/100,11*height/14 + width/100));
//        locToPoint.put(new Point(0,10), new Point(width - 2*width/17 - width/100,12*height/14 + width/100));
//        locToPoint.put(new Point(0,11), new Point(width - 2*width/17 - width/100,13*height/14 + width/100));
//        locToPoint.put(new Point(0,0), new Point(width - 2*width/17 - width/100,2*height/14 + width/100));
    }

    public Point pointFind(int [] loc){
        System.out.println("Array: " + Arrays.toString(loc));
        Point newP = new Point(loc[0],loc[1]);
        System.out.println("Pointx: " + locToPoint.get(newP).getX() + " Pointy: " + locToPoint.get(newP).getY());
        return locToPoint.get(newP);
    }
}

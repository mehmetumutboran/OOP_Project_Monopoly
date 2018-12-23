package gui.Animator;

import java.awt.*;
import java.util.HashMap;

public class PathPoints {

    private static PathPoints instance;
    private HashMap<Point,Point> locToPoint = new HashMap<>();
    private int width;
    private int height;

    private PathPoints(int width,int height){
        install();
        this.width = width;
        this.height = height;
    }

    public static PathPoints getInstance(int width,int height) {
        if(instance == null) {
            instance = new PathPoints(width,height);
        }
        return instance;
    }

    private void install(){
        locToPoint.put(new Point(0,0), new Point(width - 2*width/17 - width/100,2*height/14 + width/100));
        locToPoint.put(new Point(0,1), new Point(width - 2*width/17 - width/100,3*height/14 + width/100));
        locToPoint.put(new Point(0,2), new Point(width - 2*width/17 - width/100,4*height/14 + width/100));
        locToPoint.put(new Point(0,3), new Point(width - 2*width/17 - width/100,5*height/14 + width/100));
        locToPoint.put(new Point(0,4), new Point(width - 2*width/17 - width/100,6*height/14 + width/100));
        locToPoint.put(new Point(0,5), new Point(width - 2*width/17 - width/100,7*height/14 + width/100));
        locToPoint.put(new Point(0,6), new Point(width - 2*width/17 - width/100,8*height/14 + width/100));
        locToPoint.put(new Point(0,7), new Point(width - 2*width/17 - width/100,9*height/14 + width/100));
        locToPoint.put(new Point(0,8), new Point(width - 2*width/17 - width/100,10*height/14 + width/100));
        locToPoint.put(new Point(0,9), new Point(width - 2*width/17 - width/100,11*height/14 + width/100));
        locToPoint.put(new Point(0,10), new Point(width - 2*width/17 - width/100,12*height/14 + width/100));
        locToPoint.put(new Point(0,11), new Point(width - 2*width/17 - width/100,13*height/14 + width/100));
        locToPoint.put(new Point(0,0), new Point(width - 2*width/17 - width/100,2*height/14 + width/100));
    }

    public Point pointFind(int [] loc){
        Point newP = new Point(loc[0],loc[1]);
        return locToPoint.get(newP);
    }
}

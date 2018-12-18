package gui.Animator;
import gui.baseFrame.panels.GamePanel;

public class Animator implements Runnable {

    @SuppressWarnings("rawtypes")
//    long sleepTime = 100;
//    boolean animatorStopped = true, firstTime = true;
    GamePanel gp;

    public Animator(GamePanel gp) {
        this.gp = gp;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Program Interrupted");
                System.exit(0);
            }
            gp.repaint();
        }
    }
}

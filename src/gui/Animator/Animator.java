package gui.Animator;

import gui.baseFrame.panels.GamePanel;

public class Animator implements Runnable {

    @SuppressWarnings("rawtypes")
    public boolean animatorStopped = false;
    GamePanel gp;
    String movingToken;

    public Animator(GamePanel gp, String token) {
        this.movingToken = token;
        this.gp = gp;
    }

    public void run() {
        gp.setPath(movingToken);
        while (true) {
            try {
                Thread.sleep(100);
                synchronized (this) {
                    if (animatorStopped) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Program Interrupted");
                System.exit(0);
            }
            gp.tokenDraw(gp.getGraphics(), movingToken);
        }
    }
}

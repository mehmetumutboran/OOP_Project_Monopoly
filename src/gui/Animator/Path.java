package gui.Animator;

import java.awt.*;

public interface Path {
    /**
     * Check to see if the path has MoreSteps
     */
    boolean hasMoreSteps();

    /**
     * Get the next position.  If the path has no more steps, return
     * the current position.
     */
    Point nextPosition();
}

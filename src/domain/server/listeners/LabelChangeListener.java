package domain.server.listeners;


import java.util.ArrayList;

public interface LabelChangeListener {
    void onLabelChangeEvent(ArrayList<int[]> locationList, String actionType, int i);
}

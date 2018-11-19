package gui;

import gui.baseFrame.BaseFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BaseFrame::new);

    }

}

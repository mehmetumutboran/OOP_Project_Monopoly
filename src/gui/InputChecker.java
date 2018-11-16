package gui;

import javax.swing.*;

public class InputChecker {
    private static InputChecker ourInstance = new InputChecker();

    public static InputChecker getInstance() {
        return ourInstance;
    }

    private InputChecker() {
    }

    public boolean userNameChecker(String ID) {
        if (ID == null || ID.length() < 2 || ID.length() > 15) {
            JOptionPane.showMessageDialog(null, "Username must be between 2-15 characters",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean portChecker(String portString) {
        try {
            int port = Integer.parseInt(portString);
            if (port < 1025 || port > 65535) {
                JOptionPane.showMessageDialog(null, "Port number must be 1025-65535",
                        "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Port number mustn't contain non-numeric characters",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean ipChecker(String ip) {
        if (ip.equals("localhost")) return true;
        int[] dotLoc = {0, 0, 0, 0, ip.length()};
        int dotCount = 0;
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) == '.') {
                dotCount++;
                if (dotCount > 3) {
                    JOptionPane.showMessageDialog(null, "Ip has too many dots!!",
                            "Error: Invalid IP", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                dotLoc[dotCount] = i;
            }
        }
        if (dotCount < 3) {
            JOptionPane.showMessageDialog(null, "Ip doesn't have enough dots!!",
                    "Error: Invalid IP", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            for (int i = 0; i <= dotCount; i++) {
                int number = -1;
                if (i == 0) {
                    number = Integer.parseInt(ip.substring(dotLoc[i], dotLoc[i + 1]));
                    System.out.println(number);
                } else {
                    number = Integer.parseInt(ip.substring(dotLoc[i] + 1, dotLoc[i + 1]));
                    System.out.println(number);
                }
                if (number < 0 || number > 255) {
                    JOptionPane.showMessageDialog(null, "Ip has invalid integers!!",
                            "Error: Invalid IP", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ip has non-numeric characters!!",
                    "Error: Invalid IP", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}

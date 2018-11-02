package gui;

import gui.baseFrame.BaseFrame;
import network.Server;

public class Main {
    private static Server server;


//    public Main() {
//        HostButton.addServerSetListener(this);
//        System.out.println(this.hashCode());
//    }

    public static void main(String[] args) {
//        System.out.println("Main started");
        new BaseFrame();
//        HostButton.addServerSetListener(new Main());
    }

//    @Override
//    public void onServerSetEvent() {
//        server = HostButton.getServer();
//        System.out.println("Server created" + this.hashCode());
//    }


    public static void setServer(Server server) {
        Main.server = server;
        System.out.println("Server Created");
    }
}

package domain.board;

import domain.board.specialSquares.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
public class Board {

    private ArrayList<Square> squareList;

    public Board() {
        squareList = new ArrayList<>();
        initializePropertySquares();
        initializeRailroadSquares();
        initializeUtilitySquares();
        initializeSpecialSquares();

    }


    public ArrayList<Square> getSquareList() {
        return squareList;
    }

    public void initializePropertySquares(){

        /* Square are written in order
        * starting from payday square
        * on clockwise*/

        Property sq1 = new Property("randolph street" , 270 ,23 , "PURPLE");
        squareList.add(sq1);
         Property sq2 = new Property("lake shore drive" , 270 ,23 , "PURPLE");
        squareList.add(sq2);
         Property sq3 = new Property("wacker drive" , 300 ,23 , "PURPLE");
        squareList.add(sq3);
         Property sq4 = new Property("michigan avenue" , 300 ,23 , "PURPLE");
        squareList.add(sq4);
         Property sq5 = new Property("south temple" , 330 ,32 , "DARKOLIVEGREEN");
        squareList.add(sq5);
         Property sq6 = new Property("west temple" , 330 ,32 , "DARKOLIVEGREEN");
        squareList.add(sq6);
         Property sq7 = new Property("north temple" , 360 ,38 , "DARKOLIVEGREEN");
        squareList.add(sq7);
         Property sq8 = new Property("temple square" , 360 ,38 , "DARKOLIVEGREEN");
        squareList.add(sq8);
         Property sq9 = new Property("south street" , 390 ,45 , "LIGHTPINK");
        squareList.add(sq9);
         Property sq10 = new Property("board street" , 390 ,45 , "LIGHTPINK");
        squareList.add(sq10);
         Property sq11 = new Property("walnut street" , 420 ,55 , "LIGHTPINK");
        squareList.add(sq11);
         Property sq12 = new Property("market street" , 420 ,55 , "LIGHTPINK");
        squareList.add(sq12);
         Property sq13 = new Property("mulholland drive" , 450 ,70 , "BROWN");
        squareList.add(sq13);
         Property sq14 = new Property("ventura boulevard" , 480 ,80 , "BROWN");
        squareList.add(sq14);
         Property sq15 = new Property("rodeo drive" , 510 ,90 , "BROWN");
        squareList.add(sq15);
         Property sq16 = new Property("lake street" , 30 ,5 , "PINK");
        squareList.add(sq16);
         Property sq17 = new Property("nicollet avenue" , 30 ,5 , "PINK");
        squareList.add(sq17);
         Property sq18 = new Property("hennepin avenue" , 60 ,15 , "PINK");
        squareList.add(sq18);
         Property sq19 = new Property("esplanada avenue" , 90 ,25 , "LIGHTGREEN");
        squareList.add(sq19);
         Property sq20 = new Property("canal street" , 90 ,25 , "LIGHTGREEN");
        squareList.add(sq20);
         Property sq21 = new Property("magazine street" , 120 ,40 , "LIGHTGREEN");
        squareList.add(sq21);
         Property sq22 = new Property("bourbon street" , 120 ,40 , "LIGHTGREEN");
        squareList.add(sq22);
         Property sq23 = new Property("katy freeway" , 150 ,11 , "LIGHTYELLOW");
        squareList.add(sq23);
         Property sq24 = new Property("westheimer road" , 150 ,11 , "LIGHTYELLOW");
        squareList.add(sq24);
         Property sq25 = new Property("kirby drive" , 180 ,14 , "LIGHTYELLOW");
        squareList.add(sq25);
         Property sq26 = new Property("cullen boulevard" , 180 ,14 , "LIGHTYELLOW");
        squareList.add(sq26);
         Property sq27 = new Property("dekalb avenue" , 210 ,17 , "MEDIUMBLUE");
        squareList.add(sq27);
         Property sq28 = new Property("andrew young intl boulevard" , 210 ,17 , "MEDIUMBLUE");
        squareList.add(sq28);
         Property sq29 = new Property("decatur street" , 240 ,20 , "MEDIUMBLUE");
        squareList.add(sq29);
         Property sq30 = new Property("peachtree street" , 240 ,20 , "MEDIUMBLUE");
        squareList.add(sq30);
         Property sq31 = new Property("kentucky avenue" , 220 ,18 , "RED");
        squareList.add(sq31);
         Property sq32 = new Property("indiana avenue" , 220 ,18 , "RED");
        squareList.add(sq32);
         Property sq33 = new Property("illinois avenue" , 240 ,20 , "RED");
        squareList.add(sq33);
         Property sq34 = new Property("atlantic avenue" , 260 ,22 , "YELLOW");
        squareList.add(sq34);
         Property sq35 = new Property("ventinor avenue" , 260 ,22 , "YELLOW");
        squareList.add(sq35);
         Property sq36 = new Property("marvin gardens" , 280 ,24 , "YELLOW");
        squareList.add(sq36);
         Property sq37 = new Property("pacific avenue" , 300 ,26 , "GREEN");
        squareList.add(sq37);
         Property sq38 = new Property("north carolina avenue" , 300 ,26 , "GREEN");
        squareList.add(sq38);
         Property sq39 = new Property("pennsylvania avenue" , 320 ,28 , "GREEN");
        squareList.add(sq39);
         Property sq40 = new Property("park place" , 350 ,35 , "BLUE");
        squareList.add(sq40);
         Property sq41 = new Property("boardwalk" , 400 ,50 , "BLUE");
        squareList.add(sq41);
         Property sq42 = new Property("mediterranean avenue" , 60 ,2 , "MEDIUMPURPLE");
        squareList.add(sq42);
         Property sq43 = new Property("baltic avenue" , 60 ,4 , "MEDIUMPURPLE");
        squareList.add(sq43);
         Property sq44 = new Property("oriental avenue" , 100 ,6 , "LIGHTBLUE");
        squareList.add(sq44);
         Property sq45 = new Property("vermont avenue" , 100 ,6 , "LIGHTBLUE");
        squareList.add(sq45);
         Property sq46 = new Property("connecticut avenue" , 120 ,8 , "LIGHTBLUE");
        squareList.add(sq46);
         Property sq47 = new Property("st. charles place" , 140 ,10 , "DEEPPINK");
        squareList.add(sq47);
         Property sq48 = new Property("states avenue" , 140 ,10 , "DEEPPINK");
        squareList.add(sq48);
         Property sq49 = new Property("virginia avenue" , 160 ,12 , "DEEPPINK");
        squareList.add(sq49);
         Property sq50 = new Property("st. james place" , 180 ,14 , "ORANGE");
        squareList.add(sq50);
         Property sq51 = new Property("tennessee avenue" , 180 ,14 , "ORANGE");
        squareList.add(sq51);
         Property sq52 = new Property("new york avenue" , 200 ,16 , "ORANGE");
        squareList.add(sq52);
         Property sq53 = new Property("wall street" , 500 ,80 , "GRAY");
        squareList.add(sq53);
         Property sq54 = new Property("florida avenue" , 130 ,9 , "SANDYBROWN");
        squareList.add(sq54);
         Property sq55 = new Property("miami avenue" , 130 ,9 , "SANDYBROWN");
        squareList.add(sq55);
         Property sq56 = new Property("biscayne street" , 150 ,11 , "SANDYBROWN");
        squareList.add(sq56);
         Property sq57 = new Property("lombard street" , 210 ,17 , "WHITE");
        squareList.add(sq57);
         Property sq58 = new Property("the embarcadero" , 210 ,17 , "WHITE");
        squareList.add(sq58);
         Property sq59 = new Property("fisherman's wharf" , 250 ,21 , "WHITE");
        squareList.add(sq59);
         Property sq60 = new Property("beacon street" , 330 ,30 , "BLACK");
        squareList.add(sq60);
         Property sq61 = new Property("boylston street" , 330 ,30 , "BLACK");
        squareList.add(sq61);
         Property sq62 = new Property("newbury street" , 380 ,40 , "BLACK");
        squareList.add(sq62);
         Property sq63 = new Property("fifth avenue" , 430 ,60 , "PURPLE");
        squareList.add(sq63);
         Property sq64 = new Property("madison avenue" , 430 ,60 , "PURPLE");
        squareList.add(sq64);

    }
    public void initializeRailroadSquares(){
        // changes rent when you have more railroad !!!!!
       Railroad sq65 = new Railroad("b.&o. railroad" , 200 , 25);
       squareList.add(sq65);
        Railroad sq66 =new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq66);
        Railroad sq67 = new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq67);
        Railroad sq68 = new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq68);
        Railroad sq69 = new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq69);
        Railroad sq70 =new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq70);
        Railroad sq71 = new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq71);
        Railroad sq72 = new Railroad("b.&o. railroad" , 200 , 25);
        squareList.add(sq72);

    }
    public void initializeUtilitySquares(){

        /* rentr will be corrected */
        Utility sq73 = new Utility("internet service provider" , 150 , 1);
        squareList.add(sq73);
        Utility sq74 = new Utility("yellow cab co." , 300 , 30);
        squareList.add(sq74);
        Utility sq75 = new Utility("trash collector" , 150 , 1);
        squareList.add(sq75);
        Utility sq76 = new Utility("sewage system" , 150 , 1);
        squareList.add(sq76);
        Utility sq77 = new Utility("ute cab co." , 300 , 1);
        squareList.add(sq76);
        Utility sq78 = new Utility("checker cab co." , 300 , 1);
        squareList.add(sq77);
        Utility sq79 = new Utility("cable company" , 150 , 1);
        squareList.add(sq78);
        Utility sq80 = new Utility("internet service provider" , 150 , 1);
        squareList.add(sq79);
        Utility sq81 = new Utility("black&white cab co." , 300 , 1);
        squareList.add(sq80);
        Utility sq82 = new Utility("electric company" , 150 , 1);
        squareList.add(sq81);
        Utility sq83 = new Utility("water works" , 150 , 1);
        squareList.add(sq82);
        Utility sq84 = new Utility("gas company" , 150 , 1);
        squareList.add(sq83);
        Utility sq85 = new Utility("telephone company" , 150 , 1);
        squareList.add(sq84);
    }

    public void initializeSpecialSquares(){

        HollandTunnel sq86 = new HollandTunnel();
        //          squareList.add(sq86);
        Auction sq87 = new Auction();
   //          squareList.add(sq87);
        Chance sq88 = new Chance();
   //          squareList.add(sq88);
        CommunityChest sq89 = new CommunityChest();
        //     squareList.add(sq89);
        PayDay sq890 = new PayDay();
        //     squareList.add(sq90);
        Chance sq91 = new Chance();
        //     squareList.add(sq91);
        CommunityChest sq92 = new CommunityChest();
        //     squareList.add(sq92);
        CommunityChest sq93 = new CommunityChest();
        //     squareList.add(sq93);
        BusTicket sq94 = new BusTicket();
        //     squareList.add(sq94);
        BirthDayGift sq95 = new BirthDayGift();
        //     squareList.add(sq95);
        Chance sq96 = new Chance();
        //     squareList.add(sq96);
        Subway sq97 = new Subway();
        //     squareList.add(sq97);
        CommunityChest sq98 = new CommunityChest();
        //     squareList.add(sq98);
        BusTicket sq99 = new BusTicket();
        //     squareList.add(sq99);
        Chance sq100 = new Chance();
        //     squareList.add(sq100);

        Jail sq101 = new Jail();
        //     squareList.add(sq101);
        CommunityChest sq102 = new CommunityChest();
        //     squareList.add(sq102);
        FreeParkingSquare sq103 = new FreeParkingSquare();
        //     squareList.add(sq103);
        Chance sq104 = new Chance();
        //     squareList.add(sq104);
        Roll3 sq105 = new Roll3();
        //     squareList.add(sq105);
        CommunityChest sq106 = new CommunityChest();
        //     squareList.add(sq106);
        Chance sq107 = new Chance();
        //     squareList.add(sq107);
        LuxuryTax sq108 = new LuxuryTax();
        //     squareList.add(sq108);
        GoSquare sq109 = new GoSquare();
        //     squareList.add(sq109);
        CommunityChest sq110 = new CommunityChest();
        //     squareList.add(sq110);
        IncomeTax sq111 = new IncomeTax();
        //     squareList.add(sq111);
        Chance sq112 = new Chance();
        //     squareList.add(sq112);
        Bonus sq113 = new Bonus();
        //     squareList.add(sq113);
        StockExchange sq114 = new StockExchange();
        //     squareList.add(sq114);
        TaxRefund sq115 = new TaxRefund();
        //     squareList.add(sq115);
        Chance sq116 = new Chance();
        //     squareList.add(sq115);
        HollandTunnel sq117 = new HollandTunnel();
        //     squareList.add(sq117);

        ReverseDirection sq118 = new ReverseDirection();
        //     squareList.add(sq118);
        SqueezePlay sq119 = new SqueezePlay();
        //     squareList.add(sq119);
        CommunityChest sq120 = new CommunityChest();
        //     squareList.add(sq120);
    }

}

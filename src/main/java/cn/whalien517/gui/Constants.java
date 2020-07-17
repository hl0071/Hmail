package main.java.cn.whalien517.gui;

/**
 * @author Hwa
 * @since 2020-07-12 21:47
 */
public class Constants {
    // main frmae
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 780;


    public static final int SIDE_WIDTH = (int) (WIDTH * 0.277);  //  300
    public static final int CHILDFRAME_WIDTH = WIDTH - SIDE_WIDTH;  // 780
    public static final int SERVERPANEL_HEIGHT = (int) (HEIGHT * 0.192);  // 150
    public static final int BTN_HEIGHT = (int) (HEIGHT * 0.103);  // 80

    public static final int CBB_HEIGHT = (int) (SERVERPANEL_HEIGHT * 0.167);  // 25
    public static final int CBB_WIDTH = (int) (SIDE_WIDTH * 0.533);  // 160
    public static final int CBB_Y = (int) (SERVERPANEL_HEIGHT * 0.646);  // 96

    public static final int SERVERLOGO_HEIGHT = (int) (SERVERPANEL_HEIGHT * 0.333);  // 50
    public static final int SERVERLOGO_WIDTH = (int) (SIDE_WIDTH * 0.617);  // 185
    public static final int SERVERLOGO_Y = (int) (SERVERPANEL_HEIGHT * 0.1875);  // 28

    public static final float LINE_WIDTH = 1.5f;


    // compose frame
    public static final int BEGIN_Y= (int) (HEIGHT/26f);  // 30
    public static final int ROW_SPACE = (int) (CHILDFRAME_WIDTH / 39f);  // 20
    public static final int COL1_X = (int) (CHILDFRAME_WIDTH / 52f);  // 15
    public static final int ROW_HEIGHT = (int) (HEIGHT * 3 / 78f);  // 30
    public static final int COL2_X = (int) (CHILDFRAME_WIDTH *9/65f);  // 108

//    public static final int TXA_HEIGHT = (int) (HEIGHT * 32 / 39f);  // 640

    public static final int LBL_WIDTH = (int) (CHILDFRAME_WIDTH * 6 / 65f);  // 72
    public static final int TXF_WIDTH = (int) (CHILDFRAME_WIDTH * 21 / 26f);  // 630

    public static final int BTN_WIDTH=84;
    public static final int TXA_HEIGHT=440;

    public static final int CKB_WIDTH=120;
    public static final int CKB_SPACE=52;
    public static final int PNLFEAT_SPACE=72;



}

package main.java.cn.whalien517.gui;

/**
 * @author Hwa
 * @since 2020-07-12 21:47
 */
public class Constants {
    public static final int WIDTH;
    public static final int HEIGHT;


    public static final int SIDE_WIDTH;
    public static final int CHILDFRAME_WIDTH;
    public static final int SERVERPANEL_HEIGHT;
    public static final int BTN_HEIGHT;

    public static final int CBB_HEIGHT;
    public static final int CBB_WIDTH;
    public static final int CBB_Y;

    public static final int SERVERLOGO_HEIGHT;
    public static final int SERVERLOGO_WIDTH;
    public static final int SERVERLOGO_Y;

    public static final float LINE_WIDTH;

    static {
        // 主Frame宽度，高度
        WIDTH = 1080;
        HEIGHT = 780;

        // 侧边栏宽度高度
        SIDE_WIDTH = 300;

        // server panel高度
        SERVERPANEL_HEIGHT = 150;

        // server图标高度和宽度
        SERVERLOGO_WIDTH = 185;
        SERVERLOGO_HEIGHT = (int) (SERVERLOGO_WIDTH * 0.28);

        // 组合框宽度，组合框高度
        CBB_WIDTH = 160;
        CBB_HEIGHT = 25;

        SERVERLOGO_Y=(int)((SERVERPANEL_HEIGHT-SERVERLOGO_HEIGHT-CBB_HEIGHT)*3/8);
        CBB_Y=(int)((SERVERPANEL_HEIGHT-SERVERLOGO_HEIGHT-CBB_HEIGHT)*5/8+SERVERLOGO_HEIGHT);

        // 按钮高度
        BTN_HEIGHT = 80;

        // 子Frame宽度
        CHILDFRAME_WIDTH = WIDTH - SIDE_WIDTH;

        LINE_WIDTH=1.5f;
    }

}

package main.java.cn.whalien517.gui;

import lombok.SneakyThrows;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import static main.java.cn.whalien517.gui.Constants.*;

/**
 * @author Hwa
 * @since 2020-07-12 15:57
 */
public class Main extends JFrame implements ActionListener {

    private JPanel pnlSide;

    // 显示当前连接服务器部分
    private JPanel pnlServer;
    private JLabel lblServer;
    private JComboBox cbbServer;
    private JLabel lblLine;

    // sidemenu部分
    private JPanel pnlSideMenu;
    private JButton btnCompose;
    private JButton btnCheck;
    private JButton btnConfig;
    private JButton btnAbout;

    // ChildFrame部分
    private JPanel pnlChildFrame;
    private JLabel lblLogo;

    // 窗口
    private JFrame frmMain;
    private JFrame frmCompose;
    private JFrame frmCheck;
    private JFrame frmConfig;
    private JFrame frmAbout;

    private Vector<String> servers = new Vector<String>();

    public Main() throws IOException {
        prepareServers();
        init();
    }

    private void prepareServers() {
        servers.add("QQ邮箱");
        servers.add("WHU邮箱");
        servers.add("Gmail");
        servers.add("Outlook");
        servers.add("其他邮箱");
    }

    private void init() throws IOException {
        pnlChildFrame = new JPanel(new CardLayout(), true);
        pnlChildFrame.setBounds(SIDE_WIDTH, 0, Constants.WIDTH - SIDE_WIDTH, Constants.HEIGHT);

        pnlServer = new JPanel(null, true);
        pnlServer.setBounds(0, 0, SIDE_WIDTH, SERVERPANEL_HEIGHT);
        pnlServer.setBackground(new Color(245, 245, 245));

        pnlSideMenu = new JPanel(null, true);
        pnlSideMenu.setBounds(0, SERVERPANEL_HEIGHT, SIDE_WIDTH, Constants.HEIGHT - SERVERPANEL_HEIGHT);
        pnlSideMenu.setBackground(new Color(245, 245, 245));

        cbbServer = new JComboBox(servers);
        cbbServer.setEditable(false);
        cbbServer.setBounds(SIDE_WIDTH / 2 - CBB_WIDTH / 2, CBB_Y, CBB_WIDTH, CBB_HEIGHT);
        cbbServer.addItemListener(e -> {
            String serverLogoPath = "./src/main/resources/servers/" + cbbServer.getSelectedItem() + ".png";
            ImageIcon serverImg = new ImageIcon(serverLogoPath);
            serverImg.setImage(serverImg.getImage().getScaledInstance(SERVERLOGO_WIDTH, SERVERLOGO_HEIGHT, Image.SCALE_SMOOTH));
            lblServer.setIcon(serverImg);
        });
        pnlServer.add(cbbServer);

        String initLogoPath = "./src/main/resources/servers/QQ邮箱.png";
        lblServer = new JLabel();
        ImageIcon initImg = new ImageIcon(initLogoPath);
        initImg.setImage(initImg.getImage().getScaledInstance(SERVERLOGO_WIDTH, SERVERLOGO_HEIGHT, Image.SCALE_SMOOTH));
        lblServer.setIcon(initImg);
        lblServer.setBounds((SIDE_WIDTH - SERVERLOGO_WIDTH) / 2, SERVERLOGO_Y, SERVERLOGO_WIDTH, SERVERLOGO_HEIGHT);
        pnlServer.add(lblServer);

        lblLine = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D gPen = (Graphics2D) g;
                gPen.setColor(new Color(220, 220, 220));
                gPen.setStroke(new BasicStroke(LINE_WIDTH));
                gPen.drawLine(0, 0, SIDE_WIDTH, 0);
            }
        };
        lblLine.setBounds(0, (int) Math.ceil(SERVERPANEL_HEIGHT - LINE_WIDTH), SIDE_WIDTH, (int) Math.ceil(LINE_WIDTH));
        pnlServer.add(lblLine);

        btnCompose = new JButton();
        customButton(btnCompose, "写信", "./src/main/resources/assets/compose.png");
        btnCompose.setBounds(10, 0, SIDE_WIDTH, BTN_HEIGHT);
        pnlSideMenu.add(btnCompose);

        btnCheck = new JButton();
        customButton(btnCheck, "收信", "./src/main/resources/assets/check.png");
        btnCheck.setBounds(10, BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        pnlSideMenu.add(btnCheck);

        btnConfig = new JButton();
        customButton(btnConfig, "配置", "./src/main/resources/assets/config.png");
        btnConfig.setBounds(10, 2 * BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        pnlSideMenu.add(btnConfig);

        btnAbout = new JButton();
        customButton(btnAbout, "关于", "./src/main/resources/assets/about.png");
        btnAbout.setBounds(10, 3 * BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        pnlSideMenu.add(btnAbout);

        this.add(pnlServer);
        this.add(pnlSideMenu);
        this.add(pnlChildFrame);
        this.setTitle("Hmail");
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("./src/main/resources/assets/hmail.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void customButton(JButton button, String text, String filepath) {
        ImageIcon icn = new ImageIcon(filepath);
        icn.setImage(icn.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH));
        button.setText(text);
        button.setIcon(icn);
        button.setFont(new Font("微软雅黑", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(24);
    }

    /**
     * 设置UI字体
     *
     * @param font
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                // 设置字体
                InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 14));

                // 改变窗口边框样式
                // osLookAndFeelDecorated 系统默认
                // translucencyAppleLike 强立体半透明
                // translucencySmallShadow 弱立体半透明
                // generalNoTranslucencyShadow 普通不透明
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;

                try {
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();  // 设置主题
                    UIManager.put("RootPane.setupButtonVisible", false);  // 隐藏设置按钮
                    BeautyEyeLNFHelper.translucencyAtFrameInactive = false;  // 窗口不活动时关闭透明效果
                    // 设置TabbedPane左缩进
                    UIManager.put("TabbedPane.tabAreaInsets",
                            new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new Main();
            }
        });
    }
}

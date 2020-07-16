package main.java.cn.whalien517.gui;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static main.java.cn.whalien517.gui.Constants.*;

/**
 * @author Hwa
 * @since 2020-07-12 15:57
 */
public class Main extends JFrame implements ActionListener {
    // 显示当前连接服务器部分
    private JPanel pnlServer;
    private JLabel lblServer;
    private JComboBox<String> cbbServer;
    private JLabel lblLine;

    // sidemenu部分
    private JPanel pnlSideMenu;
    private JButton btnCompose;
    private JButton btnCheck;
    private JButton btnConfig;
    private JButton btnAbout;

    private final List<JButton> sideMenu = new ArrayList<>();

    // ChildFrame部分
    private JPanel pnlChildFrame;

    // 窗口
    private JPanel pnlCompose;
    private JPanel pnlCheck;
    private JPanel pnlConfig;
    private JPanel pnlAbout;

    private JLabel lblIndicator;

    private JButton activeBtn;

    private final Vector<String> servers = new Vector<>();

    public Main(){
        prepareServers();
        init();
        activateButton(btnCompose);
    }

    private void prepareServers() {
        servers.add("QQ邮箱");
        servers.add("WHU邮箱");
        servers.add("Gmail");
        servers.add("Outlook");
        servers.add("其他邮箱");
    }

    private void init() {
        pnlChildFrame = new JPanel(new CardLayout(), true);
        pnlChildFrame.setBounds(SIDE_WIDTH, 0, Constants.WIDTH - SIDE_WIDTH, Constants.HEIGHT);

        pnlServer = new JPanel(null, true);
        pnlServer.setBounds(0, 0, SIDE_WIDTH, SERVERPANEL_HEIGHT);
        pnlServer.setBackground(new Color(245, 245, 245));

        pnlSideMenu = new JPanel(null, true);
        pnlSideMenu.setBounds(0, SERVERPANEL_HEIGHT, SIDE_WIDTH, Constants.HEIGHT - SERVERPANEL_HEIGHT);
        pnlSideMenu.setBackground(new Color(245, 245, 245));

        cbbServer = new JComboBox<>(servers);
        cbbServer.setEditable(false);
        cbbServer.setBounds(SIDE_WIDTH / 2 - CBB_WIDTH / 2, CBB_Y, CBB_WIDTH, CBB_HEIGHT);
        cbbServer.setFont(new Font("微软雅黑", Font.PLAIN, 14));
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
        customButton(btnCompose, "Compose", "./src/main/resources/assets/compose.png");
        btnCompose.setBounds(10, 0, SIDE_WIDTH, BTN_HEIGHT);
        btnCompose.addActionListener(this);
        pnlSideMenu.add(btnCompose);

        btnCheck = new JButton();
        customButton(btnCheck, "Check", "./src/main/resources/assets/check.png");
        btnCheck.setBounds(10, BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        btnCheck.addActionListener(this);
        pnlSideMenu.add(btnCheck);

        btnConfig = new JButton();
        customButton(btnConfig, "Config", "./src/main/resources/assets/config.png");
        btnConfig.setBounds(10, 2 * BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        btnConfig.addActionListener(this);
        pnlSideMenu.add(btnConfig);

        btnAbout = new JButton();
        customButton(btnAbout, "About", "./src/main/resources/assets/about.png");
        btnAbout.setBounds(10, 3 * BTN_HEIGHT, SIDE_WIDTH, BTN_HEIGHT);
        btnAbout.addActionListener(this);
        pnlSideMenu.add(btnAbout);

        sideMenu.add(btnCompose);
        sideMenu.add(btnCheck);
        sideMenu.add(btnConfig);
        sideMenu.add(btnAbout);

        lblIndicator = new JLabel();
        lblIndicator.setBackground(new Color(0, 161, 214));
        lblIndicator.setOpaque(true);
        pnlSideMenu.add(lblIndicator);

        pnlCompose = new Compose();
        pnlChildFrame.add("Compose", pnlCompose);

        pnlCheck = new Check();
        pnlChildFrame.add("Check", pnlCheck);

        pnlConfig = new Config();
        pnlChildFrame.add("Config", pnlConfig);

        pnlAbout = new About();
        pnlChildFrame.add("About", pnlAbout);

        this.add(pnlServer);
        this.add(pnlSideMenu);
        this.add(pnlChildFrame);
        this.setLayout(null);
        this.setTitle("Hmail");
        this.setSize(Constants.WIDTH, Constants.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("./src/main/resources/assets/hmail.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void activateButton(JButton btn) {
        if(btn==activeBtn) return;
        disableBtn();
        activeBtn = btn;
        lblIndicator.setBounds(0, activeBtn.getY() + 25, 6, 30);
        activeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        activeBtn.setHorizontalTextPosition(SwingConstants.LEFT);

        CardLayout cl=(CardLayout)pnlChildFrame.getLayout();
        cl.show(pnlChildFrame, btn.getText());
    }

    private void disableBtn() {
        if (activeBtn != null) {
            activeBtn.setHorizontalAlignment(SwingConstants.LEFT);
            activeBtn.setHorizontalTextPosition(SwingConstants.TRAILING);
        }
    }

    private static void customButton(JButton button, String text, String filepath) {
        ImageIcon icn = new ImageIcon(filepath);
        icn.setImage(icn.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH));
        button.setText(text);
        button.setIcon(icn);
        button.setFont(new Font("微软雅黑", Font.BOLD, 16));
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(24);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object key = e.getSource();
        if (key instanceof JButton) {
            JButton btn = (JButton) key;
            if(sideMenu.contains(btn))
                activateButton(btn);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
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
        });
    }
}

package main.java.cn.whalien517.gui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Vector;

import static main.java.cn.whalien517.gui.Constants.*;

/**
 * @author Hwa
 * @since 2020-07-12 15:58
 */
public class Compose extends JPanel implements ActionListener {
    private JLabel lblSender;
    private JComboBox<String> cbbSender;
    private Vector<String> senders = new Vector<>();

    private JLabel lblReceiver;
    private JTextField txfReceiver;

    private JLabel lblCc;
    private JTextField txfCc;
    private JPanel pnlCc;

    private JLabel lblBcc;
    private JTextField txfBcc;
    private JPanel pnlBcc;

    private JLabel lblSubject;
    private JTextField txfSubject;

    private JButton btnAttach;
    private JButton btnDelete;
    private JComboBox<String> cbbFiles;
    Vector<String> files = new Vector<>();

    private JLabel lblBody;
    private JTextArea txaBody;
    private JScrollPane srpBody;

    private JButton btnCc;
    private JButton btnBcc;
    private JCheckBox ckbSave;
    private JCheckBox ckbencrypt;
    private JButton btnSend;
    private JPanel pnlFeat;

    private JPanel pnlSend;
    private JPanel pnlBody;
    private JScrollPane scrollPane;

    public Compose() {
        initGlobalFont(new Font("Microsoft YaHei Mono", Font.PLAIN, 16));
        init();
    }

    private void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    private void init() {
        pnlSend = new JPanel(null, true);
        pnlSend.setLayout(null);
        pnlSend.setBounds(0, 0, Constants.WIDTH - SIDE_WIDTH, Constants.HEIGHT);

        scrollPane = new JScrollPane(pnlSend, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, Constants.WIDTH - SIDE_WIDTH, Constants.HEIGHT);

        lblSender = new JLabel("发件人");
        lblSender.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSender.setBounds(COL1_X, BEGIN_Y, LBL_WIDTH, ROW_HEIGHT); // here
        pnlSend.add(lblSender);

        cbbSender = new JComboBox<>();
        cbbSender.setEditable(true);
        cbbSender.setBounds(COL2_X, BEGIN_Y, TXF_WIDTH, ROW_HEIGHT);
        pnlSend.add(cbbSender);

        lblReceiver = new JLabel("收件人");
        lblReceiver.setHorizontalAlignment(SwingConstants.RIGHT);
        lblReceiver.setBounds(COL1_X, BEGIN_Y + ROW_HEIGHT + ROW_SPACE, LBL_WIDTH, ROW_HEIGHT);
        pnlSend.add(lblReceiver);

        txfReceiver = new JTextField();
        txfReceiver.setBounds(COL2_X, BEGIN_Y + ROW_HEIGHT + ROW_SPACE, TXF_WIDTH, ROW_HEIGHT);
        pnlSend.add(txfReceiver);

        pnlCc = new JPanel(null, true);
        pnlCc.setBounds(COL1_X, BEGIN_Y + 2 * ROW_HEIGHT + 2 * ROW_SPACE, COL2_X + TXF_WIDTH - COL1_X, ROW_HEIGHT);
        pnlCc.setVisible(false);
        pnlSend.add(pnlCc);

        lblCc = new JLabel("抄送");
        lblCc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCc.setBounds(0, 0, LBL_WIDTH, ROW_HEIGHT);
        pnlCc.add(lblCc);

        txfCc = new JTextField();
        txfCc.setBounds(COL2_X - COL1_X, 0, TXF_WIDTH, ROW_HEIGHT);
        pnlCc.add(txfCc);

        pnlBcc = new JPanel(null, true);
        pnlBcc.setBounds(COL1_X, BEGIN_Y + 3 * ROW_HEIGHT + 3 * ROW_SPACE, COL2_X + TXF_WIDTH - COL1_X, ROW_HEIGHT);
        pnlBcc.setVisible(false);
        pnlSend.add(pnlBcc);

        lblBcc = new JLabel("密送");
        lblBcc.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBcc.setBounds(0, 0, LBL_WIDTH, ROW_HEIGHT);
        pnlBcc.add(lblBcc);

        txfBcc = new JTextField();
        txfBcc.setBounds(COL2_X - COL1_X, 0, TXF_WIDTH, ROW_HEIGHT);
        pnlBcc.add(txfBcc);

        pnlBody = new JPanel(null, true);
        pnlBody.setBounds(pnlCc.getX(), pnlCc.getY(), COL2_X + TXF_WIDTH - COL1_X, TXA_HEIGHT + 3 * ROW_HEIGHT + 3 * ROW_SPACE);
        pnlSend.add(pnlBody);

        lblSubject = new JLabel("主题");
        lblSubject.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSubject.setBounds(0, 0, LBL_WIDTH, ROW_HEIGHT);
        pnlBody.add(lblSubject);

        txfSubject = new JTextField();
        txfSubject.setBounds(COL2_X - COL1_X, 0, TXF_WIDTH, ROW_HEIGHT);
        pnlBody.add(txfSubject);

        int colSpace = COL2_X - COL1_X - BTN_WIDTH;
        btnAttach = new JButton("添加附件");
        btnAttach.setFont(new Font("Microsoft YeHei Mono", Font.BOLD, 14));
        btnAttach.setBounds(0, ROW_HEIGHT + ROW_SPACE, BTN_WIDTH, ROW_HEIGHT);
        btnAttach.addActionListener(this);
        pnlBody.add(btnAttach);

        cbbFiles = new JComboBox<>(files);
        cbbFiles.setEditable(false);
        cbbFiles.setBounds(BTN_WIDTH + colSpace, ROW_HEIGHT + ROW_SPACE, TXF_WIDTH - colSpace - BTN_WIDTH, ROW_HEIGHT);
        pnlBody.add(cbbFiles);

        btnDelete = new JButton("清除附件");
        btnDelete.setFont(new Font("Microsoft YeHei Mono", Font.BOLD, 14));
        btnDelete.setBounds(TXF_WIDTH + colSpace, ROW_HEIGHT + ROW_SPACE, BTN_WIDTH, ROW_HEIGHT);
        btnDelete.addActionListener(this);
        pnlBody.add(btnDelete);

        lblBody = new JLabel("正文");
        lblBody.setHorizontalAlignment(SwingConstants.RIGHT);
        lblBody.setBounds(0, 2 * (ROW_HEIGHT + ROW_SPACE), LBL_WIDTH, ROW_HEIGHT);
        pnlBody.add(lblBody);

        txaBody = new JTextArea();
        txaBody.setLineWrap(true);
        srpBody = new JScrollPane(txaBody, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        srpBody.setBounds(COL2_X - COL1_X, 2 * (ROW_HEIGHT + ROW_SPACE), TXF_WIDTH, TXA_HEIGHT);
        pnlBody.add(srpBody);

        pnlFeat = new JPanel(null, true) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int x = CKB_WIDTH * 2 + CKB_SPACE + PNLFEAT_SPACE / 7;
                Graphics2D gPen = (Graphics2D) g;
                gPen.setColor(new Color(194, 194, 194));
                gPen.setStroke(new BasicStroke(LINE_WIDTH));
                gPen.drawLine(x, 5, x, ROW_HEIGHT - 5);
            }
        };
        pnlFeat.setBounds(0, 2 * ROW_HEIGHT + 3 * ROW_SPACE + TXA_HEIGHT, TXF_WIDTH + COL2_X - COL1_X, ROW_HEIGHT);
        pnlBody.add(pnlFeat);

        ckbSave = new JCheckBox("保存到本地", false);
        ckbSave.setBounds(0, 0, CKB_WIDTH, ROW_HEIGHT);
        pnlFeat.add(ckbSave);

        ckbencrypt = new JCheckBox("加密传输", false);
        ckbencrypt.setBounds(CKB_WIDTH + CKB_SPACE / 2, 0, CKB_WIDTH, ROW_HEIGHT);
        pnlFeat.add(ckbencrypt);

        btnCc = new JButton("添加抄送");
        btnCc.setBounds(2 * CKB_WIDTH + CKB_SPACE + PNLFEAT_SPACE, 0, BTN_WIDTH, ROW_HEIGHT);
        btnCc.addActionListener(this);
        pnlFeat.add(btnCc);

        btnBcc = new JButton("添加密送");
        btnBcc.setBounds(2 * CKB_WIDTH + 2 * CKB_SPACE + PNLFEAT_SPACE + BTN_WIDTH, 0, BTN_WIDTH, ROW_HEIGHT);
        btnBcc.addActionListener(this);
        pnlFeat.add(btnBcc);

        btnSend = new JButton("发送");
        btnSend.setBounds(2 * CKB_WIDTH + 3 * CKB_SPACE + PNLFEAT_SPACE + 2 * BTN_WIDTH, 0, BTN_WIDTH, ROW_HEIGHT);
        pnlFeat.add(btnSend);

        this.add(pnlSend);
        this.add(scrollPane);
        this.setLayout(null);
        this.setSize(CHILDFRAME_WIDTH, Constants.HEIGHT+100);
    }

    private void revitalizeInterface(boolean ccSelected, boolean bccSelected) {
        int bodyX = pnlBody.getX();
        int bodyY=pnlBody.getY();
        if(ccSelected){
            pnlCc.setVisible(true);
            if(bccSelected){
                pnlBcc.setVisible(true);
                pnlBody.setLocation(bodyX, bodyY+2*(ROW_HEIGHT+ROW_SPACE));
            }else{
                pnlBody.setLocation(bodyX, bodyY+ROW_HEIGHT+ROW_SPACE);
            }
        }else {
            if(bccSelected){
                pnlBcc.setVisible(true);
                pnlBody.setLocation(bodyX, bodyY+ROW_HEIGHT+ROW_SPACE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src instanceof JButton){
            JButton btn=(JButton)src;

            // bug here
            boolean ccSelected = btnCc.getText()=="添加抄送"? true:false;
            boolean bccSelected = btnBcc.getText()=="添加密送"? true:false;
            if(btn==btnCc){
                String ccText=ccSelected? "删除抄送":"添加抄送";
                btnCc.setText(ccText);
            }
            if(btn==btnBcc){
                String bccText=bccSelected? "删除密送":"添加密送";
                btnBcc.setText(bccText);
            }
            revitalizeInterface(ccSelected, bccSelected);
        }
    }
}

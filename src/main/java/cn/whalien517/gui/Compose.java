package main.java.cn.whalien517.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Hwa
 * @since 2020-07-12 15:58
 */
public class Compose extends JPanel implements ActionListener {
    private JLabel lblSender;
    private JComboBox<String> cbbSender;

    private JLabel lblReceiver;
    private JTextField txfReceiver;

    private JLabel lblCc;
    private JTextField lblBcc;

    private JLabel lblSubject;
    private JTextField txfSubject;

    private JLabel lblAttach;
    private JComboBox<String> cbbFiles;


    public Compose(){
        init();
    }

    private void init() {


//        this.add(pnl);
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

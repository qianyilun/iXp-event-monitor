package ui;

/**
 * Created by I860745 on 7/18/2017.
 */

import javax.swing.*;
import java.awt.*;

public class D extends JFrame {
    //============================================== instance variables
    JTextArea _resultArea = new JTextArea(20, 20);

    //====================================================== constructor
    public D(String description) {
        //... Set textarea's initial text, scrolling, and border.
        _resultArea.setText(description);
        _resultArea.setSelectedTextColor(Color.WHITE);
        _resultArea.setLineWrap(true);
        _resultArea.setWrapStyleWord(true);
        JScrollPane scrollingArea = new JScrollPane(_resultArea);

        //... Get the content pane, set layout, add to center
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(scrollingArea, BorderLayout.CENTER);
        _resultArea.setFont(_resultArea.getFont().deriveFont(30f));

        //... Set window characteristics.
        this.setContentPane(content);
        this.setTitle("Detailed Description");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    //============================================================= main
//    public static void main(String[] args) {
//        JFrame win = new D("abc");
//        win.setVisible(true);
//    }
}
//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * Created by I860745 on 7/18/2017.
// */
//public class DetailedRequest {
//    public static void main(String[] args) {
//        new DetailedRequest().popWindow("I Love you ");
//    }
//    public void popWindow(String description) {
//            try {
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            JTextArea ta = new JTextArea(20, 20);
//            ta.setText(description);
//            ta.setFont(ta.getFont().deriveFont(30f));
//            ta.setSelectedTextColor(Color.WHITE);
//            ta.setLineWrap(true);
//            ta.setWrapStyleWord(true);
//            JScrollPane panel = new JScrollPane(ta);
//            panel.add(ta);
//            panel.setPreferredSize(new Dimension(600, 400));
//            panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 10, Color.white));
//            JFrame frame = new JFrame("Detailed Description");//
//            frame.setLocation(new Point(212, 184));
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.getContentPane().add(panel);
//            frame.pack();
//    }
//}

package pk1;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DatePanel extends JPanel implements Runnable {

    private static final int SLEEP_MS = 1000;
    private static final int DATE_X = 50;
    private static final int DATE_Y = 100;

    public DatePanel() {
        setBackground(Color.cyan);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(new Date().toString(), DATE_X, DATE_Y);
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(SLEEP_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.add(new DatePanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

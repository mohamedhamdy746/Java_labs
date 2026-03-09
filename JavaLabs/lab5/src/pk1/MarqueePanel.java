package pk1;

import javax.swing.*;

public class MarqueePanel extends JPanel implements Runnable {

    private static final String MESSAGE = "Scrolling ";
    private static final int INIT_X = 0;
    private static final int Y_POSITION = 50;
    private static final int SLEEP_MS = 20;
    private static final int RESET_X = -100;

    private int x = INIT_X;

    public MarqueePanel() {
        setBackground(java.awt.Color.cyan);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawString(MESSAGE, x, Y_POSITION);
    }

    @Override
    public void run() {
        while (true) {
            x++;
            if (x > getWidth()) {
                x = RESET_X;
            }
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
        JFrame frame = new JFrame("Simple Marquee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.add(new MarqueePanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

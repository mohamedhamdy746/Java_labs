package pk1;

import javax.swing.*;
import java.awt.*;

public class BallPanel extends JPanel implements Runnable {


    private static final int BALL_DIAMETER = 20;
    private static final int INIT_X = 0;
    private static final int INIT_Y = 50;
    private static final int SLEEP_MS = 20;

    private int x = INIT_X;
    private int y = INIT_Y;

    public BallPanel() {
        setBackground(Color.cyan);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    @Override
    public void run() {
        while (true) {
            x++;
            if (x > getWidth()) {
                x = -BALL_DIAMETER;
            }

            y++;
            if (y > getHeight()) {
                y = -BALL_DIAMETER;
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
        JFrame frame = new JFrame("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.add(new BallPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

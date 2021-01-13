package report;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class PinBall  {
    public static void main(String[] args) {
        JFrame window = new JFrame("PinBall");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(650, 400);

        Panel4GameBoard panel = new Panel4GameBoard();

        window.add(panel);
        window.setVisible(true);
        panel.addKeyListener(panel);

        panel.animationStart();
    }
}


//class ControlledJFrame extends JFrame {
//    public ControlledJFrame(String title, int width, int height) {
//        setTitle(title);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(width,height);
//        setLocationRelativeTo(null);
//        setLayout(null);
//        setResizable(false);
//        setFocusable(true);
//
//        Panel4GameBoard panel = new Panel4GameBoard();
//
//        add(panel);
//        setVisible(true);
//
//        panel.animationStart();
//    }
//}


class Panel4GameBoard extends JPanel implements KeyListener, ActionListener {
    private final Ball ball;
    private final Ball ball2;
    private final Timer timer;

    // 再描画タイミング
    private static final int INTERVAL = 20;

    public Panel4GameBoard() {
        timer   = new Timer(INTERVAL, this);
        ball    = new Ball(this);
        ball2   = new Ball(15, 20, 20, 5, 5, Color.green, this);

        setFocusable(true);
        addKeyListener(this);
    }

    public void animationStart() {
        ball.goHome();
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        ball.draw(graphics);
        ball2.draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ball.next();
        ball2.next();
        reflectBall(ball, ball2);
        repaint();
    }

    public void reflectBall(Ball ball1, Ball ball2) {
        double dist = Math.pow((ball1.getVx() - ball2.getVx()), 2)
                + Math.pow((ball1.getVy() - ball2.getVy()), 2);
        double thresholdDist = Math.pow(ball1.getR(), 2) + Math.pow(ball2.getR(), 2);
        if (dist == thresholdDist) {
            ball1.setVx(-ball1.getVx());
            ball1.setVy(-ball1.getVy());
            ball2.setVx(-ball2.getVx());
            ball2.setVy(-ball2.getVy());
        }
    }

    /*--- KeyListener ---*/
    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                ball.setVy(-15.0);
                System.out.println("Pressed UP");
                break;
            case KeyEvent.VK_RIGHT:
                ball.setVx(15.0);
                System.out.println("Pressed RIGHT");
                break;
            case KeyEvent.VK_LEFT:
                ball.setVx(-15.0);
                System.out.println("Pressed LEFT");
                break;
            case KeyEvent.VK_DOWN:
                ball.setVy(15.0);
                System.out.println("Pressed DOWN");
                break;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

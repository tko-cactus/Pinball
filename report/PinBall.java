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
        final int win_width = 650;
        final int win_height = 400;

        JFrame window = new JFrame("PinBall");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(win_width, win_height);

        Panel4GameBoard panel = new Panel4GameBoard();

        window.add(panel);
        window.setVisible(true);
        panel.addKeyListener(panel);

        panel.animationStart();
    }
}


class Panel4GameBoard extends JPanel implements KeyListener, ActionListener {
    private final int MARGIN_ROW = 30;
    private final int MARGIN_COL = 20;
    private final int ROW = 3;
    private final int COL = 10;
    private final Ball ball;
//    private final Ball ball2;
//    private final Ball ball3;
    private final Block[][] block = new Block[ROW][COL];
    private final Timer timer;

    // 再描画タイミング
    private static final int INTERVAL = 20;

    public Panel4GameBoard() {
        timer   = new Timer(INTERVAL, this);
        ball    = new Ball(this);

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++) {
                block[i][j] = new Block(MARGIN_ROW+60*j, MARGIN_COL+50*i, this);
            }
        }

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
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                block[i][j].draw(graphics);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ball.next();
        removeBlock();
        repaint();
    }

    public void removeBlock() {
        if (isBallTouchBlock()) {
            System.out.println("Ball touched!");
        } else {
            System.out.println('X');
        }
    }

    public boolean isBallTouchBlock() {
        double r = ball.getR();
        double x = ball.getX();
        double y = ball.getY();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                double block_left = block[i][j].getX();
                double block_right = block_left + block[i][j].getWidth();
                if (((block_left - r) < x) && (x < (block_right + r))) {
                    double block_top = block[i][j].getY();
                    double block_bottom = block_top + block[i][j].getHeight();
                    if (((block_top - r) < y) && (y < (block_bottom + r))) {
                        block[i][j].setColor(Color.red);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*--- KeyListener ---*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                ball.setVy(-15.0);
                System.out.println("Pressed UP");
                break;
            case 'd':
                ball.setVx(15.0);
                System.out.println("Pressed RIGHT");
                break;
            case 'a':
                ball.setVx(-15.0);
                System.out.println("Pressed LEFT");
                break;
            case 's':
                ball.setVy(15.0);
                System.out.println("Pressed DOWN");
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

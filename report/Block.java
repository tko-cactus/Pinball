package report;

import javax.swing.*;
import java.awt.*;

public class Block {
    private final int width = 50;
    private final int height = 30;
    private  int x;
    private  int y;
    private Color color = Color.blue;
    private final JPanel panel;

    // getter
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }

    // setter
    public void setColor(Color color) { this.color = color; }

    public Block(JPanel panel) {
        this.panel = panel;
    }

    public Block(int x, int y, JPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
    }


    // draw Block
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);
    }
}

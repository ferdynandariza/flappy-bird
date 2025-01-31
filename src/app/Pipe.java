package app;

import java.awt.*;

public class Pipe {
    private int x = Constant.DEFAULT_PIPE_X;
    private int y = Constant.DEFAULT_PIPE_Y;
    private int width = Constant.PIPE_WIDTH;
    private int height = Constant.PIPE_HEIGHT;
    private Image image;
    private boolean passed = false;

    public Pipe(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}

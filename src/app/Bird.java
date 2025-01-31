package app;

import java.awt.*;

public class Bird {
    private int x = Constant.DEFAULT_BIRD_X;
    private int y = Constant.DEFAULT_BIRD_y;
    private int width = Constant.BIRD_WIDTH;
    private int height = Constant.BIRD_HEIGHT;
    private Image image;

    public Bird(Image image) {
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
}

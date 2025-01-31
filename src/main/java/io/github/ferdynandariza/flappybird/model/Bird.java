package io.github.ferdynandariza.flappybird.model;

import io.github.ferdynandariza.flappybird.constant.Constant;
import io.github.ferdynandariza.flappybird.constant.ResourcePath;

import javax.swing.*;
import java.awt.*;

public class Bird {
    private int x = Constant.DEFAULT_BIRD_X;
    private int y = Constant.DEFAULT_BIRD_y;
    private int width = Constant.BIRD_WIDTH;
    private int height = Constant.BIRD_HEIGHT;
    private Image image;
    private int velocityY = 0;

    public Bird() {
       this.image = new ImageIcon(getClass().getResource(ResourcePath.FLAPPY_BIRD)).getImage();
    }

    public void move() {
        velocityY += Constant.GRAVITY;
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void jump() {
        velocityY = -9;
    }

    public void reset() {
        y = Constant.DEFAULT_BIRD_y;
        velocityY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }
}

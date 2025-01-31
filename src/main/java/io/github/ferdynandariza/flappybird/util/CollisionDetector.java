package io.github.ferdynandariza.flappybird.util;

import io.github.ferdynandariza.flappybird.constant.Constant;
import io.github.ferdynandariza.flappybird.model.Bird;
import io.github.ferdynandariza.flappybird.model.Pipe;

import java.util.ArrayList;

public class CollisionDetector {

    public boolean checkCollision(Bird bird, ArrayList<Pipe> pipes) {
        for (Pipe pipe : pipes) {
            if (isCollide(bird, pipe)) return true;
        }
        return false;
    }

    private boolean isCollide(Bird bird, Pipe pipe) {
        return bird.getX() < pipe.getX() + pipe.getWidth() &&
                bird.getX() + bird.getWidth() > pipe.getX() &&
                bird.getY() < pipe.getY() + pipe.getHeight() &&
                bird.getY() + bird.getHeight() > pipe.getY();
    }

    public boolean checkBirdFalling(Bird bird) {
        return bird.getY() > Constant.BOARD_HEIGHT;
    }
}

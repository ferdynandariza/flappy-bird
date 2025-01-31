package io.github.ferdynandariza.flappybird.model;

public class GameState {
    private final Double score;
    private final Boolean isGameOver;

    public GameState(Double score, Boolean isGameOver) {
        this.score = score;
        this.isGameOver = isGameOver;
    }

    public Double getScore() {
        return score;
    }

    public Boolean getGameOver() {
        return isGameOver;
    }
}

package com.github.jonizei.clickergame.game;

public class Reward {

    private int points;
    private int requiredClicks;

    public Reward() {

    }

    public Reward(int points, int requiredClicks) {
        this.points = points;
        this.requiredClicks = requiredClicks;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRequiredClicks() {
        return requiredClicks;
    }

    public void setRequiredClicks(int requiredClicks) {
        this.requiredClicks = requiredClicks;
    }
}

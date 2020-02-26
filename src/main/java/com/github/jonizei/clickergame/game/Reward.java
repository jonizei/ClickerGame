package com.github.jonizei.clickergame.game;

/**
 * This class holds reward points and clicks to next reward.
 * It will be used as a response for the user
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
public class Reward {

    /**
     * Reward points
     */
    private int points;

    /**
     * Clicks to next reward
     */
    private int requiredClicks;

    /**
     * Default constructor of Reward
     */
    public Reward() {

    }

    /**
     * Constructor of Reward
     *
     * @param points Reward points
     * @param requiredClicks Clicks to next reward
     */
    public Reward(int points, int requiredClicks) {
        this.points = points;
        this.requiredClicks = requiredClicks;
    }

    /**
     * Returns points of the reward
     *
     * @return Points of the reward
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets value for the points
     *
     * @param points Points for the reward
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Return clicks to next reward
     *
     * @return Clicks to next reward
     */
    public int getRequiredClicks() {
        return requiredClicks;
    }

    /**
     * Sets value for the clicks to next reward
     *
     * @param requiredClicks Clicks for the next reward
     */
    public void setRequiredClicks(int requiredClicks) {
        this.requiredClicks = requiredClicks;
    }
}

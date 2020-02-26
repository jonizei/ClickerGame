package com.github.jonizei.clickergame.game;

/**
 * This class is holds username and points of the user.
 * It will be used as a response to a client.
 *
 * @author Joni Koskinen
 * @version 2020-02-26
 */
public class PlayerDetails {

    /**
     * Username of the user
     */
    private String username;

    /**
     * Points of the user
     */
    private int points;

    /**
     * Default constructor of PlayerDetails
     */
    public PlayerDetails() {

    }

    /**
     * Constructor of PlayerDetails
     *
     * @param username Username of the user
     * @param points Points of the user
     */
    public PlayerDetails(String username, int points) {
        this.username = username;
        this.points = points;
    }

    /**
     * Returns username of the user
     *
     * @return Username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets value for the username
     *
     * @param username Username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns points of the user
     *
     * @return Points of the user
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set value for the points
     *
     * @param points Points for the user
     */
    public void setPoints(int points) {
        this.points = points;
    }
}

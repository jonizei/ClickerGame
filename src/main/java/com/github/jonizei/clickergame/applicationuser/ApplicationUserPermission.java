package com.github.jonizei.clickergame.applicationuser;

/**
 * This enumerable holds all user permissions of the application
 *
 * @author Joni Koskinen
 * @version 2020-02-25
 */
public enum ApplicationUserPermission {

    /**
     * Permission that lets player play the game
     */
    PLAYER_PLAY("player:play");

    /**
     * Permission as a String
     */
    private final String permission;

    /**
     * Constructor of ApplicationUserPermission
     *
     * @param permission Permission as a String
     */
    ApplicationUserPermission(String permission) {this.permission = permission;}

    /**
     * Returns permission as a String
     *
     * @return Permission as a String
     */
    public String getPermission() {return permission;}
}

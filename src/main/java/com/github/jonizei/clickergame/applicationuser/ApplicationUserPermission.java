package com.github.jonizei.clickergame.applicationuser;

public enum ApplicationUserPermission {

    PLAYER_CLICK("player:click");

    private final String permission;

    ApplicationUserPermission(String permission) {this.permission = permission;}

    public String getPermission() {return permission;}
}

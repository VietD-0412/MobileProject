// PlayerProfile.java
package com.example.chessleaderboardandsearch;

public class PlayerProfile {
    private String username;
    private int player_id;
    private String title;
    private String status;
    private String name;
    private String avatar;
    private String location;
    private int followers;
    private long joined;
    private String country;

    // Getters
    public String getUsername() {
        return username;
    }

    public int getPlayerId() {
        return player_id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLocation() {
        return location;
    }

    public int getFollowers() {
        return followers;
    }

    public long getJoined() {
        return joined;
    }
    public String getCountry() { return country; }
}
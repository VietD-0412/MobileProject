// Player.java
package com.example.chessleaderboardandsearch;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("player_id")
    private int playerId;
    @SerializedName("@id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("username")
    private String username;
    @SerializedName("score")
    private int score;
    @SerializedName("rank")
    private int rank;
    @SerializedName("avatar")
    private String avatar; // Add this field

    // Getters
    public int getPlayerId() {
        return playerId;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public String getAvatar() {
        return avatar;
    }
}
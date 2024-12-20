package com.example.chessleaderboardandsearch;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LeaderboardResponse {
    @SerializedName("daily")
    private List<Player> daily;

    @SerializedName("live_rapid")
    private List<Player> liveRapid;

    @SerializedName("live_blitz")
    private List<Player> liveBlitz;

    // Getters
    public List<Player> getDaily() {
        return daily;
    }

    public List<Player> getLiveRapid() {
        return liveRapid;
    }

    public List<Player> getLiveBlitz() {
        return liveBlitz;
    }
}
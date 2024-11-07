package com.example.chessleaderboardandsearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ChessApiService {
    @GET("leaderboards")
    Call<LeaderboardResponse> getLeaderboards();

    @GET("player/{username}")
    Call<PlayerProfile> getPlayerProfile(@Path("username") String username);

    @GET
    Call<Country> getCountry(@Url String url);
}
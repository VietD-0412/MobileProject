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

    @GET("player/{username}/stats")
    Call<DailyStats> getDailyStats(@Path("username") String username);

    @GET("player/{username}/stats")
    Call<RapidStats> getRapidStats(@Path("username") String username);

    @GET("player/{username}/stats")
    Call<BlitzStats> getBlitzStats(@Path("username") String username);

    @GET("player/{username}/stats")
    Call<BulletStats> getBulletStats(@Path("username") String username);
}
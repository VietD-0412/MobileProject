package com.example.chessleaderboardandsearch;

public class DailyStats {
    private ChessDaily chess_daily;

    public ChessDaily getChessDaily() {
        return chess_daily;
    }

    public static class ChessDaily {
        private Rating last;
        private Rating best;

        public Rating getLast() {
            return last;
        }

        public Rating getBest() {
            return best;
        }
    }

    public static class Rating {
        private int rating;

        public int getRating() {
            return rating;
        }
    }

    public int getBestRating() {
        return chess_daily != null && chess_daily.getBest() != null ? chess_daily.getBest().getRating() : 0;
    }

    public int getCurrentRating() {
        return chess_daily != null && chess_daily.getLast() != null ? chess_daily.getLast().getRating() : 0;
    }
}
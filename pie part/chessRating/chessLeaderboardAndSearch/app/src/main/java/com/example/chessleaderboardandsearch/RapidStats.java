package com.example.chessleaderboardandsearch;

public class RapidStats {
    private ChessRapid chess_rapid;

    public ChessRapid getChessRapid() {
        return chess_rapid;
    }

    public static class ChessRapid {
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
        return chess_rapid != null && chess_rapid.getBest() != null ? chess_rapid.getBest().getRating() : 0;
    }

    public int getCurrentRating() {
        return chess_rapid != null && chess_rapid.getLast() != null ? chess_rapid.getLast().getRating() : 0;
    }
}
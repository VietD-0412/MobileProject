package com.example.chessleaderboardandsearch;

public class BlitzStats {
    private ChessBlitz chess_blitz;

    public ChessBlitz getChessBlitz() {
        return chess_blitz;
    }

    public static class ChessBlitz {
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
        return chess_blitz != null && chess_blitz.getBest() != null ? chess_blitz.getBest().getRating() : 0;
    }

    public int getCurrentRating() {
        return chess_blitz != null && chess_blitz.getLast() != null ? chess_blitz.getLast().getRating() : 0;
    }
}
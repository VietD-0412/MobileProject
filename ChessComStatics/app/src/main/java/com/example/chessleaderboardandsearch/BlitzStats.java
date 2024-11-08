package com.example.chessleaderboardandsearch;

public class BlitzStats {
    private ChessBlitz chess_blitz;

    public ChessBlitz getChessBlitz() {
        return chess_blitz;
    }

    public static class ChessBlitz {
        private Rating last;
        private Rating best;
        private Record record;

        public Rating getLast() {
            return last;
        }

        public Rating getBest() {
            return best;
        }

        public Record getRecord() {
            return record;
        }
    }

    public static class Rating {
        private int rating;
        private long date; // Add date field

        public int getRating() {
            return rating;
        }

        public long getDate() {
            return date;
        }
    }

    public int getBestRating() {
        return chess_blitz != null && chess_blitz.getBest() != null ? chess_blitz.getBest().getRating() : 0;
    }

    public long getBestRatingDate() {
        return chess_blitz != null && chess_blitz.getBest() != null ? chess_blitz.getBest().getDate() : 0;
    }

    public int getCurrentRating() {
        return chess_blitz != null && chess_blitz.getLast() != null ? chess_blitz.getLast().getRating() : 0;
    }

    public long getCurrentRatingDate() {
        return chess_blitz != null && chess_blitz.getLast() != null ? chess_blitz.getLast().getDate() : 0;
    }

    public static class Record {
        private int win;
        private int loss;
        private int draw;

        public int getWin() {
            return win;
        }

        public int getLoss() {
            return loss;
        }

        public int getDraw() {
            return draw;
        }
    }

    public int getWin() {
        return chess_blitz != null && chess_blitz.getRecord() != null ? chess_blitz.getRecord().getWin() : 0;
    }

    public int getLoss() {
        return chess_blitz != null && chess_blitz.getRecord() != null ? chess_blitz.getRecord().getLoss() : 0;
    }

    public int getDraw() {
        return chess_blitz != null && chess_blitz.getRecord() != null ? chess_blitz.getRecord().getDraw() : 0;
    }
}
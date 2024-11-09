package com.example.chessleaderboardandsearch;

public class DailyStats {
    private ChessDaily chess_daily;

    public ChessDaily getChessDaily() {
        return chess_daily;
    }

    public static class ChessDaily {
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
        private long date;

        public int getRating() {
            return rating;
        }

        public long getDate() {
            return date;
        }
    }

    public int getBestRating() {
        return chess_daily != null && chess_daily.getBest() != null ? chess_daily.getBest().getRating() : 0;
    }

    public long getBestRatingDate() {
        return chess_daily != null && chess_daily.getBest() != null ? chess_daily.getBest().getDate() : 0;
    }

    public int getCurrentRating() {
        return chess_daily != null && chess_daily.getLast() != null ? chess_daily.getLast().getRating() : 0;
    }

    public long getCurrentRatingDate() {
        return chess_daily != null && chess_daily.getLast() != null ? chess_daily.getLast().getDate() : 0;
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
        return chess_daily != null && chess_daily.getRecord() != null ? chess_daily.getRecord().getWin() : 0;
    }

    public int getLoss() {
        return chess_daily != null && chess_daily.getRecord() != null ? chess_daily.getRecord().getLoss() : 0;
    }

    public int getDraw() {
        return chess_daily != null && chess_daily.getRecord() != null ? chess_daily.getRecord().getDraw() : 0;
    }
}
package com.example.chessleaderboardandsearch;

public class RapidStats {
    private ChessRapid chess_rapid;

    public ChessRapid getChessRapid() {
        return chess_rapid;
    }

    public static class ChessRapid {
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
        return chess_rapid != null && chess_rapid.getBest() != null ? chess_rapid.getBest().getRating() : 0;
    }

    public long getBestRatingDate() {
        return chess_rapid != null && chess_rapid.getBest() != null ? chess_rapid.getBest().getDate() : 0;
    }

    public int getCurrentRating() {
        return chess_rapid != null && chess_rapid.getLast() != null ? chess_rapid.getLast().getRating() : 0;
    }

    public long getCurrentRatingDate() {
        return chess_rapid != null && chess_rapid.getLast() != null ? chess_rapid.getLast().getDate() : 0;
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
        return chess_rapid != null && chess_rapid.getRecord() != null ? chess_rapid.getRecord().getWin() : 0;
    }

    public int getLoss() {
        return chess_rapid != null && chess_rapid.getRecord() != null ? chess_rapid.getRecord().getLoss() : 0;
    }

    public int getDraw() {
        return chess_rapid != null && chess_rapid.getRecord() != null ? chess_rapid.getRecord().getDraw() : 0;
    }
}
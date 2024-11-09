package com.example.chessleaderboardandsearch;

public class BulletStats {
    private ChessBullet chess_bullet;

    public ChessBullet getChessBullet() {
        return chess_bullet;
    }

    public static class ChessBullet {
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
        return chess_bullet != null && chess_bullet.getBest() != null ? chess_bullet.getBest().getRating() : 0;
    }

    public long getBestRatingDate() {
        return chess_bullet != null && chess_bullet.getBest() != null ? chess_bullet.getBest().getDate() : 0;
    }

    public int getCurrentRating() {
        return chess_bullet != null && chess_bullet.getLast() != null ? chess_bullet.getLast().getRating() : 0;
    }

    public long getCurrentRatingDate() {
        return chess_bullet != null && chess_bullet.getLast() != null ? chess_bullet.getLast().getDate() : 0;
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
        return chess_bullet != null && chess_bullet.getRecord() != null ? chess_bullet.getRecord().getWin() : 0;
    }

    public int getLoss() {
        return chess_bullet != null && chess_bullet.getRecord() != null ? chess_bullet.getRecord().getLoss() : 0;
    }

    public int getDraw() {
        return chess_bullet != null && chess_bullet.getRecord() != null ? chess_bullet.getRecord().getDraw() : 0;
    }
}
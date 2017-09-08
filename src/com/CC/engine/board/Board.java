package com.CC.engine.board;

import com.CC.engine.Alliance;
import com.CC.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

/**
 * Created by all4win78 on 8/30/2017.
 */
public class Board {

    private final List<Tile> gameBoard;
    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
    }

    private List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createSTDBoard() {return null;}

    public Tile getTile(int coordination) {
        return null;
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;

        Alliance nextPlayer;

        public Builder() {}

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance player) {
            this.nextPlayer = player;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}

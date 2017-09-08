package com.CC.engine.pieces;

import com.CC.engine.Alliance;
import com.CC.engine.board.Board;
import com.CC.engine.board.Move;

import java.util.Collection;

/**
 * Created by all4win78 on 8/30/2017.
 */
public abstract class Piece {

    protected final int position;
    protected final Alliance alliance;

    public Piece(final int position, final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
    }

    public abstract Collection<Move> getLegalMoves(final Board board);

    protected abstract boolean isValidMove(final int[] currXYCoordinates,
                                           final int[] nextXYCoordinates, final Board board);

    public Alliance getAlliance() {
        return this.alliance;
    }

    public Integer getPosition() {
        return this.position;
    }
}

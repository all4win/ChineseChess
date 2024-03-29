package com.CC.engine.pieces;

import com.CC.engine.Alliance;
import com.CC.engine.board.Board;
import com.CC.engine.board.BoardUtils;
import com.CC.engine.board.Move;
import com.CC.engine.board.Move.AttackMove;
import com.CC.engine.board.Move.MajorMove;
import com.CC.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by all4win78 on 8/31/2017.
 */
public class Ma extends Piece {

    private final static int[] CANDIDATE_MOVES_X = {-1, -1, -2, -2, 1, 1, 2, 2};
    private final static int[] CANDIDATE_MOVES_Y = {2, -2, 1, -1, 2, -2, 1, -1};

    public Ma(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> getLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int[] currXYCoordinates = BoardUtils.toXYCoordinates(this.position);
        // at most 8 possible moves
        for (int i = 0; i < 8; i++) {
            int[] nextXYCoordinates
                    = new int[]{currXYCoordinates[0] + CANDIDATE_MOVES_X[i], currXYCoordinates[1] + CANDIDATE_MOVES_Y[i]};
            if (isValidMove(currXYCoordinates, nextXYCoordinates, board)) {
                int candidateDestination = BoardUtils.toCoordinate(nextXYCoordinates);
                final Tile candidateDestinationTile = board.getTile(candidateDestination);
                if (!candidateDestinationTile.isOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestination));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getAlliance();
                    if (this.alliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, pieceAtDestination, candidateDestination));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    protected boolean isValidMove(final int[] currXYCoordinates, final int[] nextXYCoordinates, final Board board) {
        int footPosition = this.position;
        if (abs(nextXYCoordinates[0] - currXYCoordinates[0]) == 2) {
            footPosition += (nextXYCoordinates[0] - currXYCoordinates[0]) / 2;
        } else {
            footPosition += (nextXYCoordinates[1] - currXYCoordinates[1]) * 9 / 2;
        }
        return BoardUtils.isValidCoordinate(nextXYCoordinates)
                && !board.getTile(footPosition).isOccupied();
    }

}

package com.CC.engine.pieces;

import com.CC.engine.Alliance;
import com.CC.engine.board.Board;
import com.CC.engine.board.BoardUtils;
import com.CC.engine.board.Move;
import com.CC.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by all4win78 on 9/7/2017.
 */
public class Xiang extends Piece {
    private final static int[] CANDIDATE_MOVES_X = {-2, -2, 2, 2};
    private final static int[] CANDIDATE_MOVES_Y = {2, -2, 2, -2};

    public Xiang(int position, Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> getLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int[] currXYCoordinates = BoardUtils.toXYCoordinates(this.position);
        // at most 4 possible moves
        for (int i = 0; i < 4; i++) {
            int[] nextXYCoordinates
                    = new int[]{currXYCoordinates[0] + CANDIDATE_MOVES_X[i], currXYCoordinates[1] + CANDIDATE_MOVES_Y[i]};
            if (isValidMove(currXYCoordinates, nextXYCoordinates, board)) {
                int candidateDestination = BoardUtils.toCoordinate(nextXYCoordinates);
                final Tile candidateDestinationTile = board.getTile(candidateDestination);
                if (!candidateDestinationTile.isOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestination));
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getAlliance();
                    if (this.alliance != pieceAlliance) {
                        legalMoves.add(new Move.AttackMove(board, this, pieceAtDestination, candidateDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    protected boolean isValidMove(int[] currXYCoordinates, int[] nextXYCoordinates, Board board) {
        int footPosition = (this.position + BoardUtils.toCoordinate(nextXYCoordinates)) / 2;
        return BoardUtils.isValidCoordinate(nextXYCoordinates)
                && (currXYCoordinates[1]-4.5) * (nextXYCoordinates[1]-4.5) > 0
                && !board.getTile(footPosition).isOccupied();
    }
}

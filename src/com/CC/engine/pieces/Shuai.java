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

/**
 * Created by all4win78 on 9/7/2017.
 */
public class Shuai extends Piece {

    private final static int[] CANDIDATE_MOVES_X = {-1, 1, 0, 0};
    private final static int[] CANDIDATE_MOVES_Y = {0, 0, -1, 1};

    public Shuai(int position, Alliance alliance) {
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
        return BoardUtils.isValidCoordinate(nextXYCoordinates)
                // within the 3*3 grid
                && (nextXYCoordinates[0] <= 5 && nextXYCoordinates[0] >= 3)
                && (nextXYCoordinates[1] <= 2 || nextXYCoordinates[1] >= 8);
    }
}

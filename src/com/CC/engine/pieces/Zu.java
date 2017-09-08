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
 * Created by all4win78 on 9/8/2017.
 */
public class Zu extends Piece {
    private final static int[] CANDIDATE_MOVES_X = {-1, 1, 0, 0};
    private final static int[] CANDIDATE_MOVES_Y = {0, 0, -1, 1};

    public Zu(int position, Alliance alliance) {
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
        if (BoardUtils.isValidCoordinate(nextXYCoordinates)) {
            // Red always forward; Black always backward
            if (this.alliance.getDirection() == 1) {
                if (currXYCoordinates[0] <= 4) {
                    return nextXYCoordinates[1] - currXYCoordinates[1] == 1;
                }
                return nextXYCoordinates[1] - currXYCoordinates[1] != -1;
            } else {
                if (currXYCoordinates[0] >= 5) {
                    return nextXYCoordinates[1] - currXYCoordinates[1] == -1;
                }
                return nextXYCoordinates[1] - currXYCoordinates[1] != 1;
            }
        }
        return false;
    }
}

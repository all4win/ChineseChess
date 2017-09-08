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
public class Shi extends Piece {

    // Very limited situations. Hard code.
    // idx 0, 1 for centers, 2-5, 6-9 for corners
    int[] POSSIBLE_POSITIONS = {13, 76, 3, 5, 21, 23, 66, 68, 84, 86};

    public Shi(int position, Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> getLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int currPosition = this.position;
        int start_idx = 0;
        int end_idx = 0;

        if (currPosition == POSSIBLE_POSITIONS[0]) {
            start_idx = 2;
            end_idx = 5;
        } else if (currPosition == POSSIBLE_POSITIONS[1]) {
            start_idx = 6;
            end_idx = 9;
        } else if (currPosition >= 45) {
            start_idx = 1;
            end_idx = 1;
        }
        for (int i = start_idx; i <= end_idx; i++) {
            int candidateDestination = POSSIBLE_POSITIONS[i];
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

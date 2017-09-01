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
 * Created by all4win78 on 8/31/2017.
 */
public class Ma extends Piece {

    private final static int[] CANDIDATE_MOVES_X = {-1, -1, -2, -2, 1, 1, 2, 2};
    private final static int[] CANDIDATE_MOVES_Y = {2, -2, 1, -1, 2, -2, 1, -1};

    public Ma(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public Collection<Move> getLegalMoves(Board board) {
        int[] currXYCoordiates = BoardUtils.toXYCoordinates(this.position);
        final List<Move> legalMoves = new ArrayList<>();

        for (int i = 0; i < CANDIDATE_MOVES_X.length; i++) {
            int[] candidateXYCoordinates
                    = new int[]{currXYCoordiates[0] + CANDIDATE_MOVES_X[i], currXYCoordiates[1] + CANDIDATE_MOVES_Y[i]};
            if (BoardUtils.isValidCoordinate(candidateXYCoordinates)) {
                int candidateDestination = BoardUtils.toCoordinate(candidateXYCoordinates);
                final Tile candidateDestinaionTile = board.getTile(candidateDestination);
                if (!candidateDestinaionTile.isOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinaionTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getAlliance();

                    if (this.alliance != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

}

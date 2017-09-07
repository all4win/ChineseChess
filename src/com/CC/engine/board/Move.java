package com.CC.engine.board;

import com.CC.engine.pieces.Piece;

/**
 * Created by all4win78 on 8/30/2017.
 */
public abstract class Move {

    final Board board;
    final Piece piece;
    final int destination;


    Move(final Board board, final Piece piece, final int destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
    }

    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                  final Piece piece,
                  final int destination) {
            super(board, piece, destination);
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final Board board,
                   final Piece piece,
                   Piece attackedPiece,
                   final int destination) {
            super(board, piece, destination);
            this.attackedPiece = attackedPiece;
        }
    }
}

package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if(getColor() == Color.WHITE){
            p.setValues(position.getRow() -1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() -2, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getMoveCount() == 0){
                Position p2 = new Position(position.getRow() -1, position.getColumn());
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() -1, position.getColumn() -1);
            if(getBoard().PositionExists(p) && isThereOponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() -1, position.getColumn() +1);
            if(getBoard().PositionExists(p) && isThereOponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else {
            p.setValues(position.getRow() +1, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() +2, position.getColumn());
            if(getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getBoard().PositionExists(p) && !getBoard().ThereIsAPiece(p) && getMoveCount() == 0){
                Position p2 = new Position(position.getRow() -1, position.getColumn());
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() +1, position.getColumn() -1);
            if(getBoard().PositionExists(p) && isThereOponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValues(position.getRow() +1, position.getColumn() +1);
            if(getBoard().PositionExists(p) && isThereOponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }

        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}

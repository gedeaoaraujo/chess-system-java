package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	public boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	public boolean testRookCastling(Position position){
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		// above
		p.setValues(position.getRow() - 1,position.getColumn());
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1,position.getColumn());
		if(getBoard().PositionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
		}
		
		// left
		p.setValues(position.getRow(),position.getColumn() - 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		// right
		p.setValues(position.getRow(),position.getColumn() + 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// nw
		p.setValues(position.getRow() - 1,position.getColumn() - 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// ne
		p.setValues(position.getRow() - 1,position.getColumn() + 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		
		// sw
		p.setValues(position.getRow() + 1,position.getColumn() - 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		
		// se
		p.setValues(position.getRow() + 1,position.getColumn() + 1);
		if(getBoard().PositionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// Special move castling
		if(getMoveCount() == 0 && !chessMatch.getCheck()){
			// castling king side rook
			Position posOne = new Position(position.getRow(), position.getColumn() +3);
			if(testRookCastling(posOne)){
				Position pOne = new Position(position.getRow(), position.getColumn() +1);
				Position pTwo = new Position(position.getRow(), position.getColumn() +2);

				if(getBoard().piece(pOne) == null && getBoard().piece(pTwo) == null){
					mat[position.getRow()][position.getColumn() +2] = true;
				}
			}
			// castling queen side rook
			Position posTwo = new Position(position.getRow(), position.getColumn() -4);
			if(testRookCastling(posTwo)){
				Position pOne = new Position(position.getRow(), position.getColumn() -1);
				Position pTwo = new Position(position.getRow(), position.getColumn() -2);
				Position pThree = new Position(position.getRow(), position.getColumn() -3);

				if(getBoard().piece(pOne) == null && getBoard().piece(pTwo) == null && getBoard().piece(pThree) == null){
					mat[position.getRow()][position.getColumn() -2] = true;
				}
			}
		}
		
		return mat;
	}

}

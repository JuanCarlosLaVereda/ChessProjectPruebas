package es.ieslavereda.pruebas;

import java.util.Set;
import java.util.TreeSet;

public class Queen extends Piece{
    public Queen(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    public Queen(Type type){
        super(type.getType(), null);
    }


    //put your task here
    public Set<Coordinate> getNextMovements(){
        Set<Coordinate> nextMovements = new TreeSet<>();

        for (Coordinate c : Bishop.getNextMovementsAsBishop(this))
            nextMovements.add(c);

        for (Coordinate c : Rook.getNextMovementsAsRook(this))
            nextMovements.add(c);

        return nextMovements;
    }

    public enum Type {
        BLACK(Piece.Type.BLACK_QUEEN), WHITE(Piece.Type.WHITE_QUEEN);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

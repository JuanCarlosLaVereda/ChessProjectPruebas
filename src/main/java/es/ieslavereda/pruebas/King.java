package es.ieslavereda.pruebas;

import java.util.Set;
import java.util.TreeSet;

public class King extends Piece {
    public King(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    public King(King.Type type){
        super(type.getType(), null);
    }

    public boolean check(){
        Board tablero = this.getCell().getBoard();
        Color colorRival = Color.WHITE;
        if (this.getColor()==Color.WHITE){
            colorRival = Color.BLACK;
        }
        Set<Coordinate> positionsColors = tablero.getNextMovements(colorRival);
        if (positionsColors.contains(this.getCell().getCoordinate())){
            return true;
        }
        return false;
    }

    @Override
    public Set<Coordinate> getNextMovements() {

        Coordinate position = getCell().getCoordinate();
        Set<Coordinate> nextMovements = new TreeSet<>();

        if (canAddToNextMovements(position.up()))
            nextMovements.add(position.up());

        if (canAddToNextMovements(position.up().right()))
            nextMovements.add(position.up().right());

        if (canAddToNextMovements(position.right()))
            nextMovements.add(position.right());

        if (canAddToNextMovements(position.down().right()))
            nextMovements.add(position.down().right());
        if (canAddToNextMovements(position.down()))
            nextMovements.add(position.down());

        if (canAddToNextMovements(position.down().left()))
            nextMovements.add(position.down().left());

        if (canAddToNextMovements(position.left()))
            nextMovements.add(position.left());

        if (canAddToNextMovements(position.up().left()))
            nextMovements.add(position.up().left());

        return nextMovements;
    }

    public enum Type {
        BLACK(Piece.Type.BLACK_KING), WHITE(Piece.Type.WHITE_KING);
        private Piece.Type type;

        Type(Piece.Type type) {
            this.type = type;
        }

        public Piece.Type getType() {
            return type;
        }
    }
}

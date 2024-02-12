package es.ieslavereda;

public class Queen extends Piece{
    public Queen(Board board, Coordinate position, Type type) {
        super(type.getType(), board.getCellAt(position));
    }

    //put your task here
    public Coordinate[] getNextMovements(){
        Coordinate[] nextMovements = new Coordinate[0];

        for (Coordinate c : Bishop.getNextMovementsAsBishop(this))
            nextMovements=Tool.add(c,nextMovements);

        for (Coordinate c : Rook.getNextMovementsAsRook(this))
            nextMovements=Tool.add(c,nextMovements);

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
